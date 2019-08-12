package com.neu.ak.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.neu.ak.dao.AirLinerFlightsDAO;
import com.neu.ak.dao.CustomerDAO;
import com.neu.ak.dao.LoginDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.Passenger;
import com.neu.ak.pojo.Payment;
import com.neu.ak.pojo.User;

@Controller
@RequestMapping(value = "/payment*.htm")
public class PaymentController {

	@Autowired
	@Qualifier("CustomerDAO")
	CustomerDAO CustomerDAO;

	@Autowired
	@Qualifier("paymentValidator")
	PaymentValidator validator;

	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;

	@Autowired
	@Qualifier("routesDAO")
	AirLinerFlightsDAO routesDAO;
	
	@Autowired
	@Qualifier("login")
	LoginDAO login;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	
	@RequestMapping(value = "/payment.htm", method = RequestMethod.GET)
	public String initialize(@ModelAttribute("payment") Payment payment) {
		
		
		return "payment";
	}

	@RequestMapping(value = "/payment.htm", method = RequestMethod.POST)
	public String addPayment(HttpServletRequest request, HttpServletResponse response) throws UserException {

		HttpSession session = request.getSession();
		long passenger_id = ((Long) session.getAttribute("passenger_id"));

		try {
			String username = (String) session.getAttribute("username");
			String number = request.getParameter("creditCardNumber");
			number = number.replaceAll("[^0-9]", "");
			long creditCardNumber = Long.parseLong(number);
			
			String bankName = request.getParameter("bankName");
			bankName = bankName.replaceAll("[^\\dA-Za-z]", "");
			String fullName = request.getParameter("fullName");
			fullName = fullName.replaceAll("[^A-Za-z]+$", "");
			String expiration_month = request.getParameter("expiration_month");
			expiration_month = expiration_month.replaceAll("[^0-9]", "");
			Cookie exp_month = new Cookie("month", expiration_month);
			String expiration_year = request.getParameter("expiration_year");
			expiration_year = expiration_year.replaceAll("[^0-9]", "");
			Cookie exp_year = new Cookie("year", expiration_year);
		
			Passenger passenger = CustomerDAO.searchPassenger(passenger_id);
			AirLinerFlights flightDetail = (AirLinerFlights) session.getAttribute("flightdetail");
			User user = login.getUserUsingName(username);
			System.out.println("user id:"+user.getFirstName());
			ticketDAO.bookTicket(passenger, flightDetail,user.getUserID());
			int availSeats = flightDetail.getAvailableSeats();
			System.out.println("number of seats in payment controller;:"+(Integer)session.getAttribute("numberOfSeats"));
			routesDAO.updateAvailableSeats(flightDetail, availSeats, availSeats-(Integer)session.getAttribute("numberOfSeats"));
			CustomerDAO.updatePassenger(passenger_id);

			response.addCookie(exp_month);
			response.addCookie(exp_year);

		} catch (Exception e) {
			 e.printStackTrace();
		}
		session.removeAttribute("bookingSession");
		return "printTicket";
	}
}
