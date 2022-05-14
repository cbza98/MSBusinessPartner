package com.bankntt.businesspartner.domain.validation.classes;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bankntt.businesspartner.domain.validation.interfaces.DocTypeValidation;

public class DocTypeValidator implements ConstraintValidator<DocTypeValidation, String> {

	List<String> bp = Arrays.asList("00","01","04","07","06","A");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		return bp.contains(value);

	}
}