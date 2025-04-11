package atraccion;

import java.util.HashSet;

import usuario.OperadorAtraccion;

public class AtraccionMecanica extends Atraccion{
	private float alturaMinima;
	private float alturaMaxima;
	private float pesoMaximo;
	private HashSet<String> restriccionesSalud;
	private boolean riesgoAlto;
	public AtraccionMecanica(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            String nivelExclusividad, float alturaMinima, float alturaMaxima, float pesoMaximo, boolean riesgoAlto) {
		super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
		this.alturaMinima = alturaMinima;
		this.alturaMaxima = alturaMaxima;
		this.pesoMaximo = pesoMaximo;
		this.riesgoAlto = riesgoAlto;
		this.restriccionesSalud = new HashSet<>();
	}
	
	
	
	public boolean addOperador(OperadorAtraccion operador, String turno) {
		
		if (turno.equals(DIURNO) && !operador.getTurnoDia()){
			if(operador.getCapacitaciones().contains(this.nombre)) {
			this.operadoresDia.put(operador.getLogin(), operador);
			this.operativaDia = this.operadoresDia.size() >= this.empleadosMinimos;
			return true;
			}
		}
		if (turno.equals(NOCTURNO) && !operador.getTurnoNoche()){
			if(operador.getCapacitaciones().contains(this.nombre)) {
			this.operadoresNoche.put(operador.getLogin(), operador);
			this.operativaNoche = this.operadoresNoche.size() >= this.empleadosMinimos;
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



	public void setAlturaMinima(float alturaMinima) {
		this.alturaMinima = alturaMinima;
	}



	public void setAlturaMaxima(float alturaMaxima) {
		this.alturaMaxima = alturaMaxima;
	}



	public void setPesoMaximo(float pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}



	public void setRestriccionesSalud(String restriccionSalud) {
		this.restriccionesSalud.add(restriccionSalud);
	}
	
	public void deleteRestriccionesSalud(String restriccionSalud) {
		this.restriccionesSalud.remove(restriccionSalud);
	}



	public void setRiesgoAlto(boolean riesgoAlto) {
		this.riesgoAlto = riesgoAlto;
	}

	
	
	
}
