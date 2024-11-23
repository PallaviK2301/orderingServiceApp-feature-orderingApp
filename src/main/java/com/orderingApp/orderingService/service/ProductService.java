package com.orderingApp.orderingService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderingApp.orderingService.model.Product;
import com.orderingApp.orderingService.repository.ProductRepository;

@Service
public class ProductService {

	 @Autowired
	    private ProductRepository productRepository;

	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    public Optional<Product> getProductById(int id) {
	        return productRepository.findById(id);
	    }

	    public Product createProduct(String jwtToken,Product product) {
	        return productRepository.save(product);
	    }

	    public Product updateProduct(int id, Product updatedProduct) {
	        if (productRepository.existsById(id)) {
	            updatedProduct.setId(id);
	            return productRepository.save(updatedProduct);
	        }
	        return null;
	    }

	    public boolean checkStock(int productId, int quantity) {
	        Optional<Product> product = productRepository.findById(productId);
	        if (product.isPresent()) {
	            return product.get().getStockQuantity() >= quantity;
	        }
	        return false;
	    }

	    public void placeOrder(int productId, int quantity) {
	        Optional<Product> product = productRepository.findById(productId);
	        if (product.isPresent() && checkStock(productId, quantity)) {
	            Product p = product.get();
	            p.setStockQuantity(p.getStockQuantity() - quantity);
	            productRepository.save(p);
	        }
	    }
}
