package Tests;

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
	void testNuevaPeticion() {
		Taquilla taquilla = generarTaquilla();
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteExclusivo", "2024-02-03", "2024-03-03", 0, true);
		
		taquilla.nuevaPeticion(pedido);
		
		assertEquals(1, taquilla.getPedidosTiquetes().size());
		
		PedidoTiquete pedido2 = new PedidoTiquete("cclogin", "TiqueteTemporada", "2024-02-03", "2024-03-03", 0, false);
		
		taquilla.nuevaPeticion(pedido2);
		
		assertEquals(2, taquilla.getPedidosTiquetes().size());
		assertEquals(pedido, taquilla.getPedidosTiquetes().peek());
	}
	
	@Test
	void testPedidoEnFila() {
		Taquilla taquilla = generarTaquilla();
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteExclusivo", "2024-02-03", "2024-03-03", 0, true);
		taquilla.nuevaPeticion(pedido);
		
		PedidoTiquete pedidoEnFila = taquilla.pedidoEnFila();
		
		assertEquals(pedido, pedidoEnFila);
		assertEquals(0, taquilla.getPedidosTiquetes().size());
		
		taquilla.nuevaPeticion(pedidoEnFila);
		PedidoTiquete pedido2 = new PedidoTiquete("cclogin", "TiqueteTemporada", "2024-02-03", "2024-03-03", 0, false);
		taquilla.nuevaPeticion(pedido2);
		
		PedidoTiquete nuevoPedidoEnFila = taquilla.pedidoEnFila();
		
		assertEquals(pedido, nuevoPedidoEnFila);
		assertEquals(1, taquilla.getPedidosTiquetes().size());
	}

}
