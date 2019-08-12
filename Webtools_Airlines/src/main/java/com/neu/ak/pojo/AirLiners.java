package com.neu.ak.pojo;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author bvkvi
 */
import javax.persistence.*;

import com.neu.ak.pojo.*;


@Entity
@Table(name = "Airliners")
public class AirLiners {
    
    public AirLiners() {
       
    }
    
    
    
    public AirLiners(String airLineName, String airLineShortName, String owningCompany) {
		super();
		this.airLineName = airLineName;
		this.airLineShortName = airLineShortName;
		this.owningCompany = owningCompany;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="airLineId", unique = true, nullable = false)
	private int airLineId;
    
    public int getAirLineId() {
		return airLineId;
	}



	public void setAirLineId(int airLineId) {
		this.airLineId = airLineId;
	}



	@Column(name = "airLineName")
    private String airLineName = "";
    
    @Column(name = "ShortName")
    private String airLineShortName="";
    
    @Column(name = "OwningCompany")
    private String owningCompany= "";

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name="airLineId")
    private Set<AirLinerFlights> flightCatalog = new HashSet<AirLinerFlights>();
    
    public String getAirLineShortName() {
        return airLineShortName;
    }

    public void setAirLineShortName(String airLineShortName) {
        this.airLineShortName = airLineShortName;
    }
    public String getAirLineName() {
        return airLineName;
    }
    
    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }
    
    @Override
    public String toString() {
        return airLineName;
    }

	public String getOwningCompany() {
		return owningCompany;
	}

	public void setOwningCompany(String owningCompany) {
		this.owningCompany = owningCompany;
	}


}

