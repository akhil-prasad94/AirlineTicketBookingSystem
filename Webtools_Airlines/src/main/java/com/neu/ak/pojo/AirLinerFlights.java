package com.neu.ak.pojo;

import java.sql.Date;

/**
 *
 * @author bvkvi
 */
import javax.persistence.*;

@Entity
@Table(name = "AirLinerFlights")
public class AirLinerFlights {

	public AirLinerFlights() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flightID;

	public long getFlightID() {
		return flightID;
	}

	public void setFlightID(long flightID) {
		this.flightID = flightID;
	}

	
	@Column(name = "departureAirport",nullable = false)
	private String departureAirport = "";
	
	@Column(name = "arrivalAirport",  nullable = false)
	private String arrivalAirport = "";
	
	@Column(name = "numberOfSeats", nullable = false)
	private int numberOfSeats;

	@Column(name = "airLineId")
	private int airLineId;
	
	@Column(name = "departureDate",  nullable = false)
	private Date departureDate;
	
	@Column(name = "departureTime", nullable = false)
	private String departureTime = "";
	
	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Column(name = "arrivalTime", nullable = false)
	private String arrivalTime = "";
	
	
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Column(name = "arrivalDate", nullable = false)
	private Date arrivalDate;
	
	@Column(name = "AvailableSeats", nullable = false)
	private int availableSeats;
	
	public AirLinerFlights(String departureAirport, String arrivalAirport, int numberOfSeats,
			int airLineId, Date departureDate, String departureTime, String arrivalTime, Date arrivalDate,
			int availableSeats) {
		
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.numberOfSeats = numberOfSeats;
		this.airLineId = airLineId;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.arrivalDate = arrivalDate;
		this.availableSeats = availableSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getAirLineId() {
		return airLineId;
	}

	public void setAirLineId(int airLineId) {
		this.airLineId = airLineId;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}



	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}



}
