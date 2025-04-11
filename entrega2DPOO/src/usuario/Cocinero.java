package usuario;

import java.util.*;
import servicios.Comestible;
import servicios.LugarDeServicio;
import servicios.Producto;
import servicios.Cafeteria;

public class Cocinero extends Cajero{
	
	private ArrayList<String> alimentosPreparables;

	public Cocinero(String nombre, String login, String password, float altura, float peso, String rol, String turno,
			String lugarDeTrabajo, LugarDeServicio lugarServicio, ArrayList<String> alimentosPreparables) {
		super(nombre, login, password, altura, peso, rol, turno, lugarDeTrabajo, lugarServicio);
		this.alimentosPreparables = alimentosPreparables;
	}
	
	public boolean cocinar(String nombreComida, int cantidad) {
		if(lugarServicio.existeProducto(nombreComida) && alimentosPreparables.contains(nombreComida)) {
			Producto producto = this.parque.inventario.get(nombreComida);
			if(producto != null && String.valueOf(producto.getClass()).equals("Comestible")) {
				Comestible comida = (Comestible) producto;
				comida.cocinarMas(cantidad);
				this.parque.inventario.put(nombreComida, comida);
				return true;
			}
		}
		return false;
	}
}
