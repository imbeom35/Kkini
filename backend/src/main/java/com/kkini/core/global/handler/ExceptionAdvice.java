package com.kkini.core.global.handler;

import com.kkini.core.global.exception.InvalidException;
import com.kkini.core.global.exception.JwtTokenException;
import com.kkini.core.global.exception.ServiceRuntimeException;
import com.kkini.core.global.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.jar.JarException;

import static com.kkini.core.global.response.Response.ERROR;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private ResponseEntity<Response<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable, status), headers, status);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInvalidException(InvalidException e) {
        log.error("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        log.error("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/error")
    public ResponseEntity<?> notFoundException(NoHandlerFoundException e){
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<?> handleJwtTokenException(JwtTokenException e){
        return newResponse(e, HttpStatus.UNAUTHORIZED);
    }


}
