package com.bankntt.businesspartner.domain.validation.interfaces;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bankntt.businesspartner.domain.validation.classes.BusinessPartnerTypeValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = BusinessPartnerTypeValidator.class)
@Documented
public @interface BusinessPartnerTypeValidation {

	String message() default "Business Partner type not exist the valid values be: C = Company, P - People";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}