package usuario;

import java.util.ArrayList;

public class OperadorAtraccion extends Empleado{
	
	private ArrayList<String> capacitaciones;
	private String lugarDeTrabajoNoche = null;
	private String lugarDeTrabajoDia = null;
	
	
	public OperadorAtraccion(String nombre, String login, String password, int altura, int peso, String rol) {
		super(nombre, login, password, altura, peso, rol, null);
		this.capacitaciones = new ArrayList<>();
	}

	
	
	public String getLugarDeTrabajoNoche() {
		return lugarDeTrabajoNoche;
	}
	
	public String getLugarDeTrabajoDia() {
		return lugarDeTrabajoDia;
	}



	public boolean setLugarDeTrabajoNoche(String lugarDeTrabajoNoche) {
		this.lugarDeTrabajoNoche = lugarDeTrabajoNoche;
		return true;
	}
	public boolean setLugarDeTrabajoDia(String lugarDeTrabajoDia) {
		this.lugarDeTrabajoDia = lugarDeTrabajoDia;
		return true;
	}

	
	
	@Override
	public String getLugarDeTrabajo() {
		return lugarDeTrabajo+","+lugarDeTrabajoNoche;
	}
	
	@Override
	public boolean setLugarDeTrabajo(String lugarDeTrabajo) {return false;}
	
	
	
	public ArrayList<String> getCapacitaciones() {
		return capacitaciones;
	}
	
	
	public boolean setTurnoDia(boolean turnoDia) {
		return false;
	}

	public boolean setTurnoNoche(boolean turnoNoche) {
		return false;
	}
	
	
	public void addCapacitaciones(String capacitacion) {
		this.capacitaciones.add(capacitacion);
	}
	
}
