package util;

public class Respuesta {
	private int codigo;
	private String respuesta;
	
	public Respuesta() {
	}
	
	public Respuesta(int codigo, String respuesta) {
		this.codigo = codigo;
		this.respuesta = respuesta;
	}

	public int getcodigo() {
		return codigo;
	}

	public void setcodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
