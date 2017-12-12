package com.github.oneone1995.mvolunteer.web.exception;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //当前没有动态的异常处理器
    @ExceptionHandler(MomentNotFoundException.class)
    public ResponseEntity<?> momentNotFoundHandler(MomentNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ResultModel.error(ResultStatus.MOMENT_NOT_FOUNT), HttpStatus.BAD_REQUEST);
    }

    //圈子动态创建失败的异常处理器
    @ExceptionHandler(MomentCreateFailException.class)
    public ResponseEntity<?> momentCreateFailHandler(MomentCreateFailException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ResultModel.error(ResultStatus.MOMENT_CREATE_FAIL), HttpStatus.BAD_REQUEST);
    }
}
