package com.payment.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exception(SQLIntegrityConstraintViolationException exception){
        return Result.error("服务器繁忙");
    }
    @ExceptionHandler(CustomException.class)
    public Result<String> exception(CustomException exception){
        return Result.error(exception.getMessage());
    }
}
