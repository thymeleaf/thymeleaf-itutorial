/*
 * Copyright 2013 The THYMELEAF team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.thymeleaf.itutorial;

import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.VariablesMap;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.spring3.expression.ThymeleafEvaluationContext;
import static org.thymeleaf.spring3.expression.ThymeleafEvaluationContext.THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME;
import static org.thymeleaf.spring3.naming.SpringContextVariableNames.SPRING_REQUEST_CONTEXT;

/**
 * Decorator for SpringWebContext performing all required initializations.
 */
public class OfflineSpringWebContext implements IWebContext {
    
    private final SpringWebContext springWebContext;

    public OfflineSpringWebContext(
            final HttpServletRequest request, 
            final HttpServletResponse response,
            final ServletContext servletContext,
            final ApplicationContext applicationContext,
            final ConversionService conversionService) {
        // Create delegating SpringWebContext
        final Locale locale = RequestContextUtils.getLocale(request);
        springWebContext = new SpringWebContext(
            request, response, servletContext, locale, new HashMap(), applicationContext);
        // Perform initializations
        initialize(conversionService);
    }

    private void initialize(final ConversionService conversionService) {
        createRequestContext();
        createEvaluationContext(conversionService);
        createBindings();
    }
    
    private void createRequestContext() {
        RequestContext requestContext = new RequestContext(
            springWebContext.getHttpServletRequest(), springWebContext.getHttpServletResponse(), springWebContext.getServletContext(), springWebContext.getVariables());
        springWebContext.setVariable(SPRING_REQUEST_CONTEXT, requestContext);
    }

    private void createEvaluationContext(final ConversionService conversionService) {
        ThymeleafEvaluationContext evaluationContext = new ThymeleafEvaluationContext(springWebContext.getApplicationContext(), conversionService);
        evaluationContext.setTypeLocator(new WhitelistTypeLocator());
        springWebContext.setVariable(THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME, evaluationContext);
    }

    private void createBindings() {
        // TODO
    }

    public void addContextExecutionInfo(String string) {
        springWebContext.addContextExecutionInfo(string);
    }

    public HttpServletRequest getHttpServletRequest() {
        return springWebContext.getHttpServletRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return springWebContext.getHttpServletResponse();
    }

    public HttpSession getHttpSession() {
        return springWebContext.getHttpSession();
    }

    public ServletContext getServletContext() {
        return springWebContext.getServletContext();
    }

    @Deprecated
    public VariablesMap<String, String[]> getRequestParameters() {
        return springWebContext.getRequestParameters();
    }

    @Deprecated
    public VariablesMap<String, Object> getRequestAttributes() {
        return springWebContext.getRequestAttributes();
    }

    @Deprecated
    public VariablesMap<String, Object> getSessionAttributes() {
        return springWebContext.getSessionAttributes();
    }

    @Deprecated
    public VariablesMap<String, Object> getApplicationAttributes() {
        return springWebContext.getApplicationAttributes();
    }

    public VariablesMap<String, Object> getVariables() {
        return springWebContext.getVariables();
    }

    public Locale getLocale() {
        return springWebContext.getLocale();
    }
}
