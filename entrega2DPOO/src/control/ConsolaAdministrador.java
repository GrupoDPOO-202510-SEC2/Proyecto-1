package control;

import usuario.Administrador;
import usuario.Usuario;
import control.Parque;
import java.util.ArrayList;

import atraccion.*;

public class ConsolaAdministrador extends ConsolaBasica {

    private Parque parque;

    public ConsolaAdministrador(Parque parque) {
        this.parque = parque;
    }

    public void iniciar() {
        System.out.println("==== Consola del Administrador ====");

        String login = pedirCadenaAlUsuario("Login:");
        String password = pedirCadenaAlUsuario("Contraseña:");

        Usuario u = parque.usuarios.get(login);
        if (u instanceof Administrador && u.getPassword().equals(password))
            {
                Administrador admin = (Administrador) u;
                System.out.println("Inicio exitoso. Bienvenido " + admin.getNombre());
                menuAdministrador(admin);
            }
        else
            {
                System.out.println("Credenciales incorrectas o no es administrador.");
            }
    }

    private void menuAdministrador(Administrador admin) {
        String[] opciones = {
            "Crear espectáculo",
            "Cambiar fecha de espectáculo",
            "Ver espectáculo",
            "Gestionar climas restringidos de espectáculo",
            "Crear atracción mecánica",
            "Crear atracción cultural",
            "Ver atracción",
            "Modificar capacidad máxima",
            "Modificar empleados mínimos",
            "Modificar estado de servicio",
            "Modificar exclusividad",
            "Ver operadores de atracción",
            "Gestionar operadores",
            "Gestionar restricciones de salud",
            "Gestionar climas restringidos de atracción",
            "Ver alturas y pesos",
            "Modificar altura máxima",
            "Modificar altura mínima",
            "Modificar peso máximo",
            "Modificar peso mínimo",
            "Modificar riesgo alto",
            "Ver edad mínima",
            "Ver si es interactiva",
            "Modificar edad mínima",
            "Modificar interactividad",
            "Ver lugar de trabajo",
            "Modificar lugar de trabajo",
            "Ver rol",
            "Ver turnos",
            "Modificar turno día",
            "Modificar turno noche",
            "Crear cafetería",
            "Crear tienda",
            "Ver menú",
            "Ver items",
            "Gestionar comidas del menú",
            "Gestionar items de tienda",
            "Salir"
        };

        boolean continuar = true;
        while (continuar)
            {
                int opcion = mostrarMenu("Menú Administrador", opciones);

                switch (opcion)
                {
                    case 1:
                    {
                        admin.crearEspectaculo(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirCadenaAlUsuario("Horario:"),
                            pedirCadenaAlUsuario("Ubicación:")
                        );
                        break;
                    }
                    case 2:
                    {
                        admin.cambiarFechaEspectaculo(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirCadenaAlUsuario("Fecha inicio:"),
                            pedirCadenaAlUsuario("Fecha fin:")
                        );
                        break;
                    }
                    case 3:
                    {
                    	Espectaculo espectaculo = admin.getEspectaculo(pedirCadenaAlUsuario("Nombre:"));
                        System.out.println(espectaculo.getNombre()+", "+ espectaculo.getUbicacion()+", "+ espectaculo.getHorario());
                        break;
                    }
                    case 4:
                    {
                        String nombre = pedirCadenaAlUsuario("Nombre espectáculo:");
                        String clima = pedirCadenaAlUsuario("Clima:");
                        if (pedirConfirmacionAlUsuario("¿Agregar clima?"))
                        {
                            admin.agregarClimaRestringidoE(nombre, clima);
                        }
                        else
                        {
                            admin.eliminarClimaRestringidoE(nombre, clima);
                        }
                        break;
                    }
                    case 5:
                    {
                        admin.crearAtraccionMecanica(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirEnteroAlUsuario("Capacidad:"),
                            pedirEnteroAlUsuario("Empleados mínimos:"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            pedirEnteroAlUsuario("Exclusividad:"),
                            pedirEnteroAlUsuario("Altura máxima:"),
                            pedirEnteroAlUsuario("Altura mínima:"),
                            pedirEnteroAlUsuario("Peso mínimo:"),
                            pedirEnteroAlUsuario("Peso máximo:"),
                            pedirConfirmacionAlUsuario("¿Riesgo alto?")
                        );
                        break;
                    }
                    case 6:
                    {
                        admin.crearAtraccionCultural(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirEnteroAlUsuario("Capacidad:"),
                            pedirEnteroAlUsuario("Empleados mínimos:"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            pedirEnteroAlUsuario("Exclusividad:"),
                            pedirEnteroAlUsuario("Edad mínima:"),
                            pedirConfirmacionAlUsuario("¿Es interactiva?")
                        );
                        break;
                    }
                    case 7:
                    {
                        System.out.println(admin.getAtraccion(pedirCadenaAlUsuario("Nombre:")));
                        break;
                    }
                    case 8:
                    {
                        admin.setCapacidadMaxima(pedirCadenaAlUsuario("Nombre:"), pedirEnteroAlUsuario("Capacidad máxima:"));
                        break;
                    }
                    case 9:
                    {
                        admin.setEmpleadosMinimos(pedirCadenaAlUsuario("Nombre:"), pedirEnteroAlUsuario("Empleados mínimos:"));
                        break;
                    }
                    case 10:
                    {
                        admin.setEnServicio(pedirCadenaAlUsuario("Nombre:"), pedirConfirmacionAlUsuario("¿Está en servicio?"));
                        break;
                    }
                    case 11:
                    {
                        admin.setExclusividad(pedirCadenaAlUsuario("Nombre:"), pedirEnteroAlUsuario("Exclusividad:"));
                        break;
                    }
                    case 12:
                    {
                        int[] op = admin.getOperadoresAtraccion(pedirCadenaAlUsuario("Nombre:"));
                        System.out.println("Día: " + op[0] + ", Noche: " + op[1]);
                        break;
                    }
                    case 13:
                    {
                        String login = pedirCadenaAlUsuario("Login operador:");
                        String turno = pedirCadenaAlUsuario("Turno (DIURNO/NOCTURNO):");
                        String atr = pedirCadenaAlUsuario("Atracción:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.addOperadorAAtraccion(login, turno, atr);
                        }
                        else
                        {
                            admin.deleteOperadorAAtraccion(login, turno, atr);
                        }
                        break;
                    }
                    case 14:
                    {
                        String atr = pedirCadenaAlUsuario("Atracción:");
                        String res = pedirCadenaAlUsuario("Restricción salud:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.addRestriccionSalud(atr, res);
                        }
                        else
                        {
                            admin.removeRestriccionSalud(atr, res);
                        }
                        break;
                    }
                    case 15:
                    {
                        String atr = pedirCadenaAlUsuario("Atracción:");
                        String clima = pedirCadenaAlUsuario("Clima:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.addClimasRestringidos(atr, clima);
                        }
                        else
                        {
                            admin.removeClimasRestringidos(atr, clima);
                        }
                        break;
                    }
                    case 16:
                    {
                        int[] datos = admin.getAlturasPesos(pedirCadenaAlUsuario("Nombre atracción:"));
                        System.out.println("AltMax: " + datos[0] + ", AltMin: " + datos[1] + ", PesMax: " + datos[2] + ", PesMin: " + datos[3]);
                        break;
                    }
                    case 17:
                    {
                        admin.setAlturaMaxima(pedirEnteroAlUsuario("Altura máxima:"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 18:
                    {
                        admin.setAlturaMinima(pedirEnteroAlUsuario("Altura mínima:"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 19:
                    {
                        admin.setPesoMaximo(pedirEnteroAlUsuario("Peso máximo:"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 20:
                    {
                        admin.setPesoMinimo(pedirEnteroAlUsuario("Peso mínimo:"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 21:
                    {
                        admin.setRiesgoAlto(pedirConfirmacionAlUsuario("¿Riesgo alto?"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 22:
                    {
                        System.out.println("Edad mínima: " + admin.getEdadMinima(pedirCadenaAlUsuario("Atracción:")));
                        break;
                    }
                    case 23:
                    {
                        System.out.println("Es interactiva: " + admin.isEsInteractiva(pedirCadenaAlUsuario("Atracción:")));
                        break;
                    }
                    case 24:
                    {
                        admin.setEdadMinima(pedirEnteroAlUsuario("Edad mínima:"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 25:
                    {
                        admin.setEsInteractiva(pedirConfirmacionAlUsuario("¿Es interactiva?"), pedirCadenaAlUsuario("Atracción:"));
                        break;
                    }
                    case 26:
                    {
                        System.out.println("Lugar: " + admin.getLugarDeTrabajo(pedirCadenaAlUsuario("Login:")));
                        break;
                    }
                    case 27:
                    {
                        admin.setLugarDeTrabajo(pedirCadenaAlUsuario("Lugar nuevo:"), pedirCadenaAlUsuario("Login:"));
                        break;
                    }
                    case 28:
                    {
                        System.out.println("Rol: " + admin.getRol(pedirCadenaAlUsuario("Login:")));
                        break;
                    }
                    case 29:
                    {
                        boolean[] t = admin.getTurnos(pedirCadenaAlUsuario("Login:"));
                        System.out.println("Día: " + t[0] + ", Noche: " + t[1]);
                        break;
                    }
                    case 30:
                    {
                        admin.setTurnoDia(pedirConfirmacionAlUsuario("¿Turno día?"), pedirCadenaAlUsuario("Login:"));
                        break;
                    }
                    case 31:
                    {
                        admin.setTurnoNoche(pedirConfirmacionAlUsuario("¿Turno noche?"), pedirCadenaAlUsuario("Login:"));
                        break;
                    }
                    case 32:
                    {
                        admin.crearCafeteria(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            pedirCadenaAlUsuario("Tipo:")
                        );
                        break;
                    }
                    case 33:
                    {
                        admin.crearTienda(
                            pedirCadenaAlUsuario("Nombre:"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            pedirCadenaAlUsuario("Tipo:")
                        );
                        break;
                    }
                    case 34:
                    {
                        System.out.println("Menú: " + admin.getMenu());
                        break;
                    }
                    case 35:
                    {
                        System.out.println("Items: " + admin.getItems());
                        break;
                    }
                    case 36:
                    {
                        String comida = pedirCadenaAlUsuario("Comida:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.addComia(comida);
                        }
                        else
                        {
                            admin.removeComia(comida);
                        }
                        break;
                    }
                    case 37:
                    {
                        String item = pedirCadenaAlUsuario("Item:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.additem(item);
                        }
                        else
                        {
                            admin.removeitem(item);
                        }
                        break;
                    }
                    case 38:
                    {
                        continuar = false;
                        
                        try {
							Persistencia.guardarParque(Usuario.parque);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        break;
                    }
                }
                }
            }
    }

