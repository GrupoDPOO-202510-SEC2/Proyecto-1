package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atraccion.Espectaculo;
import usuario.*;
import control.Parque;
import servicios.PedidoTiquete;
import tiquete.Tiquete;
import tiquete.TiqueteExclusividad;

public class AdministradorTest {
	private Parque parque;
	Administrador a1 = new Administrador("sopa", "sopa", "sopa", 180, 80);
	@BeforeEach
	void setUp()
	{
		parque = new Parque("Parqueprueba");
		HashSet<String> climas = new HashSet<>();
		climas.add("Lluvia");
        climas.add("Tormenta");
		Espectaculo espectaculo = new Espectaculo("e1", "dia","z1", "02/05/25", "02/06/25", climas);
        parque.espectaculos = new HashMap<>();
        parque.espectaculos.put("e1", espectaculo);
        a1.parque = parque;
        
    }
	
	@Test
	void testcambiarFechaEspectaculo()
	{
		a1.cambiarFechaEspectaculo("e1", "02/06/25", "02/07/25");
		Espectaculo actualizado = parque.espectaculos.get("e1");
        assertEquals("02/06/25", actualizado.getFechaInicio());
        assertEquals("02/07/25", actualizado.getFechaFin());
	}
	@Test
	void testagregarClimaRestringidoE()
	{
		a1.agregarClimaRestringidoE("e1", "nevado");
		Espectaculo actualizado = parque.espectaculos.get("e1");
		assertTrue(actualizado.getClimasRestringidos().contains("nevado"));
	}
	@Test
	void testeliminarClimaRestringidoE()
	{
		a1.eliminarClimaRestringidoE("e1", "lluvia");
		Espectaculo actualizado = parque.espectaculos.get("e1");
		assertFalse(actualizado.getClimasRestringidos().contains("lluvia"));
	}
	@Test
	void testaddOperadorAAtraccion()
	{
	
	}
	@Test
	void testdeleteOperadorAAtraccion()
	{
		
	}
	@Test
	void testaddRestriccionSalud()
	{
		
	}
	@Test
	void removeRestriccionSalud()
	{
		
	}
}
