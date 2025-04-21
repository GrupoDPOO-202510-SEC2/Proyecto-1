package usuario;

import servicios.*;

public class Cajero extends Empleado {
	
	public Cajero(String nombre, String login, String password, int altura, int peso,
			String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, lugarDeTrabajo);
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
		
		if(parque.mapaCafeterias.containsKey(this.lugarDeTrabajo)) {
			 return (Cafeteria) parque.mapaCafeterias.get(this.lugarDeTrabajo);
		}else {
			return (Tienda) parque.mapaTiendas.get(this.lugarDeTrabajo);
		}
	}
	
	
	public boolean vender(String nombreProducto, int cantidad) {
		
		LugarDeServicio lugarServicio = getLugarDeServicio();
		
		boolean estaEnElMenu = lugarServicio.existeProducto(nombreProducto);
		boolean estaEnElInventario = parque.inventario.containsKey(nombreProducto);
		if(estaEnElMenu && estaEnElInventario) {
			Producto producto = parque.inventario.get(nombreProducto);
			boolean cafeteriaYComestible = lugarServicio.getTipo().equals("cafeteria") && producto.getClass().equals(Comestible.class);
			boolean tiendaYSouvenir = lugarServicio.getTipo().equals("tienda") && producto.getClass().equals(Souvenir.class);
			if((cafeteriaYComestible || tiendaYSouvenir) && (cantidad < producto.getCantidad())) {
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
