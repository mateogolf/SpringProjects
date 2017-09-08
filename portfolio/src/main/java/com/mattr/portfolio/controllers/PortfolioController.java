package com.mattr.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortfolioController {
	@RequestMapping("/")
    public String index() {
            return "forward:/index.html";
    }
	@RequestMapping("/me")
    public String me() {
            return "forward:/about.html";
    }
	@RequestMapping("/projects")
    public String projects() {
            return "forward:/myprojects.html";
    }
}
