package com.neu.ak.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;




public class AirLinerFlightsDAO extends DAO{

	public AirLinerFlights createFlight(int  airLineId,String departureAirport, String arrivalAirport, String departureTime,
			String arrivalTime, int numberOfSeats, Date departureDate, Date arrivalDate,
			int availableSeats) throws UserException
	{
		try{
		begin();
		
		
		AirLinerFlights flight = new AirLinerFlights(departureAirport, arrivalAirport, numberOfSeats, airLineId, departureDate, 
				departureTime, arrivalTime, arrivalDate, availableSeats);
		getSession().save(flight);
		commit();
		System.out.println("Added flight and available seats are"+flight.getDepartureTime());
		System.out.println("Added flight and available seats are"+flight.getArrivalTime());
		return flight;
		}
		catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating new flight: " + e.getMessage());
        }    finally{
			close();
		}    
	}
	
	public List listFlights() throws UserException {
        try {
            begin();
            Query q = getSession().createQuery("from Flights");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Couldn't get data from routes", e);
        }finally{
			close();
		}
    }
	

	
	public AirLinerFlights searchFlightByID(long flight_id) throws UserException {
        try {
        	
            begin();
            Query q = getSession().createQuery("from AirLinerFlights where flightID = :flight_id");
            q.setLong("flight_id", flight_id);
            AirLinerFlights fd = (AirLinerFlights) q.uniqueResult();
            commit();
            return fd;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Couldn't get data from routes whose id is: " + flight_id + " " + e.getMessage());
        }
        finally{
			close();
		}
        
    }
	
	public void deleteFlight(AirLinerFlights flight)
            throws UserException {
        try {
            begin();
            getSession().delete(flight);
            getSession().flush();
            commit();
            
            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not delete flight", e);
        }finally{
			close();
		}
    }
	
	
	public void updateFlight(AirLinerFlights flight) throws UserException
	{
		try {
            begin();
            getSession().update(flight);
            commit();
            
          
            getSession().clear();
            
        } catch (HibernateException e) {
            rollback();
            throw new UserException(e.getMessage());
        }finally{
			close();
		}
	}
	
	public void updateAvailableSeats(AirLinerFlights flight, int oldTotal, int newTotal) throws UserException
	{
		try {
            begin();
            
            	int oldAvail = flight.getAvailableSeats();
				System.out.println("Old Seats are"+flight.getAvailableSeats());
				flight.setAvailableSeats(newTotal-(oldTotal-oldAvail));
				System.out.println("Available seats "+flight.getAvailableSeats());
				
			getSession().update(flight);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update flight", e);
        }finally{
			close();
		}
	}

	public int deleteFlightByAirLiner(long airLiners_id) throws UserException {
		// TODO Auto-generated method stub
		int noOfFlightsDeleted = 0;
		try {
			begin();
		 Query q = getSession().createQuery("from AirLinerFlights where airLineId = :airLiners_id");
		 q.setLong("airLiners_id", airLiners_id);
		 List<AirLinerFlights> alf = q.list();
		 System.out.println("alf size"+alf.size());
		  
        for(AirLinerFlights a : alf) {
        	 getSession().delete(a);
     			++noOfFlightsDeleted;
        }
		commit();
		
		} catch (HibernateException e) {
            rollback();
            throw new UserException(e.getMessage());
        }finally{
			close();
		}
		return noOfFlightsDeleted;
	}
}
