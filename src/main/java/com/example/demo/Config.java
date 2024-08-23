package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config{
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer(){
		return factory -> {
			factory.addAdditionalTomcatConnectors(createConnector(8081));
		};
	}
	
	public Connector createConnector(int port) {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setPort(port);
		return connector;
	}
}