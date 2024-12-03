package com.vieira.facturation.product_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vieira.facturation.product_service.entity.Product;

public interface ProductService {
	public List<Product> findAll(Pageable page);
	public Product findByName(String name,Pageable page);
	public Product findById(int id);
	public Product save(Product obj);
	public boolean delete(int id);
}
