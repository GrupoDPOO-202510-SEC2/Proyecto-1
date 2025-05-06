package control;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import atraccion.AtraccionCultural;
import atraccion.AtraccionMecanica;
import atraccion.Espectaculo;
import servicios.*;
import tiquete.*;
import usuario.*;


public class Persistencia {

	public static void guardarParque(Parque parque) throws Exception {
		

		PrintWriter pw = new PrintWriter( "C:\\Users\\aicar\\git\\repository2\\entrega2DPOO\\src\\data\\parque.json" );
        
		JSONObject jparque = new JSONObject();
		guardarTaquilla(jparque, parque.getTaquilla());
		guardarACulturales(jparque,parque.aCulturales);
		guardarAMecanicas(jparque,parque.aMecanicas);
		guardarMapaCafeterias(jparque,parque.mapaCafeterias);
		guardarMapaTiendas(jparque,parque.mapaTiendas);
		guardarEspectaculos(jparque,parque.espectaculos);
		guardarTiquetes(jparque,parque.tiquetes);
		guardarInventario(jparque,parque.inventario);
		guardarEmpleados(jparque,parque.empleados);
		guardarUsuarios(jparque,parque.usuarios);

		String jsonString = jparque.toString(4); 
		pw.write(jsonString);
		
        pw.close( );
	}
	
	private static void guardarUsuarios(JSONObject jparque, HashMap<String, Usuario> usuarios) throws Exception {
		JSONObject jusuarios = new JSONObject();
		for (Usuario usuario: usuarios.values()) {
			JSONObject jusuario = new JSONObject();
			guardarUsuario(jusuario, usuario);
			jusuarios.put(usuario.getNombre(), jusuario);
		}
		jparque.put("usuarios", jusuarios);
	}

	private static void guardarEmpleados(JSONObject jparque, HashMap<String, Empleado> empleados) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jempleados = new JSONObject();
		for (Empleado empleado: empleados.values()) {
			JSONObject jempleado = new JSONObject();
			
			guardarUsuario(jempleado, empleado);
			guardarEmpleado(jempleado, empleado);
			jempleado.put("tipo", "Empleado");
			
			if (empleado instanceof Cocinero) {
				Cocinero cocinero = (Cocinero) empleado;
				jempleado.put("tipo", "Cocinero");
				guardarCocinero(jempleado, cocinero);
			}else if (empleado instanceof OperadorAtraccion) {
				OperadorAtraccion operadorAtraccion = (OperadorAtraccion) empleado;
				guardarOperadorAtraccion(jempleado, operadorAtraccion);
				jempleado.put("tipo", "Operador");
			}else if(empleado instanceof ServicioGeneral) {
				jempleado.put("tipo", "Servicio");
				
			}else if(empleado instanceof CajeroAtraccion) {
				jempleado.put("tipo", "CajeroAtraccion");
				
			}else if(empleado instanceof CajeroTaquilla) {
				jempleado.put("tipo", "CajeroTaquilla");
				
			}else if(empleado instanceof Cajero) {
				jempleado.put("tipo", "Cajero");
				
			}
			
			
			jempleados.put(empleado.getNombre(), jempleado);
		}
		jparque.put("empleados", jempleados);
	}

	private static void guardarUsuario(JSONObject jusuario, Usuario usuario) throws Exception {
		
		jusuario.put("nombre", usuario.getNombre());
		jusuario.put("login", usuario.getLogin());
		jusuario.put("password", usuario.getPassword());
		jusuario.put("altura", usuario.getAltura());
		jusuario.put("peso", usuario.getPeso());
		if(usuario.getTiqueteEnUso() != null) {
		jusuario.put("tiqueteEnUso", usuario.getTiqueteEnUso().getIdTiquete());
		}else {
			jusuario.put("tiqueteEnUso",0);
		}

		JSONArray jrestricciones = new JSONArray();
		for (String restriccion: usuario.getRestricciones()) {
			jrestricciones.put(restriccion);
		}
		jusuario.put("restricciones", jrestricciones);
		
		
		JSONObject jcompras = new JSONObject();
		
		for (Map.Entry<String, Integer> compra : usuario.getCompras().entrySet()) {
			jcompras.put(compra.getKey() , compra.getValue());
		}
		
		jusuario.put("compras", jcompras);
		
		JSONArray jtiquetes= new JSONArray();
		for (double codigo: usuario.getTiquetesComprados()) {
			jtiquetes.put(codigo);
		}
		jusuario.put("tiquetesComprados", jtiquetes);
		

	}

	private static void guardarOperadorAtraccion(JSONObject jempleado, OperadorAtraccion operadorAtraccion) throws Exception {

		
		JSONArray jcapacitaciones = new JSONArray();
		
		for(String capacitacion: operadorAtraccion.getCapacitaciones()) {
			jcapacitaciones.put(capacitacion);
		}
		
		jempleado.put("capacitaciones", jcapacitaciones);
		jempleado.put("lugarDeTrabajoDia", operadorAtraccion.getLugarDeTrabajoDia());
		jempleado.put("lugarDeTrabajoNoche", operadorAtraccion.getLugarDeTrabajoNoche());
		
	}

	private static void guardarCocinero(JSONObject jempleado, Cocinero cocinero) throws Exception {
		
		JSONArray jalimentosPreparables = new JSONArray();
		
		for(String alimento: cocinero.getAlimentosPreparables()){
			jalimentosPreparables.put(alimento);
		}
		
		jempleado.put("alimentosPreparables", jalimentosPreparables);
	}

	private static void guardarEmpleado(JSONObject jempleado, Empleado empleado) throws Exception {
		
		jempleado.put("turnoDia", empleado.getTurnoDia());
		jempleado.put("turnoNoche", empleado.getTurnoNoche());
		jempleado.put("lugarDeTrabajo", empleado.getLugarDeTrabajo());
		jempleado.put("disponible", empleado.estaDisponible());
		
		// TODO Auto-generated method stub
		
	}

	private static void guardarInventario(JSONObject jparque, HashMap<String, Producto> inventario) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jinventario = new JSONObject();
		for (Producto producto: inventario.values()) {
			JSONObject jproducto = new JSONObject();
			
			guardarProducto(jproducto, producto);
			jinventario.put(producto.getNombre(), jproducto);
		
		}
		
		jparque.put("inventario", jinventario);
	}
	
	private static void guardarProducto(JSONObject jproducto, Producto producto) throws Exception {
		
		// TODO Auto-generated method stub
		jproducto.put("tipo", producto.getClass().getSimpleName());
		jproducto.put("nombre", producto.getNombre());
		jproducto.put("cantidad", producto.getCantidad());
		
	}
	
	private static void guardarTiquetes(JSONObject jparque, HashMap<Double, Tiquete> tiquetes) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jtiquetes = new JSONObject();
		
		
		for (Tiquete tiquete:tiquetes.values()) {
			JSONObject jtiquete = new JSONObject();
			jtiquete.put("tipo", "Tiquete");
			
			
			if(tiquete instanceof TiqueteExclusividad ) {
				TiqueteExclusividad exclusivo = (TiqueteExclusividad) tiquete; 
				jtiquete.put("exclusividad", exclusivo.getExclusividad());
				jtiquete.put("tipo", "Exclusivo");
			}else if(tiquete instanceof TiqueteTemporada ) {
				TiqueteTemporada temporada = (TiqueteTemporada) tiquete; 
				jtiquete.put("tipo", "Temporada");
				jtiquete.put("exclusividad", temporada.getExclusividad());
				jtiquete.put("fechaInicio", temporada.getFechaInicio());
				jtiquete.put("fechaFin", temporada.getFechaFin());
			}
			if(tiquete != null) {
			guardarTiquete(jtiquete, tiquete);
			jtiquetes.put(Double.toString(tiquete.getIdTiquete()), jtiquete);
			}
		}
		
		jparque.put("tiquetes", jtiquetes);
		
	}
	
	private static void guardarTiquete(JSONObject jtiquete, Tiquete tiquete) throws Exception {
		
		// TODO Auto-generated method stub
		
		jtiquete.put("idTiquete", tiquete.getIdTiquete());
		jtiquete.put("valido", tiquete.isValido());
		jtiquete.put("fastPass", tiquete.isFastPass());
		
	}
	private static void guardarEspectaculos(JSONObject jparque, HashMap<String, Espectaculo> espectaculos) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jespectaculos = new JSONObject();
		for (Espectaculo espectaculo: espectaculos.values()) {
			JSONObject jespectaculo = new JSONObject();
			
			guardarEspectaculo(jespectaculo, espectaculo);
			
			jespectaculos.put(espectaculo.getNombre(), jespectaculo);
		}
		
		jparque.put("espectaculos", jespectaculos);
		
	}
	private static void guardarEspectaculo(JSONObject jespectaculo, Espectaculo espectaculo) throws Exception {
		
		// TODO Auto-generated method stub
		
		jespectaculo.put("nombre", espectaculo.getNombre());
		jespectaculo.put("horario", espectaculo.getHorario());
		jespectaculo.put("ubicacion", espectaculo.getUbicacion());
		jespectaculo.put("fechaInicio", ""+espectaculo.getFechaInicio());
		jespectaculo.put("fechaFin", ""+espectaculo.getFechaFin());
		JSONArray jclimas= new JSONArray();
		
		
		for(String clima: espectaculo.getClimasRestringidos()) {
			jclimas.put(clima);
		}
		
		jespectaculo.put("climasRestringidos", jclimas);
	}
	private static void guardarMapaCafeterias(JSONObject jparque, HashMap<String, Cafeteria> mapaCafeterias) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jcafeterias= new JSONObject();
		guardarMenu(jcafeterias);
		for (Cafeteria cafeteria: mapaCafeterias.values()) {
			JSONObject jcafeteria = new JSONObject();
			guardarLugarServicio(jcafeteria, cafeteria);
			
			jcafeterias.put(cafeteria.getNombre(), jcafeteria);
		}
		
		jparque.put("mapaCafeterias", jcafeterias);
		
	}
	
