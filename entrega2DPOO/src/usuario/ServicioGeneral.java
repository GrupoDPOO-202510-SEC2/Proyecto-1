package usuario;

public class ServicioGeneral extends Empleado{
	
	public ServicioGeneral(String nombre, String login, String password, int altura, int peso
			, String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, lugarDeTrabajo);
	}

	
	public void asignarLugar(String nuevoLugar) {
		lugarDeTrabajo = nuevoLugar;
	}
}
