package usuario;

public class ServicioGeneral extends Empleado{
	
	public ServicioGeneral(String nombre, String login, String password, int altura, int peso, String rol
			, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, rol, lugarDeTrabajo);
	}

	
	public void asignarLugar(String nuevoLugar) {
		lugarDeTrabajo = nuevoLugar;
	}
}
