package com.neu.ak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ak.dao.AirLinerFlightsDAO;
import com.neu.ak.dao.CitiesDAO;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.Cities;



@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	@Qualifier("routesDAO")
	AirLinerFlightsDAO routesDAO;
	
	@Autowired
	@Qualifier("citiesDAO")
	CitiesDAO citiesDAO;

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("flightDetails")AirLinerFlights flightDetails) {

	//	flightDetails.setTravelClass("Economy");
		return "index";
	}
	
	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String adminHomePage() {

		
		return "adminHome";
	}
	@RequestMapping(value="/fromCitieslist.htm", method=RequestMethod.POST)
	public void ajaxForCities(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try{
		
		PrintWriter out = response.getWriter();
		String fromCities = request.getParameter("fromCities");
		List<Cities> list =  citiesDAO.pullCityList(fromCities);
		System.out.println("input value :: "+fromCities);
		System.out.println("Lsit size "+list);
		JSONArray jsonArray = new JSONArray();
          for (int i=0; i < list.size(); i++) {
              JSONObject obj = new JSONObject();
              obj.put("cityname", list.get(i).getCityname());
              System.out.println("***********"+list.get(i).getCityname());
              jsonArray.put(obj);
          }
  
          JSONObject Obj = new JSONObject();
          Obj.put("list", jsonArray);
          out.println(Obj);
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in listing cities"+e.getMessage());
		}
	}
}
