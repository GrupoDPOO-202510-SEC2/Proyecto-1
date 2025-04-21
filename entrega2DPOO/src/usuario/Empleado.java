package usuario;
import java.time.*;



public class Empleado extends Usuario{
	protected boolean turnoDia;
	protected boolean turnoNoche;
	protected String lugarDeTrabajo;
	protected boolean disponible;
	public Empleado(String nombre, String login, String password, int altura, int peso, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso);
		this.lugarDeTrabajo = lugarDeTrabajo;
		this.turnoDia = false;
		this.turnoNoche = false;
		this.disponible = estaDisponible();
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getLugarDeTrabajo() {
		return lugarDeTrabajo;
	}

	public boolean setLugarDeTrabajo(String lugarDeTrabajo) {
		this.lugarDeTrabajo = lugarDeTrabajo;
		return true;
	}
	
	public boolean getTurnoDia() {
		return this.turnoNoche;
	}
	
	public boolean getTurnoNoche() {
		return this.turnoNoche;
	}
	
	public boolean setTurnoDia(boolean turnoDia) {
		this.turnoDia = turnoDia;
		return true;
	}

	public boolean setTurnoNoche(boolean turnoNoche) {
		this.turnoNoche = turnoNoche;
		return true;
	}

	
	public boolean estaDisponible() {
		LocalTime horaAhora = LocalTime.now();
		LocalTime medioDia = LocalTime.of(12, 0, 0, 0);
		int ampm = horaAhora.compareTo(medioDia);
		if(ampm <= 0 && turnoDia) {
			disponible = true;
		}
		else if (ampm >= 0 && turnoNoche) {
			disponible = true;
		}
		else if (turnoDia && turnoNoche) {
			disponible = true;
		}
		else {
			disponible = false;
		}
		return disponible;
	}
}
