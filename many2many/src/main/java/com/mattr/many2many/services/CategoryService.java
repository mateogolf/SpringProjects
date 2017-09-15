package com.mattr.many2many.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mattr.many2many.models.Category;
import com.mattr.many2many.models.Product;
import com.mattr.many2many.repositories.CategoryRepo;

@Service
public class CategoryService {
	private CategoryRepo cRepo;

	public CategoryService(CategoryRepo cRepo) {
		this.cRepo = cRepo;
	}
	//all Categories
	public ArrayList<Category> allCategories(){
		return (ArrayList<Category>)cRepo.findAll();
	}
	//get One
	public Category categoryAt(Long id) {
		Category category = cRepo.findOne(id);
		if(category != null) {
			return category;
		}else {
			System.out.println("Category not in table.");
			return null;
		}
	}
	//add new Category
	public Category addCategory(Category category) {
		return cRepo.save(category);
	}
	//Link product to category
	public void linkCategory(Long id,Product product) {
		Category category = cRepo.findOne(id);
		if(category != null) {
			List<Product> products = (List<Product>)category.getProducts();
			products.add(product);
			category.setProducts(products);
			cRepo.save(category);
		}else {
			System.out.println("Category not in table. Product not added to it");
		}
	}
	
}
