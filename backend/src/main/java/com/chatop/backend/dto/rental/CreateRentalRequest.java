package com.chatop.backend.dto.rental;

public class CreateRentalRequest {

    private String name;
    private Float surface;
    private Float price;
    private String description;

    public CreateRentalRequest() {
    }

    public CreateRentalRequest(String name, Float surface, Float price, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Float getSurface() {
        return surface;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurface(Float surface) {
        this.surface = surface;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
