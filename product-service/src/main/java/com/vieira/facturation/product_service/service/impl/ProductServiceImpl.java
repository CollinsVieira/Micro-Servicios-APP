package com.vieira.facturation.product_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vieira.facturation.product_service.entity.Product;
import com.vieira.facturation.product_service.exception.GeneralServiceException;
import com.vieira.facturation.product_service.exception.NoDataFoundException;
import com.vieira.facturation.product_service.exception.ValidateServiceException;
import com.vieira.facturation.product_service.repository.ProductRepository;
import com.vieira.facturation.product_service.service.ProductService;
import com.vieira.facturation.product_service.validator.ProductValidator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll(Pageable page) {
		try {
			List<Product> data = repository.findAll(page).toList();
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Product findByName(String name, Pageable page) {
		try {
			Product data = repository.findByName(name);
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Product findById(int id) {
		try {
			Product data = repository.findById(id)
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
	public Product save(Product obj) {
		try {
			ProductValidator.save(obj);
			if(obj.getId()==0) {
				Product data = repository.save(obj);
				return data;
			}
			else {
				Product data = findById(obj.getId());
				data.setName(obj.getName());
				data.setCode(obj.getCode());
				data.setPrice(obj.getPrice());
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
			Product data = findById(id);
			repository.delete(data);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
