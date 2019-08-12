package com.neu.ak.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.ak.pojo.AirLiners;

public class AirLinersValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(AirLiners.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		AirLiners airplane =(AirLiners) obj;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airLineName","error.invalid.airLineName","AirLiner Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"owningCompany","error.invalid.owningCompany","Owning Company name Required");
		
		
	}

}
