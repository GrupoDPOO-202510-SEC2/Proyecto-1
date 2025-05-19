package control;


import usuario.Usuario;

import usuario.*;
import control.Parque;

import tiquete.Tiquete;

import java.util.ArrayList;

import control.Persistencia;

public class ConsolaUsuario extends ConsolaBasica {

    private Parque parque;

    public ConsolaUsuario(Parque parque) {
        this.parque = parque;
    }

    public void iniciar() {
        System.out.println("==== Bienvenido al sistema del parque ====");

        String[] opcionesInicio = {
            "Ingresar con usuario existente",
            "Crear nuevo usuario"
        };

        int opcion = mostrarMenu("Seleccione una opción", opcionesInicio);
        Usuario usuario = null;

        if (opcion == 1) {
            String login = pedirCadenaAlUsuario("Ingrese su login");
            String password = pedirCadenaAlUsuario("Ingrese su contraseña");

            for (Usuario u : parque.usuarios.values()) {
                if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                    usuario = u;
                    System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombre() + ".");
                    break;
                }
            }

            if (usuario == null) {
                System.out.println("Credenciales incorrectas.");
                return;
            }

        } else if (opcion == 2) {
            String nombre = pedirCadenaAlUsuario("Nombre:");
            String login = pedirCadenaAlUsuario("Login:");
            String password = pedirCadenaAlUsuario("Contraseña:");
            int altura = (int) pedirEnteroAlUsuario("Altura (cm):");
            int peso = (int) pedirEnteroAlUsuario("Peso (kg):");

            usuario = new Usuario(nombre, login, password, altura, peso);
            parque.usuarios.put(login, usuario);
            System.out.println("Usuario creado exitosamente.");

            while (true) {
                String restriccion = pedirCadenaAlUsuario("Agrega una restricción (o deja vacío para terminar):");
                if (restriccion.isEmpty()) break;
                usuario.agregarRestriccion(restriccion);
            }
        }

        if (usuario != null) {
            ejecutarMenu(usuario);
        }
    }

    private void ejecutarMenu(Usuario usuario) {
        String[] opcionesMenu = {
            "Comprar tiquete",
            "Ver restricciones",
            "Agregar restricción",
            "Eliminar restricción",
            "Ver tiquetes funcionales",
            "Asignar tiquete en uso",
            "Invalidar tiquete en uso",
            "Ver tiquete en uso",
            "Ver historial de compras",
            "Salir"
        };

        boolean continuar = true;
        while (continuar) {
            int opcion = mostrarMenu("Menú Usuario", opcionesMenu);

            switch (opcion) {
                case 1:
                    String tipo = pedirCadenaAlUsuario("Tipo de tiquete");
                    String fInicio = pedirCadenaAlUsuario("Fecha de inicio (yyyy-mm-dd)");
                    String fFin = pedirCadenaAlUsuario("Fecha de fin (yyyy-mm-dd)");
                    int exclusividad = pedirEnteroAlUsuario("Nivel de exclusividad (1-4)");
                    boolean fastPass = pedirConfirmacionAlUsuario("¿Desea Fast Pass?");
                    usuario.comprarTiquete(tipo, fInicio, fFin, exclusividad, fastPass);
                    break;

                case 2:
                    System.out.println("Restricciones: " + usuario.getRestricciones());
                    break;

                case 3:
                    String nueva = pedirCadenaAlUsuario("Ingrese nueva restricción:");
                    usuario.agregarRestriccion(nueva);
                    break;

                case 4:
                    ArrayList<String> restricciones = usuario.getRestricciones();
                    for (int i = 0; i < restricciones.size(); i++) {
                        System.out.println((i + 1) + ". " + restricciones.get(i));
                    }
                    int eliminar = pedirEnteroAlUsuario("Número de restricción a eliminar:");
                    if (eliminar >= 1 && eliminar <= restricciones.size()) {
                        restricciones.remove(eliminar - 1);
                    }
                    break;

                case 5:
                    System.out.println("Tiquetes funcionales: " + usuario.getTiquetesFuncionales());
                    break;

                case 6:
                    int index = pedirEnteroAlUsuario("Índice del tiquete funcional a usar:");
                    ArrayList<Double> ids = usuario.getTiquetesFuncionales();
                    if (index >= 0 && index < ids.size()) {
                        for (double t : usuario.getTiquetesComprados()) {
                            if (parque.tiquetes.get(t).getIdTiquete() == ids.get(index)) {	
                                usuario.setTiqueteEnUso(parque.tiquetes.get(t));
                            }
                        }
                    }
                    break;

                case 7:
                    usuario.invalidarTiquete();
                    break;

                case 8:
                    System.out.println("Tiquete en uso: " + usuario.getTiqueteEnUso());
                    break;

                case 9:
                    System.out.println("Compras realizadas: " + usuario.getCompras());
                    break;

                case 10:
                    continuar = false;
                    System.out.println("Sesión finalizada.");
                    break;
            }
        }
    }
}

