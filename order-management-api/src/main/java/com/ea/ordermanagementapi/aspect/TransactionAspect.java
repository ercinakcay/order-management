package com.ea.ordermanagementapi.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ea.ordermanagementapi.business.service.impl.AbstractServiceImpl;
import com.ea.ordermanagementapi.domain.TransactionOperation;
import com.ea.ordermanagementapi.domain.enums.TransactionOperationAction;
import com.ea.ordermanagementapi.exception.TransactionAwareException;
import com.ea.ordermanagementapi.service.TransactionService;

@Aspect
public class TransactionAspect extends AbstractServiceImpl
{
    private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

    @Autowired
    private TransactionService transactionService;

    @Around("@annotation(transactionAware), argNames=transactionAware")
    public Object lockMethod(ProceedingJoinPoint joinPoint, TransactionAware transactionAware) throws Throwable
    {
        // -----

        String key = checkValidationAndReturnIdentifier(joinPoint);
        if (transactionService.isTransactionCreated(key) || transactionService.isTransactionLocked(key))
        {
            throw new TransactionAwareException(String.format("Transaction operation is created/locked already! Can't continue for key: %s", key));
        }

        transactionService.saveTransaction(new TransactionOperation(joinPoint.getArgs()[0].getClass().toString(),
                TransactionOperationAction.CREATE, key));
        transactionService.saveTransaction(new TransactionOperation(joinPoint.getArgs()[0].getClass().toString(),
                TransactionOperationAction.LOCK, key));

        // -----

        Object proceedResult;
        try
        {
            proceedResult = joinPoint.proceed();
        }
        catch (Throwable e)
        {
            logger.error("Exception occured: ", e);
            throw e;
        }
        return proceedResult;
    }

    @AfterReturning(value = "@annotation(transactionAware), argNames=transactionAware", returning = "returnValue")
    public void runAfter(JoinPoint joinPoint, TransactionAware transactionAware, Object returnValue)
    {
        logger.info("{} - method transaction unlocked", joinPoint.getSignature().getName());
        logger.info("{} - method returned value is : {}", joinPoint.getSignature().getName(), returnValue);

        // -----

        String key = checkValidationAndReturnIdentifier(joinPoint);

        if (null == returnValue)
        {
            throw new TransactionAwareException(String.format("Transaction operation is locked! Can't continue to unlock operation for key: %s", key));
        }
        else if (transactionService.isTransactionLocked(key))
        {
            transactionService.saveTransaction(new TransactionOperation(joinPoint.getArgs()[0].getClass().toString(),
                    TransactionOperationAction.UNLOCK, key));
        }

        // -----
    }

    private String checkValidationAndReturnIdentifier(JoinPoint joinPoint)
    {
        String key = null;
        Object firstArg = joinPoint.getArgs()[0];
        if (joinPoint.getArgs().length == 1 && firstArg instanceof TransactionAwareIdentifier)
        {
            key = firstArg.getClass().getSimpleName() + "-" +
                    ((TransactionAwareIdentifier) firstArg).getTransactionAwareIdentifier();
        }
        if (StringUtils.isEmpty(key))
        {
            throw new TransactionAwareException(getMessage("transaction.operation.aware.annotation.not.found"));
        }
        return key;
    }

}
