package tiquete;

public class Tiquete {
	
	protected double idTiquete;
	protected boolean valido;
	protected boolean fastPass;
	protected boolean enUso;
	
	public Tiquete(double idTiquete, boolean fastPass) {
		this.idTiquete = idTiquete;
		this.fastPass = fastPass;
		this.valido = true;
		
	}

	public double getIdTiquete() {
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
