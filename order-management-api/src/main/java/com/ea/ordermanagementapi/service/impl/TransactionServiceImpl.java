package com.ea.ordermanagementapi.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.ordermanagementapi.domain.TransactionOperation;
import com.ea.ordermanagementapi.domain.enums.TransactionOperationAction;
import com.ea.ordermanagementapi.repository.TransactionRepository;
import com.ea.ordermanagementapi.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService
{
    private TransactionRepository transactionRepository;

    private TransactionService self;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init()
    {
        self = applicationContext.getBean(TransactionService.class);
    }

    public TransactionServiceImpl(ApplicationContext applicationContext, TransactionRepository transactionRepository)
    {
        this.applicationContext = applicationContext;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean isTransactionCreated(String transactionUniqueArgs)
    {
        return transactionRepository.existsByTransactionUniqueArgsAndAction(transactionUniqueArgs,
                TransactionOperationAction.CREATE);
    }

    @Override
    public boolean isTransactionLocked(String transactionUniqueArgs)
    {
        return transactionRepository.existsByTransactionUniqueArgsAndAction(transactionUniqueArgs,
                TransactionOperationAction.LOCK)
                && !self.isTransactionReleased(transactionUniqueArgs);
    }

    @Override
    public boolean isTransactionReleased(String transactionUniqueArgs)
    {
        return transactionRepository.existsByTransactionUniqueArgsAndAction(transactionUniqueArgs,
                TransactionOperationAction.UNLOCK);
    }

    @Override
    @Transactional
    public TransactionOperation saveTransaction(TransactionOperation operation)
    {
        return transactionRepository.save(operation);
    }
}
