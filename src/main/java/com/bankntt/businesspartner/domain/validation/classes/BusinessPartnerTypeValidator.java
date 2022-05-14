package com.bankntt.businesspartner.domain.validation.classes;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bankntt.businesspartner.domain.validation.interfaces.BusinessPartnerTypeValidation;

public class BusinessPartnerTypeValidator implements ConstraintValidator<BusinessPartnerTypeValidation, String> {

	List<String> bp = Arrays.asList("P","C");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return bp.contains(value);

	}
}
