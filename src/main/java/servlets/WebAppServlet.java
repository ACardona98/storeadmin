package servlets;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.Respuesta;
import util.Util;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/WebAppServlet")
public class WebAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
//    public String getInformacion() {
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://localhost:8088/entry-point/getPagos");
//		String s = target.request().get(String.class);
//		return s;
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String jsonString = getInformacion();
//		JSONParser parser = new JSONParser();
//		JSONArray json;
//		try {
//			json = (JSONArray) parser.parse(jsonString);
//			request.setAttribute("info", json);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		request.getRequestDispatcher("/index.jsp").forward(request,response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}