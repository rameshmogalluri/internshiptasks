package com.spring;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Contact implements Serializable{
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent  
	private String name;
	@Persistent  
	private String email;
	@Persistent
	private String address;
	@Persistent  
	private String mobileNumber;
	@Persistent  
	private String password;
	@Persistent  
	private String status; 

	
	 Contact(String name, String email,  String mobileNumber,String address, String password,String status) {
		
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.status=status;
		
	}   
	
	public Key getKey() {
        return key;  
    }
	public String getstatus() {
        return status;  
    }
	public void setstatus(String status) {
        this.status=status;  
    }
	public String getName() {
		return name;
	} 

	public void setName(String name) {  
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
