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

import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.itutorial.beans.Gender;
import org.thymeleaf.itutorial.beans.PaymentMethod;
import org.thymeleaf.spring3.messageresolver.SpringMessageResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.tools.memoryexecutor.StaticTemplateExecutor;

public class TemplateExecutor {

    private StaticTemplateExecutor executor;
    
    public TemplateExecutor(
            final HttpServletRequest request, final HttpServletResponse response, 
            final ServletContext servletContext, final MessageSource messageSource, final Locale locale) {
        WebContext context = new WebContext(request, response, servletContext);
        context.setLocale(locale);
        context.setVariable("product", DAO.loadProduct());
        context.setVariable("productList", DAO.loadAllProducts());
        context.setVariable("html", "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>");
        context.setVariable("customerName", "Dr. Julius Erwing");
        context.setVariable("customer", DAO.loadCustomer());
        context.setVariable("customerList", DAO.loadAllCustomers());
        context.setVariable("genders", Gender.values());
        context.setVariable("paymentMethods", PaymentMethod.values());
        String templateMode = StandardTemplateModeHandlers.HTML5.getTemplateModeName();
        SpringMessageResolver messageResolver = new SpringMessageResolver();
        messageResolver.setMessageSource(messageSource);
        executor = new StaticTemplateExecutor(context, messageResolver, templateMode);
    }

    public String generateCode(final String code) {
        return executor.processTemplateCode(code);
    }
}
