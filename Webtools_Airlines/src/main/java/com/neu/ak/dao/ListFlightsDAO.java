package com.neu.ak.dao;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.ak.exception.UserException;

public class ListFlightsDAO extends DAO {
	
	
public List listAllFlights()throws UserException{
		
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from AirLinerFlights");
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new UserException("No flights returned", e);
		}
		finally{
			close();
		}
		
	}
	
	public List listFlights(String departureAirport, String arrivalAirport, String departureDate,int numberOfSeats)throws UserException, ParseException{
	
		List<String> list= null;
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
		  Date date = (Date)formatter.parse(departureDate);
		
		try{
			begin();
			Query q = getSession().createQuery("from AirLinerFlights where departureAirport = :departureAirport and arrivalAirport=:arrivalAirport and departureDate>= :departureDate and arrivalDate >  :departureDate and AvailableSeats>= :AvailableSeats" );
			q.setString("departureAirport", departureAirport);
			q.setString("arrivalAirport", arrivalAirport);
			q.setDate("departureDate", date);
			q.setInteger("AvailableSeats", numberOfSeats);
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new UserException("No flights returned", e);
		}finally{
			close();
		}
		
		
	}
	
	
	
	
}
