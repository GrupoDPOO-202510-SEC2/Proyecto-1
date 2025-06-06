package usuario;
import java.util.ArrayList;
import java.util.HashSet;

import atraccion.*;
import servicios.*;


public class Administrador extends Usuario{

	public Administrador(String nombre, String login, String password, int altura, int peso) {
		super(nombre, login, password, altura, peso);
	}
	
	
	//-------------------------ESPECTACULOS--------------------------//
	
	public static boolean crearEspectaculo(String nombre, String horario, String ubicacion) {
		parque.espectaculos.put(nombre,new Espectaculo(nombre, horario, ubicacion, new HashSet<String>()));
		return true;
	}
	
	public static boolean cambiarFechaEspectaculo(String nombre, String fechai, String fechaf) {
		
		if (parque.espectaculos.containsKey(nombre)) {
			Espectaculo espectaculo = parque.espectaculos.get(nombre);
		espectaculo.setFechaInicio(fechai);
		espectaculo.setFechaFin(fechaf);
		
		parque.espectaculos.put(nombre, espectaculo);
		return true;
		}
		
		return false;
		
		
	}
	
	public static Espectaculo getEspectaculo(String nombre) {
		if(parque.espectaculos.containsKey(nombre)) {
			return parque.espectaculos.get(nombre);
		}
		return null;
	}
	
	public static boolean agregarClimaRestringidoE(String nombre,String clima) {
		if(parque.espectaculos.containsKey(nombre)) {
			Espectaculo espectaculo = parque.espectaculos.get(nombre);
			boolean retorno =  espectaculo.addClimaRestringido(clima);
			parque.espectaculos.put(nombre, espectaculo);
			return retorno;
		}
		return false;
		
	}
	
	public static boolean eliminarClimaRestringidoE(String nombre,String clima) {
		
		if(parque.espectaculos.containsKey(nombre)) {
			Espectaculo espectaculo = parque.espectaculos.get(nombre);
			boolean retorno = espectaculo.deleteClimaRestringido(clima);
			parque.espectaculos.put(nombre, espectaculo);
			return retorno;
		}
		return false;
	}
	
	
	
	//-------------ATRACCIONES EWEWEWE--------------------------------------//
	

	
	
	public static boolean crearAtraccionMecanica(String nombre, int capacidad, int empleadosMin, String ubicacion, int exclusividad, int alturaMax, int alturaMin, int pesoMin, int pesoMax, boolean riesgoAlto){
	
		parque.aMecanicas.put(nombre, new AtraccionMecanica(nombre, capacidad, empleadosMin, ubicacion, exclusividad, alturaMin, alturaMax, pesoMin, pesoMax, riesgoAlto));
		return parque.aMecanicas.containsKey(nombre);
	}
	
	public static boolean crearAtraccionCultural(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion, int nivelExclusividad, int edadMinima, boolean esInteractiva) {
		parque.aCulturales.put(nombre, new AtraccionCultural(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad, edadMinima, esInteractiva));
		return parque.aCulturales.containsKey(nombre);
	}
	
