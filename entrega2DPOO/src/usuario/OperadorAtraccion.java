package usuario;

import java.util.ArrayList;

public class OperadorAtraccion extends Empleado{
	private ArrayList<String> capacitaciones;
	public OperadorAtraccion(String nombre, String login, String password, float altura, float peso, String rol,
			String turno) {
		super(nombre, login, password, altura, peso, rol, turno);
		this.capacitaciones = new ArrayList<>();

	}
}
