package servicios;

public class Comestible extends Producto{

	public Comestible(String nombre, int cantidad) {
		super(nombre, cantidad);
	}
	
	@Override
	public void cocinarMas(int cuantosMas) {
		cantidad += cuantosMas;
	}
}