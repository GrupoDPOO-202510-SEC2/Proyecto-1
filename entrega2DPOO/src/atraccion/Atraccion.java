package atraccion;

import java.util.HashMap;
import java.util.HashSet;
import usuario.*;

public abstract class Atraccion {
    protected String nombre;
    private int capacidadMaxima;
    private int cantidadDePpl;
    protected int empleadosMinimos;
    protected boolean operativaDia;
    protected boolean operativaNoche;
    private String ubicacion;
    private String nivelExclusividad;
    private HashSet<String> climasRestringidos;
    protected HashMap<String, OperadorAtraccion> operadoresDia;
    protected HashMap<String, OperadorAtraccion> operadoresNoche;
    protected static final String DIURNO = "diurno";
    protected static final String NOCTURNO = "nocturno";
    

    public Atraccion(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
                     String nivelExclusividad) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadosMinimos = empleadosMinimos;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.climasRestringidos = new HashSet<>();
    }

    
    

	public int getCantidadDePpl() {
		return cantidadDePpl;
	}

	public void setCantidadDePpl(int cantidadDePpl) {
		this.cantidadDePpl = cantidadDePpl;
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

	public void setOperativaDia(boolean operativa) {
		this.operativaDia = operativa;
	}
	public void setOperativaNoche(boolean operativa) {
		this.operativaNoche= operativa;
	}

	public void setNivelExclusividad(String nivelExclusividad) {
		this.nivelExclusividad = nivelExclusividad;
	}

	public boolean addClimasRestringidos(String climaRestringido) {
		return this.climasRestringidos.add(climaRestringido);
		
	}
	
	public boolean deleteClimasRestringidos(String climaRestringido) {
		return this.climasRestringidos.remove(climaRestringido);
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


	public boolean[] isOperativa() {
		
		return new boolean[] {this.operativaDia,this.operativaNoche};
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
	
	public boolean deleteOperador(String login, String turno) {
		if (turno.equals(DIURNO)) {
			operadoresDia.remove(login);
			return true;
		}
		if (turno.equals(NOCTURNO)) {
			operadoresNoche.remove(login);
			return true;
		}
		
		return false;
	}
	
}


