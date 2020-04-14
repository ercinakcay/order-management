package com.ea.ordermanagementapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ea.ordermanagementapi.domain.TransactionOperation;
import com.ea.ordermanagementapi.domain.enums.TransactionOperationAction;

public interface TransactionRepository extends MongoRepository<TransactionOperation, String>
{
    Boolean existsByTransactionUniqueArgsAndAction(String transactionUniqueArgs, TransactionOperationAction action);
}
