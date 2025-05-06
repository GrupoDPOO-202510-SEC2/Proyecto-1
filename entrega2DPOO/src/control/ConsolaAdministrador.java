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
            "Agregar o Eliminar climas restringidos de espectáculo",
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
            "Crear cajero atracción",
            "Crear cajero taquilla",
            "Crear cocinero",
            "Añadir comida a cocinero",
            "Crear operador atracción",
            "Crear servicio general",
            "Salir \n" 
        };

        boolean continuar = true;
        while (continuar)
            {

           		pedirCadenaAlUsuario("Presiona enter para continuar...");
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
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 2:
                    {
                        boolean seHizo = admin.cambiarFechaEspectaculo(
	                            pedirCadenaAlUsuario("Nombre del espectaculo:"),
	                            pedirCadenaAlUsuario("Fecha inicio:"),
	                            pedirCadenaAlUsuario("Fecha fin:")
                        );
                        if (seHizo) {
                        	System.out.println("Se hizo correctmente");
                            break;
                        }
                        System.out.println("Espectaculo no encontrado");
                        break;
                    }
                    case 3:
                    {
                    	Espectaculo espectaculo = admin.getEspectaculo(pedirCadenaAlUsuario("Nombre:"));
                    	if (espectaculo != null) {
	                        System.out.println("-Nombre del espectaculo: "+espectaculo.getNombre()+" \n"+"-Ubicacion: "+ espectaculo.getUbicacion()+" \n"+"-Horario: " +espectaculo.getHorario() +" \n-Fecha de Inicio: "+espectaculo.getFechaInicio()+"\n-Fecha Final: "+espectaculo.getFechaFin());
	                        System.out.println("Se hizo correctmente");
	                        break;
                    	}
                    	System.out.println("Espectaculo no encontrado");
                    	break;
                    }
                    case 4:
                    {
                    	boolean seHizo = false;
                        String nombre = pedirCadenaAlUsuario("Nombre espectáculo:");
                        String clima = pedirCadenaAlUsuario("Clima:");
                        if (pedirConfirmacionAlUsuario("¿Agregar clima?"))
                        {
                        	seHizo = admin.agregarClimaRestringidoE(nombre, clima);
                        }
                        else if ((pedirConfirmacionAlUsuario("¿Eliminar clima?")))
                        {
                        	seHizo = admin.eliminarClimaRestringidoE(nombre, clima);
                        }
                        
                        if(seHizo) {
	                        System.out.println("Se hizo correctmente");
	                        break;
                        }
                        System.out.println("No se hizo la operacion");
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
                            pedirEnteroAlUsuario("Altura máxima (en centimetros):"),
                            pedirEnteroAlUsuario("Altura mínima (en centimetros):"),
                            pedirEnteroAlUsuario("Peso mínimo (en kilogramos):"),
                            pedirEnteroAlUsuario("Peso máximo (en kilogramos):"),
                            pedirConfirmacionAlUsuario("¿Riesgo alto?")
                        );
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 6:
                    {
                        admin.crearAtraccionCultural(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirEnteroAlUsuario("Capacidad:"),
                            pedirEnteroAlUsuario("Empleados mínimos:"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            pedirEnteroAlUsuario("Exclusividad:"),
                            pedirEnteroAlUsuario("Edad mínima:"),
                            pedirConfirmacionAlUsuario("¿Es interactiva?")
                        );
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 7:
                    {
                    	Atraccion atraccion = admin.getAtraccion(pedirCadenaAlUsuario("Nombre"));
                    	if(atraccion != null) {
                    		System.out.println("Nombre "+ atraccion.getNombre()+ ", "+"Ubicacion: "+atraccion.getUbicacion()+", "+"Empleados minimos: "+atraccion.getEmpleadosMinimos()+", "+"Nivel Exclusividad: "+atraccion.getNivelExclusividad()+", "+"Capcidad Máxima: "+atraccion.getCapacidadMaxima());
	                        System.out.println("Se hizo correctmente");
	                        break;
                    	}
                    	System.out.println("No se encontro la atraccion");
                        break;
                        
                    }
                    case 8:
                    {
                        admin.setCapacidadMaxima(pedirCadenaAlUsuario("Nombre"), pedirEnteroAlUsuario("Capacidad máxima:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 9:
                    {
                        admin.setEmpleadosMinimos(pedirCadenaAlUsuario("Nombre"), pedirEnteroAlUsuario("Empleados mínimos:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 10:
                    {
                        admin.setEnServicio(pedirCadenaAlUsuario("Nombre"), pedirConfirmacionAlUsuario("¿Está en servicio?"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 11:
                    {
                        admin.setExclusividad(pedirCadenaAlUsuario("Nombre"), pedirEnteroAlUsuario("Exclusividad:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 12:
                    {
                        int[] op = admin.getOperadoresAtraccion(pedirCadenaAlUsuario("Nombre"));
                        System.out.println("Día: " + op[0] + ", Noche: " + op[1]);
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 13:
                    {
                    	boolean seHizo = false;
                        String login = pedirCadenaAlUsuario("Login operador:");
                        String turno = pedirCadenaAlUsuario("Turno (diurno/nocturno):");
                        String atr = pedirCadenaAlUsuario("Atracción:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            seHizo = admin.addOperadorAAtraccion(login, turno, atr);
                        }
                        else
                        {
                        	seHizo = admin.deleteOperadorAAtraccion(login, turno, atr);
                        }
                        
                        if (seHizo) {
	                        System.out.println("Se hizo correctmente");
	                        break;
                    	}
	                    System.out.println("Hubo un error en los datos ingresados");
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
                        System.out.println("Se hizo correctmente");
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
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 16:
                    {
                        int[] datos = admin.getAlturasPesos(pedirCadenaAlUsuario("Nombre atracción:"));
                        System.out.println("AltMax: " + datos[0] + ", AltMin: " + datos[1] + ", PesMax: " + datos[2] + ", PesMin: " + datos[3]);
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 17:
                    {
                        admin.setAlturaMaxima(pedirEnteroAlUsuario("Altura máxima:"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 18:
                    {
                        admin.setAlturaMinima(pedirEnteroAlUsuario("Altura mínima:"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 19:
                    {
                        admin.setPesoMaximo(pedirEnteroAlUsuario("Peso máximo:"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 20:
                    {
                        admin.setPesoMinimo(pedirEnteroAlUsuario("Peso mínimo:"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 21:
                    {
                        admin.setRiesgoAlto(pedirConfirmacionAlUsuario("¿Riesgo alto?"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 22:
                    {
                        System.out.println("Edad mínima: " + admin.getEdadMinima(pedirCadenaAlUsuario("Atracción:")));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 23:
                    {
                        System.out.println("Es interactiva: " + admin.isEsInteractiva(pedirCadenaAlUsuario("Atracción:")));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 24:
                    {
                        admin.setEdadMinima(pedirEnteroAlUsuario("Edad mínima:"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 25:
                    {
                        admin.setEsInteractiva(pedirConfirmacionAlUsuario("¿Es interactiva?"), pedirCadenaAlUsuario("Atracción:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 26:
                    {
                        System.out.println("Lugar: " + admin.getLugarDeTrabajo(pedirCadenaAlUsuario("Login:")));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 27:
                    {
                        admin.setLugarDeTrabajo(pedirCadenaAlUsuario("Lugar nuevo:"), pedirCadenaAlUsuario("Login:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 28:
                    {
                        System.out.println("Rol: " + admin.getRol(pedirCadenaAlUsuario("Login:")));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 29:
                    {
                        boolean[] t = admin.getTurnos(pedirCadenaAlUsuario("Login:"));
                        System.out.println("Día: " + t[0] + ", Noche: " + t[1]);
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 30:
                    {
                        admin.setTurnoDia(pedirConfirmacionAlUsuario("¿Ponerle turno diurno?"), pedirCadenaAlUsuario("Login:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 31:
                    {
                        admin.setTurnoNoche(pedirConfirmacionAlUsuario("¿ponerle un turno nocturno?"), pedirCadenaAlUsuario("Login:"));
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 32:
                    {
                        admin.crearCafeteria(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            ""
                        );
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 33:
                    {
                        admin.crearTienda(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Ubicación:"),
                            ""
                        );
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 34:
                    {
                        System.out.println("Menú: " + admin.getMenu());
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 35:
                    {
                        System.out.println("Items: " + admin.getItems());
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 36:
                    {
                        String comida = pedirCadenaAlUsuario("Comida:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.addComia(comida);
                        }
                        else if (pedirConfirmacionAlUsuario("¿Eliminar?"))
                        {
                            admin.removeComia(comida);
                        }else {
                        	System.out.println("No se realizo ningun cambio");
                            break;
                        }
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 37:
                    {
                        String item = pedirCadenaAlUsuario("Item:");
                        if (pedirConfirmacionAlUsuario("¿Agregar?"))
                        {
                            admin.additem(item);
                        }
                        else if (pedirConfirmacionAlUsuario("¿Eliminar?"))
                        {
                            admin.removeitem(item);
                        }else {
                        	System.out.println("No se realizo ningun cambio");
                            break;
                        }
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                    case 38:
                    {
                    	admin.crearCajeroAtraccion(
                    	        pedirCadenaAlUsuario("Nombre"),
                    	        pedirCadenaAlUsuario("Login:"),
                    	        pedirCadenaAlUsuario("Password:"),
                    	        pedirEnteroAlUsuario("Altura:"),
                    	        pedirEnteroAlUsuario("Peso:"),
                    	        pedirCadenaAlUsuario("Lugar de trabajo:"),
                    	        pedirCadenaAlUsuario("Turno (DIURNO/NOCTURNO):")
                    	    );
                    	System.out.println("Se hizo correctmente");
                    	break;
                    }
                    case 39: 
                    {
                        admin.crearCajeroTaquilla(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Login:"),
                            pedirCadenaAlUsuario("Password:"),
                            pedirEnteroAlUsuario("Altura:"),
                            pedirEnteroAlUsuario("Peso:"),
                            pedirCadenaAlUsuario("Turno (DIURNO/NOCTURNO):")
                        );
                        System.out.println("Se hizo correctamente");
                        break;
                    }
                    case 40: 
                    {
                        boolean seHizo = admin.crearCocinero(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Login:"),
                            pedirCadenaAlUsuario("Password:"),
                            pedirEnteroAlUsuario("Altura:"),
                            pedirEnteroAlUsuario("Peso:"),
                            pedirCadenaAlUsuario("Turno (DIURNO/NOCTURNO):"),
                            pedirCadenaAlUsuario("Lugar de trabajo:")
                        );
                        if(seHizo) {
	                        System.out.println("Se hizo correctamente");
	                        break;
                        }

                        System.out.println("no se hizo correctamente");
                        break;
                    }
                    case 41:
                    {
                    	String login = pedirCadenaAlUsuario("Login del cocinero:");
                        admin.addComidaCocinero(
                            login,
                            pedirCadenaAlUsuario("Nombre de la comida:")
                        );
                        System.out.println("Se hizo correctamente");
                        break;
                    }
                    case 42: {
                        admin.crearOperadorAtraccion(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Login:"),
                            pedirCadenaAlUsuario("Password:"),
                            pedirEnteroAlUsuario("Altura:"),
                            pedirEnteroAlUsuario("Peso:")
                        );
                        System.out.println("Se hizo correctamente");
                        break;
                    }
                    case 43:
                    {
                        admin.crearServicioGeneral(
                            pedirCadenaAlUsuario("Nombre"),
                            pedirCadenaAlUsuario("Login:"),
                            pedirCadenaAlUsuario("Password:"),
                            pedirEnteroAlUsuario("Altura:"),
                            pedirEnteroAlUsuario("Peso:"),
                            pedirCadenaAlUsuario("Lugar de trabajo:"),
                            pedirCadenaAlUsuario("Turno (DIURNO/NOCTURNO):")
                        );
                        System.out.println("Se hizo correctamente");
                        break;
                    }

                    case 44:
                    {
                        continuar = false;
                        
                        try {
							Persistencia.guardarParque(Usuario.parque);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        System.out.println("Se hizo correctmente");
                        break;
                    }
                }
                }
            }
    }

