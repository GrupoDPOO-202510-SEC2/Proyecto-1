package control;

import usuario.Administrador;

public class Main extends ConsolaBasica {

    public static void main(String[] args) {
        new Main().ejecutar();
    }

    public void ejecutar() {
        Parque parque = new Parque("Taquilla principal");
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
