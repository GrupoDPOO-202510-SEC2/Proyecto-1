package usuario;

import java.util.ArrayList;

public class OperadorAtraccion extends Empleado{
	
	private ArrayList<String> capacitaciones;
	private String lugarDeTrabajoNoche;
	private String lugarDeTrabajoDia;
	
	public OperadorAtraccion(String nombre, String login, String password, int altura, int peso) {
		super(nombre, login, password, altura, peso, null);
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
		this.setTurnoNoche(true);
		return true;
	}
	public boolean setLugarDeTrabajoDia(String lugarDeTrabajoDia) {
		this.lugarDeTrabajoDia = lugarDeTrabajoDia;
		this.setTurnoDia(true);
		return true;
	}
	
	
	public void setCapacitaciones(ArrayList<String> capacitaciones) {
		this.capacitaciones = capacitaciones;
	}



	@Override
	public String getLugarDeTrabajo() {
		return lugarDeTrabajoDia + ", " + lugarDeTrabajoNoche;
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
