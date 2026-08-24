package com.cognodb.relations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ExploreRelationsServlet
 */
@WebServlet("/explorerelationsservlet")
public class ExploreRelationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExploreRelationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		
		if(session != null) {
			
		} else {
			session = request.getSession(true);
			return;
		}
		
		Gson gson = new Gson();
		String jsonstring = (String) session.getAttribute("selectednode");
		
		JsonObject jsonObject = gson.fromJson(jsonstring, JsonObject.class);
		
		String nodename = jsonObject.get("name").getAsString();
		String nodetype = jsonObject.get("type").getAsString();
		
		System.out.println("Node Name: " + nodename);
		System.out.println("Node type: " + nodetype);
		
		String relationsJsonString = GraphService.findRelations(nodename, nodetype);
		
		System.out.println("Relations JSON String: " + relationsJsonString);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(relationsJsonString);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String selectednode = null;
		
		if(session != null) {
			selectednode = (String) request.getParameter("selectednode");
			session.setAttribute("selectednode", selectednode);
			response.getWriter().write("{'status':'success'}");
			response.sendRedirect("ExploreRelationsPage.html");
		} else {
			response.getWriter().write("{'status':'failed'}");
			return;
		}
		
		
		
		
		
		
	}

}
