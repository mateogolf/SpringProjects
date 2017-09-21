package com.mattr.events.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mattr.events.models.Event;
import com.mattr.events.models.User;
import com.mattr.events.services.EventsMessagesService;
import com.mattr.events.services.UserService;
import com.mattr.events.validator.UserValidator;

@Controller
@SessionAttributes("stateList")
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
	private EventsMessagesService service;
    
	public UserController(UserService userService, UserValidator userValidator,EventsMessagesService service) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.service = service;
	}
	@ModelAttribute("stateList")
    public String[] getStateList(){
		String[] stateList = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
		return stateList;
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
	    if(admins.size()==0 || admins==null) {
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
        
        model.addAttribute("isAdmin",user.isAdmin());
        userService.updateUser(user);
        if(user.isAdmin()) {
        	return "redirect:/admin";
        }
        return "redirect:/events";
    }
    @RequestMapping("/events")
    public String home(Principal principal, Model model,@Valid @ModelAttribute("event") Event event, BindingResult result) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
        //localEvents
        ArrayList<Event> localEvents = service.localEvents(user.getState());
        model.addAttribute("localEvents",localEvents);
        //otherEvents
        ArrayList<Event> otherEvents = service.otherEvents(user.getState());
//        ArrayList<String> state = (ArrayList<String>) Arrays.asList(user.getState());
//        ArrayList<Event> otherEvents = service.otherEvents(state);
        model.addAttribute("otherEvents",otherEvents);
        
        return "homePage";
    }
    @PostMapping("/events")
    public String createEvent(Principal principal, Model model,@Valid @ModelAttribute("event") Event event, BindingResult result) {
        //User Information
    	String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
        //Check Form
        if (!result.hasErrors()) {
    		service.addEvent(event);
    	}       
        
        
        //localEvents
        ArrayList<Event> localEvents = service.localEvents(user.getState());
        model.addAttribute("localEvents",localEvents);
        //otherEvents
        ArrayList<Event> otherEvents = service.otherEvents(user.getState());
//        ArrayList<String> state = (ArrayList<String>) Arrays.asList(user.getState());
//        ArrayList<Event> otherEvents = service.otherEvents(state);
        model.addAttribute("otherEvents",otherEvents);
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
