package control;

import atraccion.AtraccionCultural;
import atraccion.AtraccionMecanica;
import servicios.Cafeteria;
import servicios.Tienda;
import servicios.Taquilla;
import java.util.*;

public class Parque {
	private HashMap<String, AtraccionCultural> aCulturales;
	private HashMap<String, AtraccionMecanica> aMecanicas;
	private HashMap<String, Cafeteria> mapaCafeterias;
	private HashMap<String, Tienda> mapaTiendas;
	private Taquilla taquilla;
	
	
	public Parque(HashMap<String, AtraccionCultural> aCulturales, HashMap<String, AtraccionMecanica> aMecanicas,
			HashMap<String, Cafeteria> mapaCafeterias, HashMap<String, Tienda> mapaTiendas, Taquilla taquilla) {
		this.aCulturales = aCulturales;
		this.aMecanicas = aMecanicas;
		this.mapaCafeterias = mapaCafeterias;
		this.mapaTiendas = mapaTiendas;
		this.taquilla = taquilla;
	}
	
	public AtraccionCultural getAtraccionCultural(String nombreAtraccion) {
		return aCulturales.get(nombreAtraccion);
	}
	
	public AtraccionMecanica getAtraccionMecanica(String nombreAtraccion) {
		return aMecanicas.get(nombreAtraccion);
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
}