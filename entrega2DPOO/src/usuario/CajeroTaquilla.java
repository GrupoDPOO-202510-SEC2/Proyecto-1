package usuario;

import servicios.Taquilla;
import servicios.PedidoTiquete;
import usuario.Usuario;
import java.util.*;
import tiquete.*;

public class CajeroTaquilla extends Empleado{
	
	public CajeroTaquilla(String nombre, String login, String password, int altura, int peso, 
			String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, lugarDeTrabajo);
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
		
		if (usuario.getTiqueteEnUso().getClass().getSimpleName().equals("TiqueteExclusividad")) {
			usuario.invalidarTiquete();
			usuario.setTiqueteEnUso(null);
		}
	}
	
	public static void venderTiquete() {
		PedidoTiquete pedido = parque.taquilla.pedidoEnFila();
		
		Double idTiquete = Taquilla.generarCodigo();
		
		Tiquete nuevoTiquete = null;
		
		if(pedido.getTipo().equals("TiqueteExclusivo")) {
			nuevoTiquete = new TiqueteExclusividad(idTiquete, pedido.isFastPass(), pedido.getExclusividad());
		}
		else if(pedido.getTipo().equals("TiqueteTemporada")) {
			nuevoTiquete = new TiqueteTemporada(idTiquete, pedido.isFastPass(), pedido.getExclusividad(), pedido.getFechaInicioParaTemporales(), pedido.getFechaFinParaTemporales());
		}
		else if(pedido.getTipo().equals("TiqueteIndividual")) {
			nuevoTiquete = new Tiquete(idTiquete, pedido.isFastPass());
		}
		
		parque.usuarios.get(pedido.getLogin()).addTiquete(nuevoTiquete);
		parque.addTiquete(nuevoTiquete, idTiquete);
		
	}
	
}
