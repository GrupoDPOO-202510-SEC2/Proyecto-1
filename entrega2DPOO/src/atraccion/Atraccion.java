package atraccion;

import java.util.HashSet;

public abstract class Atraccion {
    private String nombre;
    private int capacidadMaxima;
    private int empleadosMinimos;
    private boolean operativa;
    private String ubicacion;
    private String nivelExclusividad;
    private HashSet<String> climasRestringidos;

    public Atraccion(String nombre, int capacidadMaxima, int empleadosMinimos, String ubicacion,
                     String nivelExclusividad) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadosMinimos = empleadosMinimos;
        this.ubicacion = ubicacion;
        this.nivelExclusividad = nivelExclusividad;
        this.climasRestringidos = new HashSet<>();
    }
}


