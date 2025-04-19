package usuario;

import java.util.*;
import control.Parque;
import servicios.*;
import tiquete.*;

public class Usuario {
	private String nombre;
	private String login;
	private String password;
	private int altura;
	private int peso;
	private ArrayList<String> restricciones;
	private HashMap<String, Integer> compras;
	private Tiquete tiqueteEnUso;
	private ArrayList<Tiquete> tiquetesComprados;
	protected static Parque parque;
	protected static final String DIURNO = "diurno";
    protected static final String NOCTURNO = "nocturno";
    protected static final int BASICO = 1;
    protected static final int FAMILIAR = 2;
    protected static final int ORO = 3;
    protected static final int DIAMANTE = 4;
	
	public Usuario(String nombre, String login, String password, int altura, int peso) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.altura = altura;
		this.peso = peso;
		this.restricciones = new ArrayList<String>();
		this.compras = new HashMap<String, Integer>();
		this.tiqueteEnUso = null;
		this.tiquetesComprados= new ArrayList<Tiquete>();
	}
	
	public ArrayList<String> getRestricciones() {
		return restricciones;
	}
	
	public ArrayList<Double> getTiquetesFuncionales(){
		ArrayList<Double> retorno = new ArrayList<Double>();
		for(Tiquete tiquete:tiquetesComprados) {
			if(tiquete.isValido()) {
				retorno.add(tiquete.getIdTiquete());
			}
		}

		return retorno;
	}
	
	public void setTiqueteEnUso(Tiquete tiquete) {
		this.tiqueteEnUso = tiquete;
	}
	
	public void invalidarTiquete() {
		this.tiqueteEnUso.desvalidar();
	}
	
	public Tiquete getTiqueteEnUso() {
		return this.tiqueteEnUso;
	}
	
	public String getNombre() {
		return nombre;
	}


	public String getLogin() {
		return login;
	}


	public String getPassword() {
		return password;
	}


	public int getAltura() {
		return altura;
	}


	public int getPeso() {
		return peso;
	}
	
	
	public HashMap<String, Integer> getCompras(){
		return compras;
	}
	
	
	public ArrayList<Tiquete> getTiquetesComprados() {
		return tiquetesComprados;
	}

	
	public void addTiquete(Tiquete tiquete) {
		tiquetesComprados.add(tiquete);
	}
	
	public void comprarTiquete(String tipo, String atraccion, String fInicio, String fFin, int exclusividad, boolean fastPass) {
		PedidoTiquete nuevoPedido = new PedidoTiquete(this, tipo, atraccion, fInicio, fFin, exclusividad, fastPass);
		parque.getTaquilla().nuevaPeticion(nuevoPedido);
	}
	
	public void agregarRestriccion(String nuevaRestriccion) {
		restricciones.add(nuevaRestriccion);
	}
	
}
