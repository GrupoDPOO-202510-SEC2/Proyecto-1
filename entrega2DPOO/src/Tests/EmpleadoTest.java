package Tests;

import static org.junit.jupiter.api.Assertions.*;
import usuario.Empleado;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;


class EmpleadoTest {
	
	int numeroTempranoOTarde() {
		LocalTime horaAhora = LocalTime.now();
		LocalTime medioDia = LocalTime.of(12, 0, 0, 0);
		return horaAhora.compareTo(medioDia);
	}
	
	@Test
	void testEstaDisponibleDiurno() {
		Empleado empleado = new Empleado("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Titirilquen");
		empleado.setTurnoDia(true);
		if(numeroTempranoOTarde() <= 0) {
			assertTrue(empleado.estaDisponible());
		}
		else {
			assertFalse(empleado.estaDisponible());
		}
	}
	
	@Test
	void testEstaDisponibleNocturno() {
		Empleado empleado = new Empleado("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Titirilquen");
		empleado.setTurnoNoche(true);
		if(numeroTempranoOTarde() >= 0) {
			assertTrue(empleado.estaDisponible());
		}
		else {
			assertFalse(empleado.estaDisponible());
		}
	}
	@Test
	void testEstaDisponibleAmbos() {
		Empleado empleado = new Empleado("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Titirilquen");
		empleado.setTurnoNoche(true);
		empleado.setTurnoDia(true);
		assertTrue(empleado.estaDisponible());
	}
	
	@Test
	void testEstaDisponibleNinguno() {
		Empleado empleado = new Empleado("Tulio Triviño", "ttlogin", "12345678", 175, 56, "Titirilquen");
		assertFalse(empleado.estaDisponible());
	}
}
