package usuario;

import servicios.Taquilla;
import servicios.PedidoTiquete;
import usuario.Usuario;
import java.util.*;
import tiquete.*;

public class CajeroTaquilla extends Empleado{
	
	Taquilla taquilla;
	
	public CajeroTaquilla(String nombre, String login, String password, float altura, float peso, String rol,
			String lugarDeTrabajo, Taquilla taquilla) {
		super(nombre, login, password, altura, peso, rol, lugarDeTrabajo);
		this.taquilla = parque.getTaquilla();
	}

	public boolean dejarEntrar(Usuario usuario,double codigoTiquete) {
		
		Tiquete tiquete = parque.tiquetes.get(codigoTiquete);
		
		if (tiquete.isValido()) {
			usuario.setTiqueteEnUso(tiquete);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setLugarDeTrabajo(String lugarDeTrabajo) {return false;}
	
	public void registrarSalida(Usuario usuario) {
		
		if (String.valueOf(usuario.getTiqueteEnUso().getClass()).equals("TiqueteExclusividad")) {
			usuario.invalidarTiquete();
			usuario.setTiqueteEnUso(null);
		}
	}
	
	public void venderTiquete() {
		PedidoTiquete pedido = taquilla.pedidoEnFila();
		
		Double idTiquete = taquilla.generarCodigo();
		
		Tiquete nuevoTiquete = null;
		
		if(pedido.getTipo() == "TiqueteExclusivo") {
			nuevoTiquete = new TiqueteExclusividad(idTiquete, pedido.isFastPass(), pedido.getExclusividad());
		}
		else if(pedido.getTipo() == "TiqueteTemporada") {
			nuevoTiquete = new TiqueteTemporada(idTiquete, pedido.isFastPass(), pedido.getExclusividad(), pedido.getFechaInicioParaTemporales(), pedido.getFechaFinParaTemporales());
		}
		else if(pedido.getTipo() == "TiqueteIndividual") {
			nuevoTiquete = new Tiquete(idTiquete, pedido.isFastPass());
		}
		
		pedido.getUsuario().addTiquete(nuevoTiquete);
		parque.addTiquete(nuevoTiquete, idTiquete);
		
	}
	
}
