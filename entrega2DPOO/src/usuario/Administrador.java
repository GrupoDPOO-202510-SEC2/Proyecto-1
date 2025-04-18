package usuario;
import java.util.HashSet;

import atraccion.*;


public class Administrador extends Usuario{

	public Administrador(String nombre, String login, String password, float altura, float peso) {
		super(nombre, login, password, altura, peso);
	}
	
	
	//-------------------------ESPECTACULOS--------------------------//
	
	public void crearEspectaculo(String nombre, String horario, String ubicacion) {
		parque.espectaculos.put(nombre,new Espectaculo(nombre, horario, ubicacion, new HashSet<String>()));
	}
	
	public void cambiarFechaEspectaculo(String nombre, String fechai, String fechaf) {
		
		Espectaculo espectaculo = parque.espectaculos.get(nombre);
		espectaculo.setFechaInicio(fechai);
		espectaculo.setFechaInicio(fechaf);
		
		parque.espectaculos.put(nombre, espectaculo);
		
	}
	
	public Espectaculo getEspectaculo(String nombre) {
		return parque.espectaculos.get(nombre);
	}
	
	public boolean agregarClimaRestringidoE(String nombre,String clima) {
		Espectaculo espectaculo = parque.espectaculos.get(nombre);
		boolean retorno =  espectaculo.addClimaRestringido(clima);
		parque.espectaculos.put(nombre, espectaculo);
		return retorno;
	}
	
	public boolean eliminarClimaRestringidoE(String nombre,String clima) {
		Espectaculo espectaculo = parque.espectaculos.get(nombre);
		boolean retorno =  espectaculo.deleteClimaRestringido(clima);
		parque.espectaculos.put(nombre, espectaculo);
		return retorno;
	}
	
	
	
	//-------------ATRACCIONES EWEWEWE--------------------------------------//
	

	
	
	public boolean crearAtraccionMecanica(String nombre, int capacidad, int empleadosMin, String ubicacion, int exclusividad, int alturaMax, int alturaMin, int pesoMin, int pesoMax, boolean riesgoAlto){
	
		parque.aMecanicas.put(nombre, new AtraccionMecanica(nombre, capacidad, empleadosMin, ubicacion, exclusividad, alturaMin, alturaMax, pesoMin, pesoMax, riesgoAlto));
		return parque.aMecanicas.containsKey(nombre);
	}
	
	public boolean crearAtraccionCultural(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion, int nivelExclusividad, int edadMinima, boolean esInteractiva) {
		parque.aCulturales.put(nombre, new AtraccionCultural(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad, edadMinima, esInteractiva));
		return parque.aCulturales.containsKey(nombre);
	}
	
	public Atraccion getAtraccion(String nombre) {
		
		if(parque.aMecanicas.containsKey(nombre)) {
			return parque.aMecanicas.get(nombre);
		}
		if (parque.aCulturales.containsKey(nombre)) {
			return parque.aCulturales.get(nombre);
		}
		return null;	
	}
	
	public void setCapacidadMaxima(String nombre, int cantidad) {
		
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
	
	public void setEmpleadosMinimos(String nombre, int cantidad) {
		
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
	
	public void setEnServicio(String nombre, boolean enServicio) {
		
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
	
	public void setExclusividad(String nombre, int exclusividad) {
		
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
	
	public int[] getOperadoresAtraccion(String nombre) {
		int[] retorno = new int[2];
		
		if(parque.aMecanicas.containsKey(nombre)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombre);
			retorno[0] = atraccion.getOperadoresDia();
			retorno[1] = atraccion.getOperadoresNoche();
		}
		if (parque.aCulturales.containsKey(nombre)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombre);
			retorno[0] = atraccion.getOperadoresDia();
			retorno[1] = atraccion.getOperadoresNoche();
			
		}
		
		return retorno;
	}
	
	public boolean addOperadorAAtraccion(String loginOperador, String turno, String nombreAtraccion) {
		
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.addOperador((OperadorAtraccion) parque.empleados.get(loginOperador), turno);
			parque.aMecanicas.put(nombreAtraccion, atraccion);
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
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
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
	
	
	public boolean deleteOperadorAAtraccion(String loginOperador, String turno, String nombreAtraccion) {
		
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, getLogin());
			parque.aMecanicas.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if (turno.equals(DIURNO)) {
				operador.setTurnoDia(false);
				operador.setLugarDeTrabajoDia(null);
			}else {
				operador.setTurnoNoche(true);
				operador.setLugarDeTrabajoNoche(nombreAtraccion);
			}
			parque.empleados.put(loginOperador, operador);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, getLogin());
			parque.aCulturales.put(nombreAtraccion, atraccion);
			OperadorAtraccion operador = (OperadorAtraccion) parque.empleados.get(loginOperador);
			if (turno.equals(DIURNO)) {
				operador.setTurnoDia(false);
				operador.setLugarDeTrabajoDia(null);
			}else {
				operador.setTurnoNoche(true);
				operador.setLugarDeTrabajoNoche(nombreAtraccion);
			}
			parque.empleados.put(loginOperador, operador);
			
		}
		return retorno;
	}
	
