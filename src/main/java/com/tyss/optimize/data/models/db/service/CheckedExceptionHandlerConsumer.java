package com.tyss.optimize.data.models.db.service;

@FunctionalInterface
public interface CheckedExceptionHandlerConsumer<Target,ExceptionObject extends Exception> {

    public void accept(Target target) throws ExceptionObject;

}
