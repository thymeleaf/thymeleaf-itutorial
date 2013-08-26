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

import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.exceptions.TemplateProcessingException;

@Controller
public class DynamicViewController {
    
    @Autowired private ServletContext servletContext;

    @RequestMapping(value = "/dynamicView", method = RequestMethod.POST)
    public void dynamicView(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        String result = generateCodeOrError(request, response, servletContext, locale, code);
        response.getWriter().print(result);
    }

    private String generateCodeOrError(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, Locale locale, String code) {
        try {
            TemplateExecutor templateExecutor = new TemplateExecutor(request, response, servletContext, locale);
            return templateExecutor.generateCode(code);
        } catch (TemplateProcessingException ex) {
            return formatErrorMessage(ex);
        }
    }
    
    private String formatErrorMessage(TemplateProcessingException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Error</h1>");
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            sb.append("<p>").append(ex.getCause().getMessage()).append("</p>");
        }
        sb.append("<p><em>(").append(ex.toString()).append(")</em></p>");
        return sb.toString();
    }
}
