package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atraccion.Atraccion;
import atraccion.AtraccionMecanica;
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
        AtraccionMecanica am1 = new AtraccionMecanica("am1", 90, 4, "entrada", 3, 150, 210, 57, 150, true);
		Espectaculo espectaculo = new Espectaculo("e1", "dia","z1", "02/05/25", "02/06/25", climas);
		OperadorAtraccion o1 = new OperadorAtraccion("o1", "o1", "o1p", 160, 80);
		OperadorAtraccion o2 = new OperadorAtraccion("o2", "o2", "o2p", 160, 80);
		o1.addCapacitaciones("am1");
		o2.addCapacitaciones("am1");
		parque.empleados = new HashMap<>();
        parque.espectaculos = new HashMap<>();
        parque.aMecanicas = new HashMap<>();
        parque.espectaculos.put("e1", espectaculo);
        parque.aMecanicas.put("am1", am1);
        parque.empleados.put("o1", o1);
        parque.empleados.put("o2", o2);
        a1.parque = parque;
        a1.addOperadorAAtraccion("o2", "diurno", "am1");   
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
		a1.addOperadorAAtraccion("o1", "diurno", "am1");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertTrue(actualizado.getOperadoresDia().contains("o1"));
		
	}
	@Test
	void testdeleteOperadorAAtraccion()
	{
		a1.deleteOperadorAAtraccion("o2", "diurno", "am1");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertFalse(actualizado.getOperadoresDia().contains("o2"));
		
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
