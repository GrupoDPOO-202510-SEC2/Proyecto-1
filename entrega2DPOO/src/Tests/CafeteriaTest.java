package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import servicios.Cafeteria;

class CafeteriaTest {
	
	Cafeteria constructorCafeteria() {
		HashSet<String> menuTest = new HashSet<String>();;
		menuTest.add("papa");
		menuTest.add("carne");
		menuTest.add("pollo");
		
		Cafeteria cafeteria = new Cafeteria("nombre", "ubicacion", "cafeteria");
		
		cafeteria.setMenu(menuTest);
		
		return cafeteria;
	}
	
	@Test
	void testExisteProducto() {
		Cafeteria cafeteria = constructorCafeteria();
		
		assertTrue(cafeteria.existeProducto("papa"));
		assertTrue(cafeteria.existeProducto("carne"));
		assertTrue(cafeteria.existeProducto("pollo"));
		assertFalse(cafeteria.existeProducto("chorizo"));
	}
	
	@Test
	void testExisteProductoNull() {
		Cafeteria cafeteria = constructorCafeteria();
		
		assertFalse(cafeteria.existeProducto(null));
	}
	
	@Test
	void testExisteProductoMenuVacio() {
		Cafeteria cafeteria = new Cafeteria("nombre", "ubicacion", "cafeteria");
		
		assertFalse(cafeteria.existeProducto(null));
		assertFalse(cafeteria.existeProducto("carne"));
		assertFalse(cafeteria.existeProducto(""));
	}
	
	@Test
	void testExisteProductoCaracteresExtra() {
		Cafeteria cafeteria = new Cafeteria("nombre", "ubicacion", "cafeteria");
		
		assertFalse(cafeteria.existeProducto("ñÑ´~^`¨¨¿¡°¬"));
	}

}
