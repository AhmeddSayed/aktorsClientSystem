package com.kreative.aktorsclientsystem.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ahmed
 */
@Entity
public class Product implements Serializable {

    // product parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long barcode;
    private String name;
    private int price;
    private String description;
    private Date releaseDate;

    public Product() {
    }

    // product constructor
    public Product(String name, int price, String description, Date releaseDate) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getBarcode() {
        return barcode;
    }
}
