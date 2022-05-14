package com.bankntt.businesspartner.domain.validation.interfaces;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bankntt.businesspartner.domain.validation.classes.DocTypeValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DocTypeValidator.class)
@Documented
public @interface DocTypeValidation{
	
	String message() default "Business Partner Document type not exist the valid values be: 00=OTROS TIPOS DE DOCUMENTOS, 01=DOCUMENTO NACIONAL DE IDENTIDAD (DNI), 04=CARNET DE EXTRANJERIA, 06=REGISTRO UNICO DE CONTRIBUYENTES, 07=PASAPORTE, A=CEDULA DIPLOMATICA";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
