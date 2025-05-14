package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atraccion.Atraccion;
import atraccion.AtraccionMecanica;

public class AtraccionTest {
	AtraccionMecanica a1 = new AtraccionMecanica("a1",40,3,"entrada",3, 140, 210, 50, 150, true);
	@BeforeEach
	void setUp()
	{
		HashSet<String> climas = new HashSet<>();
		
	}
	@Test
	void testsaddClimasRestringidos()
	{
		
	}
}