private static void guardarMapaTiendas(JSONObject jparque, HashMap<String, Tienda> mapaTiendas) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jtiendas = new JSONObject();
		guardarItems(jtiendas);
		for (Tienda tienda: mapaTiendas.values()) {
			JSONObject jtienda = new JSONObject();
			guardarLugarServicio(jtienda, tienda);
			
			
			jtiendas.put(tienda.getNombre(), jtienda);
		}
		
		jparque.put("mapaTiendas", jtiendas);
		
	}
	
	
	private static void guardarItems(JSONObject jtienda) throws JSONException {
		
		
	// TODO Auto-generated method stub
	
		JSONArray jitems= new JSONArray();
		
		if(Tienda.items != null) {
		for(String item: Tienda.items) {
			
			jitems.put(item);
			
			}
		}
		
		jtienda.put("items", jitems);
	}
	private static void guardarMenu(JSONObject jcafeteria) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONArray jmenu = new JSONArray();
		
		if(Cafeteria.menu != null) {
			for(String comestible: Cafeteria.menu) {
			
			jmenu.put(comestible);
			}
		}
		
		jcafeteria.put("menu", jmenu);
	}
	private static void guardarLugarServicio(JSONObject jcafeteria, LugarDeServicio lugar) throws Exception {
		
		// TODO Auto-generated method stub
		
		jcafeteria.put("nombre", lugar.getNombre());
		jcafeteria.put("ubicacion", lugar.getUbicacion());
		jcafeteria.put("tipo", lugar.getTipo());
		
	}
	private static void guardarAMecanicas(JSONObject jparque, HashMap<String, AtraccionMecanica> aMecanicas) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jatraccionesMecanicas= new JSONObject();
		if(aMecanicas.values() != null) {
		for (AtraccionMecanica atraccionMecanica: aMecanicas.values()) {
			JSONObject jatraccionMecanica= new JSONObject();
			guardarAMecanica(jatraccionMecanica,atraccionMecanica);
			jatraccionesMecanicas.put(atraccionMecanica.getNombre(), jatraccionMecanica);
		}
		
		jparque.put("aMecanicas", jatraccionesMecanicas);
		
	}}
	private static void guardarAMecanica(JSONObject jatraccion, AtraccionMecanica atraccion) throws Exception {
		
		// TODO Auto-generated method stub
		
		jatraccion.put("capacidadMaxima", atraccion.getCapacidadMaxima());
		jatraccion.put("cantidadDePpl", atraccion.getCantidadDePpl());
		jatraccion.put("ubicacion", atraccion.getUbicacion());
		jatraccion.put("nivelExclusividad", atraccion.getNivelExclusividad());
		
		JSONArray jclimasRestringidos = new JSONArray();
		
		for(String clima: atraccion.getClimasRestringidos()) {
			jclimasRestringidos.put(clima);
		}
		jatraccion.put("climasRestringidos", jclimasRestringidos);
		
		JSONArray jrestriccionesSalud = new JSONArray();
		for(String restriccion: atraccion.getRestriccionesSalud()) {
			jrestriccionesSalud.put(restriccion);
		}
		jatraccion.put("restriccionesSalud", jrestriccionesSalud);
		
		jatraccion.put("nombre", atraccion.getNombre());
		jatraccion.put("empleadosMinimos", atraccion.getEmpleadosMinimos());
		jatraccion.put("enServicio", atraccion.isEnServicio());
		
		JSONArray joperadoresDia = new JSONArray();
		for(String operador: atraccion.getOperadoresDia()) {
			joperadoresDia.put(operador);
		}
		jatraccion.put("operadoresDia", joperadoresDia);
		
		JSONArray joperadoresNoche = new JSONArray();
		for(String operador: atraccion.getOperadoresNoche()) {
			joperadoresNoche.put(operador);
		}
		jatraccion.put("operadoresNoche", joperadoresNoche);
		
		jatraccion.put("alturaMinima", atraccion.getAlturaMinima());
		jatraccion.put("alturaMaxima", atraccion.getAlturaMaxima());
		jatraccion.put("pesoMaximo", atraccion.getPesoMaximo());
		jatraccion.put("pesoMinimo", atraccion.getPesoMinimo());
		jatraccion.put("riesgoAlto", atraccion.isRiesgoAlto());
		
	}
	private static void guardarACulturales(JSONObject jparque, HashMap<String, AtraccionCultural> aCulturales) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jatraccionesCulturales = new JSONObject();
		for (AtraccionCultural atraccionCultural: aCulturales.values()) {
			JSONObject jatraccionCultural = new JSONObject();
			guardarACultural(jatraccionCultural,atraccionCultural);
			jatraccionesCulturales.put(atraccionCultural.getNombre(), jatraccionCultural);
		}
		
		jparque.put("aCulturales", jatraccionesCulturales);
	}

	private static void guardarACultural(JSONObject jatraccion, AtraccionCultural atraccion) throws Exception {
		
		// TODO Auto-generated method stub

		jatraccion.put("capacidadMaxima", atraccion.getCapacidadMaxima());
		jatraccion.put("cantidadDePpl", atraccion.getCantidadDePpl());
		jatraccion.put("ubicacion", atraccion.getUbicacion());
		jatraccion.put("nivelExclusividad", atraccion.getNivelExclusividad());
		
		JSONArray jclimasRestringidos = new JSONArray();
		for(String clima: atraccion.getClimasRestringidos()) {
			jclimasRestringidos.put(clima);
		}
		jatraccion.put("climasRestringidos", jclimasRestringidos);
		
		JSONArray jrestriccionesSalud = new JSONArray();
		for(String restriccion: atraccion.getRestriccionesSalud()) {
			jrestriccionesSalud.put(restriccion);
		}
		jatraccion.put("restriccionesSalud", jrestriccionesSalud);
		
		jatraccion.put("nombre", atraccion.getNombre());
		jatraccion.put("empleadosMinimos", atraccion.getEmpleadosMinimos());
		jatraccion.put("enServicio", atraccion.isEnServicio());
		
		JSONArray joperadoresDia = new JSONArray();
		for(String operador: atraccion.getOperadoresDia()) {
			joperadoresDia.put(operador);
		}
		jatraccion.put("operadoresDia", joperadoresDia);
		
		JSONArray joperadoresNoche = new JSONArray();
		for(String operador: atraccion.getOperadoresNoche()) {
			joperadoresNoche.put(operador);
		}
		jatraccion.put("operadoresNoche", joperadoresNoche);
		
		jatraccion.put("edadMinima", atraccion.getEdadMinima());
		jatraccion.put("esInteractiva", atraccion.isEsInteractiva());
		
		
	}
	
	
	
	public static void guardarTaquilla(JSONObject jparque, Taquilla taquilla) throws Exception {
		
		JSONObject jtaquilla = new JSONObject();
		jtaquilla.put("ubicacion", taquilla.getUbicacion());
		jtaquilla.put("IDs", Taquilla.getIDs());
		JSONArray jpedidos = new JSONArray();
		
		for(PedidoTiquete pedido:taquilla.getPedidosTiquetes()) {
			JSONObject jpedido = new JSONObject();
			guardarPedido(jpedido,pedido);
			jpedidos.put(jpedido);
			}
		
		jtaquilla.put("pedidosTiquetes", jpedidos);
		jparque.put("taquilla", jtaquilla);
		
	}
	
	public static void guardarPedido(JSONObject jpedido, PedidoTiquete pedido) throws Exception {
		
		jpedido.put("loginUsuario", pedido.getLogin());
		jpedido.put("tipo", pedido.getTipo());
		jpedido.put("atraccionParaIndividuales", pedido.getAtraccionParaIndividuales());
		jpedido.put("fechaInicioParaTemporales", pedido.getFechaInicioParaTemporales());
		jpedido.put("fechaFinParaTemporales", pedido.getFechaFinParaTemporales());
		jpedido.put("exclusividad", pedido.getExclusividad());
		jpedido.put("fastPass", pedido.isFastPass());
		
	}
	
