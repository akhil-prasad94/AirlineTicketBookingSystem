package com.neu.ak.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ak.dao.AirLinerDAO;
import com.neu.ak.dao.AirLinerFlightsDAO;
import com.neu.ak.dao.ListFlightsDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.AirLiners;


@Controller
@RequestMapping(value = "/*route*.htm")
public class RouteController {

	@Autowired
	@Qualifier("RouteValidator")
	RouteValidator validator;
	
	@Autowired
	@Qualifier("listRoutesDAO")
	ListFlightsDAO listRoutesDAO;
	
	@Autowired
	@Qualifier("routesDAO")
	AirLinerFlightsDAO routesDAO;
	
	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;
	
	@Autowired
	@Qualifier("airLiners")
	AirLinerDAO airLinersDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		
	} 
	
	@RequestMapping(value = "/addflights.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("fd") AirLinerFlights fd,BindingResult result) throws Exception
	{
		
		validator.validate(fd, result);
    	if(result.hasErrors()){
    		return "addFlights"; 
    	}
    	
    	try
        {
    		int airLineId = fd.getAirLineId(); 
    		
    		String departureAirport=fd.getDepartureAirport(); 
    		departureAirport = departureAirport.replaceAll("[^A-Za-z]+$", "");
    		String arrivalAirport=fd.getArrivalAirport(); 
    		arrivalAirport = arrivalAirport.replaceAll("[^A-Za-z]+$", arrivalAirport);
    		String departureTime=fd.getDepartureTime();
    		
    		String arrivalTime=fd.getArrivalTime(); 
    		
    		
    		int numberOfSeats=fd.getNumberOfSeats(); 
    		Date departureDate=fd.getDepartureDate(); 
    		Date arrivalDate=fd.getArrivalDate(); 
    		
    		
    		AirLinerFlightsDAO fdao = new AirLinerFlightsDAO();
            AirLinerFlights flight = fdao.createFlight(airLineId,departureAirport, arrivalAirport, departureTime,arrivalTime, numberOfSeats, 
            		departureDate, arrivalDate,numberOfSeats);
            //DAO.close();
            
        }
        catch (UserException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
    	
		return "addedFlight";
	}
	

	@RequestMapping(value = "/addflights.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("fd") AirLinerFlights fd,HttpServletRequest request)
	{
		
		try {
			List<AirLiners> airLinersList = airLinersDAO.listAllAirLiners();
			HashMap<Integer, String> myMap = new HashMap<Integer, String>();
			for(AirLiners al : airLinersList) {
				myMap.put(al.getAirLineId(), al.getAirLineShortName());
			}
			request.getSession().setAttribute("airLiner", airLinersList);
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addFlights";
	}
	
	@RequestMapping(value="/ListFlights.htm", method=RequestMethod.GET)
	public String listForm(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<AirLinerFlights> listOfFlights = null;
		
		try{
			
			listOfFlights = listRoutesDAO.listAllFlights();
		}
		catch(UserException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		if(session.getAttribute("listOfFlights") != null) {
			System.out.println("not null");
			session.removeAttribute("listOfFlights");
		}
		for(AirLinerFlights a : listOfFlights) {
			System.out.println("Flightnnumber : "+a.getArrivalAirport());
		}
		session.setAttribute("listOfFlights",listOfFlights);
		return "ListFlights";
	}
	
	
	@RequestMapping(value="/updateFlights.htm", method=RequestMethod.GET)
	public String updateFlights(@ModelAttribute("fd") AirLinerFlights fd, HttpServletRequest request) throws UserException
	{
		String id = request.getParameter("id");
		long flight_id = Long.parseLong(id);
		
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		if(action!=null){
		if(action.equalsIgnoreCase("update"))
		{
			AirLinerFlights flight = routesDAO.searchFlightByID(flight_id);
			 request.setAttribute("flight", flight);
		   return "updateFlight";
		   
		}
		
		}
		
		
		
			return "ListFlights";
		
		
	}
	
	@RequestMapping(value="/deleteFlights.htm", method=RequestMethod.GET)
	public void deleteFlights(@ModelAttribute("fd") AirLinerFlights fd, HttpServletRequest request,HttpServletResponse response) throws UserException, IOException
	{
		try{
			String id = request.getParameter("id");
			
			long flight_id = Long.parseLong(id);
			
			HttpSession session = request.getSession();
			
			AirLinerFlights flight = routesDAO.searchFlightByID(flight_id);
			
			ticketDAO.deleteTickets(flight_id);
			
			routesDAO.deleteFlight(flight);
			
			List<AirLinerFlights> listOfFlights = listRoutesDAO.listAllFlights();
			session.setAttribute("listOfFlights", listOfFlights);
			
		}
		catch(UserException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		response.sendRedirect("ListFlights.htm");
		
	}
	
	@RequestMapping(value="/updateFlight.htm", method=RequestMethod.POST)
	public void update(@ModelAttribute("fd") AirLinerFlights fd,BindingResult result, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		try{
			
			routesDAO.updateFlight(fd);
			System.out.println("Now Seats available are"+fd.getAvailableSeats());
			System.out.println("Flight saved/updated successfully");
			
		}
		catch(UserException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		response.sendRedirect("ListFlights.htm");
	//	return "ListFlights";
	}
}
