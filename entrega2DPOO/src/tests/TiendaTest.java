package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import servicios.Tienda;

class TiendaTest {
	
	Tienda constructorTienda() {
		ArrayList<String> itemsTest = new ArrayList<String>();;
		itemsTest.add("taza");
		itemsTest.add("iman");
		itemsTest.add("foto");
		
		Tienda tienda = new Tienda("nombre", "ubicacion", "cafeteria");
		
		tienda.setItems(itemsTest);
	
		return tienda;
	}
	
	@Test
	void testExisteProducto() {
		Tienda tienda = constructorTienda();
		
		assertTrue(tienda.existeProducto("taza"));
		assertTrue(tienda.existeProducto("iman"));
		assertTrue(tienda.existeProducto("foto"));
		assertFalse(tienda.existeProducto("sticker"));
	}
	
	@Test
	void testExisteProductoNull() {
		Tienda tienda = constructorTienda();
		
		assertFalse(tienda.existeProducto(null));
	}
	
	@Test
	void testExisteProductoMenuVacio() {
		Tienda tienda = new Tienda("nombre", "ubicacion", "cafeteria");
		
		assertFalse(tienda.existeProducto(null));
		assertFalse(tienda.existeProducto("carne"));
		assertFalse(tienda.existeProducto(""));
	}
	
	@Test
	void testExisteProductoCaracteresExtra() {
		Tienda tienda = constructorTienda();
		
		assertFalse(tienda.existeProducto("ñÑ´~^`¨¨¿¡°¬"));
	}

}
