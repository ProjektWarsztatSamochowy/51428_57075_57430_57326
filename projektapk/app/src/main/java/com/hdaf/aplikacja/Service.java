package com.hdaf.aplikacja;

public class Service {
    private String name;
    private String description;
    private double price;

    public Service(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Gettery
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    // Settery jeśli są potrzebne
}
