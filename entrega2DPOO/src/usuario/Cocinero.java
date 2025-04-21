package usuario;

import java.util.*;
import servicios.Comestible;
import servicios.LugarDeServicio;
import servicios.Producto;
import servicios.Cafeteria;

public class Cocinero extends Cajero{
	
	private ArrayList<String> alimentosPreparables;

	public Cocinero(String nombre, String login, String password, int altura, int peso,
			String lugarDeTrabajo, LugarDeServicio lugarServicio) {
		super(nombre, login, password, altura, peso, lugarDeTrabajo, lugarServicio);
		this.alimentosPreparables = new ArrayList<String>();
	}
	
	public void addAlimentoPreparable(String alimento) {
		alimentosPreparables.add(alimento);
	}
	
	public boolean cocinar(String nombreComida, int cantidad) {
		if(lugarServicio.existeProducto(nombreComida) && alimentosPreparables.contains(nombreComida)) {
			Producto producto = parque.inventario.get(nombreComida);
			if(producto != null && producto.getClass().equals(Comestible.class)) {
				Comestible comida = (Comestible) producto;
				comida.cocinarMas(cantidad);
				parque.inventario.put(nombreComida, comida);
				return true;
			}
		}
		return false;
	}
}
