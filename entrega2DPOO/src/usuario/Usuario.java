package usuario;

import java.util.ArrayList;

public abstract class Usuario {
	private String nombre;
	private String login;
	private String password;
	private float altura;
	private float peso;
	private ArrayList<String> restricciones;
	public Usuario(String nombre, String login, String password, float altura, float peso) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.altura = altura;
		this.peso = peso;
		this.restricciones = new ArrayList<String>();
	}
	
}
