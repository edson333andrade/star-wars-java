package com.exeplos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "FILMS", schema = "PUBLIC")
public class Films {
    @Id
    @Column(name = "id", nullable = false)
    private Integer episodeId;
    @Column(name = "title")
    private String title;
    @Column(name = "opening_crawl")
    private String openingCrawl;
    @Column(name = "producer")
    private String producer;
    @Column(name = "director")
    private String director;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created;
    @Column(name = "edited")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date edited;
    @Column(name = "version")
    private int version;

    @PreUpdate
    private void onUpdate() {
        this.edited = new Date();
        this.version++;
    }

}
