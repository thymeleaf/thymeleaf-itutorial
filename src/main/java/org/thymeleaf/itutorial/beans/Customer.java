package org.thymeleaf.itutorial.beans;

/**
 * Customer information.
 */
public class Customer {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private PaymentMethod paymentMethod;
    private int balance;

    
    public Customer() {
        super();
    }

    public Customer(
            final Integer id, final String firstName, final String lastName, 
            final Gender gender, final PaymentMethod paymentMethod, final int balance) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.paymentMethod = paymentMethod;
        this.balance = balance;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
}
