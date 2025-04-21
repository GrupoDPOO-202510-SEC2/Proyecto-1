package servicios;

import usuario.Usuario;

public class PedidoTiquete{
	
	private Usuario usuario;
	private String tipo;
	private String atraccionParaIndividuales;
	private String fechaInicioParaTemporales;
	private String fechaFinParaTemporales;
	private int exclusividad;
	private boolean fastPass;

	public PedidoTiquete(Usuario usuario, String tipo, String atraccionParaIndividuales,
			String fechaInicioParaTemporales, String fechaFinParaTemporales, int exclusividad, boolean fastPass) {
		this.usuario = usuario;
		this.tipo = tipo;
		this.atraccionParaIndividuales = atraccionParaIndividuales;
		this.fechaInicioParaTemporales = fechaInicioParaTemporales;
		this.fechaFinParaTemporales = fechaFinParaTemporales;
		this.exclusividad = exclusividad;
		this.fastPass = fastPass;
		
		if(tipo == "TiqueteExclusivo") {
			this.atraccionParaIndividuales = "";
			this.fechaInicioParaTemporales = "";
			this.fechaFinParaTemporales = "";
		}
		else if(tipo == "TiqueteTemporada") {
			this.atraccionParaIndividuales = "";
		}
		else if(tipo == "TiqueteIndividual") {
			this.exclusividad = 4;
			this.fechaInicioParaTemporales = "";
			this.fechaFinParaTemporales = "";
		}
		
		if(this.exclusividad > 4) {
			this.exclusividad = 4;
		}
		else if(this.exclusividad < 1) {
			this.exclusividad = 1;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public String getAtraccionParaIndividuales() {
		return atraccionParaIndividuales;
	}

	public String getFechaInicioParaTemporales() {
		return fechaInicioParaTemporales;
	}

	public String getFechaFinParaTemporales() {
		return fechaFinParaTemporales;
	}

	public int getExclusividad() {
		return exclusividad;
	}
	public boolean isFastPass(
) {
		return fastPass;
	}
}