package usuario;

import java.util.ArrayList;

public class OperadorAtraccion extends Empleado{
	
	private ArrayList<String> capacitaciones;
	
	public OperadorAtraccion(String nombre, String login, String password, float altura, float peso, String rol) {
		super(nombre, login, password, altura, peso, rol, null);
		this.capacitaciones = new ArrayList<>();
	}

	public ArrayList<String> getCapacitaciones() {
		return capacitaciones;
	}
	
	
	public void addCapacitaciones(String capacitacion) {
		this.capacitaciones.add(capacitacion);
	}
	
}
