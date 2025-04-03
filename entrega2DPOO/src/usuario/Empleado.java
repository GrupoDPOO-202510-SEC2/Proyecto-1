package usuario;

public abstract class Empleado extends Usuario{
	private String rol;
	private String turno;
	private boolean disponible;
	public Empleado(String nombre, String login, String password, float altura, float peso, String rol, String turno) {
		super(nombre, login, password, altura, peso);
		this.rol = rol;
		this.turno = turno;
	}
	
}
