package servicios;

public class LugarDeServicio{
	protected String nombre;
	protected String ubicacion;
	protected String tipo;
	
	public LugarDeServicio(String nombre, String ubicacion, String tipo) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public boolean existeProducto(String nombreP) {return false;}
	
}