package com.atguigu.admin.exception;

/* @author  i-s-j-h-d
 * @version 1.0 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//                          403状态码
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UserTooManyException extends RuntimeException{

    public UserTooManyException() {

    }

    public UserTooManyException(String message) {
        super(message);
    }

}
