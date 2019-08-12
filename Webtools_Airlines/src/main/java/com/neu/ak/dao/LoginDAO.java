package com.neu.ak.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.ak.controller.LoginController;
import com.neu.ak.exception.UserException;
import com.neu.ak.pojo.User;

@SuppressWarnings("deprecation")
public class LoginDAO extends DAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	public boolean validateAdmin(String username,String password) throws UserException
	{
		try{
			
			Query q = getSession().createQuery("from User where username = :username and password= :password and role = 'admin'" );
			q.setString("username",username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			
		    if(user==null)
		    {
		    	
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new UserException("Could not find any user", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	public boolean validateUser(String username,String password) throws UserException
	{
		try{
			
			
			Query q = getSession().createQuery("from User where username = :username and password= :password and role = 'customer'" );
			q.setString("username",username);
			q.setString("password", password);
			
			User user = (User) q.uniqueResult();
			
		  
			if(user==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new UserException("Could not find any user", e);
		}finally{
			close();
		}
		return true;
		
	}
	
	
	
	
	public void addUser(String firstName,String lastName,String userName, String password, String role) throws UserException{
		
		try{
			begin();
			User user = new User(firstName,lastName, userName, password, role);
			getSession().save(user);
			commit();
		}
		catch(HibernateException e){
			rollback();
            throw new UserException("Could not add user due to error : ", e);
		}finally{
			close();
		}
	
		
		
	}
	
	public boolean userExists(String username)
	{
		try{
			begin();
			Query q = getSession().createQuery("From User where username=:username");
			q.setString("username", username);
			List list = q.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}
			
			
			
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		finally{
			close();
		}
		return true;
	}
	
	public User getUserUsingName(String username)
	{
		try{
			begin();
			Query q = getSession().createQuery("From User where username=:username");
			q.setString("username", username);
			User list = (User) q.uniqueResult();
			commit();
			
			return list;
			
			
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		finally{
			close();
		}
		return null;
	}
}
