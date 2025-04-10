package tiquete;

public class TiqueteExclusividad extends Tiquete{
	
	
	private String exclusividad;

	public TiqueteExclusividad(String idTiquete, boolean fastPass, String exclusividad) {
		super(idTiquete, fastPass);
		this.exclusividad = exclusividad;
	}

	public String getExclusividad() {
		return exclusividad;
	}
}