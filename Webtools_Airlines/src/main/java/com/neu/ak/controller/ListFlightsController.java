package com.neu.ak.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ak.dao.ListFlightsDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;

@Controller
@RequestMapping(value="/listflights.htm")
public class ListFlightsController {

	@Autowired
	@Qualifier("listRoutesDAO")
	ListFlightsDAO listRoutesDAO;
	
	
	@RequestMapping(value = "/listflights.htm", method = RequestMethod.POST)
	public String initializeForm(@ModelAttribute("flightDetails") AirLinerFlights flightDetails, HttpServletRequest request) throws UserException, ParseException
	{
		HttpSession session = request.getSession();
		String departureAirport = request.getParameter("departureAirport");
		departureAirport = departureAirport.replaceAll("[^A-Za-z]+$", "");
		String arrivalAirport = request.getParameter("arrivalAirport");
		arrivalAirport = arrivalAirport.replaceAll("[^A-Za-z]+$", "");
		String departureDate = request.getParameter("departureDate");
		try{
			int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
			session.setAttribute("numberOfSeats", numberOfSeats);
			
		List<String> flightlist = listRoutesDAO.listFlights(departureAirport, arrivalAirport, departureDate,numberOfSeats);
		System.out.println("nmber of seats selected :: "+numberOfSeats);
		session.setAttribute("flightlist", flightlist);
	}
		catch(UserException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		return "flightList";
	}
	
}
