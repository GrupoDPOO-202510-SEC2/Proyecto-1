package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import usuario.*;
import control.Parque;
import servicios.PedidoTiquete;
import tiquete.Tiquete;
import tiquete.TiqueteExclusividad;

class CajeroTaquillaTest {
	
	Parque parque = new Parque("Entrada");
	
	CajeroTaquilla crearCajero() {
		
		Usuario usuario = new Usuario("Carlos", "cclogin", "4321", 160, 60);
		parque.usuarios.put("cclogin", usuario);
		Tiquete tiquete = new Tiquete(10000001, false);
		parque.tiquetes.put(tiquete.getIdTiquete(), tiquete);
		PedidoTiquete pedido = new PedidoTiquete("cclogin", "TiqueteExclusividad", "", "", 3, false);
		parque.taquilla.addPedidoTiquete(pedido);
		CajeroTaquilla.parque = parque;
		CajeroTaquilla cajero = new CajeroTaquilla("Tulio", "ttlogin", "1234", 175, 56, "Taquilla");
		return cajero;
	}
	
	@Test
	void testDejarEntrar() {
		CajeroTaquilla cajero = crearCajero();
		Usuario usuario = new Usuario("Carlos", "cclogin", "4321", 160, 60);
		Tiquete tiquete = new Tiquete(10000001, false);
		
		boolean funciona = cajero.dejarEntrar(usuario, tiquete.getIdTiquete());
		
		assertTrue(funciona);
		assertEquals(parque.tiquetes.get(tiquete.getIdTiquete()), usuario.getTiqueteEnUso());
	}
	
	@Test
	void testRegistrarSalida() {
		CajeroTaquilla cajero = crearCajero();
		Usuario usuario = new Usuario("Carlos", "cclogin", "4321", 160, 60);
		Tiquete tiquete = new TiqueteExclusividad(10000222, false, 3);
		parque.tiquetes.put(tiquete.getIdTiquete(), tiquete);
		usuario.setTiqueteEnUso(tiquete);
		
		cajero.registrarSalida(usuario);
		
		assertEquals(null, usuario.getTiqueteEnUso());
		assertFalse(parque.tiquetes.get(tiquete.getIdTiquete()).isValido());
	}
	
	@Test
	void testVenderTiquete() {
		CajeroTaquilla cajero = crearCajero();
		cajero.venderTiquete();
		
		assertTrue(parque.usuarios.get("cclogin").getTiquetesComprados().contains((double) 10000001));
	}

}
