package com.neu.ak.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.ak.pojo.AirLinerFlights;

public class RouteValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		
		return aClass.equals(AirLinerFlights.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
		AirLinerFlights af = (AirLinerFlights)obj;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airLineId","error.invalid.airLineId","Airliner id is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureAirport","error.invalid.airLineId","Departure Airport is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalAirport","error.invalid.arrivalAirport","Arrival Airport is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureTime","error.invalid.departureTime","Departure Time is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalTime","error.invalid.arrivalTime","Arrival Time is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"numberOfSeats","error.invalid.travelClass","Travel Class is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureDate","error.invalid.totalSeats","Total Seats is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalDate","error.invalid.availableSeats","Available Seats is mandatory");
		
	}

	
	
}
