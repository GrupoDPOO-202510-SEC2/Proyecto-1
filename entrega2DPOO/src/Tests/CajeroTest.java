package Tests;

import static org.junit.jupiter.api.Assertions.*;
import usuario.Cajero;
import java.util.*;
import servicios.*;
import control.Parque;
import org.junit.jupiter.api.Test;

class CajeroTest {
	
	Parque parque = crearParque();
	
	Parque crearParque() {
		Parque parque = new Parque("Entrada");
		
		Comestible comida = new Comestible("carne", 10);
		Souvenir souv = new Souvenir("taza", 10);
		
		parque.inventario.put(comida.getNombre(), comida);
		parque.inventario.put(souv.getNombre(), souv);
		
		Cafeteria cafeteria = new Cafeteria("Juan Valdez", "Salida", "cafeteria");
		HashSet<String> menu = new HashSet<String>();
		menu.add("carne");
		cafeteria.setMenu(menu);
		parque.mapaCafeterias.put("Juan Valdez", cafeteria);
		
		Tienda tienda = new Tienda("Cachivaches", "Salida", "tienda");
		HashSet<String> items = new HashSet<String>();
		items.add("taza");
		tienda.setItems(items);
		parque.mapaTiendas.put("Cachivaches", tienda);
		
		return parque;
	}
	
	Cajero crearCajeroCafeteria() {
		Cajero.parque = parque;
		Cajero cajero = new Cajero ("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Juan Valdez");
		return cajero;
	}
	
	Cajero crearCajeroTienda() {
		Cajero.parque = parque;
		Cajero cajero = new Cajero ("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Cachivaches");
		return cajero;
	}
	
//Tests Cafeteria
	
	@Test
	void testVenderComidaEnCafeteriaCorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("carne", 3);
		
		assertTrue(funciona);
		assertEquals(7, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testVenderComidaEnCafeteriaCorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("carne", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testVenderComidaEnCafeteriaIncorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("carne", 3);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testVenderComidaEnCafeteriaIncorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("carne", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testVenderSouvenirEnCafeteriaIncorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("taza", 3);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("taza").getCantidad());
	}
	
	@Test
	void testVenderSouvenirEnCafeteriaIncorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("taza", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("taza").getCantidad());
	}
	
//Tests Tienda
	
	@Test
	void testVenderSouvenirEnTiendaCorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("taza", 3);
		
		assertTrue(funciona);
		assertEquals(7, parque.inventario.get("taza").getCantidad());
	}
	
	@Test
	void testVenderSouvenirEnTiendaCorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("taza", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("taza").getCantidad());
	}
	
	@Test
	void testVenderSouvenirEnTiendaIncorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("taza", 3);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("taza").getCantidad());
	}
	
	@Test
	void testVenderSouvenirEnTiendaIncorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroCafeteria();
		
		boolean funciona = cajero.vender("taza", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("taza").getCantidad());
	}
	
	@Test
	void testVenderComidaEnTiendaIncorrectoYSuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("carne", 3);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testVenderComidaEnTiendaIncorrectoEInsuficienteStock() {
		Cajero cajero = crearCajeroTienda();
		
		boolean funciona = cajero.vender("carne", 11);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("carne").getCantidad());
	}
	
}