	public boolean addRestriccionSalud(String nombreAtraccion, String restriccion) {
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
	
	public boolean removeRestriccionSalud(String nombreAtraccion, String restriccion) {
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
	
	public HashSet<String> getClimasRestringidos(String nombreAtraccion) {
		
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
	
	public boolean addClimasRestringidos(String nombreAtraccion, String climaRestringido) {
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
	
	public boolean removeClimasRestringidos(String nombreAtraccion, String climaRestringido) {
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
	
	public int[] getAlturasPesos(String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		return new int[] {atraccion.getAlturaMaxima(),atraccion.getAlturaMinima(),atraccion.getPesoMaximo(),atraccion.getPesoMinimo()};
	}
	
	public void setAlturaMaxima(int alturaMaxima,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setAlturaMaxima(alturaMaxima);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public void setAlturaMinima(int alturaMinima,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setAlturaMinima(alturaMinima);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}

	public void setPesoMaximo(int pesoMaximo,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setPesoMaximo(pesoMaximo);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public void setPesoMinimo(int pesoMinimo,String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setPesoMinimo(pesoMinimo);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	public void setRiesgoAlto(boolean riesgoAlto, String nombreAtraccion) {
		AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
		atraccion.setRiesgoAlto(riesgoAlto);
		parque.aMecanicas.put(nombreAtraccion, atraccion);
	}
	
	//-----------------------ATRACCIONES CULTURALES----------------------------//
	
	public int getEdadMinima(String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		return atraccion.getEdadMinima();
	}

	public boolean isEsInteractiva(String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		return atraccion.isEsInteractiva();
	}
	
	public void setEdadMinima(int edadMinima, String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		atraccion.setEdadMinima(edadMinima);
		parque.aCulturales.put(nombreAtraccion, atraccion);
	}

	public void setEsInteractiva(boolean esInteractiva, String nombreAtraccion) {
		AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
		atraccion.setEsInteractiva(esInteractiva);
		parque.aCulturales.put(nombreAtraccion, atraccion);
	}
	
	//--------------------------EMPLEADOS---------------------------//
	
	public String getLugarDeTrabajo(String loginEmpleado) {
		
		return parque.empleados.get(loginEmpleado).getLugarDeTrabajo();
	}

	public boolean setLugarDeTrabajo(String lugarDeTrabajo, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setLugarDeTrabajo(lugarDeTrabajo);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}

	public String getRol(String loginEmpleado) {
		return parque.empleados.get(loginEmpleado).getRol();
	}
	
	
	public boolean[] getTurnos(String loginEmpleado) {
		return new boolean[] {parque.empleados.get(loginEmpleado).getTurnoDia(),parque.empleados.get(loginEmpleado).getTurnoNoche()};
	}
	
	public boolean setTurnoDia(boolean turnoDia, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setTurnoDia(turnoDia);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}
	
	public boolean setTurnoNoche(boolean turnoNoche, String loginEmpleado) {
		Empleado empleado = parque.empleados.get(loginEmpleado);
		boolean retorno = empleado.setTurnoNoche(turnoNoche);
		parque.empleados.put(loginEmpleado, empleado);
		return retorno;
	}
	
}

