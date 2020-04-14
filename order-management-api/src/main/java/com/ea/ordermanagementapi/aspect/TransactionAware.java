package com.ea.ordermanagementapi.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For usage of {@code TransactionAware} annotation,
 * the demanded class need implementation of {@code TransactionAwareIdentifier}.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionAware
{
    // At this moment not using for changing
    // active transaction state. but can be used...
    boolean lockUntilTransactionComplete() default true;
}
