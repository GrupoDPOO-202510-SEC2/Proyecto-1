package control;

import usuario.Administrador;
import usuario.Usuario;
import control.Persistencia;
import interfaz.Ventana;

public class Main extends ConsolaBasica {

    public static void main(String[] args) throws  Exception {
        new Main().ejecutar();
    }

    public void ejecutar() throws Exception {
    	
    	//Persistencia.cargarParque()
    	
        Usuario.parque = Persistencia.cargarParque();
        Administrador admin = new Administrador("Administrador", "sopa", "sopa", 180, 70);
        Usuario.parque.usuarios.put("sopa", admin);
        
        new Ventana();
        
        
        String a =  """
        String[] opciones = {
            "Consola de Usuario",
            "Consola de Administrador"
        };

        int opcion = mostrarMenu("Seleccione tipo de consola:", opciones);

        if (opcion == 1)
        {
            ConsolaUsuario consolaUsuario = new ConsolaUsuario(Usuario.parque);
            consolaUsuario.iniciar();
        }
        else if (opcion == 2)
        {
            ConsolaAdministrador consolaAdmin = new ConsolaAdministrador(Usuario.parque);
            consolaAdmin.iniciar();
        }
        else
        {
            System.out.println("Opción no válida.");
        }
        		""";     
    }
}
