package com.mattr.pollLitHub.controllers;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mattr.pollLitHub.models.Follower;
import com.mattr.pollLitHub.models.User;
import com.mattr.pollLitHub.models.World;
import com.mattr.pollLitHub.services.UserService;
import com.mattr.pollLitHub.services.WorldService;
import com.mattr.pollLitHub.validator.UserValidator;

@Controller
@RequestMapping("/worlds")
public class WorldController {
	private UserService userService;
	private UserValidator userValidator;
	private WorldService service;
    
	public WorldController(UserService userService, UserValidator userValidator,WorldService service) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.service = service;
	}
	//1)View: All
	@RequestMapping("")
	public String index(Principal principal,Model model,@Valid @ModelAttribute("world") World world, BindingResult result) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
		
		ArrayList<World> worlds = service.allWorlds();
        model.addAttribute("worlds", worlds);
		return "allWorlds";
	}
	//2)Show One
	@RequestMapping("/{id}")
	public String viewWorld(Principal principal,Model model,@PathVariable("id") Long id) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
		model.addAttribute("world", service.worldAt(id));
		return "viewWorld";
	}

	//3)GET: New
	@RequestMapping("/new")
	public String newWorld(Principal principal,Model model,@Valid @ModelAttribute("world") World world, BindingResult result){
		String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("currentUser",user);
        model.addAttribute("isAdmin",user.isAdmin());
		
		return "newWorld";
	}

	//4)POST: Create
	@PostMapping("/new")
	public String createWorld(Principal principal,Model model,@Valid @ModelAttribute("world") World world, BindingResult result) {
		if (result.hasErrors()) {
			return "form";
		}else{
			//Add Entry of World
			World saved = service.addWorld(world);
			//go to dashboard OR to the new entry's page
//			return "redirect:/dashboard";//go to dashboard
			return "redirect:/worlds/"+ saved.getId(); //new entry's page
		}
	}


	//5)GET: Edit
	@RequestMapping("/{id}/edit")
	public String editBook(Principal principal,@PathVariable("id") Long id, Model model,@Valid @ModelAttribute("world") World world, BindingResult result) {
		World currentWorld = service.worldAt(id);
		if (currentWorld != null){
			String username = principal.getName();
	        User user = userService.findByUsername(username);
	        //Make sure the logged in user is a writer
	        if(!service.isWriter(user.getId(), currentWorld)) {
	        	return "redirect:/profile";
	        }
	        model.addAttribute("currentUser",user);
	        model.addAttribute("isAdmin",user.isAdmin());
			model.addAttribute("world", currentWorld);
			return "editWorld";
		}else{
			return "redirect:/worlds/new";
		}
	 }
	//6)POST: Update
	@PostMapping("/{id}/edit")
	public String updateWorld(Principal principal,Model model,@PathVariable("id") int id, @Valid @ModelAttribute("world") World world, BindingResult result) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        if(!service.isWriter(user.getId(), world)) {
        	return "redirect:/profile";
        }
        if (result.hasErrors()) {
        	model.addAttribute("currentUser",user);
	        model.addAttribute("isAdmin",user.isAdmin());
			return "editWorld";
		}else{
            ArrayList<User> writers = new ArrayList<User>();
            writers.add(user);
            world.setWriters(writers);
        	service.addWorld(world);
			return "redirect:/profile";
		}
	}

	//7)Destroy
	@RequestMapping("/{id}/delete")
	public String deleteWorld(Principal principal,@PathVariable("id") Long id) {
		World currentWorld = service.worldAt(id);
		if (currentWorld != null){
			String username = principal.getName();
	        User user = userService.findByUsername(username);
	        if(!service.isWriter(user.getId(), currentWorld)) {
	        	return "redirect:/profile";
	        }
			service.destroyWorld(id);
			return "redirect:/worlds";
		}
		return "redirect:/profile";
	}
	//Search
	@PostMapping("/search")
	public String redirect(@RequestParam(value="search") String search) {
		return "redirect:/worlds/search/"+ search;
	}
	@RequestMapping("/search/{search}")
	public String viewResults(Model model, @PathVariable("search") String search, RedirectAttributes redirectAttributes) {
		ArrayList<World> results = service.worldsBy(search);
		model.addAttribute("search", search);
		if(results.size() == 0) {
			redirectAttributes.addFlashAttribute("errors", "NO Results found!!");
			return "searchResults";
		}else {
			model.addAttribute("results", results);
			return "searchResults";
		}
	}
	//Follow World
	@RequestMapping("/{id}/follow")
	public String followWorld(Principal principal,Model model,@PathVariable("id") Long worldId) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        World world = service.worldAt(worldId);
        if(world!=null) {
        	service.follow(user, world);
        }
        return "redirect:/profile";
	}
	@RequestMapping("/{id}/unfollow")
	public String unfollow(Principal principal,Model model,@PathVariable("id") Long id) {
		String username = principal.getName();
        User user = userService.findByUsername(username);
        Follower follower = service.followerAt(id);
        //if follower exists &&( the logged in user is the follower || user is one of the writers for the world)
        if(follower!=null && follower.getUser().equals(user) ) {//&& follower.getWorld().isWriter(user)) {
//        World world = service.worldAt(worldId);
//        if(world!=null) {
//        	ArrayList<Follower> follows = service.followerAt(user, world);
//        	if(follows!=null) {
//        		for(Follower follower:follows) {
        			service.destroyFollower(id);
//        		}
//        	}
//        }
        }
        return "redirect:/profile";
	}
}
