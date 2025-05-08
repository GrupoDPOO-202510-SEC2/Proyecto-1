package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
		ArrayList<String> alimentosPreparables = new ArrayList<>();
		climas.add("Lluvia");
        climas.add("Tormenta");
        alimentosPreparables.add("hamburguesa");
		Espectaculo espectaculo = new Espectaculo("e1", "dia","z1", "02/05/25", "02/06/25", climas);
		OperadorAtraccion o1 = new OperadorAtraccion("o1", "o1", "o1p", 160, 80);
		OperadorAtraccion o2 = new OperadorAtraccion("o2", "o2", "o2p", 160, 80);
		Cocinero coci1 = new Cocinero("cosi1", "cosi1", "cosi1", 160, 80, "entrada", alimentosPreparables);
		o1.addCapacitaciones("am1");
		o2.addCapacitaciones("am1");
		parque.empleados = new HashMap<>();
        parque.espectaculos = new HashMap<>();
        parque.espectaculos.put("e1", espectaculo);
        parque.empleados.put("o1", o1);
        parque.empleados.put("o2", o2);
        parque.empleados.put("coci1", coci1);
        a1.parque = parque;
        a1.crearAtraccionMecanica("am1", 90, 4, "entrada", 3, 150, 210, 57, 150, true);
        a1.addRestriccionSalud("am1", "enanismo");
        a1.addOperadorAAtraccion("o2", "diurno", "am1"); 
        a1.addClimasRestringidos("am1", "lluvia");
        a1.crearCafeteria("caf1", "entrada", "tip1");
        a1.crearTienda("ti1", "entrada", "dulces");
        HashSet<String> menu = new HashSet<>();
        a1.parque.mapaCafeterias.get("caf1").setMenu(menu);
        a1.addComia("pollo");
        a1.additem("gomas");
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
		a1.addRestriccionSalud("am1", "obesidad");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertTrue(actualizado.getRestriccionesSalud().contains("obesidad"));
	}
	@Test
	void testremoveRestriccionSalud()
	{
		a1.removeRestriccionSalud("am1", "enanismo");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertFalse(actualizado.getRestriccionesSalud().contains("enanismo"));
	}
	@Test
	void testaddClimasRestringidos()
	{
		a1.addClimasRestringidos("am1","tormenta");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertTrue(actualizado.getClimasRestringidos().contains("tormenta"));
		
	}
	@Test
	void testremoveClimasRestringidos()
	{
		a1.removeClimasRestringidos("am1","lluvia");
		AtraccionMecanica actualizado = parque.aMecanicas.get("am1");
		assertFalse(actualizado.getClimasRestringidos().contains("lluvia"));
	}
	@Test
	void testaddComia()
	{
		a1.addComia("pasta");
		assertTrue(a1.getMenu().contains("pasta"));
	}
	@Test
	void testremoveComia()
	{
		a1.removeComia("pollo");
		assertFalse(a1.getMenu().contains("pollo"));
	}
	@Test
	void testaddItem()
	{
		a1.additem("chocolate");
		assertTrue(a1.getItems().contains("chocolate"));
	}
	@Test
	void testremoveItem()
	{
		a1.removeitem("gomas");
		assertFalse(a1.getItems().contains("gomas"));
	}
	@Test
	void testaddComidaCocinero()
	{
		a1.addComidaCocinero("coci1", "pasta");
		Cocinero cosi1p = (Cocinero) parque.empleados.get("coci1");
		assertTrue(cosi1p.getAlimentosPreparables().contains("pasta"));
	}
}
