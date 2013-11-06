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

import java.util.Arrays;
import java.util.List;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypeLocator;
import org.springframework.expression.spel.support.StandardTypeLocator;

/**
 * Decorator for StandardTypeLocator only allowing access to certain fixed types.
 */
public class WhitelistTypeLocator implements TypeLocator {
    
    private static final List<String> whitelist = Arrays.asList("java.lang.Math", "Math", "java.util.Date");

    private final StandardTypeLocator standardTypeLocator = new StandardTypeLocator(); 
    
    public WhitelistTypeLocator() {
    }

    public Class<?> findType(String typeName) throws EvaluationException {
        if (notInWhilelist(typeName)) {
            throw new EvaluationException("Forbidden type: " + typeName);
        }
        return standardTypeLocator.findType(typeName);
    }

    private boolean notInWhilelist(String typeName) {
        return !whitelist.contains(typeName);
    }
}
