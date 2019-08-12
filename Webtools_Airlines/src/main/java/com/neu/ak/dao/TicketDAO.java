package com.neu.ak.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.AirLiners;
import com.neu.ak.pojo.Passenger;
import com.neu.ak.pojo.Ticket;
import com.neu.ak.pojo.User;

public class TicketDAO extends DAO{

	public Ticket bookTicket(Passenger passengerDetails, AirLinerFlights AirLinerFlights,long user) throws UserException{
		
		
		try {
            begin();
            Ticket ticket = new Ticket(passengerDetails, AirLinerFlights,user);   
            getSession().save(ticket);
            commit();
            return ticket;
            
        } catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while creating ticket: " + e.getMessage());
        }  finally{
			close();
		}      

		
		
	}
	
	public void cancelTicket(Passenger passengerDetails,AirLinerFlights AirLinerFlights,int numberOfSeats) throws UserException
	{
		try{
			begin();
			long passenger_id = passengerDetails.getId();
			long flight_id = AirLinerFlights.getFlightID();
			Query q = getSession().createQuery("From Ticket where passenger_id=:passenger_id and flightID=:flight_id");
			q.setLong("passenger_id",passenger_id);
			q.setLong("flight_id",flight_id);
			Ticket ticket = (Ticket)q.uniqueResult();
			getSession().delete(ticket);
			commit();
			
			getSession().clear();
			close();
			begin();
			int oldAvail = AirLinerFlights.getAvailableSeats();
			System.out.println("Old available :: "+oldAvail);
			AirLinerFlights.setAvailableSeats(oldAvail+numberOfSeats);
			System.out.println("new  available :: "+AirLinerFlights.getAvailableSeats());
			getSession().update(AirLinerFlights);
			commit();
			
		}
		catch (HibernateException e) {
            rollback();
           throw new UserException("Exception while deleting ticket: " + e.getMessage());
        } 
		finally{
			close();
		}
	}
	
	public void deleteTickets(long flight_id) throws UserException
	{
		try{
			begin();
			Query q = getSession().createQuery("From Ticket where flightID=:flight_id");
			q.setLong("flight_id",flight_id);	
			List <Ticket>list = q.list();
			commit();
			//Iterate to delete all ticket objects
			
			for(Ticket t:list)
			{
				begin();
				getSession().delete(t);
				commit();
			}
			
		}
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while deleting ticket: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public List TicketList()
	{
		List<Ticket>ticketList= new ArrayList<Ticket>();
		try{
		begin();
		Query q = getSession().createQuery("From Ticket");
		ticketList = q.list();
		commit();
		
		}
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            System.out.println("Exception while listing ticket: " + e.getMessage());
        }  
		finally{
			close();
		}
		return ticketList;
		
	}
	
	public List TicketListByUserID(long userID)
	{
		List<Ticket>ticketList= new ArrayList<Ticket>();
		try{
		begin();
		Query q = getSession().createQuery("From Ticket where userID = :userID");
		q.setString("userID",Long.toString(userID));
		ticketList = q.list();
		commit();
		
		}
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            System.out.println("Exception while listing ticket: " + e.getMessage());
        }  
		finally{
			close();
		}
		return ticketList;
		
	}

	public int deleteTicketsByAirLiners(long airLiners_id) {
		// TODO Auto-generated method stub
		
		List<AirLinerFlights> flightsList = new ArrayList<AirLinerFlights>();
		int ticketsDeleted = 0;
		try{
			begin();
			Query q = getSession().createQuery("From AirLinerFlights where flightID=:flight_id");
			q.setLong("flight_id",airLiners_id);
			flightsList = q.list();
			for(AirLinerFlights myList : flightsList) {
				
				 int noOfRows = getSession().createQuery(
				            "delete from Ticket where flight_id = :ids")
					        .setParameter("ids", myList.getAirLineId())
					        .executeUpdate();
				 
				 ticketsDeleted += noOfRows;
			}
			commit();
			}
			catch (HibernateException e) {
	            rollback();
	            System.out.println("Exception while showing ticket: " + e.getMessage());
	        }  
			finally{
				close();
			}
		return ticketsDeleted;
	}
	
}
