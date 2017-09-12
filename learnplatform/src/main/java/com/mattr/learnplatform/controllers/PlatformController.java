package com.mattr.learnplatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlatformController {
	@RequestMapping("/")
	public String index() {
		return "platform";
	}
	@RequestMapping("/m/{chapter}/0553/{assignmentNumber}")
	public String lessions(Model model,@PathVariable int chapter,@PathVariable String assignmentNumber){
		if(chapter == 38) {
			if(assignmentNumber.equals("0733")) {
				model.addAttribute("content","Setting up your servers");
			}else if(assignmentNumber.equals("0342")) {
				model.addAttribute("content","Punch Cards");
			}
		} else if (chapter == 26) {
			if(assignmentNumber.equals("0348")) {
				model.addAttribute("content","Advanced Fortran Intro");
			}
		} else {
			model.addAttribute("content","Path not programmed in yet!");
		}
		return "platform";
	}
	@RequestMapping("/m/{chapter}/0483/{assignmentNumber}")
	public String assignments(Model model,@PathVariable int chapter,@PathVariable String assignmentNumber){
		if(chapter == 38) {
			if(assignmentNumber.equals("0345")) {
				model.addAttribute("content","Coding Forms");
			}
		} else if (chapter == 26) {
			if(assignmentNumber.equals("2342")) {
				model.addAttribute("content","Advanced Fortran Intro");
			}
		}
		else {
			model.addAttribute("content","Path not programmed in yet!");
		}
		return "assignment";
	}
}
