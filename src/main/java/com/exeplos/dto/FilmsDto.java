package com.exeplos.dto;

import com.exeplos.model.Films;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class FilmsDto {
    private List<Films> results;
    private Long count;
    private Integer next;
    private Integer previous;
}
