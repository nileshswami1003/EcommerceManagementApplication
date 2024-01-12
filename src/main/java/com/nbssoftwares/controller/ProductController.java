package com.nbssoftwares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbssoftwares.entity.Product;
import com.nbssoftwares.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String getAllProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products",products);
		return "all-products";
	}
	
	@GetMapping("/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping("/add")
	public String addProduct(@ModelAttribute Product product) {
		productService.addProduct(product);
		return "redirect:/products"; // user when want to redirect using request instead page name
	}
	
	@GetMapping("/edit/{id}")
	public String showEditProductForm(@PathVariable Long id, Model model) {
		Product exProduct = productService.getProductById(id);
		model.addAttribute("product", exProduct);
		return "edit-product";
	}
	
	@PostMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
		productService.updateProduct(id, product);
		return "redirect:/products"; // user when want to redirect using request instead page name
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/products"; // user when want to redirect using request instead page name
	}
}
