package control;

import usuario.Administrador;
import usuario.Usuario;
import control.Persistencia;

public class Main extends ConsolaBasica {

    public static void main(String[] args) throws  Exception {
        new Main().ejecutar();
    }

    public void ejecutar() throws Exception {
    	
    	//Persistencia.cargarParque()
    	
        Parque parque = new Parque("Entrada");
        Usuario.parque = parque;
        Administrador admin = new Administrador("Administrador", "sopa", "sopa", 180, 70);
        parque.usuarios.put("sopa", admin);

        if (parque.usuarios == null)
        {
            parque.usuarios = new java.util.HashMap<>();
        }

        String[] opciones = {
            "Consola de Usuario",
            "Consola de Administrador"
        };

        int opcion = mostrarMenu("Seleccione tipo de consola:", opciones);

        if (opcion == 1)
        {
            ConsolaUsuario consolaUsuario = new ConsolaUsuario(parque);
            consolaUsuario.iniciar();
        }
        else if (opcion == 2)
        {
            ConsolaAdministrador consolaAdmin = new ConsolaAdministrador(parque);
            consolaAdmin.iniciar();
        }
        else
        {
            System.out.println("Opción no válida.");
        }
    }
}
