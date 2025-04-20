package atraccion;

import java.util.HashSet;

import usuario.OperadorAtraccion;
import usuario.Usuario;

public class AtraccionMecanica extends Atraccion{
	private int alturaMinima;
	private int alturaMaxima;
	private int pesoMaximo;
	private int pesoMinimo;
	private boolean riesgoAlto;
	public AtraccionMecanica(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            int nivelExclusividad, int alturaMinima, int alturaMaxima, int pesoMinimo, int pesoMaximo, boolean riesgoAlto) {
		super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
		this.alturaMinima = alturaMinima;
		this.alturaMaxima = alturaMaxima;
		this.pesoMaximo = pesoMaximo;
		this.pesoMinimo = pesoMinimo;
		this.riesgoAlto = riesgoAlto;
	}
	
	
	
	public boolean addOperador(OperadorAtraccion operador, String turno) {
		if (turno.equals(DIURNO) && !operador.getTurnoDia()){
			if(operador.getCapacitaciones().contains(this.nombre) || !this.riesgoAlto ) {
			this.operadoresDia.add(operador.getLogin());
			return true;
			}
		}
		if (turno.equals(NOCTURNO) && !operador.getTurnoNoche()){
			if(operador.getCapacitaciones().contains(this.nombre) || !this.riesgoAlto ) {
			this.operadoresNoche.add(operador.getLogin());
			return true;
			}
		}
		return false;
	}

	public int getAlturaMinima() {
		return alturaMinima;
	}

	public int getAlturaMaxima() {
		return alturaMaxima;
	}

	public int getPesoMaximo() {
		return pesoMaximo;
	}
	
	public int getPesoMinimo() {
		return pesoMinimo;
	}

	public HashSet<String> getRestriccionesSalud() {
		return restriccionesSalud;
	}

	public boolean isRiesgoAlto() {
		return riesgoAlto;
	}

	public void setAlturaMaxima(int alturaMaxima) {
		this.alturaMaxima = alturaMaxima;
	}
	
	public void setAlturaMinima(int alturaMinima) {
		this.alturaMinima = alturaMinima;
	}

	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	
	public void setPesoMinimo(int pesoMinimo) {
		this.pesoMinimo = pesoMinimo;
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
