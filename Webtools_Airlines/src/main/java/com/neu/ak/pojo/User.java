package com.neu.ak.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
	
	public User() {
		super();
	}

	public User(String firstName,String lastName,String userName,String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "userName")
	private String userName;
	
	 @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
		@JoinColumn(name="userID")
	    private Set<Ticket> userTickets = new HashSet<Ticket>();
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name ="password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

}
