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
		parque.espectaculos.get(nombre).setFechaInicio(fechai);
		parque.espectaculos.get(nombre).setFechaInicio(fechaf);
	}
	
	public Espectaculo getEspectaculo(String nombre) {
		return parque.espectaculos.get(nombre);
	}
	
	public boolean agregarClimaRestringidoE(String espectaculo,String clima) {
		return parque.espectaculos.get(espectaculo).addClimaRestringido(clima);
	}
	
	public boolean eliminarClimaRestringidoE(String espectaculo,String clima) {
		return parque.espectaculos.get(espectaculo).deleteClimaRestringido(clima);
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
			retorno = atraccion.addOperador((OperadorAtraccion) parque.empleados.get(loginOperador), getLogin());
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.addOperador((OperadorAtraccion) parque.empleados.get(loginOperador), getLogin());
			parque.aCulturales.put(nombreAtraccion, atraccion);
		}
		return retorno;
	}
	
	
	public boolean deleteOperadorAAtraccion(String loginOperador, String turno, String nombreAtraccion) {
		
		boolean retorno = false;
		
		if(parque.aMecanicas.containsKey(nombreAtraccion)) {
			AtraccionMecanica atraccion = parque.aMecanicas.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, getLogin());
			parque.aMecanicas.put(nombreAtraccion, atraccion);
		}
		if (parque.aCulturales.containsKey(nombreAtraccion)) {
			AtraccionCultural atraccion = parque.aCulturales.get(nombreAtraccion);
			retorno = atraccion.deleteOperador(loginOperador, getLogin());
			parque.aCulturales.put(nombreAtraccion, atraccion);
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
	
	public int[] getRangosMecanicos(String nombreAtraccion) {
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
	
	 
	
}

