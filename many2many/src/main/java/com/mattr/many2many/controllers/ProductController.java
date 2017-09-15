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
@RequestMapping("/products")
public class ProductController {
	private ProductService service;
	private CategoryService cService;
	
	public ProductController(ProductService service, CategoryService cService) {
		this.service = service;
		this.cService = cService;
	}
	//View Form "new"
	@RequestMapping("new")
	public String viewForm(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		return "newProduct";
	}
	//Add "new"
	@PostMapping("new")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors()) {
			return "newProduct";
		}else {
			Product saved = service.addProduct(product);
			return "redirect:/products/"+saved.getId();
		}
	}
	//View Page "{id}"
	@RequestMapping("{id}")
	public String productPage(Model model,@PathVariable("id") Long id) {
		model.addAttribute("product", service.productAt(id));//given product
		model.addAttribute("categories", cService.allCategories());//all categories for dropdown
		return "viewProduct";
	}
	
	//Link a category to a product
	@PostMapping("{id}")
	public String linkCategory(Model model,@PathVariable("id") Long id, @RequestParam(value="categoryId") Long categoryId) {
		Category category = cService.categoryAt(categoryId);
		service.linkCategory(id, category);
		return "redirect:/products/"+id;
	}
	
}
