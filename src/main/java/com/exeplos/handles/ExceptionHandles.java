package com.exeplos.handles;

import com.exeplos.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static java.util.Objects.isNull;

@ControllerAdvice
public class ExceptionHandles {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Error validationError(MethodArgumentNotValidException ex, WebRequest request) {
        final var result = ex.getBindingResult();
        final StringBuilder stringBuilder = new StringBuilder();
        final var errorDetail = result.getFieldErrors().stream()
                .map(it -> {
                    final var details = new ErrorDetails();
                    stringBuilder.append(it.getDefaultMessage());
                    details.setInformationCode("error.request." + it.getField() + ".invalid." + it.getCode());
                    details.setMessage(stringBuilder.toString());
                    return details;
                })
                .findFirst()
                .orElse(null);
        if(isNull(errorDetail)) {
            stringBuilder.append("Validation error");
        }
        return new Error(BAD_REQUEST, stringBuilder.toString(), request.getContextPath(), errorDetail);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handlerNotFoundException(NotFoundException e, HttpServletRequest request) {
        final var details = getErrorDetails(e);
        return ResponseEntity.status(NOT_FOUND)
                .body(new Error(NOT_FOUND, e.getMessage(), request.getContextPath(), details));
    }

    private ErrorDetails getErrorDetails(Exception e) {
        final var details = new ErrorDetails();
        details.setInformationCode(null);
        details.setMessage(e.getMessage());
        return details;
    }
}
