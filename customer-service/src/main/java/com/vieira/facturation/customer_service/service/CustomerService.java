package com.vieira.facturation.customer_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vieira.facturation.customer_service.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll(Pageable page);
	public Customer findByName(String name,Pageable page);
	public Customer findById(int id);
	public Customer save(Customer obj);
	public boolean delete(int id);
}
