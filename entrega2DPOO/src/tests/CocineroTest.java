package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import control.Parque;
import servicios.*;
import usuario.Cocinero;

class CocineroTest {
	
	Parque parque = crearParque();
	
	Parque crearParque() {
		Parque parque = new Parque("Entrada");
		
		Comestible comida = new Comestible("carne", 10);
		Comestible comida2 = new Comestible("arroz", 10);
		Souvenir souv = new Souvenir("taza", 10);
		
		parque.inventario.put(comida.getNombre(), comida);
		parque.inventario.put(comida2.getNombre(), comida2);
		parque.inventario.put(souv.getNombre(), souv);
		
		Cafeteria cafeteria = new Cafeteria("Juan Valdez", "Salida", "cafeteria");
		ArrayList<String> menu = new ArrayList<String>();
		menu.add("carne");
		cafeteria.setMenu(menu);
		parque.mapaCafeterias.put("Juan Valdez", cafeteria);
		
		return parque;
	}
	
	Cocinero crearCocinero() {
		Cocinero.parque = parque;
		Cocinero cocinero = new Cocinero ("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Titirilquen", parque.getCafeteria("Juan Valdez"));
		cocinero.addAlimentoPreparable("carne");
		cocinero.addAlimentoPreparable("pollo");
		return cocinero;
	}
	
	@Test
	void testCocinarPosibleYExistente() {
		Cocinero cocinero = crearCocinero();
		
		boolean funciona = cocinero.cocinar("carne", 10);
		
		assertTrue(funciona);
		assertEquals(20, parque.inventario.get("carne").getCantidad());
	}
	
	@Test
	void testCocinarImposibleYExistente() {
		Cocinero cocinero = crearCocinero();
		
		boolean funciona = cocinero.cocinar("arroz", 10);
		
		assertFalse(funciona);
		assertEquals(10, parque.inventario.get("arroz").getCantidad());
	}
	
	@Test
	void testCocinarPosibleEInexistente() {
		Cocinero cocinero = crearCocinero();
		
		boolean funciona = cocinero.cocinar("pollo", 10);
		
		assertFalse(funciona);
	}
	
	@Test
	void testCocinarImposibleEInexistente() {
		Cocinero cocinero = crearCocinero();
		
		boolean funciona = cocinero.cocinar("pez", 10);
		
		assertFalse(funciona);
	}
}
