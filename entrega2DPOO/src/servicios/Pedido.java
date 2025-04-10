package servicios;

import java.util.*;
import usuario.Usuario;

public class Pedido{
	
	private Usuario usuario;
	private String nombreProducto;
	private int cantidad;
	
	public Pedido(Usuario usuario, String nombreProducto, int cantidad) {
		this.usuario = usuario;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}
}