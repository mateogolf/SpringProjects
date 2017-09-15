package com.mattr.many2many.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mattr.many2many.models.Category;
import com.mattr.many2many.models.Product;
import com.mattr.many2many.repositories.ProductRepo;

@Service
public class ProductService {
	private ProductRepo pRepo;

	public ProductService(ProductRepo pRepo) {
		this.pRepo = pRepo;
	}
	
	//all Products
	public ArrayList<Product> allProducts(){
		return (ArrayList<Product>)pRepo.findAll();
	}
	//get One
	public Product productAt(Long id) {
		Product product = pRepo.findOne(id);
		if(product != null) {
			return product;
		}else {
			System.out.println("Product not in table.");
			return null;
		}
	}
	
	//add
	public Product addProduct(Product product) {
		return pRepo.save(product);
	}
	
	//Link category to product
	public void linkCategory(Long id,Category category) {
		Product product = pRepo.findOne(id);
		if(product != null) {
			List<Category> categories = (List<Category>)product.getCategories();
			categories.add(category);
			product.setCategories(categories);
			pRepo.save(product);
		}else {
			System.out.println("Product not in table. Category not added to it");
		}
	}
}
