package tests;

import static org.junit.jupiter.api.Assertions.*;
import servicios.Taquilla;
import servicios.PedidoTiquete;
import usuario.Usuario;
import org.junit.jupiter.api.Test;

class TaquillaTest {
	
	Taquilla generarTaquilla() {
		return new Taquilla("Noreste");
	}
	
	@Test
	void testGenerarCodigo() {
		Taquilla taquilla = generarTaquilla();
		assertEquals(10000001, taquilla.generarCodigo());
		assertEquals(10000002, taquilla.generarCodigo());
		assertEquals(10000003, taquilla.generarCodigo());
	}
	
	@Test
	void testNuevaPeticion() {
		Taquilla taquilla = generarTaquilla();
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteExclusivo", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 0, true);
		
		taquilla.nuevaPeticion(pedido);
		
		assertEquals(1, taquilla.getPedidosTiquetes().size());
		
		Usuario usuario2 = new Usuario("Juan Carlos Bodoque", "cclogin", "87654321", 160, 50);
		PedidoTiquete pedido2 = new PedidoTiquete(usuario2, "TiqueteTemporada", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 0, false);
		
		taquilla.nuevaPeticion(pedido2);
		
		assertEquals(2, taquilla.getPedidosTiquetes().size());
		assertEquals(pedido, taquilla.getPedidosTiquetes().peek());
	}
	
	@Test
	void testPedidoEnFila() {
		Taquilla taquilla = generarTaquilla();
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteExclusivo", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 0, true);
		taquilla.nuevaPeticion(pedido);
		
		PedidoTiquete pedidoEnFila = taquilla.pedidoEnFila();
		
		assertEquals(pedido, pedidoEnFila);
		assertEquals(0, taquilla.getPedidosTiquetes().size());
		
		taquilla.nuevaPeticion(pedidoEnFila);
		Usuario usuario2 = new Usuario("Juan Carlos Bodoque", "cclogin", "87654321", 160, 50);
		PedidoTiquete pedido2 = new PedidoTiquete(usuario2, "TiqueteTemporada", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 0, false);
		taquilla.nuevaPeticion(pedido2);
		
		PedidoTiquete nuevoPedidoEnFila = taquilla.pedidoEnFila();
		
		assertEquals(pedido, nuevoPedidoEnFila);
		assertEquals(1, taquilla.getPedidosTiquetes().size());
	}

}
