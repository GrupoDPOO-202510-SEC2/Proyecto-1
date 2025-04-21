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
		
		
		PrintWriter pw = new PrintWriter( "../data/parque.json" );
        jparque.write(pw);
        pw.close( );
	}
	
	private static void guardarUsuarios(JSONObject jparque, HashMap<String, Usuario> usuarios) {
		
		
		
	}

	private static void guardarEmpleados(JSONObject jparque, HashMap<String, Empleado> empleados) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jempleados = new JSONObject();
		for (Empleado empleado: empleados.values()) {
			JSONObject jempleado = new JSONObject();
			
			guardarUsuario(jempleado, empleado);
			guardarEmpleado(jempleado, empleado);
			
			
			if (empleado instanceof Cocinero) {
				Cocinero cocinero = (Cocinero) empleado;
				guardarCocinero(jempleado, cocinero);
			}else if (empleado instanceof OperadorAtraccion) {
				OperadorAtraccion operadorAtraccion = (OperadorAtraccion) empleado;
				guardarOperadorAtraccion(jempleado, operadorAtraccion);
			}
			
			jempleados.put(empleado.getNombre(), jempleado);
		}
		jparque.put("inventario", jempleados);
	}

	private static void guardarUsuario(JSONObject jusuario, Usuario usuario) throws Exception {
		
		jusuario.put("nombre", usuario.getNombre());
		jusuario.put("login", usuario.getLogin());
		jusuario.put("password", usuario.getPassword());
		jusuario.put("altura", usuario.getAltura());
		jusuario.put("peso", usuario.getPeso());
		jusuario.put("tiqueteEnUso", usuario.getTiqueteEnUso());

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
		jusuario.put("tiquetes", jtiquetes);
		

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
		
		jproducto.put("nombre", producto.getNombre());
		jproducto.put("cantidad", producto.getCantidad());
		
	}
	
	private static void guardarTiquetes(JSONObject jparque, HashMap<Double, Tiquete> tiquetes) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONObject jtiquetes = new JSONObject();
		ArrayList<Tiquete> tiquetesA = (ArrayList<Tiquete>) tiquetes.values();
		for (int i = 0; i> tiquetes.size(); i++) {
			JSONObject jtiquete = new JSONObject();
			Tiquete tiquete = tiquetesA.get(i);
			
			
			
			if(tiquetesA.get(i) instanceof TiqueteExclusividad ) {
				TiqueteExclusividad exclusivo = (TiqueteExclusividad) tiquetesA.get(i); 
				jtiquete.put("exclusividad", exclusivo.getExclusividad());
			}else if(tiquetesA.get(i) instanceof TiqueteTemporada ) {
				TiqueteTemporada temporada = (TiqueteTemporada) tiquetesA.get(i); 
				jtiquete.put("exclusividad", temporada.getExclusividad());
				jtiquete.put("fechaInicio", temporada.getFechaInicio());
				jtiquete.put("fechaFin", temporada.getFechaFin());
			}
			
			guardarTiquete(jtiquete, tiquete);
			jtiquetes.put(Double.toString(tiquete.getIdTiquete()), jtiquete);
			
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
		jespectaculo.put("fechaInicio", espectaculo.getFechaInicio());
		jespectaculo.put("fechaFin", espectaculo.getFechaFin());
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
		
		for(String item: Tienda.items) {
			
			jitems.put(item);
			
		}
		
		jtienda.put("items", jitems);
	}
	private static void guardarMenu(JSONObject jcafeteria) throws Exception {
		
		// TODO Auto-generated method stub
		
		JSONArray jmenu = new JSONArray();
		
		for(String comestible: Cafeteria.menu) {
			
			jmenu.put(comestible);
			
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
		for (AtraccionMecanica atraccionMecanica: aMecanicas.values()) {
			JSONObject jatraccionMecanica= new JSONObject();
			guardarAMecanica(jatraccionMecanica,atraccionMecanica);
			jatraccionesMecanicas.put(atraccionMecanica.getNombre(), jatraccionMecanica);
		}
		
		jparque.put("aMecanicas", jatraccionesMecanicas);
		
	}
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
	
	
	public Parque cargarParque() throws Exception {
		
		String contenido = new String(Files.readAllBytes(Paths.get("../data/parque.json")));
		JSONObject jsonParque = new JSONObject(contenido);
		Parque parque = new Parque("Entrada");
		parque.taquilla = cargarTaquilla(jsonParque.getJSONObject("taquilla"));
		parque.aCulturales = cargarACulturales(jsonParque.getJSONObject("aCulturales"));
		parque.aMecanicas= cargarAMecanicas(jsonParque.getJSONObject("aMecanicas"));
		parque.mapaCafeterias= cargarMapaCafeterias(jsonParque.getJSONObject("mapaCafeterias"));
		parque.mapaTiendas= cargarMapaTiendas(jsonParque.getJSONObject("mapaTiendas"));
		parque.espectaculos= cargarEspectaculos(jsonParque.getJSONObject("espectaculos"));
		parque.tiquetes= cargarTiquetes(jsonParque.getJSONObject("tiquetes"));
		cargarTiquetes(parque,parque.tiquetes);
		cargarInventario(parque,parque.inventario);
		cargarEmpleados(parque,parque.empleados);
		cargarUsuarios(parque,parque.usuarios);
		
		
		
		return parque;
		
	}

	private HashMap<Double, Tiquete> cargarTiquetes(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	private HashMap<String, Espectaculo> cargarEspectaculos(JSONObject jespectaculos) throws Exception {
		
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

	private Espectaculo cargarEspectaculo(JSONObject jespectaculo) throws Exception {
		
		
		
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

	private HashMap<String, Tienda> cargarMapaTiendas(JSONObject jaCafeterias) throws Exception {
		
		HashMap<String, Cafeteria> mapaCafeterias = new HashMap<String, Cafeteria>(); 
		JSONArray jmenu = jaCafeterias.getJSONArray("items");
		HashSet<String> menu = new HashSet<String>();
		
		for(int i = 0;i<jmenu.length();i++) {
			
			String comia= jmenu.getString(i);
			menu.add(comia);
		}
		Tienda.items = menu;
		
		Iterator<String> keys = jaCafeterias.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jcafeteria= (JSONObject) jaCafeterias.get(key);
			Tienda cafeteria = cargarTienda(jcafeteria);
			mapaCafeterias.put(cafeteria.getNombre(), cafeteria);
			}
		
		return mapaCafeterias;
	}

	private Tienda cargarTienda(JSONObject jaCafeterias) {
		
		HashMap<String, Cafeteria> mapaCafeterias = new HashMap<String, Cafeteria>(); 
		JSONArray jmenu = jaCafeterias.getJSONArray("menu");
		HashSet<String> menu = new HashSet<String>();
		
		for(int i = 0;i<jmenu.length();i++) {
			
			String comia= jmenu.getString(i);
			menu.add(comia);
		}
		Cafeteria.menu = menu;
		
		
		
		Iterator<String> keys = jaCafeterias.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jcafeteria= (JSONObject) jaCafeterias.get(key);
			Cafeteria cafeteria = cargarCafeteria(jcafeteria);
			mapaCafeterias.put(cafeteria.getNombre(), cafeteria);
			}
		
		return mapaCafeterias;
	}

	private HashMap<String, Cafeteria> cargarMapaCafeterias(JSONObject jaCafeterias) throws Exception {
		
		HashMap<String, Cafeteria> mapaCafeterias = new HashMap<String, Cafeteria>(); 
		JSONArray jmenu = jaCafeterias.getJSONArray("menu");
		HashSet<String> menu = new HashSet<String>();
		
		for(int i = 0;i<jmenu.length();i++) {
			
			String comia= jmenu.getString(i);
			menu.add(comia);
		}
		Cafeteria.menu = menu;
		
		
		
		Iterator<String> keys = jaCafeterias.keys();
		
		while (keys.hasNext()) {
			String key = keys.next();
			JSONObject jcafeteria= (JSONObject) jaCafeterias.get(key);
			Cafeteria cafeteria = cargarCafeteria(jcafeteria);
			mapaCafeterias.put(cafeteria.getNombre(), cafeteria);
			}
		
		return mapaCafeterias;
	}
	
	
	

	private Cafeteria cargarCafeteria(JSONObject jcafeteria) throws Exception {
		
		String nombre = jcafeteria.getString("nombre");
		String ubicacion = jcafeteria.getString("ubicacion");
		String tipo =	jcafeteria.getString("tipo");
		
		return new Cafeteria(nombre,ubicacion,tipo);
	}

	private HashMap<String, AtraccionMecanica> cargarAMecanicas(JSONObject jaMecanicas) throws Exception {
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

	private AtraccionMecanica cargarAtraccionMecanica(JSONObject jatraccionMecanica) throws Exception {
		
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
		

	private HashMap<String, AtraccionCultural> cargarACulturales(JSONObject jaCulturales) throws Exception {
		
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

	private AtraccionCultural cargarAtraccionCultural(JSONObject jatraccionCultural) throws Exception {
		
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

	private Taquilla cargarTaquilla(JSONObject jtaquilla) throws Exception {

		
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

	private PedidoTiquete cargarPedido(JSONObject jpedido) throws Exception {
		
		PedidoTiquete pedido = new PedidoTiquete(jpedido.getString("loginUsuario"),jpedido.getString("tipo") ,jpedido.getString("atraccionParaIndividuales" ) 
				, jpedido.getString("fechaInicioParaTemporales" ), jpedido.getString("fechaFinParaTemporales"), jpedido.getInt("exclusividad"), jpedido.getBoolean("fastPass") );
		return pedido;
	}
	
	
	
	
}
