package com.mattr.hellohuman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetController {
	@RequestMapping("/")
	public String index(Model model,@RequestParam(name="name", required=false) String searchQuery) {
		System.out.println(searchQuery == null);
		if(searchQuery == null) {
			model.addAttribute("name", "Human");
		}else {
			model.addAttribute("name", searchQuery);
		}
        return "index.jsp";
    }
}
