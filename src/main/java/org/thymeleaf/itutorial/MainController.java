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

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.itutorial.beans.Customer;
import org.thymeleaf.itutorial.beans.Gender;
import org.thymeleaf.itutorial.beans.PaymentMethod;
import org.thymeleaf.tools.htmlizer.JavaHTMLizer;
import org.thymeleaf.tools.htmlizer.XmlHTMLizer;


@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
    
    @RequestMapping("/templates/{exercise}/question.html")
    public String question(@PathVariable("exercise") String exercise) {
        return "/" + exercise + "/question.html";
    }
    
    private void setModelBeans(Model model) {
        model.addAttribute("product", DAO.loadProduct());
        model.addAttribute("productList", DAO.loadAllProducts());
        model.addAttribute("html", "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>");
        model.addAttribute("customerName", "Dr. Julius Erwing");
        model.addAttribute("customer", DAO.loadCustomer());
        model.addAttribute("customerList", DAO.loadAllCustomers());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
    }
    
    @RequestMapping("/templates/{exercise}/answer.html")
    public String answer(@PathVariable("exercise") String exercise,
            @RequestParam(value = "page", required = false) String page, Model model) {
        setModelBeans(model);
        return "/" + exercise + "/answer" + (page != null ? page : "") + ".html";
    }
    
    @RequestMapping("/templates/{exercise}/solution.html")
    public String solution(@PathVariable("exercise") String exercise,
            @RequestParam(value = "page", required = false) String page, Model model) {
        setModelBeans(model);
        return "/" + exercise + "/solution" + (page != null ? page : "") + ".html";
    }

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
    
    
    
    @RequestMapping("/resources/code/{resource}.{extension}")
    public String codeResources(@PathVariable("resource") String resource, @PathVariable("extension") String extension,
            HttpServletRequest request, HttpSession session, Model model) throws IOException {
        ServletContext context = session.getServletContext();
        String resourcePath = context.getRealPath("/WEB-INF/code/" + resource + "." + extension);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileCopyUtils.copy(new FileInputStream(resourcePath), out);
        model.addAttribute("resource", resource + "." + extension);
        model.addAttribute("code", JavaHTMLizer.htmlize(out.toString()));
        String referer = request.getHeader("Referer");
        model.addAttribute("backUrl", referer != null ? referer : request.getContextPath() + "/");
        return "resources.html";
    }
    
    @RequestMapping("/resources/templates/{exercise}/{resource}.{extension}")
    public String templateResources(@PathVariable("exercise") String exercise, @PathVariable("resource") String resource, 
            @PathVariable("extension") String extension,
            HttpServletRequest request, HttpSession session, Model model) throws IOException {
        ServletContext context = session.getServletContext();
        String resourcePath = context.getRealPath("/WEB-INF/templates/" + exercise + "/" + resource + "." + extension);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileCopyUtils.copy(new FileInputStream(resourcePath), out);
        model.addAttribute("resource", exercise + "/" + resource + "." + extension);
        model.addAttribute("code", XmlHTMLizer.htmlize(out.toString()));
        String referer = request.getHeader("Referer");
        model.addAttribute("backUrl", referer != null ? referer : request.getContextPath() + "/");
        return "resources.html";
    }
    
    
    @RequestMapping("/resources/i18n/{resource}.properties")
    public String i18nResources(@PathVariable("resource") String resource,
            HttpServletRequest request, HttpSession session, Model model) throws IOException {
        ServletContext context = session.getServletContext();
        String resourcePath = context.getRealPath("/WEB-INF/classes/" + resource + ".properties");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileCopyUtils.copy(new FileInputStream(resourcePath), out);
        model.addAttribute("resource", resource + ".properties");
        model.addAttribute("code", XmlHTMLizer.htmlize(out.toString()));
        String referer = request.getHeader("Referer");
        model.addAttribute("backUrl", referer != null ? referer : request.getContextPath() + "/");
        return "resources.html";
    }
    
}
