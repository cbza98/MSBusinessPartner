package com.bankntt.businesspartner.domain.Entity;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="BusinessPartner")
public class BusinessPartner {
	@Id
	private String codeBP;
	
	@NotNull
	@Pattern(regexp = "/00|01|04|07|06|A/i", message = "Business Partner Document type not exist the valid values be: 00=OTROS TIPOS DE DOCUMENTOS, 01=DOCUMENTO NACIONAL DE IDENTIDAD (DNI), 04=CARNET DE EXTRANJERIA, 06=REGISTRO UNICO DE CONTRIBUYENTES, 07=PASAPORTE, A=CEDULA DIPLOMATICA")
	private String docType;
	
	@NotBlank
	@Size(max=19)
	private String docNum;
	
	@NotBlank
	@Size(max=100)
	private String name;
	
	@NotNull
	@Size(max=1, message ="Business Partner type max length is 1")
	@Pattern(regexp = "[cpCP]", message = "Business Partner type not exist the valid values be: C = Company, P - People")
	
	private String type;
	
	
	@Size(max=20)
	private String telephone1;
	
	@Size(max=20)
	private String telephone2;
	
	@Size(max=90)
	private String contactPerson;	
	
	@Digits(integer = 19,fraction = 6)	
	private BigDecimal creditCardLine;
	
	@Digits(integer = 19,fraction = 6)
	private BigDecimal creditLine;
	
	@Digits(integer = 19,fraction = 0)
	private BigDecimal creditCard;
	
	@Digits(integer = 19,fraction = 6)
	private BigDecimal debitLine;
	
	@Digits(integer = 19,fraction = 0)
	private BigDecimal debitCard;
	
	@Email
	private String email;
	
	private Boolean valid;

}
