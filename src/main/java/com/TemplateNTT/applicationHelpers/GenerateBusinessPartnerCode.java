package com.TemplateNTT.applicationHelpers;

public class GenerateBusinessPartnerCode {
	public static String Generate(String _DocNum, String TypeBp ) {
		String newBP =(new StringBuilder()).append(TypeBp).append(_DocNum).toString();  
		return newBP;
	}


}