//--------------------------------------------Cargar shit--------------------------------------------------------//
	
	
	public static Parque cargarParque() throws Exception {
		
		String contenido = new String(Files.readAllBytes(Paths.get("C:\\Users\\aicar\\git\\repository2\\entrega2DPOO\\src\\data\\parque.json")));
		JSONObject jsonParque = new JSONObject(contenido);
		Parque parque = new Parque("Entrada");
		parque.taquilla = cargarTaquilla(jsonParque.getJSONObject("taquilla"));
		parque.aCulturales = cargarACulturales(jsonParque.getJSONObject("aCulturales"));
		parque.aMecanicas= cargarAMecanicas(jsonParque.getJSONObject("aMecanicas"));
		parque.mapaCafeterias= cargarMapaCafeterias(jsonParque.getJSONObject("mapaCafeterias"));
		parque.mapaTiendas = cargarMapaTiendas(jsonParque.getJSONObject("mapaTiendas"));
		parque.espectaculos = cargarEspectaculos(jsonParque.getJSONObject("espectaculos"));
		Usuario.parque = parque;
		parque.tiquetes = cargarTiquetes(jsonParque.getJSONObject("tiquetes"));
		parque.inventario = cargarInventario(jsonParque.getJSONObject("inventario"));
		parque.empleados = cargarEmpleados(jsonParque.getJSONObject("empleados"), parque);
		parque.usuarios = cargarUsuarios(jsonParque.getJSONObject("usuarios"), parque);
		
		
		
		
		return parque;
		
	}
	
	private static HashMap<String, Usuario> cargarUsuarios(JSONObject jempleados, Parque parque) throws Exception {
		
		HashMap<String, Usuario> empleados = new HashMap<String, Usuario>(); 
		
		Iterator<String> keys = jempleados.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jproducto = (JSONObject) jempleados.get(key);
			Usuario empleado = cargarUsuario(jproducto,parque);
			empleados.put(empleado.getLogin(), empleado);
			}
		
		return empleados;
	}

	private static Usuario cargarUsuario(JSONObject jproducto, Parque parque) throws Exception {
		
		String nombre = jproducto.getString("nombre");
		String login = jproducto.getString("login");
		String password = jproducto.getString("password");
		int altura = jproducto.getInt("altura");
		int peso = jproducto.getInt("peso");
		Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
		
		ArrayList<String> restricciones = new ArrayList<String>();
		JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
		for(int i = 0;i<jrestricciones.length();i++) {
			
			String restriccion = jrestricciones.getString(i);
			restricciones.add(restriccion);
		}
		
		HashMap<String, Integer> compras = new HashMap<String, Integer>();
		Iterator<String> keys = jproducto.getJSONObject("compras").keys();
		while (keys.hasNext()) {
			String key = keys.next();
			int compra = jproducto.getInt(key);
			compras.put(key, compra); 
			}
		
		ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
		JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
		for(int i = 0;i<jcomprados.length();i++) {
			Double id = jrestricciones.getDouble(i);
			tiquetesComprados.add(id);
		}
		
		
		Usuario usuario = new Usuario(nombre, login, password, altura, peso);
		usuario.setCompras(compras);
		usuario.setRestricciones(restricciones);
		usuario.setTiqueteEnUso(tiqueteEnUso);
		usuario.setTiquetesComprados(tiquetesComprados);
		return usuario;
		
	}

	private static HashMap<String, Empleado> cargarEmpleados(JSONObject jempleados, Parque parque) throws Exception {
		// TODO Auto-generated method stub
		
		HashMap<String, Empleado> empleados = new HashMap<String, Empleado>(); 
		
		Iterator<String> keys = jempleados.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jproducto = (JSONObject) jempleados.get(key);
			Empleado empleado = cargarEmpleado(jproducto,parque);
			empleados.put(empleado.getLogin(), empleado);
			}
		
		return empleados;
	}

	private static Empleado cargarEmpleado(JSONObject jproducto, Parque parque) throws Exception {
			String tipo = jproducto.getString("tipo");
			
			if(tipo.equals("Servicio")) {
				return (ServicioGeneral) cargarServicio(jproducto,parque);
			}if(tipo.equals("Cajero")) {
				return (Cajero) cargarCajero(jproducto,parque);
			}if(tipo.equals("CajeroTaquilla")) {
				return (CajeroTaquilla) cargarCajeroTaquilla(jproducto,parque);
			}if(tipo.equals("CajeroAtraccion")) {
				return (CajeroAtraccion) cargarCajeroAtraccion(jproducto,parque);
			}if(tipo.equals("Operador")) {
				return (OperadorAtraccion) cargarOperador(jproducto,parque);
			}else {
				return (Cocinero) cargarCocinero(jproducto,parque);
			}
		
		
	}

	private static Cocinero cargarCocinero(JSONObject jproducto,Parque parque) throws Exception {
		 
		
		ArrayList<String> alimentosPreparables = new ArrayList<String>() ;
		JSONArray jrestricciones = jproducto.getJSONArray("alimentosPreparables");
		for(int i = 0;i<jrestricciones.length();i++) {
			
			String restriccion = jrestricciones.getString(i);
			alimentosPreparables.add(restriccion);
		}
		String nombre = jproducto.getString("nombre");
		String login = jproducto.getString("login");
		String password = jproducto.getString("password");
		int altura = jproducto.getInt("altura");
		int peso = jproducto.getInt("peso");
		Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
		
		ArrayList<String> restricciones = new ArrayList<String>();
		JSONArray jrestricciones1 = jproducto.getJSONArray("restricciones");
		for(int i = 0;i<jrestricciones1.length();i++) {
			
			String restriccion = jrestricciones1.getString(i);
			restricciones.add(restriccion);
		}
		
		HashMap<String, Integer> compras = new HashMap<String, Integer>();
		Iterator<String> keys = jproducto.getJSONObject("compras").keys();
		while (keys.hasNext()) {
			String key = keys.next();
			int compra = jproducto.getInt(key);
			compras.put(key, compra); 
			}
		
		ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
		JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
		for(int i = 0;i<jcomprados.length();i++) {
			Double id = jrestricciones1.getDouble(i);
			tiquetesComprados.add(id);
		}
		
		
		boolean turnoDia = jproducto.getBoolean("turnoDia");
		boolean turnoNoche = jproducto.getBoolean("turnoNoche");
		String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
		boolean disponible = jproducto.getBoolean("disponible"); 
		
		Cocinero cocinero = new Cocinero(nombre, login, password, altura, peso,
				lugarDeTrabajo, alimentosPreparables);
		
		cocinero.setCompras(compras);
		cocinero.setDisponible(disponible);
		cocinero.setLugarDeTrabajo(lugarDeTrabajo);
		cocinero.setRestricciones(restricciones);
		cocinero.setTiqueteEnUso(tiqueteEnUso);
		cocinero.setTiquetesComprados(tiquetesComprados);
		cocinero.setTurnoDia(turnoDia);
		cocinero.setTurnoNoche(turnoNoche);
		
		return cocinero;
		
	}

	private static OperadorAtraccion cargarOperador(JSONObject jproducto,Parque parque) throws Exception {
		
		String nombre = jproducto.getString("nombre");
		String login = jproducto.getString("login");
		String password = jproducto.getString("password");
		int altura = jproducto.getInt("altura");
		int peso = jproducto.getInt("peso");
		Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
		
		ArrayList<String> restricciones = new ArrayList<String>();
		JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
		for(int i = 0;i<jrestricciones.length();i++) {
			
			String restriccion = jrestricciones.getString(i);
			restricciones.add(restriccion);
		}
		
		HashMap<String, Integer> compras = new HashMap<String, Integer>();
		Iterator<String> keys = jproducto.getJSONObject("compras").keys();
		while (keys.hasNext()) {
			String key = keys.next();
			int compra = jproducto.getInt(key);
			compras.put(key, compra); 
			}
		
		ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
		JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
		for(int i = 0;i<jcomprados.length();i++) {
			Double id = jrestricciones.getDouble(i);
			tiquetesComprados.add(id);
		}
		
		
		boolean turnoDia = jproducto.getBoolean("turnoDia");
		boolean turnoNoche = jproducto.getBoolean("turnoNoche");
		String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
		boolean disponible = jproducto.getBoolean("disponible"); 
		
		
		
		
		ArrayList<String> capacitaciones = new ArrayList<String>();
		JSONArray jcomprados1 = jproducto.getJSONArray("capacitaciones");
		for(int i = 0;i<jcomprados1.length();i++) {
			String id = jcomprados1.getString(i);
			capacitaciones.add(id);
		}
		
		String[] lugaresDeTrabajo = jproducto.getString("lugarDeTrabajo").split(", ");
		
		OperadorAtraccion operador = new OperadorAtraccion(nombre, login, password, altura, peso);

		operador.setCompras(compras);
		operador.setDisponible(disponible);
		operador.setLugarDeTrabajoDia(lugaresDeTrabajo[0]);
		operador.setLugarDeTrabajoNoche(lugaresDeTrabajo[1]);
		operador.setLugarDeTrabajo(lugarDeTrabajo);
		operador.setRestricciones(restricciones);
		operador.setTiqueteEnUso(tiqueteEnUso);
		operador.setTiquetesComprados(tiquetesComprados);
		operador.setTurnoDia(turnoDia);
		operador.setTurnoNoche(turnoNoche);
		operador.setCapacitaciones(capacitaciones);
		
		return operador;
	}

	//basico
	
	private static ServicioGeneral cargarServicio(JSONObject jproducto, Parque parque) throws Exception {
		
		String nombre = jproducto.getString("nombre");
		String login = jproducto.getString("login");
		String password = jproducto.getString("password");
		int altura = jproducto.getInt("altura");
		int peso = jproducto.getInt("peso");
		Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
		
		ArrayList<String> restricciones = new ArrayList<String>();
		JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
		for(int i = 0;i<jrestricciones.length();i++) {
			
			String restriccion = jrestricciones.getString(i);
			restricciones.add(restriccion);
		}
		
		HashMap<String, Integer> compras = new HashMap<String, Integer>();
		Iterator<String> keys = jproducto.getJSONObject("compras").keys();
		while (keys.hasNext()) {
			String key = keys.next();
			int compra = jproducto.getInt(key);
			compras.put(key, compra); 
			}
		
		ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
		JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
		for(int i = 0;i<jcomprados.length();i++) {
			Double id = jrestricciones.getDouble(i);
			tiquetesComprados.add(id);
		}
		
		
		boolean turnoDia = jproducto.getBoolean("turnoDia");
		boolean turnoNoche = jproducto.getBoolean("turnoNoche");
		String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
		boolean disponible = jproducto.getBoolean("disponible"); 
		
		ServicioGeneral empleado = new ServicioGeneral(nombre, login, password, altura, peso, lugarDeTrabajo);
		
		empleado.setCompras(compras);
		empleado.setTiquetesComprados(tiquetesComprados);
		empleado.setRestricciones(restricciones);
		empleado.setTurnoDia(turnoDia);
		empleado.setTurnoNoche(turnoNoche);
		empleado.setDisponible(disponible);
		
		return empleado;
		
		
	}
	
