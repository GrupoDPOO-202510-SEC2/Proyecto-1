package tiquete;

public class TiqueteIndividual extends Tiquete{
	
	private String nombreAtraccion;

	public TiqueteIndividual(String idTiquete, boolean fastPass, String nombreAtraccion) {
		super(idTiquete, fastPass);
		this.nombreAtraccion = nombreAtraccion;
	}

	public String getNombreAtraccion() {
		return nombreAtraccion;
	}
	
}