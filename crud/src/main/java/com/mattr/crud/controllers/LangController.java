package com.mattr.crud.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mattr.crud.models.Language;
import com.mattr.crud.services.CRUDService;

@Controller
@RequestMapping("/languages")
public class LangController {
	private final CRUDService crud;
	
	public LangController(CRUDService crud) {
		this.crud = crud;
	}
	
	@RequestMapping("")
	public String index(Model model,@Valid @ModelAttribute("language") Language language, BindingResult result) {
		ArrayList<Language> languages = crud.allLang();
        model.addAttribute("languages", languages);
		return "index.jsp";
	}
	
	//view
	@RequestMapping("/{id}")
	public String showLang(Model model, @PathVariable("id") Long index) {
		Language language = crud.langAt(index);
		model.addAttribute("language", language);
		model.addAttribute("id", index);
		return "showLang.jsp";
	}
	//create
	@PostMapping("/create")
	public String createLang(@Valid @ModelAttribute("language") Language language, BindingResult result,Model model) {
        if (result.hasErrors()) {
        	System.out.println("There were errors");
        	return "redirect:/languages";
        }else{
        	crud.addLang(language);
            return "redirect:/languages";
        }
	}
	//view edit
	@RequestMapping("/edit/{id}")
	public String editLang(Model model,@PathVariable("id") Long index) {
		Language language = crud.langAt(index);
		if (language != null){
			model.addAttribute("language", language);
            return "editLang.jsp";
		} else {
			return "redirect:/languages";
		}
	}
	//Update
	@PostMapping("/edit/{id}")
	public String updateLang(@PathVariable("id") Long id,@Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
            return "editLang.jsp";
        }else{
            crud.updateLang(id, language);
            return "redirect:/languages";
        }
	}
	//destroy
	@RequestMapping("/delete/{id}")
	public String deleteLang(@PathVariable("id") Long id) {
		crud.destroyLang(id);
		return "redirect:/languages";
		
	}
}
