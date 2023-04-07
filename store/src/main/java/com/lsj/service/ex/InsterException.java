package com.lsj.service.ex;

public class InsterException extends ServiceException{
    public InsterException() {
        super();
    }

    public InsterException(String message) {
        super(message);
    }

    public InsterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsterException(Throwable cause) {
        super(cause);
    }

    protected InsterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
