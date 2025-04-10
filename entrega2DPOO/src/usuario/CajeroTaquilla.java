package usuario;

import servicios.Taquilla;
import servicios.PedidoTiquete;
import usuario.Usuario;
import java.util.*;
import tiquete.*;

public class CajeroTaquilla extends Empleado{
	
	Taquilla taquilla;
	
	public CajeroTaquilla(String nombre, String login, String password, float altura, float peso, String rol,
			String turno, String lugarDeTrabajo, Taquilla taquilla) {
		super(nombre, login, password, altura, peso, rol, turno, lugarDeTrabajo);
		this.taquilla = taquilla;
	}
	
	public String nuevoCodigo(Usuario usuario, String tipo) {
		String codigo = "";
		codigo += usuario.getLogin();
		codigo += tipo;
		String codigoCabeza = codigo;
		boolean repetido = true;
		while (repetido) {
			for(int i=0; i<5; i++) {
				Random random = new Random();
				int rando = random.nextInt(10);
				codigo += String.valueOf(rando);
			}
			if(taquilla.codigoEstaUtilizado(codigo) == true) {
				codigo = codigoCabeza;
			}
			else {
				repetido = false;
			}
		}
		taquilla.addCodigo(codigo);
		return codigo;
	}
	
	public void venderTiquete() {
		PedidoTiquete pedido = taquilla.pedidoEnFila();
		
		String idTiquete = nuevoCodigo(pedido.getUsuario(), pedido.getTipo());
		
		Tiquete nuevoTiquete = null;
		
		if(pedido.getTipo() == "TiqueteExclusivo") {
			nuevoTiquete = new TiqueteExclusividad(idTiquete, pedido.isFastPass(), pedido.getExclusividad());
		}
		else if(pedido.getTipo() == "TiqueteTemporada") {
			nuevoTiquete = new TiqueteTemporada(idTiquete, pedido.isFastPass(), pedido.getExclusividad(), pedido.getFechaInicioParaTemporales(), pedido.getFechaFinParaTemporales());
		}
		else if(pedido.getTipo() == "TiqueteIndividual") {
			nuevoTiquete = new TiqueteIndividual(idTiquete, pedido.isFastPass(), pedido.getAtraccionParaIndividuales());
		}
		
		pedido.getUsuario().addTiquete(nuevoTiquete);
		
	}
	
}
