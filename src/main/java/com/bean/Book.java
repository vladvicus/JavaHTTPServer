package com.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlType(name = "book", propOrder = {"id", "name", "author", "pages", "language"})
@XmlAccessorType(XmlAccessType.NONE)

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "book")

public class Book {
    public Book(){
    }

    public Book(int id, String name, String author, double pages, String language) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPages() {
        return pages;
    }

    public void setPages(double pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("id")
    @XmlElement(required = true, name = "id")
    private int id;

    @JsonProperty("name")
    @XmlElement(required = true, name = "name")
    private String name;

    @JsonProperty("author")
    @XmlElement(required = true, name = "author")
    private String author;

    @JsonProperty("pages")
    @XmlElement(required = true, name = "pages")
    private double pages;

    @JsonProperty("language")
    @XmlElement(required = true, name = "language")
    private String language;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", language='" + language + '\'' +
                '}';
    }
}


