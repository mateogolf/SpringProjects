package com.mattr.relationships.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mattr.relationships.models.License;
import com.mattr.relationships.models.Person;
import com.mattr.relationships.services.PersonService;


@Controller
public class PersonController {
	private PersonService service;
	
	
	public PersonController(PersonService service) {
		this.service = service;
	}
	@RequestMapping("/persons/new")
	public String viewNewPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		return "newPerson";
	}
	@PostMapping("/persons/create")
	public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "newPerson";
		}else {
			service.addPerson(person);
			return "redirect:/licenses/new";
		}
	}
	
	
	//View New License
	@RequestMapping("/persons/{id}")
	public String viewPerson(@PathVariable("id") Long id, Model model) {
		Person person = service.personAt(id);
		model.addAttribute("person", person);
		return "viewProfile";
	}
	
}
