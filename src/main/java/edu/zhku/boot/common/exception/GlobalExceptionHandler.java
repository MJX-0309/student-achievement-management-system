package edu.zhku.boot.common.exception;

import edu.zhku.boot.common.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MJX
 * @date 2021/10/24
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
