package com.neu.ak.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.neu.ak.pojo.AirLinerFlights;
import com.neu.ak.pojo.Passenger;



@Entity
@Table(name="ticket")
public class Ticket {

	@Id 
	@GeneratedValue
	@Column(name="ticket_id", unique = true, nullable = false)
	long ticket_id;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="passenger_id")
	Passenger passengerDetails;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="flightID")
	AirLinerFlights flightDetails;
	
	Ticket() {
		
	}
	
	
	public Ticket(Passenger passengerDetails, AirLinerFlights flightDetails,long user) {
		
		
		this.passengerDetails = passengerDetails;
		this.flightDetails = flightDetails;
		this.userID = user;
	}


	public long getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public Passenger  getPassengerDetails() {
		return passengerDetails;
	}

	public void setPassengerDetails(Passenger  passengerDetails) {
		this.passengerDetails = passengerDetails;
	}

	public AirLinerFlights getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(AirLinerFlights flightDetails) {
		this.flightDetails = flightDetails;
	}

	@Column(name="userID")
	private long userID;

	public long getUserID() {
		return userID;
	}


	public void setUserID(long userID) {
		this.userID = userID;
	}
	
}
