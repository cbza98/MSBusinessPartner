package com.bankntt.businesspartner.domain.Entity;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bankntt.businesspartner.domain.validation.interfaces.BusinessPartnerTypeValidation;
import com.bankntt.businesspartner.domain.validation.interfaces.DocTypeValidation;

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
@Document(collection = "BusinessPartner")
public class BusinessPartner {
	@Id
	private String id;
	
	@NotNull
	@DocTypeValidation
	private String docType;
	
	@NotBlank
	@Size(max=19)
	private String docNum;
	
	@NotBlank
	@Size(max=100)
	private String name;
	
	@NotNull
	@BusinessPartnerTypeValidation	
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
