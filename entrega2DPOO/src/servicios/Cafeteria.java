package servicios;
import java.util.*;

public class Cafeteria extends LugarDeServicio{
	
	private HashMap<String, Comestible> menu;


	public Cafeteria(String nombre, String ubicacion, String tipo, HashMap<String, Comestible> menu) {
		super(nombre, ubicacion, tipo);
		this.tipo = "cafeteria";
		this.menu = menu;
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return menu.containsKey(nombreP);
	}
	
	public Producto getProducto(String nombreP) {
		return menu.get(nombreP);
	}
	
}