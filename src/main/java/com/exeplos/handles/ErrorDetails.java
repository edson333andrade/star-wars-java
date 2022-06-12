package com.exeplos.handles;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ErrorDetails {
    @ApiModelProperty(example = "e9ef6d22-7f46-426b-8121-417e1714ad6d", required = false,
            value = "Unique identifier of the error. Can be used to track error propagation.")
    private String uniqueId = UUID.randomUUID().toString();

    @ApiModelProperty(example = "error.business.request.invalid", required = false, value = "Error Code.")
    private String informationCode;

    @ApiModelProperty(example = "The attribute {1} must be filled.", required = false,
            value = "Description of the error.")
    private String message;

}
