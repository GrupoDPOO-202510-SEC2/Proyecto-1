package usuario;

import java.util.*;
import control.Parque;
import servicios.*;
import tiquete.*;

public class Usuario {
	private String nombre;
	private String login;
	private String password;
	private float altura;
	private float peso;
	private ArrayList<String> restricciones;
	private HashMap<String, Integer> compras;
	private Tiquete tiqueteEnUso;
	private ArrayList<Tiquete> tiquetesUsados;
	private ArrayList<Tiquete> tiquetesSinUsar;
	private Parque parque;
	
	public Usuario(String nombre, String login, String password, float altura, float peso) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.password = password;
		this.altura = altura;
		this.peso = peso;
		this.restricciones = new ArrayList<String>();
		this.compras = new HashMap<String, Integer>();
		this.tiqueteEnUso = null;
		this.tiquetesUsados = new ArrayList<Tiquete>();
		this.tiquetesSinUsar = new ArrayList<Tiquete>();
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


	public float getAltura() {
		return altura;
	}


	public float getPeso() {
		return peso;
	}
	
	public HashMap<String, Integer> getCompras(){
		return compras;
	}
	
	public void addTiquete(Tiquete tiquete) {
		tiquetesSinUsar.add(tiquete);
	}
	
	public void comprarTiquete(String tipo, String atraccion, String fInicio, String fFin, String exclusividad, boolean fastPass) {
		PedidoTiquete nuevoPedido = new PedidoTiquete(this, tipo, atraccion, fInicio, fFin, exclusividad, fastPass);
		parque.getTaquilla().nuevaPeticion(nuevoPedido);
	}
	
	public void comprarComida(String nombreCafeteria, String nombreComida, int cantidad) {
		Cafeteria cafe = parque.getCafeteria(nombreCafeteria);
		if(cafe.existeProducto(nombreComida) == true) {
			Comestible comida = (Comestible)cafe.getProducto(nombreComida);
			if(comida.getCantidad() >= cantidad) {
				Pedido pedido = new Pedido(this, nombreComida, cantidad);
				cafe.getPedidosSinAtender().add(pedido);
			}
		}
	}
	
	public void comprarSouvenir(String nombreTienda, String nombreS, int cantidad) {
		Cafeteria cafe = parque.getCafeteria(nombreTienda);
		if(cafe.existeProducto(nombreS) == true) {
			Comestible comida = (Comestible)cafe.getProducto(nombreS);
			if(comida.getCantidad() >= cantidad) {
				Pedido pedido = new Pedido(this, nombreS, cantidad);
				cafe.getPedidosSinAtender().add(pedido);
			}
		}
	}
	
	public void agregarRestriccion(String nuevaRestriccion) {
		restricciones.add(nuevaRestriccion);
	}
	
}
