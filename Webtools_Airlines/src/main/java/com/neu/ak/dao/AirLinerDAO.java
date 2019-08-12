package com.neu.ak.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.AirLiners;



public class AirLinerDAO extends DAO {

	public AirLiners create(String airlineName, String owner,String shortCode) throws UserException{
		try {
            begin();
            AirLiners airliner = new AirLiners(airlineName,shortCode,owner);
            getSession().save(airliner);
            commit();
            return airliner;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not create the airplane", e);
        }
		finally{
			close();
		}
		
	}
	
	
	public void updateAirLiners(AirLiners airLines) throws UserException {
        try {
            begin();
            getSession().update(airLines);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the airplane", e);
        }
        finally{
			close();
		}
    }
	
	public AirLiners searchAirLinersByID(long airplane_id) throws UserException {
        try {
        	System.out.println("AIRLINER ID ::"+airplane_id);
            begin();
            Query q = getSession().createQuery("from AirLiners where airLineId = :airplane_id");
            q.setLong("airplane_id", airplane_id);
            AirLiners airliner = (AirLiners) q.uniqueResult();
            commit();
            return airliner;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not obtain the airplane details whose id is: " + airplane_id + " " + e.getMessage());
        }
        finally{
			close();
		}
    }
	
	public void deleteAirLiners(long airplane_id) throws UserException{
		
		try{
			
			AirLiners airplane = searchAirLinersByID(airplane_id);
			begin();
			System.out.println("AIRPLAN DATA:: "+airplane.getAirLineId());
			getSession().delete(airplane);
			commit();
			
			
		}
		catch (HibernateException e) {
            rollback();
            throw new UserException("Could not delete the airplane", e);
            
        }
		finally{
			close();
		}
		
		
	}
	public List<AirLiners> listAllAirLiners() throws UserException {
		List<AirLiners> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from AirLiners");
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
	
}
