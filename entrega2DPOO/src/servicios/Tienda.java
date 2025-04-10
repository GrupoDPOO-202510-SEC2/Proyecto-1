package servicios;
import java.util.*;

public class Tienda extends LugarDeServicio{
	
	private HashMap<String, Souvenir> items;

	public Tienda(String nombre, String ubicacion, String tipo, HashMap<String, Souvenir> items) {
		super(nombre, ubicacion, tipo);
		this.tipo = "tienda";
		this.items = items;
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return items.containsKey(nombreP);
	}
	
	public Producto getProducto(String nombreP) {
		return items.get(nombreP);
	}
	
}