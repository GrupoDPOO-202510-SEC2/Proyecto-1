package Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import tiquete.Tiquete;

public class TiqueteTest {
	Tiquete t1 = new Tiquete(256747, false);
	@Test
	void testdesvalidar()
	{
		t1.desvalidar();
		assertFalse(t1.isValido());
	}

}
