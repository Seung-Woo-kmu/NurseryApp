package com.example.api.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.example.api")
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadCredentialsException.class})
    public LoginError notReadable(Exception e) {
        return new LoginError("아이디 또는 비밀번호가 잘못되었습니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AuthenticationServiceException.class})
    public LoginError auth(Exception e) {
        return new LoginError("존재하지 않는 사용자입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LockedException.class})
    public LoginError locked(Exception e) {
        return new LoginError("잠긴 계정입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DisabledException.class})
    public LoginError disabled(Exception e) {
        return new LoginError("비활성화 된 계정입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AccountExpiredException.class})
    public LoginError expired(Exception e) {
        return new LoginError("만료된 계정입니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({CredentialsExpiredException.class})
    public LoginError creExpired(Exception e) {
        return new LoginError("비밀번호가 만료되었습니다.");
    }
    @Data
    @AllArgsConstructor
    static class LoginError {
        String message;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    errors.put(((FieldError) error).getField(), error.getDefaultMessage());
                } );
        return ResponseEntity.badRequest().body(errors);
    }
}
