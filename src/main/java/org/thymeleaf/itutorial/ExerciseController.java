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
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.itutorial.beans.Customer;

@Controller
public class ExerciseController {

    private static final String CHARSET = "UTF-8";

    @Autowired private ServletContext servletContext;
    @Autowired private String thymeleafVersion;

    @RequestMapping(value = "/exercise/{index}", method = RequestMethod.GET)
    public String exercise(@PathVariable("index") final Integer index, final Model model) throws IOException {
        Exercise exercise = Exercise.get(index);
        String question = new ExerciseResourceLoader(servletContext, exercise).getResource("question.html", CHARSET);
        model.addAttribute("question", question);
        model.addAttribute("exercise", exercise);
        model.addAttribute("thymeleafVersion", thymeleafVersion);
        return "exercise.html";
    }

    // Auxiliar mapping for Exercise 11.
    @RequestMapping("/exercise11/product.html")
    public String product(@RequestParam("action") String action, Model model) {
        model.addAttribute("product", DAO.loadProduct());
        if ("view".equals(action)) {
            return "/exercise11/viewProduct.html";
        } else if ("edit".equals(action)) {
            return "/exercise11/editProduct.html";
        } else {
            throw new IllegalArgumentException("Action -" + action +"- not recognized");
        }
    }

    // Auxiliar mapping for Exercise 12.
    @RequestMapping("/exercise12/saveCustomer.html")
    public String saveCustomer(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "/exercise12/saveCustomer.html";
    }
}
