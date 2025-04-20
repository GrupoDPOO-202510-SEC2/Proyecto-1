package tests;

import static org.junit.jupiter.api.Assertions.*;
import servicios.Producto;
import org.junit.jupiter.api.Test;

class ProductoTest {

	@Test
	void testVender() {
		Producto producto = new Producto("comida", 32);
		producto.vender();
		assertEquals(31, producto.getCantidad());
	}
	
	@Test
	void testVenderEnCeros() {
		Producto producto = new Producto("nada", 0);
		producto.vender();
		assertEquals(0, producto.getCantidad());
	}

}
