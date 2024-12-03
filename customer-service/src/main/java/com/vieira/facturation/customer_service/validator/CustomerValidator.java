package com.vieira.facturation.customer_service.validator;

import com.vieira.facturation.customer_service.entity.Customer;
import com.vieira.facturation.customer_service.exception.ValidateServiceException;

public class CustomerValidator {
	public static void save(Customer obj) {
		if (obj.getName()==null || obj.getName().trim().isEmpty()) {
			throw new ValidateServiceException("El campo nombre es obligatorio");
		}
	}
}
