package servicios;

import java.util.*;

public class Taquilla{
	
	private String ubicacion;
	private Queue<PedidoTiquete> pedidosTiquetes;
	private ArrayList<String> codigosUtilizados;
	
	public Taquilla(String ubicacion) {
		this.ubicacion = ubicacion;
		this.codigosUtilizados = new ArrayList<String>();
		this.pedidosTiquetes = new LinkedList<PedidoTiquete>();
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void nuevaPeticion(PedidoTiquete pedido) {
		pedidosTiquetes.add(pedido);
	}
	
	public boolean codigoEstaUtilizado(String codigo) {
		return codigosUtilizados.contains(codigo);
	}
	
	public void addCodigo(String codigo) {
		codigosUtilizados.add(codigo);
	}
	
	public PedidoTiquete pedidoEnFila() {
		return pedidosTiquetes.remove();
	}
}