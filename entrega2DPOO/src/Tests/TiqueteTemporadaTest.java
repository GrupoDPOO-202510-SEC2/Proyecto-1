package Tests;

import static org.junit.jupiter.api.Assertions.*;
import tiquete.TiqueteTemporada;
import org.junit.jupiter.api.Test;

class TiqueteTemporadaTest {

	@Test
	void testIsValidoEnRango() {
		TiqueteTemporada tiquete = new TiqueteTemporada(10000001, false, 3, "2025-01-01", "2025-06-20");
		assertTrue(tiquete.isValido());
	}
	
	@Test
	void testIsValidoTemprano() {
		TiqueteTemporada tiquete = new TiqueteTemporada(10000001, false, 3, "2025-07-01", "2025-12-20");
		assertFalse(tiquete.isValido());
	}
	
	@Test
	void testIsValidoTarde() {
		TiqueteTemporada tiquete = new TiqueteTemporada(10000001, false, 3, "2024-07-01", "2024-12-20");
		assertFalse(tiquete.isValido());
	}
}
