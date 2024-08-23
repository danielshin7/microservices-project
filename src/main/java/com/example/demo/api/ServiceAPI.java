/*
 * API service to check api connection
 */
package com.example.demo.api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServiceAPI {
	/**
	 * Show that api connection is live
	 * @return message
	 */
	@GetMapping
	public String healthCheck() {
		return "<h1>Connection is live.</h1>" ;
	
	}
}