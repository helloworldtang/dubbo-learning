package com.tangcheng.dubbo.exception;

/**
 * Created by tang.cheng on 2017/6/1.
 */
public class NotFound extends RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
