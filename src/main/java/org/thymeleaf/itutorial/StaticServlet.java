/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2012, The THYMELEAF team (http://www.thymeleaf.org)
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

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class StaticServlet extends HttpServlet {

    private static final long serialVersionUID = 4301609727617039377L;

    private static final String SERVLET_PREFIX = "/static/";
    
    @Override
    public void service(final ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        final String requestURI = ((HttpServletRequest)request).getRequestURI();
        final int staticIndex = requestURI.indexOf(SERVLET_PREFIX);
        final String requestPath = requestURI.substring(staticIndex + SERVLET_PREFIX.length());
        
        final String filePath = getServletContext().getRealPath("/WEB-INF/"+requestPath);
        
        final FileReader fr = new FileReader(filePath);
        
        response.setContentType("text/html");
        for(int c = fr.read();c != -1; c = fr.read() ) {
          response.getOutputStream().write(c);
        }
        
    }
    
}
