package com.example.step2_projectdve.Model;

public class Product {
    private int id;
    private String name;
    private String link;

    public Product() {
    }

    public Product(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Product(int id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
