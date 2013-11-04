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

import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.support.RequestContext;
import org.thymeleaf.itutorial.beans.Customer;
import org.thymeleaf.itutorial.beans.Gender;
import org.thymeleaf.itutorial.beans.PaymentMethod;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.spring3.expression.ThymeleafEvaluationContext;
import org.thymeleaf.spring3.messageresolver.SpringMessageResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.tools.memoryexecutor.StaticTemplateExecutor;
import static org.thymeleaf.spring3.expression.ThymeleafEvaluationContext.THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME;
import static org.thymeleaf.spring3.naming.SpringContextVariableNames.SPRING_REQUEST_CONTEXT;

public class TemplateExecutor {

    private final StaticTemplateExecutor executor;
    
    public TemplateExecutor(
            final HttpServletRequest request, final HttpServletResponse response, 
            final ServletContext servletContext, final MessageSource messageSource,
            final Locale locale, final ApplicationContext applicationContext,
            final ConversionService conversionService) {
        SpringWebContext context = new SpringWebContext(request, response, servletContext, locale, new HashMap(), applicationContext);
        // Necessary to avoid NullPointerException in 'exercise 12: forms'
        RequestContext requestContext = new RequestContext(request, response, servletContext, context.getVariables());
        context.setVariable(SPRING_REQUEST_CONTEXT, requestContext);
        Customer customer = DAO.loadCustomer();
        String customerVariableName = "customer";
        WebDataBinder dataBinder = new WebDataBinder(customer, customerVariableName);
        dataBinder.setConversionService(conversionService);            
        String bindingResultName = BindingResult.MODEL_KEY_PREFIX + customerVariableName;
        context.setVariable(customerVariableName, customer);
        context.setVariable(bindingResultName, dataBinder.getBindingResult());
        // Necessary for the ConversionService to work//        // Necessary for the ConversionService to work
        ThymeleafEvaluationContext evaluationContext = new ThymeleafEvaluationContext(applicationContext, conversionService);
        context.setVariable(THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME, evaluationContext);
        // Model attributes
        context.setVariable("product", DAO.loadProduct());
        context.setVariable("productList", DAO.loadAllProducts());
        context.setVariable("html", "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>");
        context.setVariable("customerName", "Dr. Julius Erwing");
        context.setVariable("customerList", DAO.loadAllCustomers());
        context.setVariable("genders", Gender.values());
        context.setVariable("paymentMethods", PaymentMethod.values());
        context.setVariable("price", DAO.loadAmount());
        context.setVariable("releaseDate", DAO.loadReleaseDate());
        String templateMode = StandardTemplateModeHandlers.HTML5.getTemplateModeName();
        SpringMessageResolver messageResolver = new SpringMessageResolver();
        messageResolver.setMessageSource(messageSource);
        executor = new StaticTemplateExecutor(context, messageResolver, templateMode);
    }

    public String generateCode(final String code) {
        return executor.processTemplateCode(code);
    }
}
