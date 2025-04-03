package tiquete;

public abstract class Tiquete {
	public String idTiquete;
	public boolean valido;
	public boolean fastPass;
	public Tiquete(String idTiquete, boolean fastPass) {
		super();
		this.idTiquete = idTiquete;
		this.fastPass = fastPass;
	}
	
}
