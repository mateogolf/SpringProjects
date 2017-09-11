package com.mattr.templating.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sessionAttribute")
public class HomeController {
//	  //Shows  basic reference to jsp templating
//    @RequestMapping("/")
//    public String index() {
//            return "index.jsp";
//    }
	@ModelAttribute("sessionAttribute")
    public String getSessionAttribute(){
        return "";
    }
	@RequestMapping("/")
    public String index(Model model,@ModelAttribute("sessionAttribute") String sessionAttribute) {
            model.addAttribute("dojoName", "Burbank");
            
            return "index.jsp";
    }
	@RequestMapping("/setSession")
    public String setSession(Model model){
		model.addAttribute("sessionAttribute", "exampleData");
        return "redirect:/";
    }
	
//    @RequestMapping("/")
//    public String index(@ModelAttribute("sessionAttribute") String sessionAttribute) {
//        System.out.println(sessionAttribute);
//        return "index.jsp";
//    }
}