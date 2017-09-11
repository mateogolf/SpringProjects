package com.mattr.dojosurvey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping(path="/process", method=RequestMethod.POST)
	public String process(Model model, @RequestParam(value="name") String name, @RequestParam(value="location") String location, @RequestParam(value="favorite") String favorite, @RequestParam(value="comments") String comments){
		//Validation Conditionals
		boolean flag = false;
		if(name.length()<1) {
			flag = true;
			model.addAttribute("noName", "You must entry a name");
			model.addAttribute("comments", comments);
		}
		if(comments.length()<1) {
			flag = true;
			model.addAttribute("noComments", "You must have a comment.");
			model.addAttribute("name", name);
		}
		
		if(flag) {
			return "redirect:/";
		}else {
			model.addAttribute("name", name);
			model.addAttribute("location", location);
			model.addAttribute("favorite", favorite);
			model.addAttribute("comments", comments);
			return "results.jsp";
		}
	}
	@RequestMapping("/return")
	public String goBack() {
		return "redirect:/";
	}
}
