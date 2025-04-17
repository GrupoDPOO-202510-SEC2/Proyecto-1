package tiquete;

public class TiqueteExclusividad extends Tiquete{
	
	
	private int exclusividad;
    protected static final int BASICO = 1;
    protected static final int FAMILIAR = 2;
    protected static final int ORO = 3;
    protected static final int DIAMANTE = 4;

	public TiqueteExclusividad(double idTiquete, boolean fastPass, int exclusividad) {
		super(idTiquete, fastPass);
		this.exclusividad = exclusividad;
	}

	public int getExclusividad() {
		return exclusividad;
	}
}