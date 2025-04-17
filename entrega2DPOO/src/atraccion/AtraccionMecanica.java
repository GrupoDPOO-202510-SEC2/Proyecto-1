package atraccion;

import java.util.HashSet;

import usuario.OperadorAtraccion;
import usuario.Usuario;

public class AtraccionMecanica extends Atraccion{
	private int alturaMinima;
	private int alturaMaxima;
	private int pesoMaximo;
	private int pesoMinimo;
	private HashSet<String> restriccionesSalud;
	private boolean riesgoAlto;
	public AtraccionMecanica(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            int nivelExclusividad, int alturaMinima, int alturaMaxima, int pesoMinimo, int pesoMaximo, boolean riesgoAlto) {
		super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
		this.alturaMinima = alturaMinima;
		this.alturaMaxima = alturaMaxima;
		this.pesoMaximo = pesoMaximo;
		this.pesoMinimo = pesoMinimo;
		this.riesgoAlto = riesgoAlto;
		this.restriccionesSalud = new HashSet<>();
	}
	
	
	
	public boolean addOperador(OperadorAtraccion operador, String turno) {
		if (turno.equals(DIURNO) && !operador.getTurnoDia()){
			if(operador.getCapacitaciones().contains(this.nombre) || !this.riesgoAlto ) {
			this.operadoresDia.put(operador.getLogin(), operador);
			return true;
			}
		}
		if (turno.equals(NOCTURNO) && !operador.getTurnoNoche()){
			if(operador.getCapacitaciones().contains(this.nombre) || !this.riesgoAlto ) {
			this.operadoresNoche.put(operador.getLogin(), operador);
			return true;
			}
		}
		return false;
	}

	public float getAlturaMinima() {
		return alturaMinima;
	}

	public float getAlturaMaxima() {
		return alturaMaxima;
	}

	public float getPesoMaximo() {
		return pesoMaximo;
	}

	public HashSet<String> getRestriccionesSalud() {
		return restriccionesSalud;
	}

	public boolean isRiesgoAlto() {
		return riesgoAlto;
	}

	public float getPesoMinimo() {
		return pesoMinimo;
	}

	public void setPesoMinimo(int pesoMinimo) {
		this.pesoMinimo = pesoMinimo;
	}

	public void setAlturaMinima(int alturaMinima) {
		this.alturaMinima = alturaMinima;
	}

	public void setAlturaMaxima(int alturaMaxima) {
		this.alturaMaxima = alturaMaxima;
	}



	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}



	public void addRestriccionesSalud(String restriccionSalud) {
		this.restriccionesSalud.add(restriccionSalud);
	}
	
	public void deleteRestriccionesSalud(String restriccionSalud) {
		this.restriccionesSalud.remove(restriccionSalud);
	}
	
	public void setRiesgoAlto(boolean riesgoAlto) {
		this.riesgoAlto = riesgoAlto;
	}

	public boolean cumpleCondicionesFisicas(Usuario usuario) {
		if(this.alturaMinima>usuario.getAltura() && usuario.getAltura() > this.alturaMaxima) {
			if(this.pesoMinimo > usuario.getPeso() && usuario.getPeso() > this.pesoMaximo) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
