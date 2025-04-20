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
	
	public Cajero(String nombre, String login, String password, int altura, int peso) {
		super(nombre, login, password, altura, peso, null);
	}
	
	
	@Override
	public boolean setLugarDeTrabajo(String lugarDeTrabajo) {
		if(parque.mapaCafeterias.containsKey(lugarDeTrabajo)){
			this.lugarDeTrabajo = lugarDeTrabajo;
			return true;
		}if( parque.mapaTiendas.containsKey(lugarDeTrabajo)) {
			this.lugarDeTrabajo = lugarDeTrabajo;
			return true;
		}
		return false;
	}
	
	
	public LugarDeServicio getLugarDeServicio() {
		LugarDeServicio lugarServicio;
		
		if(parque.mapaCafeterias.containsKey(this.lugarDeTrabajo)) {
			lugarServicio = parque.mapaCafeterias.get(this.lugarDeTrabajo);
		}else {
			lugarServicio = parque.mapaTiendas.get(this.lugarDeTrabajo);
		}
		
		return lugarServicio;
	}
	
	
	public boolean vender(String nombreProducto, int cantidad) {
		
		LugarDeServicio lugarServicio = getLugarDeServicio();
		
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
