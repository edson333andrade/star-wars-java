package com.exeplos.repository;

import com.exeplos.model.Films;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Integer> {
    Page<Films> findAll(Pageable pageable);
}
