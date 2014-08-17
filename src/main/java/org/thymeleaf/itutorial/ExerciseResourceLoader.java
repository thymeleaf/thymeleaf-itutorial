/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import org.springframework.util.FileCopyUtils;

public class ExerciseResourceLoader {

    private static final String TEMPLATE_DIR = "/WEB-INF/templates";

    private ServletContext context;
    private Exercise exercise;

    public ExerciseResourceLoader(final ServletContext context, final Exercise exercise) {
        this.context = context;
        this.exercise = exercise;
    }
    
    public InputStream getResourceAsStream(final String resource) {
        String resourcePath = TEMPLATE_DIR + "/" + exercise.getPath() + "/" + resource;
        return context.getResourceAsStream(resourcePath);
    }

    public String getResource(final String resource, final String charset) throws IOException {
        InputStream resourceStream = getResourceAsStream(resource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileCopyUtils.copy(resourceStream, outputStream);
        return outputStream.toString(charset);
    }
}
