package com.neu.ak.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cities")
public class Cities {

	@Id
	@Column(name="cityname")
	private String cityname;

	@Column(name="threeLetterCode")
	private String threeLetterCode;
	
	public String getThreeLetterCode() {
		return threeLetterCode;
	}

	public void setThreeLetterCode(String threeLetterCode) {
		this.threeLetterCode = threeLetterCode;
	}

	public Cities() {
		
	}

	public Cities(String cityname, String threeLetterCode) {
		super();
		this.cityname = cityname;
		this.threeLetterCode = threeLetterCode;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	
	
}
