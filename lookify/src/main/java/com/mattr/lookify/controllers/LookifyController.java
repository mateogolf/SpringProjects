package com.mattr.lookify.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mattr.lookify.models.Song;
import com.mattr.lookify.services.LookifyService;

@Controller
public class LookifyController {
	private LookifyService lookie;

	public LookifyController(LookifyService lookie) {
		this.lookie = lookie;
	}
	
	//Welcome Page
	@RequestMapping("/")
	public String index() {
		return "welcome";
	}
	//Dashboard "/dashboard"
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		ArrayList<Song> songs = (ArrayList<Song>)lookie.allSongs();
		model.addAttribute("songs", songs);
		return "dash";
	}
//	
//	//Search results: "/search"
	@PostMapping("/search")
	public String redirect(@RequestParam(value="search") String search) {
		return "redirect:/search/"+ search;
	}
	
	@RequestMapping("/search/{search}")
	public String viewResults(Model model, @PathVariable("search") String search, RedirectAttributes redirectAttributes) {
		ArrayList<Song> results = lookie.songsBy(search);
		model.addAttribute("search", search);
		if(results.size() == 0) {
			redirectAttributes.addFlashAttribute("errors", "NO Results found!!");
			return "searchResults";
		}else {
			model.addAttribute("results", results);
			return "searchResults";
		}
	}
	
	//Top10: "/top10"
	@RequestMapping("/top10")
	public String top10(Model model) {
		ArrayList<Song> results = lookie.top10();
		model.addAttribute("results", results);
		return "topTen";
	}
	//View one "/song/{id}"
	@RequestMapping("/song/{id}")
	public String viewSong(@PathVariable("id") Long id,Model model) {
		model.addAttribute("song", lookie.songAt(id));
		return "viewSong";
	}
	
	//Add new: "/song/new"==>newSong.jsp
	@RequestMapping("/song/new")
	public String newSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
		return "editSong";
	}
	@PostMapping("/song/create")
	public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
//			ArrayList<ObjectError> errors = (ArrayList<ObjectError>)result.getAllErrors();
//			int count = 1;
//			for(ObjectError error:errors) {
//				redirectAttributes.addFlashAttribute("errors"+ count, error.getDefaultMessage());
//				count++;
//			}
            return "editSong";
        }else{
        	lookie.addSong(song);
        	return "redirect:/dashboard";
        }
	}
	//View edit page: "/song/edit/{id}"==>newSong.jsp w/ song as attribute
	
	//Delete Song: "/delete/{id}"
	@RequestMapping("/delete/{id}")
	public String destroy(@PathVariable("id") Long id) {
		lookie.destorySong(id);
		return "redirect:/dashboard";
	}
}
