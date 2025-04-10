package servicios;

import usuario.Usuario;

public class PedidoTiquete{
	
	private Usuario usuario;
	private String tipo;
	private String atraccionParaIndividuales;
	private String fechaInicioParaTemporales;
	private String fechaFinParaTemporales;
	private String exclusividad;
	private boolean fastPass;

	public PedidoTiquete(Usuario usuario, String tipo, String atraccionParaIndividuales,
			String fechaInicioParaTemporales, String fechaFinParaTemporales, String exclusividad, boolean fastPass) {
		this.usuario = usuario;
		this.tipo = tipo;
		this.atraccionParaIndividuales = atraccionParaIndividuales;
		this.fechaInicioParaTemporales = fechaInicioParaTemporales;
		this.fechaFinParaTemporales = fechaFinParaTemporales;
		this.exclusividad = exclusividad;
		this.fastPass = fastPass;
		
		if(tipo == "TiqueteExclusivo") {
			atraccionParaIndividuales = "";
			fechaInicioParaTemporales = "";
			fechaFinParaTemporales = "";
		}
		else if(tipo == "TiqueteTemporada") {
			atraccionParaIndividuales = "";
		}
		else if(tipo == "TiqueteIndividual") {
			exclusividad = "";
			fechaInicioParaTemporales = "";
			fechaFinParaTemporales = "";
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

	public String getExclusividad() {
		return exclusividad;
	}

	public boolean isFastPass() {
		return fastPass;
	}
}