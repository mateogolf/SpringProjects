package com.mattr.dojoninjas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mattr.dojoninjas.models.Dojo;
import com.mattr.dojoninjas.models.Ninja;
import com.mattr.dojoninjas.services.DojoNinjasService;

@Controller
public class DojoNinjaController {
	private DojoNinjasService service;

	public DojoNinjaController(DojoNinjasService service) {
		this.service = service;
	}
	
	//View Dojo Form "/dojos/new"
	@RequestMapping("/dojos/new")
	public String newDojoForm(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		return "newDojo";
	}
	
	//Post Dojo Form "/dojos/new"
	@PostMapping("/dojos/new")
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "newDojo";
		}else {
			service.addDojo(dojo);
			return "redirect:/ninjas/new";
		}
	}
	//View Ninja Form "/ninjas/new"
	@RequestMapping("/ninjas/new")
	public String newNinjaForm(Model model,@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		ArrayList<Dojo> dojos = (ArrayList<Dojo>)service.allDojos();
		model.addAttribute("dojos", dojos);
		return "newNinja";
	}
	
	//Post Ninja Form "/ninjas/new"
	@PostMapping("/ninjas/new")
	public String createNinja(Model model,@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if (result.hasErrors()) {
			ArrayList<Dojo> dojos = (ArrayList<Dojo>)service.allDojos();
			model.addAttribute("dojos", dojos);
			return "newNinja";
		}else {
			service.addNinja(ninja);
			return "redirect:/dojos/"+ninja.getDojo().getId();
		}
	}
	//View Dojo and it's ninjas "/dojos/{id}"
	@RequestMapping("/dojos/{id}")
	public String viewDojo(Model model,@PathVariable("id") Long id) {
		Dojo dojo = service.dojoAt(id);
		model.addAttribute("dojo",dojo);
		ArrayList<Object[]> table = service.joinDojosAndNinjas2();
		model.addAttribute("table",table);
//		ArrayList<Dojo> dojos = service.findAllDojosByNinjaCountDesc();
//		
//		model.addAttribute("dojos",dojos);
		return "viewDojo";
	}
		//use Dojo.getNinjas(); for model.addAttribute("ninjas",ninjas);
}
