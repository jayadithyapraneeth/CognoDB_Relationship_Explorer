package com.cognodb.relations;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

@WebListener
public class CognoDBConnection extends HttpServlet implements ServletContextListener {
	
	private static Driver driver = null; //this single instance will be used throughout the application
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Initializing CognoDB connection...");
        String uri = System.getenv("CognoDB_URL");
        String username = System.getenv("CognoDB_Username");
        String password = System.getenv("CognoDB_Password");
        
        	try {
        		driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
	            driver.verifyConnectivity();

	            System.out.println("Connected to CognoDB successfully!");

	        }catch(Exception e) {
	            System.err.println("Failed to connect to CognoDB: " + e.getMessage());
	        }  	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (driver != null) {
			driver.close();
			System.out.println("Disconnected from CognoDB.");
		}
	}
	
	public static Driver getDriver() {
		return driver;
	}
	
}

