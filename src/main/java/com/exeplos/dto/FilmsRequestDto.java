package com.exeplos.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FilmsRequestDto {
    @NotNull(message = "openingCrawl is not null")
    @NotEmpty(message = "openingCrawl is not empty")
    private String openingCrawl;
}
