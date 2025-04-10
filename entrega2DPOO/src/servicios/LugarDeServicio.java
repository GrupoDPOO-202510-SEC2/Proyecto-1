package servicios;
import java.util.*;

public class LugarDeServicio{
	protected String nombre;
	protected String ubicacion;
	protected Queue<Pedido> pedidosSinAtender;
	protected String tipo;
	
	public LugarDeServicio(String nombre, String ubicacion, String tipo) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.pedidosSinAtender = new LinkedList<Pedido>();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public Queue<Pedido> getPedidosSinAtender(){
		return pedidosSinAtender;
	}
	
	public Producto getProducto(String nombreP) { return null; }
	public boolean existeProducto(String nombreP) { return false; }
}