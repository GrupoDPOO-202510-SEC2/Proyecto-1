package servicios;
import java.util.*;

public class Tienda extends LugarDeServicio{
	
	private ArrayList<String> items;

	public Tienda(String nombre, String ubicacion, String tipo) {
		super(nombre, ubicacion, tipo);
		this.tipo = "tienda";
		items = new ArrayList<String>();
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return items.contains(nombreP);
	}
}