	public static Atraccion getAtraccion(String nombre) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			return parque.aMecanicas.get(nombre);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			return parque.aCulturales.get(nombre);
		}
		return null;	
	}
	
	public static void setCapacidadMaxima(String nombre, int cantidad) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setCapacidadMaxima(cantidad);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setCapacidadMaxima(cantidad);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public static void setEmpleadosMinimos(String nombre, int cantidad) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setEmpleadosMinimos(cantidad);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setEmpleadosMinimos(cantidad);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public static void setEnServicio(String nombre, boolean enServicio) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setEnServicio(enServicio);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setEnServicio(enServicio);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public static void setExclusividad(String nombre, int exclusividad) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			atraccion.setNivelExclusividad(exclusividad);
			parque.aMecanicas.put(nombre,atraccion);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			atraccion.setNivelExclusividad(exclusividad);
			parque.aCulturales.put(nombre,atraccion);
		}
	}
	
	public static int[] getOperadoresAtraccion(String nombre) {
		int[] retorno = new int[2];
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			retorno[0] = atraccion.getOperadoresDiaSize();
			retorno[1] = atraccion.getOperadoresNocheSize();
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			retorno[0] = atraccion.getOperadoresDiaSize();
			retorno[1] = atraccion.getOperadoresNocheSize();
			
		}
		
		return retorno;
	}
	
	public static boolean addOperadorAAtraccion(String loginOperador, String turno, String nombreAtraccion) {
		
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.addOperador((OperadorAtraccion) parque.empleados.get(loginOperador), turno);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if(retorno) {
				if (turno.equals(DIURNO)) {
					operador.setTurnoDia(true);
					operador.setLugarDeTrabajoDia(nombreAtraccion);
				}else {
					operador.setTurnoNoche(true);
					operador.setLugarDeTrabajoNoche(nombreAtraccion);
				}
				parque.empleados.put(loginOperador, operador);
			}
			
		}
		else if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.addOperador((OperadorAtraccion) parque.empleados.get(loginOperador), turno);
			parque.aCulturales.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if (turno.equals(DIURNO)) {
				operador.setTurnoDia(true);
				operador.setLugarDeTrabajoDia(nombreAtraccion);
			}else {
				operador.setTurnoNoche(true);
				operador.setLugarDeTrabajoNoche(nombreAtraccion);
			}
			parque.empleados.put(loginOperador, operador);
		}
		return retorno;
	}
	
	
	public static boolean deleteOperadorAAtraccion(String loginOperador, String turno, String nombreAtraccion) {
		
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, turno);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if (turno.equals(DIURNO)) {
				operador.setTurnoDia(false);
				operador.setLugarDeTrabajoDia(null);
			}else {
				operador.setTurnoNoche(false);
				operador.setLugarDeTrabajoNoche(null);
			}
			parque.empleados.put(loginOperador, operador);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, turno);
			parque.aCulturales.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if (turno.equals(DIURNO)) {
				operador.setTurnoDia(false);
				operador.setLugarDeTrabajoDia(null);
			}else {
				operador.setTurnoNoche(false);
				operador.setLugarDeTrabajoNoche(null);
			}
			parque.empleados.put(loginOperador, operador);
			
		}
		return retorno;
	}
	
	public static boolean addRestriccionSalud(String nombreAtraccion, String restriccion) {
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.addRestriccionSalud(restriccion);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.addRestriccionSalud(restriccion);
			parque.aCulturales.put(nombreAtraccion, atraccion);
		}
		return retorno;
	}
	
	public static boolean removeRestriccionSalud(String nombreAtraccion, String restriccion) {
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.removeRestriccionSalud(restriccion);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.removeRestriccionSalud(restriccion);
			parque.aCulturales.put(nombreAtraccion, atraccion);
		}
		return retorno;
	}
	
	public static HashSet<String> getClimasRestringidos(String nombreAtraccion) {
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			return atraccion.getClimasRestringidos();
			
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			return atraccion.getClimasRestringidos();
		}
		return null;
	}
	
	public static boolean addClimasRestringidos(String nombreAtraccion, String climaRestringido) {
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.addClimasRestringidos(climaRestringido);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.addClimasRestringidos(climaRestringido);
			parque.aCulturales.put(nombreAtraccion, atraccion);
		}
		return retorno;
	}
	
	public static boolean removeClimasRestringidos(String nombreAtraccion, String climaRestringido) {
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.removeClimasRestringidos(climaRestringido);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.removeClimasRestringidos(climaRestringido);
			parque.aCulturales.put(nombreAtraccion, atraccion);
		}
		return retorno;
	}	
	
	//------------------------ATRACCIONES MECANICAS---------------------------//
	
	public static int[] getAlturasPesos(String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		return new int[] {atraccion.getAlturaMaxima(),atraccion.getAlturaMinima(),atraccion.getPesoMaximo(),atraccion.getPesoMinimo()};
	}
	
	public static void setAlturaMaxima(int alturaMaxima,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setAlturaMaxima(alturaMaxima);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public static void setAlturaMinima(int alturaMinima,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setAlturaMinima(alturaMinima);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}

	public static void setPesoMaximo(int pesoMaximo,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setPesoMaximo(pesoMaximo);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public static void setPesoMinimo(int pesoMinimo,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setPesoMinimo(pesoMinimo);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public static void setRiesgoAlto(boolean riesgoAlto, String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setRiesgoAlto(riesgoAlto);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	//-----------------------ATRACCIONES CULTURALES----------------------------//
	
	public static int getEdadMinima(String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		return atraccion.getEdadMinima();
	}

	public static boolean isEsInteractiva(String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		return atraccion.isEsInteractiva();
	}
	
	public static void setEdadMinima(int edadMinima, String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		atraccion.setEdadMinima(edadMinima);
		parque.aCulturales.put(nombreAtraccion, atraccion);
	}

	public static void setEsInteractiva(boolean esInteractiva, String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		atraccion.setEsInteractiva(esInteractiva);
		parque.aCulturales.put(nombreAtraccion, atraccion);
	}
	
	//--------------------------EMPLEADOS---------------------------//
	
	public static String getLugarDeTrabajo(String loginEmpleado) {
		
		return parque.empleados.get(loginEmpleado).getLugarDeTrabajo();
	}

	public static boolean setLugarDeTrabajo(String lugarDeTrabajo, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setLugarDeTrabajo(lugarDeTrabajo);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}

	public static String getRol(String loginEmpleado) {

		return parque.empleados.get(loginEmpleado).getClass().getSimpleName();
	}
	
	
	public static boolean[] getTurnos(String loginEmpleado) {
		return new boolean[] {parque.empleados.get(loginEmpleado).getTurnoDia(),parque.empleados.get(loginEmpleado).getTurnoNoche()};
	}
	
	public static boolean setTurnoDia(boolean turnoDia, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setTurnoDia(turnoDia);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}
	
	public static boolean setTurnoNoche(boolean turnoNoche, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setTurnoNoche(turnoNoche);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}
	
	//--------------------------LUGAR DE SERVICIO----------------------------------//
	
	public static void crearCafeteria(String nombre, String ubicacion, String tipo) {
		parque.mapaCafeterias.put(nombre, new Cafeteria(nombre, ubicacion, tipo));
	}
	
	public static void crearTienda(String nombre, String ubicacion, String tipo) {
		parque.mapaTiendas.put(nombre, new Tienda(nombre, ubicacion, tipo));
	}

	public static HashSet<String> getMenu() {
		return Cafeteria.menu;
	}
	
	public static HashSet<String> getItems() {
		return Tienda.items;
	}
	
	public static boolean addComia(String comia) {
		return Cafeteria.menu.add(comia);
	}
	
	public static boolean removeComia(String comia) {
		return Cafeteria.menu.remove(comia);
	}

	
	public static boolean additem(String item) {
		return Tienda.items.add(item);
	}
	
	public static boolean removeitem(String item) {
		return Tienda.items.remove(item);
	}
	
	
	//---------------------------------------EMPLEADOS 2.0--------------------------------------------------//
	
	
	public static boolean crearCajero(String nombre, String login, String password, int altura, int peso, String lugarDeTrabajo, String turno) {
		Cajero cajero = new Cajero(nombre, login, password, altura, peso, lugarDeTrabajo);
		if (cajero.setLugarDeTrabajo(lugarDeTrabajo)) {
			if (turno.equals(DIURNO)) {
				cajero.setTurnoDia(true);
				parque.empleados.put(login, cajero);
				return true;
			}if (turno.equals(NOCTURNO)) {
				cajero.setTurnoNoche(true);
				parque.empleados.put(login, cajero);
				return true;
			}
		}
	return false;
	}
	
	public static boolean crearCajeroAtraccion(String nombre, String login, String password, int altura, int peso,
			String lugarDeTrabajo,String turno) {
		CajeroAtraccion cajero = new CajeroAtraccion(nombre,login,password,altura,peso,null);
		if(cajero.setLugarDeTrabajo(lugarDeTrabajo)) {
			if (turno.equals(DIURNO)) {
				cajero.setTurnoDia(true);
				parque.empleados.put(login, cajero);
				return true;
			}if (turno.equals(NOCTURNO)) {
				cajero.setTurnoNoche(true);
				parque.empleados.put(login, cajero);
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean crearCajeroTaquilla(String nombre, String login, String password, int altura, int peso,
		String turno) {
		CajeroTaquilla cajero = new CajeroTaquilla(nombre,login,password,altura,peso,"Taquilla");
		if (turno.equals(DIURNO)) {
			cajero.setTurnoDia(true);
			parque.empleados.put(login, cajero);
			return true;
		}if (turno.equals(NOCTURNO)) {
			cajero.setTurnoNoche(true);
			parque.empleados.put(login, cajero);
			return true;
		}
		return false;
	}
	
	public static boolean crearCocinero(String nombre, String login, String password, int altura, int peso,
			String turno, String lugarDeTrabajo) {
		
		Cocinero cocinero = new Cocinero(nombre,login,password,altura,peso, null,new ArrayList<String>());
		if(cocinero.setLugarDeTrabajo(lugarDeTrabajo)) {
			if (turno.equals(DIURNO)) {
				cocinero.setTurnoDia(true);
				parque.empleados.put(login, cocinero);
				return true;
			}if (turno.equals(NOCTURNO)) {
				cocinero.setTurnoNoche(true);
				parque.empleados.put(login, cocinero);
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean addComidaCocinero(String loginCocinero, String comia) {
		Cocinero cocinero = (Cocinero) parque.empleados.get(loginCocinero);
		
		cocinero.addAlimentoPreparable(comia);
		parque.empleados.put(loginCocinero,cocinero); 
		return true;
	}
	
	
	public static boolean crearOperadorAtraccion(String nombre, String login, String password, int altura, int peso) {
		OperadorAtraccion operador = new OperadorAtraccion(nombre, login, password, altura, peso);
		parque.empleados.put(login,operador);
		return true;
	}
	
	public static boolean crearServicioGeneral(String nombre, String login, String password, int altura, int peso
			, String lugarDeTrabajo, String turno) {
		ServicioGeneral empleado = new ServicioGeneral(nombre, login, password, altura, peso, lugarDeTrabajo);
		empleado.setLugarDeTrabajo(lugarDeTrabajo);
		if (turno.equals(DIURNO)) {
			empleado.setTurnoDia(true); 
			parque.empleados.put(login, empleado);
			return true;
		}if (turno.equals(NOCTURNO)) {
			empleado.setTurnoNoche(true);
			parque.empleados.put(login, empleado);
			return true;
		}
		return false;
	}
}

