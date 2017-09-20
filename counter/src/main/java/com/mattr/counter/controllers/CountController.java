package com.mattr.counter.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("counter")
public class CountController {
	@ModelAttribute("counter")
    public int getSessionAttribute(){
        return 0;
    }//The hard way is commented out and NOT using @ModelAttribute as parameter
	@RequestMapping("/")
    public String index(Model model,@ModelAttribute("counter") int counter) {//HttpSession session) {
//		if(session.getAttribute("counter") == null) {
//			session.setAttribute("counter", 1);
//			System.out.println(session.getAttribute("counter"));
//		}else {
			int newCount = counter + 1;
			model.addAttribute("counter", newCount);
//		}
        return "forward:/index.html";
    }
	@RequestMapping("/counter")
    public String setSession(Model model,@ModelAttribute("counter") int counter) {//,HttpSession session){
//		if(session.getAttribute("counter") == null) {
//			return "redirect:/";
//			model.addAttribute("counter", 0);
//		}else {
//			model.addAttribute("counter", (int)session.getAttribute("counter"));
//		}
        return "count";
    }
	@RequestMapping("/reset")
	public String resetAttribute(Model model){
		model.addAttribute("counter",0);
		return "redirect:/";
    }

}
