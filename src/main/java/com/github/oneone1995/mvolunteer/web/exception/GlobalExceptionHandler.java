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

    //环信群组创建失败的异常处理器,客户端不需要明确知道该错误详情，返回系统内部错误即可
    @ExceptionHandler(EasemobGroupCreateFailException.class)
    public ResponseEntity<?> easemobGroupCreateFailHandler(EasemobGroupCreateFailException e) {
        return internalServerErrorHandler(e);
    }

    //添加用户至环信IM聊天群异常处理器,和环信群组创建失败异常一样，返回系统内部错误即可
    @ExceptionHandler(PutUserToEasemobGroupFailException.class)
    public ResponseEntity<?> putUserToEasemobGroupFailHandler(PutUserToEasemobGroupFailException e) {
        return internalServerErrorHandler(e);
    }

    //生成环信token失败的异常处理器，和上两个异常一样，返回系统内部错误即可
    @ExceptionHandler(GenerateEasemobTokenFailException.class)
    public ResponseEntity<?> generateEasemobTokenFailHandler(GenerateEasemobTokenFailException e) {
        return internalServerErrorHandler(e);
    }

    //系统内部错误的处理
    private ResponseEntity<?> internalServerErrorHandler(RuntimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(ResultModel.error(ResultStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
