package com.neu.ak.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Airports")
public class Airports {

	@Id
	@Column(name="AirportName")
	private String AirportName;

	public String getAirportName() {
		return AirportName;
	}

	public void setAirportName(String airportName) {
		AirportName = airportName;
	}
}
