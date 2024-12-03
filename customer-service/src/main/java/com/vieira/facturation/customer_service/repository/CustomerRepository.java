package com.vieira.facturation.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieira.facturation.customer_service.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Customer findByName(String name);
}
