package servicios;
import java.util.*;

public class Tienda extends LugarDeServicio{
	
	public static HashSet<String> items;

	public Tienda(String nombre, String ubicacion, String tipo) {
		super(nombre, ubicacion, tipo);
		this.tipo = "tienda";
		items = new HashSet<String>();
	}
	
	public void setItems(HashSet<String> itemsNuevo) {
		Tienda.items = itemsNuevo;
	}
	
	@Override
	public boolean existeProducto(String nombreP) {
		return items.contains(nombreP);
	}
	
	
	public static HashSet<String> getitems() {
		return items;
	}

	public static boolean additem(String item) {
		return Tienda.items.add(item);
	}
	
	public static boolean removeitem(String item) {
		return Tienda.items.remove(item);
	}
}