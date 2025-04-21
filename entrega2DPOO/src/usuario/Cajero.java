package usuario;

import servicios.*;

public class Cajero extends Empleado {
	
	protected LugarDeServicio lugarServicio;
	
	public Cajero(String nombre, String login, String password, int altura, int peso,
			String lugarDeTrabajo, LugarDeServicio lugarServicio) {
		super(nombre, login, password, altura, peso, lugarDeTrabajo);
		this.lugarServicio = lugarServicio;
	}
	
	public boolean vender(String nombreProducto, int cantidad) {
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
