package usuario;

import atraccion.*;
import tiquete.*;

public class CajeroAtraccion  extends Empleado{

	public CajeroAtraccion(String nombre, String login, String password, float altura, float peso, String rol,
			String lugarDeTrabajo) {
		super(nombre, login, password, altura, peso, rol, lugarDeTrabajo);
	}
	
	public boolean puedePasar(Usuario usuario) {
		
		Tiquete tiquete = usuario.getTiqueteEnUso();
		String clase = tiquete.getClass().toString();
		
		
		if(parque.aCulturales.containsKey(this.lugarDeTrabajo)) {
			AtraccionCultural atraccion = parque.aCulturales.get(lugarDeTrabajo);
			if(atraccion.isEnServicio()) {
				if (atraccion.getCantidadDePpl() < atraccion.getCapacidadMaxima()) {
					if (atraccion.isOperable()) {
						if(atraccion.isEnServicio()) {
							if(atraccion.estaRestringido(usuario)) {
								
								if (clase.equals("Tiquete")) {
									 if (tiquete.isValido()) {
										 usuario.invalidarTiquete();
										 return true;
									 }
									 return false;
								}else if(clase.equals("TiqueteExclusividad")){
									TiqueteExclusividad tiqueteE = (TiqueteExclusividad) tiquete;
									if (tiquete.isValido() && tiqueteE.getExclusividad()>= atraccion.getNivelExclusividad()) {
										return true;
									}
									return false;
									
								}else{
									TiqueteExclusividad tiqueteT = (TiqueteTemporada) tiquete;
									if(tiqueteT.isValido()) {
										return true;
									}
									return false;
								}
							}
						}
					}
				}
			}
		}
		
		
		if(parque.aMecanicas.containsKey(this.lugarDeTrabajo)){
			AtraccionMecanica atraccion = parque.aMecanicas.get(lugarDeTrabajo);
			if(atraccion.isEnServicio()) {
				if (atraccion.getCantidadDePpl() < atraccion.getCapacidadMaxima()) {
					if (atraccion.isOperable()) {
						if(atraccion.isEnServicio()) {
							if(atraccion.estaRestringido(usuario) && atraccion.cumpleCondicionesFisicas(usuario)) {
								
								if (clase.equals("Tiquete")) {
									 if (tiquete.isValido()) {
										 usuario.invalidarTiquete();
										 return true;
									 }
									 return false;
								}else if(clase.equals("TiqueteExclusividad")){
									TiqueteExclusividad tiqueteE = (TiqueteExclusividad) tiquete;
									if (tiquete.isValido() && tiqueteE.getExclusividad()>= atraccion.getNivelExclusividad()) {
										return true;
									}
									return false;
									
								}else{
									TiqueteExclusividad tiqueteT = (TiqueteTemporada) tiquete;
									if(tiqueteT.isValido()) {
										return true;
									}
									return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public String dejarPasar(Usuario usuario) {
		if(puedePasar(usuario)) {
			if(usuario.getTiqueteEnUso().isFastPass()) {
				return "Tiene fastPass y puede pasar";
			}
			return "No tiene fastPass, tiene que hacer fila";
		}
		return "No puede entrar a esta atraccion";
	}
}
