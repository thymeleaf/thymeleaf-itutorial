/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2013, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.itutorial;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.thymeleaf.itutorial.beans.Customer;
import org.thymeleaf.itutorial.beans.Gender;
import org.thymeleaf.itutorial.beans.PaymentMethod;
import org.thymeleaf.itutorial.beans.Product;
import org.thymeleaf.itutorial.beans.Amount;

/**
 * Mock persistence.
 */
public class DAO {

    private static final String NO_WEBSITE = null;
    
    public static Product loadProduct() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new Product("Wooden wardrobe with glass doors", Integer.valueOf(850), sdf.parse("2013-02-18"));
        } catch (ParseException ex) {
            throw new RuntimeException("Invalid date");
        }
    }
    
    public static List<Product> loadAllProducts() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Product> products = new ArrayList<Product>();
        try {
            products.add(new Product("Chair", Integer.valueOf(25), sdf.parse("2013-02-18")));
            products.add(new Product("Table", Integer.valueOf(150), sdf.parse("2013-02-15")));
            products.add(new Product("Armchair", Integer.valueOf(85), sdf.parse("2013-02-20")));
            products.add(new Product("Wardrobe", Integer.valueOf(450), sdf.parse("2013-02-21")));
            products.add(new Product("Kitchen table", Integer.valueOf(49), sdf.parse("2013-02-15")));
            products.add(new Product("Bookcase", Integer.valueOf(80), sdf.parse("2013-02-17")));
        } catch (ParseException ex) {
            throw new RuntimeException("Invalid date");
        }
        return products;
    }

    public static Customer loadCustomer() {
        Customer customer = new Customer();
        customer.setId(Integer.valueOf(1085));
        customer.setFirstName("Peter");
        customer.setLastName("Jackson");
        customer.setGender(Gender.MALE);
        customer.setPaymentMethod(PaymentMethod.DIRECT_DEBIT);
        customer.setBalance(2500000);
        return customer;
    }
    
    public static List<Customer> loadAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer(Integer.valueOf(101), "Peter", "Houston", Gender.MALE, PaymentMethod.CREDIT_CARD, 3000, NO_WEBSITE));
        customers.add(new Customer(Integer.valueOf(102), "Mary", "Johnson", Gender.FEMALE, PaymentMethod.BANK_TRANSFER, 12000, "http://maryjohnson.blogspot.com/"));
        customers.add(new Customer(Integer.valueOf(103), "Andy", "Hoffman", Gender.MALE, PaymentMethod.DIRECT_DEBIT, 35000, NO_WEBSITE));
        customers.add(new Customer(Integer.valueOf(104), "Jane", "Jones", null, PaymentMethod.CREDIT_CARD, 3050, NO_WEBSITE));
        customers.add(new Customer(Integer.valueOf(105), "Owen", "Houston", Gender.MALE, PaymentMethod.BANK_TRANSFER, 1500, "http://owenhouston.blogspot.com/"));
        customers.add(new Customer(Integer.valueOf(106), "Margaret", "Jackson", Gender.FEMALE, PaymentMethod.DIRECT_DEBIT, 3900, NO_WEBSITE));
        customers.add(new Customer(Integer.valueOf(107), "Rafael", "Garcia", null, PaymentMethod.CREDIT_CARD, 5000, "http://rafaelgarcia.blogspot.com/"));
        return customers;
    }

    public static Amount loadAmount() {
        BigDecimal amount = new BigDecimal("2599.50");
        return new Amount(amount);
    }

    public static Timestamp loadReleaseDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = sdf.parse("2014-01-31 15:00");
            return new Timestamp(date.getTime());
        } catch (ParseException ex) {
            throw new RuntimeException("Invalid date");
        }
    }
    
}
