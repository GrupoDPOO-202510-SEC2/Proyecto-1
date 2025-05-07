package servicios;
import java.util.*;

public class Cafeteria extends LugarDeServicio{
	
	public static HashSet<String> menu;


	public Cafeteria(String nombre, String ubicacion, String tipo) {
		super(nombre, ubicacion, tipo);
		this.tipo = "cafeteria";
	}
	
	public static void setMenu(HashSet<String> menuNuevo) {
		Cafeteria.menu = menuNuevo;
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return menu.contains(nombreP);
	}
	
}