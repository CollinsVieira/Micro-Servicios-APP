package com.vieira.facturation.customer_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vieira.facturation.customer_service.entity.Customer;
import com.vieira.facturation.customer_service.exception.GeneralServiceException;
import com.vieira.facturation.customer_service.exception.NoDataFoundException;
import com.vieira.facturation.customer_service.exception.ValidateServiceException;
import com.vieira.facturation.customer_service.repository.CustomerRepository;
import com.vieira.facturation.customer_service.service.CustomerService;
import com.vieira.facturation.customer_service.validator.CustomerValidator;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll(Pageable page) {
		try {
			List<Customer> data = repository.findAll(page).toList();
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Customer findByName(String name, Pageable page) {
		try {
			Customer data = repository.findByName(name);
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Customer findById(int id) {
		try {
			Customer data = repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Customer save(Customer obj) {
		try {
			CustomerValidator.save(obj);
			if(obj.getId()==0) {
				Customer data = repository.save(obj);
				return data;
			}
			else {
				Customer data = findById(obj.getId());
				data.setName(obj.getName());
				data.setAddress(obj.getAddress());
				data.setEmail(obj.getEmail());
				data.setPhone(obj.getPhone());
				return repository.save(data);				
			}
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			Customer data = findById(id);
			repository.delete(data);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
