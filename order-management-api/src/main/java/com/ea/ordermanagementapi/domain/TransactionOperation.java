package com.ea.ordermanagementapi.domain;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ea.ordermanagementapi.domain.enums.TransactionOperationAction;
import com.ea.ordermanagementapi.util.DateUtils;

@Document(collection = "TransactionOperation")
public class TransactionOperation
{
    protected String uniqueId;

    protected long timestamp;

    String clazz;

    TransactionOperationAction action;

    String transactionUniqueArgs;

    public TransactionOperation()
    {
    }

    public TransactionOperation(String clazz, TransactionOperationAction action, String transactionUniqueArgs)
    {
        this.uniqueId = UUID.randomUUID().toString().replace("-", "");
        this.timestamp = DateUtils.getNow();
        this.clazz = clazz;
        this.action = action;
        this.transactionUniqueArgs = transactionUniqueArgs;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public String getClazz()
    {
        return clazz;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public TransactionOperationAction getAction()
    {
        return action;
    }

    public void setAction(TransactionOperationAction action)
    {
        this.action = action;
    }

    public String getTransactionUniqueArgs()
    {
        return transactionUniqueArgs;
    }

    public void setTransactionUniqueArgs(String transactionUniqueArgs)
    {
        this.transactionUniqueArgs = transactionUniqueArgs;
    }
}
