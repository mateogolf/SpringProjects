package com.mattr.thecode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeController {
	@RequestMapping("/")
	public String index() {
		return "login";
	}
	@PostMapping("/process")
	public String process(@RequestParam(value="password") String password,RedirectAttributes redirectAttributes) {
		if(password.equals("bushido")) {
			return "code";
		}else {
			redirectAttributes.addFlashAttribute("errors", "You must train harder!");
			return "redirect:/";
		}
		
	}
}
