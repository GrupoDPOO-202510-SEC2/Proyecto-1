package Tests;

import static org.junit.jupiter.api.Assertions.*;
import servicios.Comestible;
import org.junit.jupiter.api.Test;

class ComestibleTest {

	@Test
	void testCocinarMas() {
		Comestible comida = new Comestible("carne", 0);
		comida.cocinarMas(3);
		assertEquals(3, comida.getCantidad());
	}
	
	@Test
	void testCocinarMasConNegativos() {
		Comestible comida = new Comestible("carne", 0);
		comida.cocinarMas(-3);
		assertEquals(0, comida.getCantidad());
	}
}
