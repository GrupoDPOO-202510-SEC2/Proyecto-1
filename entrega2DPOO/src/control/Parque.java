package control;

import atraccion.AtraccionCultural;
import atraccion.AtraccionMecanica;
import atraccion.Espectaculo;
import tiquete.Tiquete;
import usuario.Empleado;
import servicios.Cafeteria;
import servicios.Tienda;
import servicios.Taquilla;
import servicios.*;
import java.util.*;

public class Parque {
	public HashMap<String, AtraccionCultural> aCulturales;
	public HashMap<String, AtraccionMecanica> aMecanicas;
	public HashMap<String, Cafeteria> mapaCafeterias;
	public HashMap<String, Tienda> mapaTiendas;
	public HashMap<String, Espectaculo> espectaculos;
	public HashMap<Double, Tiquete> tiquetes;
	public HashMap<String, Producto> inventario;
	public HashMap<String, Empleado> empleados;
	private Taquilla taquilla;

	public Parque(String ubicacionTaquilla) {
		this.aCulturales = new HashMap<String, AtraccionCultural>();
		this.aMecanicas = new HashMap<String, AtraccionMecanica>();
		this.mapaCafeterias = new  HashMap<String, Cafeteria>();
		this.mapaTiendas = new HashMap<String, Tienda>();
		this.taquilla = new Taquilla("Entrada");
	}
		
	public AtraccionCultural getAtraccionCultural(String nombreAtraccion) {
		return aCulturales.get(nombreAtraccion);
	}
	
	public AtraccionMecanica getAtraccionMecanica(String nombreAtraccion) {
		return aMecanicas.get(nombreAtraccion);
	}
	
	public Espectaculo getEspectaculo(String nombreEspectaculo) {
		return espectaculos.get(nombreEspectaculo);
	}
	
	public Cafeteria getCafeteria(String nombreCafeteria) {
		return mapaCafeterias.get(nombreCafeteria);
	}
	
	public Tienda getTienda(String nombreTienda) {
		return mapaTiendas.get(nombreTienda);
	}
	
	public Taquilla getTaquilla() {
		return taquilla;
	}
	
	public void addTiquete(Tiquete tiquete, Double idTiquete) {
		this.tiquetes.put(idTiquete, tiquete);
	}
}