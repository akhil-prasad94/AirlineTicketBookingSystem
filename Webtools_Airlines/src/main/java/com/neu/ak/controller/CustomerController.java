package com.neu.ak.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.ak.dao.CustomerDAO;
import com.neu.ak.dao.LoginDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.pojo.Passenger;
import com.neu.ak.pojo.Ticket;
import com.neu.ak.pojo.User;

@Controller
@RequestMapping(value="/*customer*.htm")
public class CustomerController {

	@Autowired
	@Qualifier("CustomerDAO")
	CustomerDAO CustomerDAO;
	
	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;
	
	@Autowired
	@Qualifier("login")
	LoginDAO login;

	
	@RequestMapping(value = "/customerDetails.htm", method = RequestMethod.GET)
	public String checkUserInSession(HttpServletRequest request, @ModelAttribute("passenger") Passenger passenger,BindingResult result ) {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
			System.out.println("number of seats here are :: "+session.getAttribute("numberOfSeats"));
		if (username == null) {
			return "message";
		}

		else {
			return "customerDetails";
		}

	}

	
	@RequestMapping(value="/passenger.htm", method=RequestMethod.GET)
	public String initialize(@ModelAttribute("passenger")Passenger passenger,HttpServletRequest request,BindingResult result)
	{
		HttpSession session = request.getSession();
		
		session.setAttribute("noOfTravellers",session.getAttribute("noOfTravellers"));
		return "passenger";
	}
	
	
	@RequestMapping(value="/passenger.htm", method=RequestMethod.POST)
	public String doSubmit(@ModelAttribute("passenger")Passenger passenger,BindingResult result, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		//validator.validate(passenger, result);
    	if(result.hasErrors()){
    		return "passenger"; 
    	}
    	
		try{
	
			String firstName=passenger.getFirstName(); 
			firstName = firstName.replaceAll("[^A-Za-z]+$", "");
			String lastName=passenger.getLastName(); 
			lastName = lastName.replaceAll("[^A-Za-z]+$", "");
			String gender=passenger.getGender(); 

			
			Passenger pas = CustomerDAO.createPassenger(firstName,lastName,gender);
			long passenger_id = pas.getId(); 
			session.setAttribute("passenger_id", passenger_id);
			System.out.println("number of seats in names ::"+(Integer)request.getSession().getAttribute("numberOfSeats"));
			
		}
		catch(Exception e)
		{
			System.out.println("Could not create Passenger"+e.getMessage());
		}
		
		//Payment payment = new Payment();
		
		return "payment";
	}
	
	@RequestMapping(value="/viewpassengers.htm", method=RequestMethod.GET)
	public ModelAndView viewPassenger(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<Ticket> ticketList= new ArrayList<Ticket>();
		try{
			ticketList = ticketDAO.TicketList();
			System.out.println("----->"+ticketList.size());
		   }
		catch(Exception e)
		{
			System.out.println("Could not list passengers"+e.getMessage());
		}
		ModelAndView mv = new ModelAndView("passengerList", "ticketList", ticketList);
        return mv;
	}
	
	@RequestMapping(value="/bookingHistory.htm", method=RequestMethod.GET)
	public ModelAndView bookingHistory(@ModelAttribute("ticketList") Ticket ticket,HttpServletRequest request)
	{
		try {
		User userID = login.getUserUsingName((String)request.getSession().getAttribute("username"));
		System.out.println("User id: "+userID);
		List<Ticket> tickets = ticketDAO.TicketListByUserID(userID.getUserID());
		 return new ModelAndView("bookingHistory", "ticketList", tickets);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ModelAndView("bookingHistory", "ticketList", null);
		}
        
	}
}
