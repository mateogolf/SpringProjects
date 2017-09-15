package com.mattr.many2many.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mattr.many2many.models.Category;
import com.mattr.many2many.models.Product;
import com.mattr.many2many.services.CategoryService;
import com.mattr.many2many.services.ProductService;
@Controller
@RequestMapping("/categories")
public class CatController {
	private CategoryService service;
	private ProductService pService;

	public CatController(CategoryService service, ProductService pService) {
		this.service = service;
		this.pService = pService;
	}
	//View Form "new"
	@RequestMapping("new")
	public String viewForm(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		return "newCategory";
	}
	//Add "new"
	@PostMapping("new")
	public String createCat(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			return "newCategory";
		}else {
			Category saved = service.addCategory(category);
			return "redirect:/categories/"+saved.getId();
		}
	}
	//View Page "{id}"
	@RequestMapping("{id}")
	public String categoryPage(Model model,@PathVariable("id") Long id) {
		model.addAttribute("category", service.categoryAt(id));//given category
		model.addAttribute("products", pService.allProducts());//all products for dropdown
		return "viewCategory";
	}
	
	//Link a category to a product
	@PostMapping("{id}")
	public String linkProduct(Model model,@PathVariable("id") Long id, @RequestParam(value="productId") Long productId) {
		Product product = pService.productAt(productId);
		service.linkCategory(id, product);
		return "redirect:/products/"+id;
	}

}