private static CajeroTaquilla cargarCajeroTaquilla(JSONObject jproducto, Parque parque) throws Exception {
		
		String nombre = jproducto.getString("nombre");
		String login = jproducto.getString("login");
		String password = jproducto.getString("password");
		int altura = jproducto.getInt("altura");
		int peso = jproducto.getInt("peso");
		Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
		
		ArrayList<String> restricciones = new ArrayList<String>();
		JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
		for(int i = 0;i<jrestricciones.length();i++) {
			
			String restriccion = jrestricciones.getString(i);
			restricciones.add(restriccion);
		}
		
		HashMap<String, Integer> compras = new HashMap<String, Integer>();
		Iterator<String> keys = jproducto.getJSONObject("compras").keys();
		while (keys.hasNext()) {
			String key = keys.next();
			int compra = jproducto.getInt(key);
			compras.put(key, compra); 
			}
		
		ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
		JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
		for(int i = 0;i<jcomprados.length();i++) {
			Double id = jrestricciones.getDouble(i);
			tiquetesComprados.add(id);
		}
		
		
		boolean turnoDia = jproducto.getBoolean("turnoDia");
		boolean turnoNoche = jproducto.getBoolean("turnoNoche");
		String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
		boolean disponible = jproducto.getBoolean("disponible"); 
		
		CajeroTaquilla empleado = new CajeroTaquilla(nombre, login, password, altura, peso, lugarDeTrabajo);
		
		empleado.setCompras(compras);
		empleado.setTiquetesComprados(tiquetesComprados);
		empleado.setRestricciones(restricciones);
		empleado.setTurnoDia(turnoDia);
		empleado.setTurnoNoche(turnoNoche);
		empleado.setDisponible(disponible);
		
		return empleado;
		
		
	}
	
