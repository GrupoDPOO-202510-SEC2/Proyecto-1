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
	
	public boolean vender(String nombreProducto, int cantidad) {
		boolean estaEnElMenu = lugarServicio.existeProducto(nombreProducto);
		boolean estaEnElInventario = this.parque.inventario.containsKey(nombreProducto);
		if(estaEnElMenu && estaEnElInventario) {
			Producto producto = this.parque.inventario.get(nombreProducto);
			boolean cafeteriaYComestible = lugarServicio.getTipo().equals("cafeteria") && String.valueOf(producto.getClass()).equals("Comestible");
			boolean tiendaYSouvenir = lugarServicio.getTipo().equals("tienda") && String.valueOf(producto.getClass()).equals("Souvenir");
			
			if((cafeteriaYComestible || tiendaYSouvenir) && (producto.getCantidad() >= cantidad)) {
				for(int i=0; i<cantidad; i++) {
					producto.vender();
				}
				this.parque.inventario.put(nombreProducto, producto);
				return true;
			}
		}
		return false;
	}
	
}
