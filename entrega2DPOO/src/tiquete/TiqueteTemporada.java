package tiquete;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TiqueteTemporada extends TiqueteExclusividad{
	
	private String fechaInicio;
	private String fechaFin;

	public TiqueteTemporada(double idTiquete, boolean fastPass, int exclusividad, String fechaInicio, String fechaFin) {
		super(idTiquete, fastPass, exclusividad);
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		isValido();
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	@Override
	public boolean isValido() {
		if(valido == true) {
			LocalDate fechaHoy = LocalDate.now();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("YYYY-MM-dd");
			String fechaHoyStr = fechaHoy.format(formato);
			
			LocalDate fechaHoyFormat = LocalDate.parse(fechaHoyStr);
			
			LocalDate fechaInicioLD = LocalDate.parse(fechaInicio);
			LocalDate fechaFinLD = LocalDate.parse(fechaFin);
			if((fechaHoyFormat.compareTo(fechaInicioLD) < 0) || (fechaHoyFormat.compareTo(fechaFinLD) > 0)) {
				valido = false;
			}
		}
		return valido;
		
	}
}