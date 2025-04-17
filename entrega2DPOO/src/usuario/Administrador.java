package usuario;
import java.util.HashSet;

import atraccion.*;


public class Administrador extends Usuario{

	public Administrador(String nombre, String login, String password, float altura, float peso) {
		super(nombre, login, password, altura, peso);
	}
	
	
	//-------------ESPECTACULOS--------------------------------------//
	
	public void crearEspectaculo(String nombre, String horario, String ubicacion) {
		parque.espectaculos.put(nombre,new Espectaculo(nombre, horario, ubicacion, new HashSet<String>()));
	}
	
	public void cambiarFechaEspectaculo(String nombre, String fechai, String fechaf) {
		parque.espectaculos.get(nombre).setFechaInicio(fechai);
		parque.espectaculos.get(nombre).setFechaInicio(fechaf);
	}
	
	public Espectaculo getEspectaculo(String nombre) {
		return parque.espectaculos.get(nombre);
	}
	
	public boolean agregarClimaRestringidoE(String espectaculo,String clima) {
		return parque.espectaculos.get(espectaculo).addClimaRestringido(clima);
	}
	
	public boolean eliminarClimaRestringidoE(String espectaculo,String clima) {
		return parque.espectaculos.get(espectaculo).deleteClimaRestringido(clima);
	}
	
	
	
	//-------------ATRACCIONES EWEWEWE--------------------------------------//
	

	
	
	public boolean crearAtraccionMecanica(String nombre, int capacidad, int empleadosMin, String ubicacion, int exclusividad, int alturaMax, int alturaMin, int pesoMin, int pesoMax, boolean riesgoAlto){
	
		parque.aMecanicas.put(nombre, new AtraccionMecanica(nombre, capacidad, empleadosMin, ubicacion, exclusividad, alturaMin, alturaMax, pesoMin, pesoMax, riesgoAlto));
		return parque.aMecanicas.containsKey(nombre);
	}
	
	public boolean crearAtraccionCultural(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion, int nivelExclusividad, int edadMinima, boolean esInteractiva) {
		parque.aCulturales.put(nombre, new AtraccionCultural(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad, edadMinima, esInteractiva));
		return parque.aCulturales.containsKey(nombre);
	}
	
	public Atraccion getAtraccion(String nombre) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			return parque.aMecanicas.get(nombre);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			return parque.aCulturales.get(nombre);
		}
		return null;	
	}
	
	public void setCapacidadMaxima(String nombre, int cantidad) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setCapacidadMaxima(cantidad);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setCapacidadMaxima(cantidad);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public void setEmpleadosMinimos(String nombre, int cantidad) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setEmpleadosMinimos(cantidad);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setEmpleadosMinimos(cantidad);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public void setEnServicio(String nombre, boolean enServicio) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setEnServicio(enServicio);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setEnServicio(enServicio);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	

		
}
