package com.exeplos.handles;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApiModel
@Getter
@Setter
public class Error {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String method;
    private String path;
    private ErrorDetails errorDetails;

    public Error(HttpStatus httpStatus, String message, String path, ErrorDetails errorDetails) {
        super();
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        this.message = message;
        this.path = path;
        this.errorDetails = errorDetails;
    }

}
