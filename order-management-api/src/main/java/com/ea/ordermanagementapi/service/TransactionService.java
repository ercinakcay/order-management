package com.ea.ordermanagementapi.service;

import com.ea.ordermanagementapi.domain.TransactionOperation;

public interface TransactionService
{
    boolean isTransactionCreated(String transactionUniqueArgs);

    boolean isTransactionLocked(String transactionUniqueArgs);

    boolean isTransactionReleased(String transactionUniqueArgs);

    TransactionOperation saveTransaction(TransactionOperation operation);

}
