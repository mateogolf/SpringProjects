package com.mattr.controllerpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
        @RequestMapping("/")
        public String index() {
                return "forward:/index.html";
        }
}

//@RestController
//@RequestMapping("/hello")
//public class HomeController {
//	@RequestMapping("")
//	public String hello() {
//		return "Hello";
//	}
//	@RequestMapping("/world")
//	public String world() {
//		return "Hello World";
//	}
//	
//}
