package atraccion;

import usuario.OperadorAtraccion;

public class AtraccionCultural extends Atraccion{
	
	
	private int edadMinima;
	private boolean esInteractiva;
	
	
	public AtraccionCultural(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            int nivelExclusividad, int edadMinima, boolean esInteractiva) {
	super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
	this.edadMinima = edadMinima;
	this.esInteractiva = esInteractiva;
	}


	public int getEdadMinima() {
		return edadMinima;
	}

	public boolean isEsInteractiva() {
		return esInteractiva;
	}
	
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public void setEsInteractiva(boolean esInteractiva) {
		this.esInteractiva = esInteractiva;
	}
	
	
	public boolean addOperador(OperadorAtraccion operador, String turno) {
		if (turno.equals(DIURNO)){
			this.operadoresDia.add(operador.getLogin());
			return true;
		}
		if (turno.equals(NOCTURNO)){
			this.operadoresNoche.add(operador.getLogin());
			return true;
		}
		return false;
	}
	
}
