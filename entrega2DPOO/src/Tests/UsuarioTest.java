import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import usuario.Usuario;
import tiquete.Tiquete;
import java.util.List;

public class UsuarioTest {

    @Test
    public void agregarRestriccionTest() {
        Usuario u = new Usuario("Ana", "ana01", "clave", 160, 55);
        u.agregarRestriccion("Alergia");
        assertTrue(u.getRestricciones().contains("Alergia"));
    }

    @Test
    public void getTiquetesFuncionalesTest() {
        Usuario u = new Usuario("Luis", "luis01", "clave", 170, 65);
        Tiquete t1 = new Tiquete(1.0, false);
        Tiquete t2 = new Tiquete(2.0, false);
        t2.desvalidar();
        u.addTiquete(t1);
        u.addTiquete(t2);
        List<Double> validos = u.getTiquetesFuncionales();
        assertEquals(1, validos.size());
        assertEquals(1.0, validos.get(0));
    }

    @Test
    public void setTiqueteEnUsoTest() {
        Usuario u = new Usuario("Laura", "laura01", "clave", 165, 60);
        Tiquete t = new Tiquete(5.0, false);
        u.setTiqueteEnUso(t);
        assertEquals(t, u.getTiqueteEnUso());
    }

    @Test
    public void invalidarTiqueteTest() {
        Usuario u = new Usuario("Carlos", "carlos01", "clave", 175, 68);
        Tiquete t = new Tiquete(3.0, false);
        u.setTiqueteEnUso(t);
        u.invalidarTiquete();
        assertFalse(t.isValido());
    }
}  
