package com.mattr.templating.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
//	  //Shows  basic reference to jsp templating
//    @RequestMapping("/")
//    public String index() {
//            return "index.jsp";
//    }
	@RequestMapping("/")
    public String index(Model model) {
            model.addAttribute("dojoName", "Burbank");
            return "index.jsp";
    }
}