package control;

import usuario.Usuario;
import usuario.Empleado;
import control.Parque;

public class ConsolaUsuario extends ConsolaBasica {

    private Parque parque;

    public ConsolaUsuario(Parque parque) {
        this.parque = parque;
    }

    public void iniciar() {
        System.out.println("==== Bienvenido al sistema del parque ====");

        String login = pedirCadenaAlUsuario("Ingrese su login");
        String password = pedirCadenaAlUsuario("Ingrese su contraseña");

        Empleado usuario = parque.empleados.get(login);

        if (usuario != null && usuario.getPassword().equals(password)) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombre() + ".");
            ejecutarMenu(usuario);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private void ejecutarMenu(Usuario usuario) {
        String[] opcionesMenu = {
            "Comprar tiquete",
            "Comprar comida",
            "Comprar souvenir",
            "Ver restricciones",
            "Salir"
        };

        boolean continuar = true;
        while (continuar) {
            int opcion = mostrarMenu("Menú Principal", opcionesMenu);

            switch (opcion) {
                case 1:
                    String tipo = pedirCadenaAlUsuario("Tipo de tiquete");
                    String atraccion = pedirCadenaAlUsuario("Atracción asociada");
                    String fInicio = pedirCadenaAlUsuario("Fecha de inicio (yyyy-mm-dd)");
                    String fFin = pedirCadenaAlUsuario("Fecha de fin (yyyy-mm-dd)");
                    int exclusividad = pedirEnteroAlUsuario("Nivel de exclusividad (1-4)");
                    boolean fastPass = pedirConfirmacionAlUsuario("¿Desea Fast Pass?");
                    usuario.comprarTiquete(tipo, atraccion, fInicio, fFin, exclusividad, fastPass);
                    System.out.println("Tiquete solicitado.");
                    break;

                case 2:
                    String cafeteria = pedirCadenaAlUsuario("Nombre de la cafetería");
                    String comida = pedirCadenaAlUsuario("Nombre del producto");
                    int cantidadComida = pedirEnteroAlUsuario("Cantidad");
                    usuario.comprarComida(cafeteria, comida, cantidadComida);
                    System.out.println("Pedido de comida enviado.");
                    break;

                case 3:
                    String tienda = pedirCadenaAlUsuario("Nombre de la tienda");
                    String souvenir = pedirCadenaAlUsuario("Nombre del souvenir");
                    int cantidadSouvenir = pedirEnteroAlUsuario("Cantidad");
                    usuario.comprarSouvenir(tienda, souvenir, cantidadSouvenir);
                    System.out.println("Pedido de souvenir enviado.");
                    break;

                case 4:
                    System.out.println("Restricciones del usuario: " + usuario.getRestricciones());
                    break;

                case 5:
                    System.out.println("Gracias por usar el sistema. Hasta pronto.");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
