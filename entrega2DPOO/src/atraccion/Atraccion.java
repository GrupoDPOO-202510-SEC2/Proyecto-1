package atraccion;

import java.util.HashMap;
import java.util.HashSet;
import usuario.*;
import java.time.*;

public abstract class Atraccion { 
	
    private int capacidadMaxima;
    private int cantidadDePpl = 0;
    private String ubicacion;
    private int nivelExclusividad;
    private HashSet<String> climasRestringidos;
    protected HashSet<String> restriccionesSalud;
    protected String nombre;
    protected int empleadosMinimos;
    protected boolean enServicio;
    protected HashSet<String> operadoresDia;
    protected HashSet<String> operadoresNoche;
    protected static final String DIURNO = "diurno";
    protected static final String NOCTURNO = "nocturno";
    protected static final int BASICO = 1;
    protected static final int FAMILIAR = 2;
    protected static final int ORO = 3;
    protected static final int DIAMANTE = 4;
    

    public Atraccion(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
                     int nivelExclusividad) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadosMinimos = empleadosMinimos;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.climasRestringidos = new HashSet<String>();
        this.restriccionesSalud = new HashSet<String>();
        this.operadoresDia = new HashSet<String>();
        this.operadoresNoche = new HashSet<String>();
    }
    
    public void setClimasRestringidos(HashSet<String> climasRestringidos) {
		this.climasRestringidos = climasRestringidos;
	}

	public void setRestriccionesSalud(HashSet<String> restriccionesSalud) {
		this.restriccionesSalud = restriccionesSalud;
	}

	public void setOperadoresDia(HashSet<String> operadoresDia) {
		this.operadoresDia = operadoresDia;
	}

	public void setOperadoresNoche(HashSet<String> operadoresNoche) {
		this.operadoresNoche = operadoresNoche;
	}

	public HashSet<String> getRestriccionesSalud() {
		return restriccionesSalud;
	}

	public int getOperadoresDiaSize() {
		return operadoresDia.size();
	}

	public int getOperadoresNocheSize() {
		return operadoresNoche.size();
	}

	public int getCantidadDePpl() {
		return cantidadDePpl;
	}

	public void setCantidadDePpl(int cantidadDePpl) {
		this.cantidadDePpl = cantidadDePpl;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public void setEmpleadosMinimos(int empleadosMinimos) {
		this.empleadosMinimos = empleadosMinimos;
	}

	public void setEnServicio(boolean operativa) {
		this.enServicio = operativa;
	}
	public void setNivelExclusividad(int nivelExclusividad) {
		this.nivelExclusividad = nivelExclusividad;
	}

	public boolean addClimasRestringidos(String climaRestringido) {
		return this.climasRestringidos.add(climaRestringido);
		
	}
	
	public boolean removeClimasRestringidos(String climaRestringido) {
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


	public boolean isEnServicio() {
		
		return this.enServicio;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public int getNivelExclusividad() {
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
	
	
	
	public boolean estaRestringido(Usuario usuario) {
		for(String restriccion:usuario.getRestricciones()) {
			if(this.restriccionesSalud.contains(restriccion));
				return true;
		}
		return false;
	}
	
	public boolean addRestriccionSalud(String restriccion) {
		return this.restriccionesSalud.add(restriccion);
	}
	public boolean removeRestriccionSalud(String restriccion) {
		return this.restriccionesSalud.remove(restriccion);
	}
	
	
	public boolean isOperable() {
		if(isDia()) {
			if(this.getOperadoresDiaSize() >= this.getEmpleadosMinimos()) {
				return true;
			}
			return false;
		}
		if(this.getOperadoresNocheSize() >= this.getEmpleadosMinimos()) {
			return true;
		}
		return false;
	}
	
	public HashSet<String> getOperadoresDia() {
		return operadoresDia;
	}

	public HashSet<String> getOperadoresNoche() {
		return operadoresNoche;
	}

	public boolean isDia() {
		LocalTime horaAhora = LocalTime.now();
		LocalTime medioDia = LocalTime.of(12, 0, 0, 0);
		int ampm = horaAhora.compareTo(medioDia);
		if(ampm >= 0) {
			return false;
		}
		return true;
	}
}


