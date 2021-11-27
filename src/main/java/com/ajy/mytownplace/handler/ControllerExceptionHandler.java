package com.ajy.mytownplace.handler;

import com.ajy.mytownplace.handler.ex.CustomValidationException;
import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.handler.ex.CustomApiException;
import com.ajy.mytownplace.handler.ex.CustomException;
import com.ajy.mytownplace.handler.ex.CustomValidationApiException;
import com.ajy.mytownplace.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice//모든 예외를 여기서 잡아준다. 모든 @Controller 전역에서 발생할 수 있는 예외를 처리하는 어노테이션
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음.
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        if(e.getErrorMap() == null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST) ;
    }
}
