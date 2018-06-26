package com.example.list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min = 2, max=50)
    private String resourseName;

    @NotEmpty
    @Size(min = 4, max=30)
    private String url;

    private LocalDateTime dayEntered;

    private String person;

    public Link() {
        dayEntered = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResourseName() {
        return resourseName;
    }

    public void setResourseName(String name) {
        this.resourseName = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDayEntered() {
        return dayEntered;
    }

    public void setDayEntered(LocalDateTime dayEntered) {
        this.dayEntered = dayEntered;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
