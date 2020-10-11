package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.CodigosRespuestas;
import util.Respuesta;
import util.Util;

/**
 * Servlet implementation class AdministrarClientesServlet
 */
public class AdministrarClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/pages/cliente_form.jsp").forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String accion = request.getParameter("accion");
		
		try {
			switch(accion) {
			case "crear":
				guardarCliente(request, response);
				break;
			case "consultar":
				consultarClientes(request, response);
				break;
			}
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error");
			e.printStackTrace();
			throw e;
		}
	}
	
	private void guardarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Respuesta respuesta = new Respuesta(0, "El número de colilla debe ser numérico");
		
		String nombreCompleto = request.getParameter("nombreCompleto");
		String identificacion = request.getParameter("identificacion");
		String direccion = request.getParameter("direccion");
		String celular = request.getParameter("celular");
		String fechaNacimientoStr = request.getParameter("fechaNacimiento");
		Date fechaNacimiento = null;
		
		
		String accion = request.getParameter("accion");
		
		StringBuilder mensajeValidaciones = new StringBuilder();
		
		if(Util.isEmpty(nombreCompleto))
			mensajeValidaciones.append("Nombre Completo<br>");
		
		if(Util.isNumeric(identificacion))
			mensajeValidaciones.append("Identificación<br>");
		
		if(Util.isEmpty(direccion))
			mensajeValidaciones.append("Dirección<br>");
		
		if(Util.isEmpty(celular))
			mensajeValidaciones.append("Celular<br>");
		
		if(Util.isEmpty(fechaNacimientoStr)) {
			mensajeValidaciones.append("Fecha Nacimiento<br>");
		}else {
			if(!Util.isFechaNacimientoValida(fechaNacimientoStr)) {
				mensajeValidaciones.append("Fecha Nacimiento válida");
			}
		}
		
		//Se valida si hay mensajes de validaciones para mostrar
		if(!Util.isEmpty(mensajeValidaciones.toString())) {
			mensajeValidaciones.insert(0, "Debe ingresar los siguientes campos\n");
			JSONObject jsonObj  = new JSONObject();
	        jsonObj.put("codigo", CodigosRespuestas.ERR);
	        jsonObj.put("respuesta", mensajeValidaciones.toString());
	        
	        Util.writeResponse(response, jsonObj.toJSONString());
	        return;
		}
		
		JSONObject cliente = new JSONObject();
		cliente.put("nombreCompleto", nombreCompleto);
		cliente.put("identificacion", identificacion);
		cliente.put("direccion", direccion);
		cliente.put("celular", celular);
		cliente.put("fechaNacimiento", fechaNacimientoStr);
		
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8088/entry-point/guardarCliente");
		
		Future<Response> sFuture = target.request().async().post(Entity.entity(
			    new ByteArrayInputStream(("{\"cliente\":" + cliente.toJSONString() + "}").getBytes()),
			    MediaType.APPLICATION_JSON
			));
		try {
			
			Response s = sFuture.get(60, TimeUnit.SECONDS);
			respuesta = s.readEntity(Respuesta.class);
			
		} catch (InterruptedException e) {
			respuesta = new Respuesta(CodigosRespuestas.ERR, "Error al obtener la respuesta");
			e.printStackTrace();
		} catch (ExecutionException e) {
			respuesta = new Respuesta(CodigosRespuestas.ERR, "Error al obtener la respuesta");
			e.printStackTrace();
		} catch (TimeoutException e) {
			respuesta = new Respuesta(CodigosRespuestas.ERR, "Se superó el tiempo límite para responder la petición");
			e.printStackTrace();
		}
	
		
		JSONObject jsonObj  = new JSONObject();
        jsonObj.put("codigo", respuesta.getcodigo());
        jsonObj.put("respuesta", respuesta.getRespuesta());
        
		Util.writeResponse(response, jsonObj.toJSONString());
	}
	
	
	private void consultarClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray clientes = new JSONArray();
		response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().append(clientes.toJSONString());
	}
}
