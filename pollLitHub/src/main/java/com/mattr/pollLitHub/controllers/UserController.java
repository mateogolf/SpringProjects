package com.mattr.pollLitHub.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattr.pollLitHub.models.User;
import com.mattr.pollLitHub.models.World;
import com.mattr.pollLitHub.services.UserService;
import com.mattr.pollLitHub.services.WorldService;
import com.mattr.pollLitHub.validator.UserValidator;

@Controller
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
	private WorldService worldService;
    
	public UserController(UserService userService, UserValidator userValidator,WorldService worldService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.worldService = worldService;
	}
	@RequestMapping("/registration")
	public String viewLogReg(@Valid @ModelAttribute("user") User user,BindingResult result) {
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
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model,@Valid @ModelAttribute("user") User user,BindingResult result) {
        
    	if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
            System.out.println("Error created");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
            System.out.println("Logout created");
        }
        return "loginReg";
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
        return "redirect:/profile";
    }
    @RequestMapping("/profile")
    public String profile(Principal principal, Model model,@Valid @ModelAttribute("world") World world, BindingResult result) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
        return "profile";
    }
    @PostMapping("/profile")
    public String addWorld(Principal principal, Model model,@Valid @ModelAttribute("world") World world, BindingResult result) {
        if (!result.hasErrors()) {
        	String username = principal.getName();
            User user = userService.findByUsername(username);
            ArrayList<User> writers = new ArrayList<User>();
            writers.add(user);
            world.setWriters(writers);
        	worldService.addWorld(world);
        }
        String username = principal.getName();
        User thisUser = userService.findByUsername(username);
        model.addAttribute("currentUser",thisUser);
        model.addAttribute("isAdmin",thisUser.isAdmin());
        return "profile";
    }
    
//    @RequestMapping("/users/${userId}/unfollow/${worldId}")
//    public String unfollow(@PathVariable("userId") Long userId,@PathVariable("worldId") Long worldId) {
//    	worldService.unfollow(userId, worldId);
//    	return "redirect:/profile";
//    }
    
    
    @RequestMapping("/makeAdmin/{id}")
    public String makeAd(@PathVariable("id") Long id) {
    	userService.makeAdmin(id);
    	return "redirect:/admin";
    }
    @DeleteMapping("/users/{id}/delete")
    public String delUser(@PathVariable("id") Long id) {
    	userService.destroyUser(id);
    	return "redirect:/admin";
    }
}
