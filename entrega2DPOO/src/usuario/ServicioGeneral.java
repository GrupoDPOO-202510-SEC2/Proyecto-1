package usuario;

public class ServicioGeneral extends Empleado{
	
	public ServicioGeneral(String nombre, String login, String password, float altura, float peso, String rol,
			String turno, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, rol, turno, lugarDeTrabajo);
	}

	
	public String getLugar() {
		return this.lugarDeTrabajo;
	}
	public void asignarLugar(String nuevoLugar) {
		lugarDeTrabajo = nuevoLugar;
	}
}
