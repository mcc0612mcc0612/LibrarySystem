package com.library.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Book implements Serializable {

    private long book_id;
    private String name;
    private String author;
    private String publish;
    private String ISBN;
    private String introduction;
    private String language;
    private BigDecimal price;
    private String pub_date;
    private int class_id;
    private int status;


    public long getBookId() {
        return book_id;
    }

    public void setBookId(long book_id) {
        this.book_id = book_id;
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

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPubdate() {
        return pub_date;
    }

    public void setPubdate(String pub_date) {
        this.pub_date = pub_date;
    }

    public int getClassId() {
        return class_id;
    }

    public void setClassId(int class_id) {
        this.class_id = class_id;
    }

    public int getStatus(){return status;}

    public void setStatus(int status){this.status = status;}
}
