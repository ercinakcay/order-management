package com.ea.ordermanagementapi.domain.enums;

public enum TransactionOperationAction
{
    CREATE,
    LOCK(true),
    UNLOCK(false);

    private Boolean value;

    TransactionOperationAction()
    {
    }

    TransactionOperationAction(Boolean value)
    {
        this.value = value;
    }

    public Boolean getValue()
    {
        return value;
    }
}
