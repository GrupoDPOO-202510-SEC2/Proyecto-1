package usuario;

import java.util.HashMap;

import servicios.LugarDeServicio;
import servicios.Pedido;
import servicios.Cafeteria;
import servicios.Tienda;
import servicios.Comestible;
import servicios.Souvenir;
import servicios.Producto;

public class Cajero extends Empleado {
	
	protected LugarDeServicio lugarServicio;
	
	public Cajero(String nombre, String login, String password, float altura, float peso, String rol,
			String lugarDeTrabajo, LugarDeServicio lugarServicio) {
		super(nombre, login, password, altura, peso, rol, lugarDeTrabajo);
		this.lugarServicio = lugarServicio;
	}
	
	public void vender() {
		Pedido pedidoEnAccion = lugarServicio.getPedidosSinAtender().remove();
		Usuario usuario = pedidoEnAccion.getUsuario();
		String nombreP = pedidoEnAccion.getNombreProducto();
		int cantidad = pedidoEnAccion.getCantidad();
		for(int i=0; i<cantidad; i++) {
			lugarServicio.getProducto(nombreP).vender();
		}
		
		HashMap<String, Integer> compras = usuario.getCompras();
		if(compras.containsKey(nombreP) == true) {
			int cantidadOriginal = compras.get(nombreP);
			cantidadOriginal += cantidad;
			compras.put(nombreP, cantidadOriginal);
		} else {
			compras.put(nombreP, cantidad);
		}
	}
	
}
