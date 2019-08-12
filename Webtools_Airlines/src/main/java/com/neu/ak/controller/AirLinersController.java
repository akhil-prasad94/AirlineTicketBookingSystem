package com.neu.ak.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.cj.Session;
import com.neu.ak.dao.AirLinerDAO;
import com.neu.ak.dao.AirLinerFlightsDAO;
import com.neu.ak.dao.ListFlightsDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.AirLiners;


@Controller
@RequestMapping(value = "/*AirLiners.htm")
public class AirLinersController {

	@Autowired
	@Qualifier("AirLinersValidator")
	AirLinersValidator validator;

	@Autowired
	@Qualifier("airLiners")
	AirLinerDAO airLiners;

	@Autowired
	@Qualifier("listRoutesDAO")
	ListFlightsDAO listRoutesDAO;
	
	@Autowired
	@Qualifier("routesDAO")
	AirLinerFlightsDAO routesDAO;
	
	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;
	
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/addAirLiners.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("AirLiners") AirLiners AirLiners, BindingResult result)
			throws Exception {
		validator.validate(AirLiners, result);
		if (result.hasErrors()) {
			return "addAirLiners";
		}

		try {

			String name = AirLiners.getAirLineName();
			name = name.replaceAll("[^A-Za-z]+$", "");
			String owner = AirLiners.getOwningCompany();
			owner = owner.replaceAll("[^A-Za-z]+$", "");
			String shortCode = AirLiners.getAirLineShortName();
			airLiners.create(name, owner, shortCode);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "AirLineSuccess";

	}

	@RequestMapping(value = "/addAirLiners.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("AirLiners") AirLiners AirLiners, BindingResult result) {

		return "addAirline";
	}

	@RequestMapping(value = "/deleteAirLiners.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute("alf") AirLiners alf,HttpServletRequest request) {

		try {
			List<AirLiners> airLinersList = airLiners.listAllAirLiners();
			HashMap<Integer, String> myMap = new HashMap<Integer, String>();
			for(AirLiners al : airLinersList) {
				myMap.put(al.getAirLineId(), al.getAirLineShortName());
			}
			request.getSession().setAttribute("airLiner", airLinersList);
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleteAirLiners";
	}

	@RequestMapping(value = "/deleteAirLiners.htm", method = RequestMethod.POST)
	public String deleteAirLiners(@ModelAttribute("fd") AirLiners fd,HttpServletRequest request) {

		try {

//			String id = request.getParameter("airplane_id");
//			id = id.replaceAll("[^\\d]+$", "");
			
			long AirLiners_id = fd.getAirLineId();
			System.out.println("AIRLINER ID:: "+AirLiners_id);
//			int noOfTix = ticketDAO.deleteTicketsByAirLiners(AirLiners_id);
//			request.getSession().setAttribute("noOfTickets", noOfTix);
//			
//			int noOfFligts = routesDAO.deleteFlightByAirLiner(AirLiners_id);
//			request.getSession().setAttribute("noOfFligts", noOfFligts);
//			
//			System.out.println("AirLiners_id:::"+AirLiners_id);
			airLiners.deleteAirLiners(AirLiners_id);
			
			
		}

		catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());

		}
		
		return "deletedAirLiners";

	}
	
}
