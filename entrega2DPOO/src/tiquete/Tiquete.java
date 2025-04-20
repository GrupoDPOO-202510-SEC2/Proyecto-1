package tiquete;

public class Tiquete {
	
	protected double idTiquete;
	protected boolean valido;
	protected boolean fastPass;
	
	public Tiquete(double idTiquete, boolean fastPass) {
		this.idTiquete = idTiquete;
		this.fastPass = fastPass;
		this.valido = true;
		
	}

	public Double getIdTiquete() {
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
