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
 * Servlet implementation class FindPathServlet
 */
@WebServlet("/findpathservlet")
public class FindPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 request.setCharacterEncoding("UTF-8");
		 HttpSession session = request.getSession(false);
		 
		 if(session != null) {
			 
		 }else if (session == null) {

	        JsonObject result = new JsonObject();
	        
	        result.addProperty("path", "");
			
	        result.addProperty("pathIdentity", "Session not found");

	        response.getWriter().write(result.toString());
	        
	        return;
		 }
		 
		 System.out.println("doGet executed first");
		 
		 String jsonstring1 = (String) session.getAttribute("fromnode");
		 String jsonstring2 = (String) session.getAttribute("tonode");
		 
		 Gson gson = new Gson();
		 
		 JsonObject jsonObject = gson.fromJson(jsonstring1, JsonObject.class);
		 JsonObject jsonObject2 = gson.fromJson(jsonstring2, JsonObject.class);

         // Validate conversion
         if (jsonObject == null || jsonObject2 == null) {
             System.out.println("Conversion failed: JSON string is null or empty.");
             JsonObject result = new JsonObject();

             result.addProperty("path", "");
             result.addProperty("pathIdentity",
                     "Source or destination node missing");

             response.getWriter().write(result.toString());

             return;
         }
		 
         String fromNode = jsonObject.get("name").getAsString();
         String fromType = jsonObject.get("type").getAsString();
         
         String toNode = jsonObject2.get("name").getAsString();
         String toType = jsonObject2.get("type").getAsString();
         
		 System.out.println("From Node: " + fromNode);
		 System.out.println("From Type: " + fromType);
		 
		 System.out.println("To Node: " + toNode);
		 System.out.println("To Type: " + toType);
		 
		 String pathString = GraphService.findPathString(fromType, fromNode, toType, toNode);
		 
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 
		 String pathIdentity = "Relation between " + fromNode + " & " + toNode;
		 
		 JsonObject jsonResponse = new JsonObject();
		 jsonResponse.addProperty("path", pathString);
		 jsonResponse.addProperty("pathIdentity", pathIdentity);
		 
		 response.getWriter().write(jsonResponse.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		String sessionid = null;
		
		if(session != null) {
			sessionid = session.getId();
			
		}else {
			session = request.getSession(true);
			sessionid = session.getId();
		}
		
		System.out.println("doPost executed first");
		
		session.setAttribute("fromnode", request.getParameter("from"));
		session.setAttribute("tonode", request.getParameter("to"));
				
		response.setContentType("application/json");
		
		response.getWriter().write("{'status':'success'}");

		response.sendRedirect("FindPathPage.html");
		return;
		
	}

}
