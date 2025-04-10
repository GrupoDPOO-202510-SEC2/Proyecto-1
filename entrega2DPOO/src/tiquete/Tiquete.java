package tiquete;

public class Tiquete {
	
	protected String idTiquete;
	protected boolean valido;
	protected boolean fastPass;
	
	public Tiquete(String idTiquete, boolean fastPass) {
		this.idTiquete = idTiquete;
		this.fastPass = fastPass;
		this.valido = true;
	}

	public String getIdTiquete() {
		return idTiquete;
	}

	public boolean isValido() {
		return valido;
	}

	public boolean isFastPass() {
		return fastPass;
	}
	
	public void desvalidar() {
		valido = false;
	}
	
}
