package usuario;
import java.time.*;



public class Empleado extends Usuario{
	protected String rol;
	protected boolean turnoDia;
	protected boolean turnoNoche;
	protected String lugarDeTrabajo;
	protected boolean disponible;
	public Empleado(String nombre, String login, String password, float altura, float peso, String rol, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso);
		this.rol = rol;
		this.lugarDeTrabajo = lugarDeTrabajo;
		this.disponible = estaDisponible();
	}
	
	public String getRol() {
		return this.rol;
	}
	
	
	public boolean getTurnoDia() {
		return this.turnoNoche;
	}
	
	public boolean getTurnoNoche() {
		return this.turnoNoche;
	}
	
	public void setTurnoDia(boolean turnoDia) {
		this.turnoDia = turnoDia;
	}

	public void setTurnoNoche(boolean turnoNoche) {
		this.turnoNoche = turnoNoche;
	}

	public String getLugar() {
		return this.lugarDeTrabajo;
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
