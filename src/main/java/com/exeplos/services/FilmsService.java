package com.exeplos.services;

import com.exeplos.dto.FilmsDto;
import com.exeplos.dto.FilmsRequestDto;
import com.exeplos.exception.NotFoundException;
import com.exeplos.model.Films;
import com.exeplos.repository.FilmsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class FilmsService {
    private static Integer PAGE_SIZE = 10;
    private static Integer PAGE = 0;
    private final FilmsRepository filmsRepository;

    public FilmsService(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    /**
     * @apiNote Metodo de listagem de Filmes utilizando JPA pagination
     * @return FilmsDto
     */
    public FilmsDto doGet(){
        PageRequest pageRequest = PageRequest.of(PAGE, PAGE_SIZE);
        var resp = this.filmsRepository.findAll(pageRequest);
        return FilmsDto.builder()
                .count(resp.getTotalElements())
                .results(resp.getContent())
                .build();
    }

    /**
     * @apiNote Metodo de Consulat de Filme por Id
     * @param id
     * @return Films
     */
    public Films doGet(Integer id){
        var resp = this.filmsRepository.findById(id);
        return resp.orElse(null);
    }

    /**
     * @apiNote Metodo de Atulização de filmes
     * @param id
     * @param body
     */
    public void doPut(Integer id, FilmsRequestDto body) {
        var entity  = this.filmsRepository.findById(id);
        if(entity.isEmpty()){
           throw new NotFoundException("Not found films");
        }
        entity.get().setOpeningCrawl(body.getOpeningCrawl());
        this.filmsRepository.saveAndFlush(entity.get());
    }
}
