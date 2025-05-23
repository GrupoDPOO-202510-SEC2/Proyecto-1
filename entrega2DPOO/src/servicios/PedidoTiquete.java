package servicios;

import usuario.CajeroTaquilla;
import usuario.Usuario;

public class PedidoTiquete{
	
	private String loginUsuario;
	private String tipo;
	private String fechaInicioParaTemporales;
	private String fechaFinParaTemporales;
	private int exclusividad;
	private boolean fastPass;

	public PedidoTiquete(String loginUsuario, String tipo,
			String fechaInicioParaTemporales, String fechaFinParaTemporales, int exclusividad, boolean fastPass) {
		
		if(exclusividad>4) {
			exclusividad = 4;
		}
		if(exclusividad<1) {
			exclusividad = 1;
		}
		
		this.loginUsuario = loginUsuario;
		this.tipo = tipo;
		this.fechaInicioParaTemporales = fechaInicioParaTemporales;
		this.fechaFinParaTemporales = fechaFinParaTemporales;
		this.exclusividad = exclusividad;
		this.fastPass = fastPass;
		
		if(tipo.equals("TiqueteExclusivo")) {
			fechaInicioParaTemporales = "";
			fechaFinParaTemporales = "";
		}
		else if(tipo.equals("TiqueteTemporada")) {
		}
		else if(tipo.equals("TiqueteIndividual")) {
			exclusividad = 4;
			fechaInicioParaTemporales = "";
			fechaFinParaTemporales = "";
		}
	}

	public String getLogin() {
		return loginUsuario;
	}

	public String getTipo() {
		return tipo;
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