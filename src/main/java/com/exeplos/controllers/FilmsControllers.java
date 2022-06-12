package com.exeplos.controllers;

import com.exeplos.dto.FilmsDto;
import com.exeplos.dto.FilmsRequestDto;
import com.exeplos.model.Films;
import com.exeplos.services.FilmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Films", tags = { "Films" })
@RestController
public class FilmsControllers {

    private final FilmsService filmsService;

    public FilmsControllers(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @ApiOperation(value = "List Films")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = FilmsDto.class)})
    @GetMapping(value = "/films")
    public ResponseEntity<FilmsDto> doGet() {
        var resp = this.filmsService.doGet();
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Find Films")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = FilmsDto.class)})
    @GetMapping(value = "/films/{id}")
    public ResponseEntity<Films> doGet(@PathVariable("id") Integer id) {
        var resp = this.filmsService.doGet(id);
        return ResponseEntity.ok(resp);
    }

    @ApiOperation(value = "Update Films")
    @ApiResponses({
            @ApiResponse(code = 202, message = "NO_CONTENT")})
    @PutMapping(value = "/films/{id}")
    public ResponseEntity<Void> doPut(@PathVariable("id") Integer id,@Valid @RequestBody FilmsRequestDto body) {
        this.filmsService.doPut(id, body);
        return ResponseEntity.noContent().build();
    }
}
