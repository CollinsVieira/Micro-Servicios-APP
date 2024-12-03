package com.vieira.facturation.product_service.validator;

import com.vieira.facturation.product_service.entity.Product;
import com.vieira.facturation.product_service.exception.ValidateServiceException;

public class ProductValidator {
	public static void save(Product obj) {
		if (obj.getName()==null || obj.getName().trim().isEmpty()) {
			throw new ValidateServiceException("El campo nombre es obligatorio");
		}
		if (obj.getName().length()>=100) {
			throw new ValidateServiceException("El nombre debe tener menos de 100 caracteres");
		}
		if (obj.getPrice() == null) {
			throw new ValidateServiceException("El campo precio es obligatorio");
		}
	}
}
