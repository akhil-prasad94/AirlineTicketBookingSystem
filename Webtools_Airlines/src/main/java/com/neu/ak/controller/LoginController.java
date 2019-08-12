package com.neu.ak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.ak.dao.CitiesDAO;
import com.neu.ak.dao.LoginDAO;
import com.neu.ak.dao.TicketDAO;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.Ticket;
import com.neu.ak.pojo.User;

@Controller
@RequestMapping(value = "/*")
public class LoginController {

	@Autowired
	@Qualifier("login")
	LoginDAO login;

	@Autowired
	@Qualifier("citiesDAO")
	CitiesDAO citiesDAO;
	

	@Autowired
	@Qualifier("ticketDAO")
	TicketDAO ticketDAO;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/")
	public ModelAndView viewHome() {

		if (!login.userExists("admin")) {
			String username = "admin";
			String firstname = "admin";
			String lastname = "admin";
			String password = "admin";
			String role = "admin";

			try {
				login.addUser(firstname, lastname, username, password, role);
			} catch (UserException e) {

				System.out.println("Exception: " + e.getMessage());

			}
		}
		try {
			if(!citiesDAO.checkIfCitiesAreAdded()) {
				populateCitiesTable();
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("home");
	}

	public void populateCitiesTable() {

		try {
			HashMap<String, String> cities = new HashMap<String, String>();
			cities.put("New York", "NYC");
			cities.put("Boston", "BOS");
			cities.put("San Fransico", "SFO");
			cities.put("Chicago", "ORD");
			cities.put("London", "LHR");
			cities.put("Mumbai", "BOM");
			cities.put("New Delhi", "DEL");
			cities.put("Hyderabad", "HYD");
			citiesDAO.addCities(cities);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("user") User user) {

		return new ModelAndView("Login","user",user);
	}

	@RequestMapping(value = "/AdminHome.htm", method = RequestMethod.GET)
	public String home() {

		return "AdminHome";
	}
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String showHome() {

		return "home";
	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.GET)
	public String SignUp(@ModelAttribute("user") User user) {

		return "register";

	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView validateUser(HttpServletRequest request,@ModelAttribute("user") User user) throws Exception {

		HttpSession session = request.getSession();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");

		boolean flag = false;
		boolean check = false;
		ModelAndView mv= new ModelAndView();
		try {

			flag = login.validateAdmin(username, password);
			check = login.validateUser(username, password);

		} catch (Exception e) {

			logger.info("Exception: " + e.getMessage());

		}

		if (flag) {
			System.out.println("Login successful");
			session.setAttribute("username", username);
			mv.setViewName("AdminHome");
			

		}

		else if (check) {
			
			System.out.println("Login successful");
			session.setAttribute("username", username);
			mv.setViewName("bookingCart");
			

		} else {
			logger.info("error with flag " + flag);
			logger.info("error with check " + check);
			mv.addObject("validUserCreds", "false");
			
			mv.setViewName("Login");

			
		}
		return mv;
	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String SignUpUser(@ModelAttribute("user") User user, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {

		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("ajaxCheck")) {
			PrintWriter out = response.getWriter();
			logger.info("in here");
			if (login.userExists(request.getParameter("userName"))) {
				logger.info("checking for user and user exists in DB");
				JSONObject obj = new JSONObject();
				obj.put("message", "Username already exists");
				out.println(obj);

			} else {
				out.println("Username is available");
			}
			return null;
		}

		else if (action.equalsIgnoreCase("register")) {
			HttpSession session = request.getSession();
			String username = request.getParameter("userName");
			String firstname = request.getParameter("firstName");
			String lastname = request.getParameter("lastName");
			username = username.replaceAll("[^\\dA-Za-z ]", "");
			String password = request.getParameter("password");
			String role = "customer";

			try {
				login.addUser(firstname, lastname, username, password, role);
			} catch (UserException e) {

				System.out.println("Exception: " + e.getMessage());

			}

			System.out.println("New user added successfully");
			session.setAttribute("userName", username);

		}

		return "home";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(@ModelAttribute("flightDetails") AirLinerFlights flightDetails, HttpServletRequest request) {

		HttpSession session = request.getSession();

		session.invalidate();
		return "home";

	}
}
