package org.thymeleaf.itutorial.beans;

public enum Gender {
    
    FEMALE("Female gender"),
    MALE("Male gender");

    private String description;
    
    Gender(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}

