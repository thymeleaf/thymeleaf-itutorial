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

public enum ModelAttribute {

    PRODUCT("product", "code/Product.java"),
    PRODUCT_LIST("productList", "code/Product.java"),
    HTML("html", null),
    CUSTOMER_NAME("customerName", null),
    CUSTOMER("customer", "code/Customer.java"),
    CUSTOMER_LIST("customerList", "code/Customer.java"),
    GENDER("(Gender.java)", "code/Gender.java"),
    PAYMENT_METHOD("(PaymentMethod.java)", "code/PaymentMethod.java"),
    MESSAGES_EN("(messages_en)", "classes/messages_en.properties"),
    MESSAGES_ES("(messages_es)", "classes/messages_es.properties"),
    MESSAGES_FR("(messages_fr)", "classes/messages_fr.properties");

    private String name;
    private String file;

    private ModelAttribute(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }
}
