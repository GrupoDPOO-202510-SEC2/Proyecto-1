package servicios;

public class Producto {
	protected String nombre;
	protected int cantidad;
	
	public Producto(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void vender() {
		cantidad -= 1;
	}
	
	public void cocinarMas(int cuantosMas) {}
}