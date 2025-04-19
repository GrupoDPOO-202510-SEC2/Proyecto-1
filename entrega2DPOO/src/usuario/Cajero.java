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
	
	protected LugarDeServicio lugarServicio = null;
	
	public Cajero(String nombre, String login, String password, int altura, int peso, String rol) {
		super(nombre, login, password, altura, peso, rol, null);
	}
	
	
	@Override
	public boolean setLugarDeTrabajo(String lugarDeTrabajo) {
		if(parque.mapaCafeterias.containsKey(lugarDeTrabajo)){
			this.lugarDeTrabajo = lugarDeTrabajo;
			this.lugarServicio = parque.mapaCafeterias.get(lugarDeTrabajo);
			return true;
		}if( parque.mapaTiendas.containsKey(lugarDeTrabajo)) {
			this.lugarDeTrabajo = lugarDeTrabajo;
			this.lugarServicio = parque.mapaTiendas.get(lugarDeTrabajo);
			return true;
		}
		return false;
	}
	
	
	public boolean vender(String nombreProducto, int cantidad) {
		boolean estaEnElMenu = lugarServicio.existeProducto(nombreProducto);
		boolean estaEnElInventario = parque.inventario.containsKey(nombreProducto);
		if(estaEnElMenu && estaEnElInventario) {
			Producto producto = parque.inventario.get(nombreProducto);
			boolean cafeteriaYComestible = lugarServicio.getTipo().equals("cafeteria") && String.valueOf(producto.getClass()).equals("Comestible");
			boolean tiendaYSouvenir = lugarServicio.getTipo().equals("tienda") && String.valueOf(producto.getClass()).equals("Souvenir");
			
			if((cafeteriaYComestible || tiendaYSouvenir) && (producto.getCantidad() >= cantidad)) {
				for(int i=0; i<cantidad; i++) {
					producto.vender();
				}
				parque.inventario.put(nombreProducto, producto);
				return true;
			}
		}
		return false;
	}
	
}
