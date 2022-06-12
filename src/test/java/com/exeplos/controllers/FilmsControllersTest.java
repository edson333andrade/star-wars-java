package com.exeplos.controllers;

import com.exeplos.dto.FilmsRequestDto;
import com.exeplos.exception.NotFoundException;
import com.exeplos.services.FilmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FilmsControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilmsService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void wenFilmsThenList() throws Exception {
        mockMvc.perform(get("/films")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void wenFilmsFindThenOne() throws Exception {
        mockMvc.perform(get("/films/1")
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.episodeId", is(1)));
    }


    @Test
    public void wenUpdateFilms() throws Exception {
        var body = new FilmsRequestDto();
        body.setOpeningCrawl("Teste");
        mockMvc.perform(put("/films/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void wenUpdateFilmsWhenNotFund() throws Exception {
        var body = new FilmsRequestDto();
        body.setOpeningCrawl("Teste");
        mockMvc.perform(put("/films/6")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isNotFound());
    }

}

