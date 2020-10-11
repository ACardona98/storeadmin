package util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class Util {
	
	public static boolean isNumeric(String str) { 
	  try {  
	    Integer.parseInt(str);  
	    return true;
	  } catch(NumberFormatException e){  
	    return false;  
	  }  
	}
	
	public static boolean isEmpty(String str) {
		return str.isEmpty();
	}
	
	public static boolean isFechaNacimientoValida(String str) {
		Date fecha;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(str);
			return fecha.before(new Date());
		} catch (ParseException e) {
			return false;
		}
	}	
	
	public static void writeResponse(HttpServletResponse response, String text) throws IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().append(text);
	}
}
