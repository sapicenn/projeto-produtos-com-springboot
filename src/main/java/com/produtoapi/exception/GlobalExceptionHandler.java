package com.produtoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {

        List<Map<String, String>> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> erro = new HashMap<>();
                    erro.put("field", error.getField());
                    erro.put("defaultMessage", error.getDefaultMessage());
                    return erro;
                })
                .toList();

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("errors", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
