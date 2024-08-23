package com.example.demo.object;

import org.json.JSONObject;

public class CustomerFactory {
	
	public static Customer getCustomer(String json_string){
		
		//Convert string to json for api requests
        JSONObject json = new org.json.JSONObject(json_string); 
       
        int id = (int) json.get("id");
        String name = (String) json.get("name"); 
        String email = (String) json.get("email"); 
        String password = (String) json.get("password"); 
		
		//Creating the customer
		Customer c = new Customer();
		c.setName(name);
		c.setId(id);
		c.setEmail(email);
		c.setPassword(password);
		return c;
	}
	
	//Adding information to the JSON object
	public static String getCustomerAsJSONString(Customer customer) {
        JSONObject obj = new JSONObject(); 
        
        obj.put("name", customer.getName()); 
        obj.put("email", customer.getEmail());
        obj.put("password", customer.getPassword());
        obj.put("id", customer.getId());
        
        String output = obj.toString();
        return output;
	}

}