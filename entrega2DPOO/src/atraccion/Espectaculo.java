package atraccion;

import java.util.*;

public class Espectaculo {

	private String nombre;
	private String horario;
	private String ubicacion;
	private String fechaInicio;
	private String fechaFin;
	private HashSet<String> climasRestringidos;
	
	public HashSet<String> getClimasRestringidos() {
		return climasRestringidos;
	}

	public Espectaculo(String nombre, String horario, String ubicacion, String fechaInicio, String fechaFin,
			HashSet<String> climasRestringidos) {
		this.nombre = nombre;
		this.horario = horario;
		this.ubicacion = ubicacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.climasRestringidos = climasRestringidos;
	}	
	
	public Espectaculo(String nombre, String horario, String ubicacion,HashSet<String> climasRestringidos) {

		this.nombre = nombre;
		this.horario = horario;
		this.ubicacion = ubicacion;
		this.fechaInicio = null;
		this.fechaFin = null;
		this.climasRestringidos = climasRestringidos;
		
		
		
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getHorario() {
		return horario;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
		
	
	public boolean addClimaRestringido(String clima){
		return this.climasRestringidos.add(clima);
	}
	
	public boolean deleteClimaRestringido(String clima){
		return this.climasRestringidos.remove(clima);
	}
	
}
