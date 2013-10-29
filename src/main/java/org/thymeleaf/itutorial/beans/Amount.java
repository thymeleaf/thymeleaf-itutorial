package org.thymeleaf.itutorial.beans;

import java.math.BigDecimal;

public class Amount {

    private final BigDecimal amount;
    
    public Amount(final BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
