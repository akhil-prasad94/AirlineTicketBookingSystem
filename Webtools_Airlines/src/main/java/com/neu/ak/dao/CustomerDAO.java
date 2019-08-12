package com.neu.ak.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.Passenger;
import com.neu.ak.pojo.Payment;

public class CustomerDAO extends DAO {

	public Passenger createPassenger(String firstName, String lastName, String gender) throws UserException
	{
		try{
			begin();
			Passenger passenger = new Passenger(firstName, lastName, gender);
			getSession().save(passenger);
			commit();
			return passenger;
		}
		
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while creating new passenger: " + e.getMessage());
        }  
		finally{
			close();
		}
		
	}
	
	public Payment createPayment(long creditCardNumber, String bankName, String fullName, String expiration_month,
			String expiration_year)throws UserException
	{
		try{
			begin();
			Payment p = new Payment(creditCardNumber, bankName, fullName, expiration_month,
					 expiration_year);
			getSession().save(p);
			commit();
			return p;
		}
		
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while creating new payment: " + e.getMessage());
        }   
		finally{
			close();
		}
		
		
	}
	
	public void updatePassenger(long passenger_id) throws UserException
	{
		
		try{
			begin();
			Query query = getSession().createQuery("From Passenger where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			Passenger passenger = (Passenger)query.uniqueResult();
			getSession().update(passenger);
			commit();
			
		}
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while updating passenger: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public Passenger searchPassenger(long passenger_id) throws UserException
	{
		Passenger passenger;
		try{
			begin();
			Query query = getSession().createQuery("From Passenger where passenger_id=:passenger_id ");
			query.setLong("passenger_id", passenger_id);
			passenger = (Passenger)query.uniqueResult();
			
			commit();
		}
		catch (HibernateException e) {
            rollback();
            //throw new UserException("Could not create flight", e);
            throw new UserException("Exception while searching passenger: " + e.getMessage());
        }  finally{
			close();
		}
		return passenger;
	}
	
	public List ListPassengers() throws UserException
	{
		try{
			begin();
			Query q = getSession().createQuery("From Passenger");
			List list = q.list();
			commit();
			return list;
			
		}
		catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while listing passenger: " + e.getMessage());
        } 
		finally{
			close();
		}
		
	}
}
