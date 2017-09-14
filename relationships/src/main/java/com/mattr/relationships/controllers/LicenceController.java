package com.mattr.relationships.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mattr.relationships.models.License;
import com.mattr.relationships.models.Person;
import com.mattr.relationships.services.LicenseService;
import com.mattr.relationships.services.PersonService;

@Controller
public class LicenceController {
//	ArrayList<String> states = new ArrayList<String>(Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
//			"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
//			"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
//			"Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
//			"New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
//			"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
//			"Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"));
	private LicenseService service;
	private PersonService pservice;
	
	public LicenceController(LicenseService service, PersonService pservice) {
		this.service = service;
		this.pservice = pservice;
	}

	@RequestMapping("/licenses/new")
	public String viewNewLicense(Model model, @Valid @ModelAttribute("license") License license, BindingResult result) {
		ArrayList<Person> persons = pservice.allDriversNoLicense();
		model.addAttribute("persons", persons);
		return "newLicense";
	}
	
	@PostMapping("/licenses/create")
	public String addLicense(Model model,@Valid @ModelAttribute("license") License license, BindingResult result) {
		if (result.hasErrors()) {
			return "newLicense";
		}else {
			service.addLicense(license);
			Person person = license.getPerson();
			return "redirect:/persons/"+person.getId();
		}
	}
}
