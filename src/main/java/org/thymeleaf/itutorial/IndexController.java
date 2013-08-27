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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired private String thymeleafVersion;
    
    @RequestMapping("/")
    public String index(final Model model) {
        model.addAttribute("thymeleafVersion", thymeleafVersion);
        model.addAttribute("exercises", Exercise.values());
        return "index.html";
    }

/*
    @RequestMapping("/templates/exercise11/product.html")
    public String product(@RequestParam("action") String action, Model model) {
        setModelBeans(model);
        if (action.equals("view")) {
            return "/exercise11/viewProduct.html";
        } else if (action.equals("edit")) {
            return "/exercise11/editProduct.html";
        } else {
            throw new IllegalArgumentException("Action -" + action +"- not recognized");
        }
    }
    
    @RequestMapping("/templates/exercise12/saveCustomer.html")
    public String saveCustomer(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "/exercise12/saveCustomer.html";
    }
*/
}
