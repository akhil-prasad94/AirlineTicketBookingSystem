package com.neu.ak.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.Cities;

public class CitiesDAO extends DAO {

	public List pullCityList(String cityName) throws UserException {
		try {

			begin();
			
			Criteria city = getSession().createCriteria(Cities.class);
			city.add(Restrictions.ilike("cityname", cityName,MatchMode.ANYWHERE)); 
			
			List list = city.list();
			System.out.println("list dao output"+list.size());
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not obtain cities list from database");
		} finally {
			close();
		}

	}

	public void addCities(HashMap<String, String> cities) throws UserException {
		try {

			begin();
			for (Entry<String, String> mymap : cities.entrySet()) {
				Cities city = new Cities(mymap.getKey(), mymap.getValue());
				getSession().save(city);
				
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not add into the city");
		} finally {
			close();
		}

	}
	
	public boolean checkIfCitiesAreAdded() throws UserException {
		try{
			begin();
			Query q = getSession().createQuery("From Cities");
			List list = q.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}
			
			
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not add into the city");
		} finally {
			close();
		}
		return true;
	}
}
