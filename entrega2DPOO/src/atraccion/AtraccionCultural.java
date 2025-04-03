package atraccion;

public class AtraccionCultural extends Atraccion{
	private int edadMinima;
	private boolean esInteractiva;
	public AtraccionCultural(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            String nivelExclusividad, int edadMinima, boolean esInteractiva) {
	super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
	this.edadMinima = edadMinima;
	this.esInteractiva = esInteractiva;
	}
}
