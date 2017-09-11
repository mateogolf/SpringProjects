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
    }
	@RequestMapping("/")
    public String index(Model model,HttpSession session,@ModelAttribute("counter") int sessionAttribute) {
		int newCount = (int)session.getAttribute("counter") + 1;
		System.out.println(newCount);
		model.addAttribute("counter", newCount);
        return "forward:/index.html";
    }
	@RequestMapping("/counter")
    public String setSession(Model model,HttpSession session,@ModelAttribute("counter") int sessionAttribute){
//		int newCount = (int)session.getAttribute("counter") + 1;
//		System.out.println(newCount);
//		model.addAttribute("counter", newCount);
        return "count.jsp";
    }
//	@ModelAttribute("/reset")
	public String resetAttribute(Model model, HttpSession session){
//		getSessionAttribute();
		session.invalidate();
//		session.setAttribute("counter", 0);
//		session.removeAttribute("sessionAttribute");
		return "forward:/index.html";
    }

}
