package com.bankntt.businesspartner.applicationHelpers;

public class GenerateBusinessPartnerCode {
	public static String generate(String docNum, String typeBp ) {
		String newBP =(new StringBuilder()).append(typeBp).append(docNum).toString();  
		return newBP;
	}


}
