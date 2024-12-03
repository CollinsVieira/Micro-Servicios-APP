package com.vieira.facturation.product_service.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vieira.facturation.product_service.entity.Product;
import com.vieira.facturation.product_service.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
				Pageable page = PageRequest.of(pageNumber, pageSize);
				List<Product> data = service.findAll(page);
				return new ResponseEntity<>(data,HttpStatus.OK);	
	}
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product data){
		Product dat = service.save(data);
		return new ResponseEntity<>(dat,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id){
		Product dato = service.findById(id);
		return new ResponseEntity<>(dato,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product reg){
		Product dat = service.save(reg);
		return new ResponseEntity<>(dat,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		service.delete(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
}
