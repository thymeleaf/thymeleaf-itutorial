package org.thymeleaf.itutorial.beans;

import java.util.Date;

public class Product {

    private String description;
    private Integer price;
    private Date availableFrom;
    
    public Product(final String description, final Integer price, final Date availableFrom) {
        this.description = description;
        this.price = price;
        this.availableFrom = availableFrom;
    }

    public Date getAvailableFrom() {
        return this.availableFrom;
    }

    public void setAvailableFrom(final Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }
    
}
