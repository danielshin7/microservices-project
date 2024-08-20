//Customer file containing getters/setters for customer data
package com.example.demo;

public class Customer{
	private Long id;
	private String name;
	private String email;
	private String password;

	public Customer(long id, String name, String email, String password) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public String toJSON(){
		// return "{\"id:\"" + id + ", \"name:\"" + name + ", \"password:\"" + password + ", \"email:\"" + email + " }";
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"password\":\"" + password + "\", \"email\":\"" + email + "\" }";
	}


	public Long getId(){
		return id;
	}
	
	public void setId() {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName() {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail() {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		this.password = password;
	}

}	