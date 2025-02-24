package com.atguigu.admin.exception;

/* @author  i-s-j-h-d
 * @version 1.0 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个Web controller的异常
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class}) //处理异常
    public String handleArithException(Exception e) {
        log.error("异常是：{}",e);
        return "login"; // 视图地址
    }

}
