package servicios;

import java.util.*;

public class Taquilla{
	
	private String ubicacion;
	private Queue<PedidoTiquete> pedidosTiquetes;
	private static double IDs = 10000000;
	
	
	public Taquilla(String ubicacion) {
		this.ubicacion = ubicacion;
		this.pedidosTiquetes = new LinkedList<PedidoTiquete>();
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void nuevaPeticion(PedidoTiquete pedido) {
		pedidosTiquetes.add(pedido);
	}
	
	public double generarCodigo() {
		IDs++; return IDs;
	}
	
	public PedidoTiquete pedidoEnFila() {
		return pedidosTiquetes.remove();
	}
}