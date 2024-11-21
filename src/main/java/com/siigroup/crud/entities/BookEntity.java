package com.siigroup.crud.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    public BookEntity() {

    }

    public BookEntity(Long l, String s) {
        this.id = l;
        this.title = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
