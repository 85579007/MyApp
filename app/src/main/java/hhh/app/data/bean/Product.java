package hhh.app.data.bean;

import java.util.Date;

/**
 * Created by hhh on 2016/10/25.
 */
public class Product {
    private int id;
    private String name;
    private String image;
    private double price;
    private double special;
    private double rating;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getSpecial() {
        return special;
    }

    public void setSpecial(float special) {
        this.special = special;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
