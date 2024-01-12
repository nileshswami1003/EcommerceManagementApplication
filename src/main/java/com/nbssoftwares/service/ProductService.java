package com.nbssoftwares.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbssoftwares.entity.Product;
import com.nbssoftwares.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id, Product product) {
		Product exProduct = getProductById(id);
		if(exProduct!=null) {
			exProduct.setName(product.getName());
			exProduct.setPrice(product.getPrice());
			return productRepository.save(product);
		}
		return null;
	}
	
	public void deleteProduct(Long id) {
		Product exProduct = getProductById(id);
		productRepository.delete(exProduct);
	}
}