private static Cajero cargarCajero(JSONObject jproducto, Parque parque) throws Exception {
	
	String nombre = jproducto.getString("nombre");
	String login = jproducto.getString("login");
	String password = jproducto.getString("password");
	int altura = jproducto.getInt("altura");
	int peso = jproducto.getInt("peso");
	Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
	
	ArrayList<String> restricciones = new ArrayList<String>();
	JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
	for(int i = 0;i<jrestricciones.length();i++) {
		
		String restriccion = jrestricciones.getString(i);
		restricciones.add(restriccion);
	}
	
	HashMap<String, Integer> compras = new HashMap<String, Integer>();
	Iterator<String> keys = jproducto.getJSONObject("compras").keys();
	while (keys.hasNext()) {
		String key = keys.next();
		int compra = jproducto.getInt(key);
		compras.put(key, compra); 
		}
	
	ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
	JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
	for(int i = 0;i<jcomprados.length();i++) {
		Double id = jrestricciones.getDouble(i);
		tiquetesComprados.add(id);
	}
	
	
	boolean turnoDia = jproducto.getBoolean("turnoDia");
	boolean turnoNoche = jproducto.getBoolean("turnoNoche");
	String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
	boolean disponible = jproducto.getBoolean("disponible"); 
	
	Cajero empleado = new Cajero(nombre, login, password, altura, peso, lugarDeTrabajo);
	
	empleado.setCompras(compras);
	empleado.setTiquetesComprados(tiquetesComprados);
	empleado.setRestricciones(restricciones);
	empleado.setTurnoDia(turnoDia);
	empleado.setTurnoNoche(turnoNoche);
	empleado.setDisponible(disponible);
	
	return empleado;
	
	
}

	private static CajeroAtraccion cargarCajeroAtraccion(JSONObject jproducto, Parque parque) throws Exception {
	
	String nombre = jproducto.getString("nombre");
	String login = jproducto.getString("login");
	String password = jproducto.getString("password");
	int altura = jproducto.getInt("altura");
	int peso = jproducto.getInt("peso");
	Tiquete tiqueteEnUso = parque.tiquetes.get(jproducto.getDouble("tiqueteEnUso"));
	
	ArrayList<String> restricciones = new ArrayList<String>();
	JSONArray jrestricciones = jproducto.getJSONArray("restricciones");
	for(int i = 0;i<jrestricciones.length();i++) {
		
		String restriccion = jrestricciones.getString(i);
		restricciones.add(restriccion);
	}
	
	HashMap<String, Integer> compras = new HashMap<String, Integer>();
	Iterator<String> keys = jproducto.getJSONObject("compras").keys();
	while (keys.hasNext()) {
		String key = keys.next();
		int compra = jproducto.getInt(key);
		compras.put(key, compra); 
		}
	
	ArrayList<Double> tiquetesComprados = new ArrayList<Double>();
	JSONArray jcomprados = jproducto.getJSONArray("tiquetesComprados");
	for(int i = 0;i<jcomprados.length();i++) {
		Double id = jrestricciones.getDouble(i);
		tiquetesComprados.add(id);
	}
	
	
	boolean turnoDia = jproducto.getBoolean("turnoDia");
	boolean turnoNoche = jproducto.getBoolean("turnoNoche");
	String lugarDeTrabajo = jproducto.getString("lugarDeTrabajo");
	boolean disponible = jproducto.getBoolean("disponible"); 
	
	CajeroAtraccion empleado = new CajeroAtraccion(nombre, login, password, altura, peso, lugarDeTrabajo);
	
	empleado.setCompras(compras);
	empleado.setTiquetesComprados(tiquetesComprados);
	empleado.setRestricciones(restricciones);
	empleado.setTurnoDia(turnoDia);
	empleado.setTurnoNoche(turnoNoche);
	empleado.setDisponible(disponible);
	
	return empleado;
	
	
}
	

	private static HashMap<String, Producto> cargarInventario(JSONObject jinventario) throws Exception {
		
		HashMap<String, Producto> productos = new HashMap<String, Producto>(); 
		
		Iterator<String> keys = jinventario.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jproducto = (JSONObject) jinventario.get(key);
			Producto producto = cargarProducto(jproducto);
			productos.put(producto.getNombre(), producto);
			}
		
		return productos;
	}
	
	
	private static Producto cargarProducto(JSONObject jproducto) throws JSONException {
		
		String nombre = jproducto.getString("nombre");
		int cantidad = jproducto.getInt("cantidad");
		
		if(jproducto.getString("tipo").equals("Comestible")) {
			return new Comestible(nombre,cantidad);
		}
		else {
			return new Souvenir(nombre,cantidad);
		}
	}

	private static HashMap<Double, Tiquete> cargarTiquetes(JSONObject jtiquetes) throws Exception, Exception {
	
	HashMap<Double, Tiquete> tiquetes = new HashMap<Double, Tiquete>(); 
		
		Iterator<String> keys = jtiquetes.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jtiquete= (JSONObject) jtiquetes.get(key);
			
			
			if(jtiquete.getString("tipo").equals("Tiquete")) {
				Tiquete tiquete = cargarTiquete(jtiquete);
				tiquetes.put(tiquete.getIdTiquete(), tiquete);
			}else if(jtiquete.getString("tipo").equals("Exclusivo")) {
				TiqueteExclusividad tiquete = cargarExclusivo(jtiquete);
				tiquetes.put(tiquete.getIdTiquete(), tiquete);
			}else {
				TiqueteTemporada tiquete =  cargarTemporada(jtiquete);
				tiquetes.put(tiquete.getIdTiquete(), tiquete);
			}
			
		
		}
		return tiquetes;
	}
	
	private static TiqueteTemporada cargarTemporada(JSONObject jtiquete) throws Exception {
		double idTiquete = jtiquete.getDouble("idTiquete");
		boolean valido = jtiquete.getBoolean("valido");
		boolean fastPass = jtiquete.getBoolean("fastPass");
		int exclusividad = jtiquete.getInt("exclusividad");
		String fechaInicio = jtiquete.getString("fechaInicio");
		String fechaFin = jtiquete.getString("fechaFin");
		
		TiqueteTemporada tiquete = new TiqueteTemporada(idTiquete,fastPass,exclusividad,fechaInicio, fechaFin);
		tiquete.setValido(valido);
		
		return tiquete;
	}

	private static TiqueteExclusividad cargarExclusivo(JSONObject jtiquete) throws Exception {
		
		double idTiquete = jtiquete.getDouble("idTiquete");
		boolean valido = jtiquete.getBoolean("valido");
		boolean fastPass = jtiquete.getBoolean("fastPass");
		int exclusividad = jtiquete.getInt("exclusividad");
		TiqueteExclusividad tiquete = new TiqueteExclusividad(idTiquete,fastPass,exclusividad);
		tiquete.setValido(valido);
		
		return tiquete;
	}

	private static Tiquete cargarTiquete(JSONObject jtiquete) throws Exception {

		double idTiquete = jtiquete.getDouble("idTiquete");
		boolean valido = jtiquete.getBoolean("valido");
		boolean fastPass = jtiquete.getBoolean("fastPass");
		
		Tiquete tiquete = new Tiquete(idTiquete,fastPass);
		tiquete.setValido(valido);
		
		return tiquete;
		
	}

	

	private static HashMap<String, Espectaculo> cargarEspectaculos(JSONObject jespectaculos) throws Exception {
		
		HashMap<String, Espectaculo> espectaculos = new HashMap<String, Espectaculo>(); 
		
		Iterator<String> keys = jespectaculos.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jespectaculo= (JSONObject) jespectaculos.get(key);
			Espectaculo espectaculo = cargarEspectaculo(jespectaculo);
			espectaculos.put(espectaculo.getNombre(), espectaculo);
			}
		
		return espectaculos;
	}

	private static Espectaculo cargarEspectaculo(JSONObject jespectaculo) throws Exception {
		
		
		
		String nombre = jespectaculo.getString("nombre");
		String horario = jespectaculo.getString("horario");
		String ubicacion = jespectaculo.getString("ubicacion");
		String fechaInicio = jespectaculo.getString("fechaInicio");
		String fechaFin = jespectaculo.getString("fechaFin");
		HashSet<String> climasRestringidos = new HashSet<String>(); 
		for(int i = 0; i< jespectaculo.getJSONArray("climasRestringidos").length(); i++) {
			climasRestringidos.add(jespectaculo.getJSONArray("climasRestringidos").getString(i));
		}
		
		return new Espectaculo(nombre, horario, ubicacion, fechaInicio, fechaFin,climasRestringidos);
	}

	private static HashMap<String, Tienda> cargarMapaTiendas(JSONObject jaCafeterias) throws Exception {
		
		HashMap<String, Tienda> mapaCafeterias = new HashMap<String, Tienda>(); 
		JSONArray jmenu = jaCafeterias.getJSONArray("items");
		HashSet<String> menu = new HashSet<String>();
		for(int i = 0;i<jmenu.length();i++) {
			
			String comia= jmenu.getString(i);
			menu.add(comia);
		}
		Tienda.items = menu;
		
		Iterator<String> keys = jaCafeterias.keys();
		keys.next();
		while (keys.hasNext()) {
			String key = keys.next();
			if(!key.equals("items")) {
				JSONObject jcafeteria= (JSONObject) jaCafeterias.get(key);
				Tienda cafeteria = cargarTienda(jcafeteria);
				mapaCafeterias.put(cafeteria.getNombre(), cafeteria);
			}
		}
		return mapaCafeterias;
	}

	private static Tienda cargarTienda(JSONObject jcafeteria) throws Exception {
		
		String nombre = jcafeteria.getString("nombre");
		String ubicacion = jcafeteria.getString("ubicacion");
		String tipo =	jcafeteria.getString("tipo");
		
		return new Tienda(nombre,ubicacion,tipo);
	}

	private static HashMap<String, Cafeteria> cargarMapaCafeterias(JSONObject jaCafeterias) throws Exception {
		
		HashMap<String, Cafeteria> mapaCafeterias = new HashMap<String, Cafeteria>(); 
		JSONArray jmenu = jaCafeterias.getJSONArray("menu");
		HashSet<String> menu = new HashSet<String>();
		for(int i = 0;i<jmenu.length();i++) {
			
			String comia= jmenu.getString(i);

			System.out.print(comia);
			menu.add(comia);
		}
		Cafeteria.menu = menu;

		
		Iterator<String> keys = jaCafeterias.keys();		
		while (keys.hasNext()) {
			String key = keys.next();
			if(!key.equals("menu")) {
			JSONObject jcafeteria= (JSONObject) jaCafeterias.get(key);
			Cafeteria cafeteria = cargarCafeteria(jcafeteria);
			mapaCafeterias.put(cafeteria.getNombre(), cafeteria);
			}
		}
		
		return mapaCafeterias;
	}
	
	
	

	private static Cafeteria cargarCafeteria(JSONObject jcafeteria) throws Exception {
		
		String nombre = jcafeteria.getString("nombre");
		String ubicacion = jcafeteria.getString("ubicacion");
		String tipo =	jcafeteria.getString("tipo");
		
		return new Cafeteria(nombre,ubicacion,tipo);
	}

	private static HashMap<String, AtraccionMecanica> cargarAMecanicas(JSONObject jaMecanicas) throws Exception {
		HashMap<String, AtraccionMecanica> aMecanica = new HashMap<String, AtraccionMecanica>(); 
		
		//  extraido de: https://stackoverflow.com/questions/9151619/how-to-iterate-over-a-jsonobject  //

		Iterator<String> keys = jaMecanicas.keys();
	
		while (keys.hasNext()) {
		String key = keys.next();
		JSONObject jatraccionMecanica= (JSONObject) jaMecanicas.get(key);
		AtraccionMecanica atraccionMecanica= cargarAtraccionMecanica(jatraccionMecanica);
		aMecanica.put(atraccionMecanica.getNombre(), atraccionMecanica);
		}

		return aMecanica;
		
	}

	private static AtraccionMecanica cargarAtraccionMecanica(JSONObject jatraccionMecanica) throws Exception {
		
		int capacidadMaxima = jatraccionMecanica.getInt("capacidadMaxima");
		int cantidadDePpl = jatraccionMecanica.getInt("cantidadDePpl");
		String ubicacion = jatraccionMecanica.getString("ubicacion");
		int nivelExclusividad = jatraccionMecanica.getInt("nivelExclusividad");
		
		HashSet<String> climasRestringidos = new HashSet<String>();
		
		JSONArray jclimasRestringidos = jatraccionMecanica.getJSONArray("climasRestringidos");
		for(int i = 0;i<jclimasRestringidos.length();i++) {
			
			String clima = jclimasRestringidos.getString(i);
			climasRestringidos.add(clima);
			
		}
		
		
		HashSet<String> restriccionesSalud = new HashSet<String>();
		
		JSONArray jrestriccionesSalud = jatraccionMecanica.getJSONArray("restriccionesSalud");
		for(int i = 0;i<jrestriccionesSalud.length();i++) {
			
			String restriccion = jrestriccionesSalud.getString(i);
			restriccionesSalud.add(restriccion);
			
		}
		
		
		String nombre = jatraccionMecanica.getString("nombre");
		int empleadosMinimos = jatraccionMecanica.getInt("empleadosMinimos");
		boolean enServicio = jatraccionMecanica.getBoolean("enServicio");
		
		
		HashSet<String> operadoresDia = new HashSet<String>();
		
		JSONArray joperadoresDia = jatraccionMecanica.getJSONArray("operadoresDia");
		for(int i = 0;i<joperadoresDia.length();i++) {
			
			String operador = joperadoresDia.getString(i);
			operadoresDia.add(operador);
			
		}
		

		HashSet<String> operadoresNoche = new HashSet<String>();
		
		JSONArray joperadoresNoche = jatraccionMecanica.getJSONArray("operadoresNoche");
		for(int i = 0;i<joperadoresNoche.length();i++) {
			
			String operador = joperadoresNoche.getString(i);
			operadoresNoche.add(operador);
		}
			
		int alturaMinima = jatraccionMecanica.getInt("alturaMinima");
		int alturaMaxima = jatraccionMecanica.getInt("alturaMaxima");
		int pesoMaximo = jatraccionMecanica.getInt("pesoMaximo");
		int pesoMinimo = jatraccionMecanica.getInt("pesoMinimo");
		boolean riesgoAlto = jatraccionMecanica.getBoolean("riesgoAlto");
		
		AtraccionMecanica atraccion = new AtraccionMecanica(nombre, capacidadMaxima, empleadosMinimos, ubicacion,
	            nivelExclusividad, alturaMinima, alturaMaxima, pesoMinimo, pesoMaximo, riesgoAlto);
		atraccion.setClimasRestringidos(climasRestringidos);
		atraccion.setOperadoresDia(operadoresDia);
		atraccion.setOperadoresNoche(operadoresNoche);
		atraccion.setRestriccionesSalud(restriccionesSalud);
		atraccion.setEnServicio(enServicio);
		atraccion.setCantidadDePpl(cantidadDePpl);
		return atraccion;
		}
		

	private static HashMap<String, AtraccionCultural> cargarACulturales(JSONObject jaCulturales) throws Exception {
		
		HashMap<String, AtraccionCultural> aCulturales = new HashMap<String, AtraccionCultural>(); 
		
		//  extraido de: https://stackoverflow.com/questions/9151619/how-to-iterate-over-a-jsonobject  //

		Iterator<String> keys = jaCulturales.keys();
	
		while (keys.hasNext()) {
		String key = keys.next();
		JSONObject jatraccionCultural = (JSONObject) jaCulturales.get(key);
		AtraccionCultural atraccionCultural = cargarAtraccionCultural(jatraccionCultural);
		aCulturales.put(atraccionCultural.getNombre(), atraccionCultural);
		}

		return aCulturales;
		
	}

	private static AtraccionCultural cargarAtraccionCultural(JSONObject jatraccionCultural) throws Exception {
		
		int capacidadMaxima = jatraccionCultural.getInt("capacidadMaxima");
		int cantidadDePpl = jatraccionCultural.getInt("cantidadDePpl");
		String ubicacion = jatraccionCultural.getString("ubicacion");
		int nivelExclusividad = jatraccionCultural.getInt("nivelExclusividad");
		
		HashSet<String> climasRestringidos = new HashSet<String>();
		
		JSONArray jclimasRestringidos = jatraccionCultural.getJSONArray("climasRestringidos");
		for(int i = 0;i<jclimasRestringidos.length();i++) {
			
			String clima = jclimasRestringidos.getString(i);
			climasRestringidos.add(clima);
			
		}
		
		
		HashSet<String> restriccionesSalud = new HashSet<String>();
		
		JSONArray jrestriccionesSalud = jatraccionCultural.getJSONArray("restriccionesSalud");
		for(int i = 0;i<jrestriccionesSalud.length();i++) {
			
			String restriccion = jrestriccionesSalud.getString(i);
			restriccionesSalud.add(restriccion);
			
		}
		
		
		String nombre = jatraccionCultural.getString("nombre");
		int empleadosMinimos = jatraccionCultural.getInt("empleadosMinimos");
		boolean enServicio = jatraccionCultural.getBoolean("enServicio");
		
		
		HashSet<String> operadoresDia = new HashSet<String>();
		
		JSONArray joperadoresDia = jatraccionCultural.getJSONArray("operadoresDia");
		for(int i = 0;i<joperadoresDia.length();i++) {
			
			String operador = joperadoresDia.getString(i);
			operadoresDia.add(operador);
			
		}
		

		HashSet<String> operadoresNoche = new HashSet<String>();
		
		JSONArray joperadoresNoche = jatraccionCultural.getJSONArray("operadoresNoche");
		for(int i = 0;i<joperadoresNoche.length();i++) {
			
			String operador = joperadoresNoche.getString(i);
			operadoresNoche.add(operador);
			
		}
		
		int edadMinima = jatraccionCultural.getInt("edadMinima");
		boolean esInteractiva = jatraccionCultural.getBoolean("esInteractiva");
		
		
		AtraccionCultural atraccion = new AtraccionCultural(nombre, capacidadMaxima, empleadosMinimos, ubicacion, nivelExclusividad, edadMinima, esInteractiva);
		atraccion.setClimasRestringidos(climasRestringidos);
		atraccion.setOperadoresDia(operadoresDia);
		atraccion.setOperadoresNoche(operadoresNoche);
		atraccion.setRestriccionesSalud(restriccionesSalud);
		atraccion.setEnServicio(enServicio);
		atraccion.setCantidadDePpl(cantidadDePpl);
		
		return atraccion;
	}

	private static Taquilla cargarTaquilla(JSONObject jtaquilla) throws Exception {

		
		Taquilla taquilla = new Taquilla(jtaquilla.getString("ubicacion"));
		Taquilla.setIDs(jtaquilla.getDouble("IDs"));
		JSONArray jpedidos = jtaquilla.getJSONArray("pedidosTiquetes");
		
		Queue<PedidoTiquete> pedidos = new LinkedList<PedidoTiquete>();
		
		for(int i = 0;i<jpedidos.length();i++) {
			
			JSONObject jpedido = jpedidos.getJSONObject(i);
			PedidoTiquete pedido = cargarPedido(jpedido);
			pedidos.add(pedido);
			
		}
		
		taquilla.setPedidosTiquetes(pedidos);
		
		return taquilla;
	}

	private static PedidoTiquete cargarPedido(JSONObject jpedido) throws Exception {
		
		PedidoTiquete pedido = new PedidoTiquete(jpedido.getString("loginUsuario"),jpedido.getString("tipo") ,jpedido.getString("atraccionParaIndividuales" ) 
				, jpedido.getString("fechaInicioParaTemporales" ), jpedido.getString("fechaFinParaTemporales"), jpedido.getInt("exclusividad"), jpedido.getBoolean("fastPass") );
		return pedido;
	}
	
	
	
	
}
