package tests;

import static org.junit.jupiter.api.Assertions.*;
import servicios.PedidoTiquete;
import usuario.Usuario;
import org.junit.jupiter.api.Test;

class PedidoTiqueteTest {

	@Test
	void testCrearTiqueteExclusivo() {
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteExclusivo", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 3, true);
		assertEquals("", pedido.getAtraccionParaIndividuales());
		assertEquals("", pedido.getFechaInicioParaTemporales());
		assertEquals("", pedido.getFechaFinParaTemporales());
	}
	
	@Test
	void testCrearTiqueteExclusivoMenor() {
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteExclusivo", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 0, true);
		assertEquals(1, pedido.getExclusividad());
	}
	
	@Test
	void testCrearTiqueteExclusivoMayor() {
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteExclusivo", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 5, true);
		assertEquals(4, pedido.getExclusividad());
	}
	
	@Test
	void testCrearTiqueteTemporada() {
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteTemporada", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 3, true);
		assertEquals("", pedido.getAtraccionParaIndividuales());
	}
	
	void testCrearTiqueteIndividual() {
		Usuario usuario = new Usuario("Tulio Triviño", "ttlogin", "12345678", 175, 56);
		PedidoTiquete pedido = new PedidoTiquete(usuario, "TiqueteTemporada", "Rueda de la Fortuna", "2024-02-03", "2024-03-03", 3, true);
		assertEquals("", pedido.getFechaInicioParaTemporales());
		assertEquals("", pedido.getFechaFinParaTemporales());
		assertEquals(4, pedido.getExclusividad());
	}
}
