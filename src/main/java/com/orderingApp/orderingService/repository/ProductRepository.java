package com.orderingApp.orderingService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderingApp.orderingService.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findById(Long id);

	boolean existsById(Long id);

}
