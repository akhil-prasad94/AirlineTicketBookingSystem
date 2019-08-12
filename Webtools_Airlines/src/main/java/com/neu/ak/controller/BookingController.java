package com.neu.ak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.ak.dao.AirLinerFlightsDAO;
import com.neu.ak.dao.LoginDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.Ticket;
import com.neu.ak.pojo.User;

@Controller
@RequestMapping(value="/*Cart.htm")
public class BookingController {

	@Autowired
	@Qualifier("routesDAO")
	AirLinerFlightsDAO airLinerFlightsDAO;
	
	@Autowired
	@Qualifier("login")
	LoginDAO login;

	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;
	
	@RequestMapping(value="/addtoBookingCart.htm", method=RequestMethod.GET)
	public String intialize(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException, JSONException{
		
		try{
		HttpSession session = request.getSession();
		Long flightID = Long.parseLong(request.getParameter("flightID"));
		List<AirLinerFlights> flights;
 
		AirLinerFlights flightDetails = airLinerFlightsDAO.searchFlightByID(flightID);
		int noOfSeats = flightDetails.getAvailableSeats();
		PrintWriter out = response.getWriter();
		if(noOfSeats>0)
		{
			System.out.println("number of seats"+noOfSeats);
				
			out.println("Seats are available");
			if (session.getAttribute("bookingSession") != null) {
				System.out.println("session not null");
				
				flights = (ArrayList<AirLinerFlights>) session.getAttribute("bookingSession");
	         } else {
	        		System.out.println("session null");
					
	    			
	        	 flights = new ArrayList<AirLinerFlights>();
	         }
			System.out.println("session not null");
			
			
			flights.add(flightDetails);
			 session.setAttribute("bookingSession", flights);
			 session.setAttribute("flightdetail", flightDetails);
			 
			 
	         return "bookingCart";
	         
			}
		
		else
		{
			
			return "notAvailable";
			
		}	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value="/removeFromCart.htm", method=RequestMethod.GET)
	public String removeItems(HttpServletRequest request) throws UserException{
		
		HttpSession session = request.getSession();
		try{
			
			List<AirLinerFlights> flights =(ArrayList<AirLinerFlights>) session.getAttribute("bookingSession");
			String id = request.getParameter("id");
			long flight_id = Long.parseLong(id); 
			
			
			for(AirLinerFlights flightDetails:flights){
				if(flightDetails.getFlightID()==flight_id){
					flights.remove(flightDetails);
					break;
					
				}
			}
			
			session.setAttribute("bookingSession", flights);
			
			
		}
		
		catch(Exception e)
		{
			System.out.println("Could not remove flight object"+ e);
		}
		
		
		return "bookingCart";
	}
	
	@RequestMapping(value="/bookingCart.htm", method=RequestMethod.GET)
	public ModelAndView bookingCart(HttpServletRequest request) {
		User userID = login.getUserUsingName((String)request.getSession().getAttribute("username"));
		ModelAndView mv= new ModelAndView();
		List<Ticket> tickets = ticketDAO.TicketListByUserID(userID.getUserID());
		request.getSession().setAttribute("tickets", tickets);
		mv.setViewName("bookingCart");
		return mv;
		
	}
		
	}

