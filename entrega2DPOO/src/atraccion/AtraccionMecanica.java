package atraccion;

import java.util.HashSet;

public class AtraccionMecanica extends Atraccion{
	private float alturaMinima;
	private float alturaMaxima;
	private float pesoMaximo;
	private HashSet<String> restriccionesSalud;
	private boolean riesgoAlto;
	public AtraccionMecanica(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
            String nivelExclusividad, float alturaMinima, float alturaMaxima, float pesoMaximo, boolean riesgoAlto) {
		super(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad);
		this.alturaMinima = alturaMinima;
		this.alturaMaxima = alturaMaxima;
		this.pesoMaximo = pesoMaximo;
		this.riesgoAlto = riesgoAlto;
		this.restriccionesSalud = new HashSet<>();
	}
	

}
