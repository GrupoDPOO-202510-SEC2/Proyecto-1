package usuario;
import java.time.*;

public class Empleado extends Usuario{
	protected String rol;
	protected String turno;
	protected String lugarDeTrabajo;
	protected boolean disponible;
	public Empleado(String nombre, String login, String password, float altura, float peso, String rol, String turno, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso);
		this.rol = rol;
		this.turno = turno;
		this.lugarDeTrabajo = lugarDeTrabajo;
		this.disponible = estaDisponible();
	}
	
	public String getRol() {
		return this.rol;
	}
	
	public String getTurno() {
		return this.turno;
	}
	
	public boolean estaDisponible() {
		LocalTime horaAhora = LocalTime.now();
		LocalTime medioDia = LocalTime.of(12, 0, 0, 0);
		int ampm = horaAhora.compareTo(medioDia);
		if(ampm <= 0 && turno == "diurno") {
			disponible = true;
		}
		else if (ampm >= 0 && turno == "nocturno") {
			disponible = true;
		}
		else if (turno == "ambos") {
			disponible = true;
		}
		else {
			disponible = false;
		}
		return disponible;
	}
}
