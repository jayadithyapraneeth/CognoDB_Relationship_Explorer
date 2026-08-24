package com.cognodb.relations;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/homepageservlet")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HomePage() {
        System.out.println("HomePage servlet initialized.");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method triggered.");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		HttpSession session = request.getSession(false);
		
		System.out.println("Session: " + session);
		
		if(session != null) {
			System.out.println("Session exists.");
		}else {
			System.out.println("Creating new session.");
			session = request.getSession(true);
		}
		
		String jsonstring = CypherToJson.getNodeJsonString();
		System.out.println("JSON String: " + jsonstring);
		
		response.getWriter().write(jsonstring);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}