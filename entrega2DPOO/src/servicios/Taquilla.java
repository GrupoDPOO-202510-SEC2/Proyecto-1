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
	
	public static double generarCodigo() {
		IDs++; return IDs;
	}
	
	public Queue<PedidoTiquete> getPedidosTiquetes() {
		return pedidosTiquetes;
	}

	public void addPedidoTiquete(PedidoTiquete pedido) {
		this.pedidosTiquetes.add(pedido);
	}
	
	
	public static double getIDs() {
		return IDs;
	}

	public static void setIDs(double iDs) {
		IDs = iDs;
	}

	public PedidoTiquete pedidoEnFila() {
		return pedidosTiquetes.remove();
	}
}