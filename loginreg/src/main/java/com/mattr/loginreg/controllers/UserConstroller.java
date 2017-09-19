package com.mattr.loginreg.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattr.loginreg.models.Role;
import com.mattr.loginreg.models.User;
import com.mattr.loginreg.services.UserService;
import com.mattr.loginreg.validator.UserValidator;

@Controller
public class UserConstroller {
	private UserService service;
	private UserValidator validator;
	public UserConstroller(UserService service, UserValidator validator) {
		this.service = service;
		this.validator = validator;
	}
	
	@RequestMapping(value= {"/login","/registration"})
	public String viewLogReg(@Valid @ModelAttribute("user") User user) {
		return "loginReg";
	}
	@PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		validator.validate(user, result);
	
	    
	    if (result.hasErrors()) {
	        return "loginReg";
	    }
	    List<User> admins = service.allAdmins();
	    if(admins.size()==0) {
	    	System.out.println("No admins, new user will be admin");
	    	service.saveUserWithAdminRole(user);
	    	return "redirect:/admin";
	    }else {
	    	System.out.println("Regular USER!!!");
	    	service.saveWithUserRole(user);
	    	return "redirect:/";
	    }
    }
	
    @PostMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "redirect:/";
    }
    @RequestMapping(value= {"/","/dashboard"})
    public String openDash(Principal principal,Model model) {
    	String email = principal.getName();
    	User user = service.findByEmail(email);
        model.addAttribute("currentUser", user);
        service.updateUser(user);
        return "dash";
    }
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
    	String email = principal.getName();
    	User user = service.findByEmail(email);
        model.addAttribute("currentUser", user);
        ArrayList<User> users = service.allUsers();
        model.addAttribute("users", users);
        service.updateUser(user);
        return "adminPage";
    }
    @RequestMapping("/makeAdmin/{id}")
    public String makeAd(@PathVariable("id") Long id) {
    	service.makeAdmin(id);
    	return "redirect:/admin";
    }
    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id) {
    	service.destroyUser(id);
    	return "redirect:/admin";
    }
}
