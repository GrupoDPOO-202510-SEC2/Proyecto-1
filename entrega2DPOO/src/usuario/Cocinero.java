package usuario;

import java.util.*;
import servicios.Comestible;
import servicios.LugarDeServicio;
import servicios.Cafeteria;

public class Cocinero extends Cajero{
	
	private ArrayList<String> alimentosPreparables;

	public Cocinero(String nombre, String login, String password, float altura, float peso, String rol, String turno,
			String lugarDeTrabajo, LugarDeServicio lugarServicio, ArrayList<String> alimentosPreparables) {
		super(nombre, login, password, altura, peso, rol, turno, lugarDeTrabajo, lugarServicio);
		this.alimentosPreparables = alimentosPreparables;
	}
	
	public void cocinar(String nombreComida, int cantidad) {
		if((lugarServicio.existeProducto(nombreComida) == true) && (alimentosPreparables.contains(nombreComida))) {
			lugarServicio = (Cafeteria)lugarServicio;
			lugarServicio.getProducto(nombreComida).cocinarMas(cantidad);
		}
	}
}
