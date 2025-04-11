package atraccion;

import java.util.HashMap;
import java.util.HashSet;
import usuario.*;

public abstract class Atraccion {
    private String nombre;
    private int capacidadMaxima;
    private int empleadosMinimos;
    private boolean operativa;
    private String ubicacion;
    private String nivelExclusividad;
    private HashSet<String> climasRestringidos;
    private HashMap<String, OperadorAtraccion> Operadores;
    

    public Atraccion(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
                     String nivelExclusividad) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadosMinimos = empleadosMinimos;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.climasRestringidos = new HashSet<>();
    }


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}


	public void setEmpleadosMinimos(int empleadosMinimos) {
		this.empleadosMinimos = empleadosMinimos;
	}


	public void setOperativa(boolean operativa) {
		this.operativa = operativa;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public void setNivelExclusividad(String nivelExclusividad) {
		this.nivelExclusividad = nivelExclusividad;
	}

	public void addClimasRestringidos(String climaRestringido) {
		this.climasRestringidos.add(climaRestringido);
		
	}
	
	public void deleteClimasRestringidos(String climaRestringido) {
		this.climasRestringidos.remove(climaRestringido);
	}


	public String getNombre() {
		return nombre;
	}


	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}


	public int getEmpleadosMinimos() {
		return empleadosMinimos;
	}


	public boolean isOperativa() {
		return operativa;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public String getNivelExclusividad() {
		return nivelExclusividad;
	}


	public HashSet<String> getClimasRestringidos() {
		return climasRestringidos;
	}
	
	
	
	
	
    
}


