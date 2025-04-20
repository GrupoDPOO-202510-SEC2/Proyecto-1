package usuario;

import java.util.*;
import servicios.Comestible;
import servicios.LugarDeServicio;
import servicios.Producto;
import servicios.Cafeteria;

public class Cocinero extends Cajero{
	
	private ArrayList<String> alimentosPreparables;

	public ArrayList<String> getAlimentosPreparables() {
		return alimentosPreparables;
	}

	public Cocinero(String nombre, String login, String password, int altura, int peso,
			String lugarDeTrabajo, ArrayList<String> alimentosPreparables) {
		super(nombre, login, password, altura, peso);
		this.alimentosPreparables = alimentosPreparables;
	}
	
	public boolean cocinar(String nombreComida, int cantidad) {
		if(getLugarDeServicio().existeProducto(nombreComida) && alimentosPreparables.contains(nombreComida)) {
			Producto producto = parque.inventario.get(nombreComida);
			if(producto != null && String.valueOf(producto.getClass()).equals("Comestible")) {
				Comestible comida = (Comestible) producto;
				comida.cocinarMas(cantidad);
				parque.inventario.put(nombreComida, comida);
				return true;
			}
		}
		return false;
	}
	
	public void addAlimentoPreparable(String alimento) {
		this.alimentosPreparables.add(alimento);
	}
	
	
}
