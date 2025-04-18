package servicios;
import java.util.*;

public class Cafeteria extends LugarDeServicio{
	
	private ArrayList<String> menu;


	public Cafeteria(String nombre, String ubicacion, String tipo) {
		super(nombre, ubicacion, tipo);
		this.tipo = "cafeteria";
		this.menu = new ArrayList<String>();
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return menu.contains(nombreP);
	}
	
	
	
}