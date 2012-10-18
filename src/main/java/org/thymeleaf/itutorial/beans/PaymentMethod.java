package org.thymeleaf.itutorial.beans;

public enum PaymentMethod {
    
    CREDIT_CARD("Credit card payment"),
    DIRECT_DEBIT("Direct debit payment"),
    BANK_TRANSFER("Bank transfer payment");

    private String description;
    
    PaymentMethod(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
