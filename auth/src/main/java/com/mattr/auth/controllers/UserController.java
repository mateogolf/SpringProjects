package com.mattr.auth.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattr.auth.models.User;
import com.mattr.auth.services.UserService;
import com.mattr.auth.validator.UserValidator;

@Controller
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
    
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}

//	@RequestMapping("/registration")
//    public String registerForm(@Valid @ModelAttribute("user") User user) {
//        return "registrationPage";
//    }
//	@RequestMapping("/login")
//    public String login() {
//        return "loginPage";
//    }
	@RequestMapping(value= {"/login","/registration"})
	public String viewLogReg(@Valid @ModelAttribute("user") User user) {
		return "loginReg";
	}
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		
	    if (result.hasErrors()) {
	        return "loginReg";
	    }
	    List<User> admins = userService.allAdmins();
	    if(admins.size()==0) {
	    	System.out.println("No admins, new user will be admin");
	    	userService.saveUserWithAdminRole(user);
	    	return "redirect:/admin";
	    }else {
	    	System.out.println("Regular USER Created!!!");
	    	userService.saveWithUserRole(user);
	    	return "redirect:/";
	    }
    }
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
    	String username = principal.getName();
    	User user = userService.findByUsername(username);
        model.addAttribute("currentUser", user);
        ArrayList<User> users = userService.allUsers();
        model.addAttribute("users", users);
        userService.updateUser(user);
        return "adminPage";
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
    
    @RequestMapping("/")
    public String afterLogin(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        System.out.println(user.isAdmin());
        model.addAttribute("isAdmin",user.isAdmin());
        userService.updateUser(user);
        if(user.isAdmin()) {
        	return "redirect:/admin";
        }
        return "homePage";
    }
    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
        userService.updateUser(user);
        return "homePage";
    }
    @RequestMapping("/makeAdmin/{id}")
    public String makeAd(@PathVariable("id") Long id) {
    	userService.makeAdmin(id);
    	return "redirect:/admin";
    }
    @DeleteMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id) {
    	userService.destroyUser(id);
    	return "redirect:/admin";
    }
}
