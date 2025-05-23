package Tests;

import static org.junit.jupiter.api.Assertions.*;
import servicios.PedidoTiquete;
import usuario.Usuario;
import org.junit.jupiter.api.Test;

class PedidoTiqueteTest {

	@Test
	void testCrearTiqueteExclusivo() {
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteExclusivo", "2024-02-03", "2024-03-03", 3, true);
		assertTrue("2024-02-03".equals(pedido.getFechaInicioParaTemporales()) );
		assertTrue("2024-03-03".equals(pedido.getFechaFinParaTemporales()) );
	}
	
	@Test
	void testCrearTiqueteExclusivoMenor() {
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteExclusivo", "2024-02-03", "2024-03-03", 0, true);
		assertEquals(1, pedido.getExclusividad());
	}
	
	@Test
	void testCrearTiqueteExclusivoMayor() {
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteExclusivo", "2024-02-03", "2024-03-03", 5, true);
		assertEquals(4, pedido.getExclusividad());
	}
	
	@Test
	void testCrearTiqueteTemporada() {
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteTemporada", "2024-02-03", "2024-03-03", 3, true);
		//assertEquals("", pedido.getAtraccionParaIndividuales());
	}
	
	void testCrearTiqueteIndividual() {
		PedidoTiquete pedido = new PedidoTiquete("ttlogin", "TiqueteTemporada", "2024-02-03", "2024-03-03", 3, true);
		assertEquals("", pedido.getFechaInicioParaTemporales());
		assertEquals("", pedido.getFechaFinParaTemporales());
		assertEquals(4, pedido.getExclusividad());
	}
}
