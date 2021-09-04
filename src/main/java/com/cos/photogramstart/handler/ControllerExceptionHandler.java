package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice//모든 예외를 여기서 잡아준다. 모든 @Controller 전역에서 발생할 수 있는 예외를 처리하는 어노테이션
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto validationException(CustomValidationException e){
        return new CMRespDto(e.getMessage(),e.getErrorMap());
    }
}
