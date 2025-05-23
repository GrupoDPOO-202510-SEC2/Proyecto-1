package interfaz;

import java.awt.GridLayout;
import java.util.List;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import atraccion.Atraccion;
import atraccion.Espectaculo;
import usuario.Administrador;

public class VentanaAdministrador {
    private JPanel panelCentralEspectaculos;
    private JPanel panelCentralAtracciones;
    private JPanel panelCentralEmpleados;

    public VentanaAdministrador() {
        JTabbedPane tabbedpane = new JTabbedPane();

        // ——— Pestaña Espectáculos ———
        String[] metodos1 = {
            "1. Crear espectáculo",
            "2. Cambiar fecha de espectáculo",
            "3. Ver espectáculo",
            "4. Agregar o Eliminar climas restringidos"
        };
        JList<String> lista1 = new JList<>(metodos1);
        JScrollPane scroll1 = new JScrollPane(lista1);
        scroll1.setPreferredSize(new Dimension(300, 600));

        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        panelCentralEspectaculos = new JPanel();
        panelCentralEspectaculos.add(new JLabel("Seleccione una opción"));
        panel1.add(scroll1);
        panel1.add(panelCentralEspectaculos);

        lista1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                panelCentralEspectaculos.removeAll();
                switch (lista1.getSelectedIndex()) {
                    case 0:
                    	JPanel panelCrearEspectaculo = new JPanel(new GridLayout(0, 2, 5, 5)); 
                    	panelCrearEspectaculo.add(new JLabel("Nombre:"));
                    	JTextField tfNombre = new JTextField();
                    	panelCrearEspectaculo.add(tfNombre);

                    	panelCrearEspectaculo.add(new JLabel("Horario:"));
                    	JTextField tfHorario = new JTextField();
                    	panelCrearEspectaculo.add(tfHorario);

                    	panelCrearEspectaculo.add(new JLabel("Ubicación:"));
                    	JTextField tfUbicacion = new JTextField();
                    	panelCrearEspectaculo.add(tfUbicacion);
                    	
                    	JButton btnCrear = new JButton("Crear espectáculo");
                        panelCrearEspectaculo.add(btnCrear, BorderLayout.SOUTH);
                        
                        btnCrear.addActionListener(evt -> {
                            String nombre   = tfNombre.getText();
                            String horario  = tfHorario.getText();
                            String ubicacion= tfUbicacion.getText();
							Administrador.crearEspectaculo(nombre, horario, ubicacion);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se creo correctamente el espectaculo", JOptionPane.INFORMATION_MESSAGE);
							});
                    	
                    	panelCentralEspectaculos.removeAll();
                    	panelCentralEspectaculos.add(panelCrearEspectaculo);
                    	panelCentralEspectaculos.revalidate();
                    	panelCentralEspectaculos.repaint();
                        break;
                    case 1:
                        JPanel panelCambiarFecha = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelCambiarFecha.add(new JLabel("Nombre: "));
                        JTextField tfNombre1 = new JTextField();
                        panelCambiarFecha.add(tfNombre1);
                    	
                    	panelCambiarFecha.add(new JLabel("Fecha Inicial: "));
                        JTextField tfFechai = new JTextField();
                        panelCambiarFecha.add(tfFechai);
                    	
                    	panelCambiarFecha.add(new JLabel("Fecha Final: "));
                        JTextField tfFechaf = new JTextField();
                        panelCambiarFecha.add(tfFechaf);
                    	
                    	JButton btnCrear1 = new JButton("Crear espectáculo");
                    	panelCambiarFecha.add(btnCrear1, BorderLayout.SOUTH);
                    	
                    	btnCrear1.addActionListener(evt -> {
                            String nombre1   = tfNombre1.getText();
                            String fechai  = tfFechai.getText();
                            String fechaf= tfFechaf.getText();
							Administrador.cambiarFechaEspectaculo(nombre1, fechai, fechaf);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se cambió Correctamente la fecha", JOptionPane.INFORMATION_MESSAGE);
							});
                    	
                        panelCentralEspectaculos.removeAll();
                        panelCentralEspectaculos.add(panelCambiarFecha);
                    	panelCentralEspectaculos.revalidate();
                    	panelCentralEspectaculos.repaint();
                        break;
                    case 2:
                        JPanel panelVerEsp = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelVerEsp.add(new JLabel("Nombre: "));
                        JTextField tfNombre2 = new JTextField();
                        panelVerEsp.add(tfNombre2);
                        JButton btnCrear2 = new JButton("Ver");
                    	panelVerEsp.add(btnCrear2, BorderLayout.SOUTH);
                    	
                    	btnCrear2.addActionListener(evt -> {
                            panelVerEsp.removeAll();
                    		String nombre2 = tfNombre2.getText();
                            panelVerEsp.add(new JLabel("Nombre: "));
                            panelVerEsp.add(tfNombre2);
                        	panelVerEsp.add(btnCrear2, BorderLayout.SOUTH);
                            Espectaculo esp = Administrador.getEspectaculo(nombre2);
							panelVerEsp.add(new JLabel("Nombre: "+ esp.getNombre()));
	                        panelVerEsp.add(new JLabel("Ubicación: "+ esp.getUbicacion()));
	                        panelVerEsp.add(new JLabel("Horario: "+ esp.getHorario()));
	                        panelVerEsp.add(new JLabel("Fecha Inicio: "+ esp.getFechaInicio()));
	                        panelVerEsp.add(new JLabel("Fecha Fin: "+ esp.getFechaFin()));
	                    	panelCentralEspectaculos.revalidate();
	                    	panelCentralEspectaculos.repaint();
	                        
							});
                    	panelCentralEspectaculos.removeAll();
                        panelCentralEspectaculos.add(panelVerEsp);
                    	panelCentralEspectaculos.revalidate();
                    	panelCentralEspectaculos.repaint();
                        break;
                    case 3:
                        JPanel panelClimas = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelClimas.add(new JLabel("Nombre: "));
                        JTextField tfNombre3 = new JTextField();
                        panelClimas.add(tfNombre3);
                    	
                    	panelClimas.add(new JLabel("Clima: "));
                        JTextField tfClima = new JTextField();
                        panelClimas.add(tfClima);
                        JButton btnagregar = new JButton("Agregar");
                    	panelClimas.add(btnagregar, BorderLayout.SOUTH);
                    	JButton btneliminar = new JButton("Eliminar");
                    	panelClimas.add(btneliminar, BorderLayout.SOUTH);
                    	
                    	btnagregar.addActionListener(evt -> {
                            String nombre3   = tfNombre3.getText();
                            String clima  = tfClima.getText();
							Administrador.agregarClimaRestringidoE(nombre3, clima);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se agregó correctamente el clima", JOptionPane.INFORMATION_MESSAGE);
							});
                    	btneliminar.addActionListener(evt -> {
                            String nombre3   = tfNombre3.getText();
                            String clima  = tfClima.getText();
							Administrador.eliminarClimaRestringidoE(nombre3, clima);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se eliminó correctamente el clima", JOptionPane.INFORMATION_MESSAGE);
							});
                    	
                    	panelCentralEspectaculos.removeAll();
                        panelCentralEspectaculos.add(panelClimas);
                    	panelCentralEspectaculos.revalidate();
                    	panelCentralEspectaculos.repaint();
                        break;
                    default:
                        panelCentralEspectaculos.add(new JLabel("Opción no implementada"));
                }
                panelCentralEspectaculos.revalidate();
                panelCentralEspectaculos.repaint();
            }
        });

        tabbedpane.addTab("Espectáculos", panel1);

        // ——— Pestaña Atracciones ———
        String[] metodos2 = {
        		"1. Crear atracción mecánica",
        	    "2. Crear atracción cultural",
        	    "3. Ver atracción",
        	    "4. Modificar capacidad máxima",
        	    "5. Modificar empleados mínimos",
        	    "6. Modificar estado de servicio",
        	    "7. Modificar exclusividad",
        	    "8. Ver operadores de atracción",
        	    "9. Gestionar operadores",
        	    "10. Gestionar restricciones de salud",
        	    "11. Gestionar climas restringidos de atracción",
        	    "12. Ver alturas y pesos",
        	    "13. Modificar altura máxima",
        	    "14. Modificar altura mínima",
        	    "15. Modificar peso máximo",
        	    "16. Modificar peso mínimo",
        	    "17. Modificar riesgo alto",
        	    "18. Ver edad mínima",
        	    "19. Ver si es interactiva",
        	    "20. Modificar edad mínima",
        	    "21. Modificar interactividad"
        };
        JList<String> lista2 = new JList<>(metodos2);
        JScrollPane scroll2 = new JScrollPane(lista2);
        scroll2.setPreferredSize(new Dimension(300, 600));

        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panelCentralAtracciones = new JPanel();
        panelCentralAtracciones.add(new JLabel("Seleccione una opción"));
        panel2.add(scroll2);
        panel2.add(panelCentralAtracciones);

        lista2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                panelCentralAtracciones.removeAll();
                switch (lista2.getSelectedIndex()) {
                case 0:
                    JPanel panelCrearAtraccionM = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearAtraccionM.add(new JLabel("Nombre:"));
                    JTextField tfNombreM = new JTextField();
                    panelCrearAtraccionM.add(tfNombreM);

                    panelCrearAtraccionM.add(new JLabel("Capacidad:"));
                    JTextField tfCapacidadM = new JTextField();
                    panelCrearAtraccionM.add(tfCapacidadM);

                    panelCrearAtraccionM.add(new JLabel("Empleados mínimos:"));
                    JTextField tfEminM = new JTextField();
                    panelCrearAtraccionM.add(tfEminM);

                    panelCrearAtraccionM.add(new JLabel("Ubicación:"));
                    JTextField tfUbicM = new JTextField();
                    panelCrearAtraccionM.add(tfUbicM);

                    panelCrearAtraccionM.add(new JLabel("Exclusividad:"));
                    JTextField tfExclM = new JTextField();
                    panelCrearAtraccionM.add(tfExclM);

                    panelCrearAtraccionM.add(new JLabel("Altura máx (cm):"));
                    JTextField tfAltMax = new JTextField();
                    panelCrearAtraccionM.add(tfAltMax);

                    panelCrearAtraccionM.add(new JLabel("Altura mín (cm):"));
                    JTextField tfAltMin = new JTextField();
                    panelCrearAtraccionM.add(tfAltMin);

                    panelCrearAtraccionM.add(new JLabel("Peso mín (kg):"));
                    JTextField tfPesMin = new JTextField();
                    panelCrearAtraccionM.add(tfPesMin);

                    panelCrearAtraccionM.add(new JLabel("Peso máx (kg):"));
                    JTextField tfPesMax = new JTextField();
                    panelCrearAtraccionM.add(tfPesMax);

                    panelCrearAtraccionM.add(new JLabel("¿Riesgo alto?:"));
                    JCheckBox cbRiesgo = new JCheckBox();
                    panelCrearAtraccionM.add(cbRiesgo);

                    JButton btnCrearM = new JButton("Crear Atracción Mecánica");
                    panelCrearAtraccionM.add(btnCrearM, BorderLayout.SOUTH);

                    btnCrearM.addActionListener(evt -> {
                        String nombre       = tfNombreM.getText();
                        int capacidad       = Integer.parseInt(tfCapacidadM.getText());
                        int empleadosMin    = Integer.parseInt(tfEminM.getText());
                        String ubicacion    = tfUbicM.getText();
                        int exclusividad    = Integer.parseInt(tfExclM.getText());
                        int alturaMax       = Integer.parseInt(tfAltMax.getText());
                        int alturaMin       = Integer.parseInt(tfAltMin.getText());
                        int pesoMin         = Integer.parseInt(tfPesMin.getText());
                        int pesoMax         = Integer.parseInt(tfPesMax.getText());
                        boolean riesgoAlto  = cbRiesgo.isSelected();

                        Administrador.crearAtraccionMecanica(nombre, capacidad, empleadosMin, ubicacion, exclusividad, alturaMax, alturaMin, pesoMin, pesoMax, riesgoAlto);
                        JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se creo correctamente la Atracción Mecanica", JOptionPane.INFORMATION_MESSAGE);
                    });

                    panelCentralAtracciones.removeAll();
                    panelCentralAtracciones.add(panelCrearAtraccionM);
                    panelCentralAtracciones.revalidate();
                    panelCentralAtracciones.repaint();
                    break;
        	
                    case 1:
                    	JPanel panelCrearAtraccionC = new JPanel(new GridLayout(0, 2, 5, 5)); 
                    	panelCrearAtraccionC.add(new JLabel("Nombre: "));
                    	JTextField tfNombreAC = new JTextField();
                    	panelCrearAtraccionC.add(tfNombreAC);

                    	panelCrearAtraccionC.add(new JLabel("Capacidad: "));
                    	JTextField tfCapacidad = new JTextField();
                    	panelCrearAtraccionC.add(tfCapacidad);

                    	panelCrearAtraccionC.add(new JLabel("Empleados Mínimos: "));
                    	JTextField tfEminimos = new JTextField();
                    	panelCrearAtraccionC.add(tfEminimos);
                    	
                    	panelCrearAtraccionC.add(new JLabel("Ubicación: "));
                    	JTextField tfUbicacionAC = new JTextField();
                    	panelCrearAtraccionC.add(tfUbicacionAC);
                    	
                    	panelCrearAtraccionC.add(new JLabel("Exclusividad: "));
                    	JTextField tfExclusividad = new JTextField();
                    	panelCrearAtraccionC.add(tfExclusividad);
                    	
                    	panelCrearAtraccionC.add(new JLabel("Edad mínima: "));
                    	JTextField tfEdMin = new JTextField();
                    	panelCrearAtraccionC.add(tfEdMin);
                    	
                    	panelCrearAtraccionC.add(new JLabel("¿Es Interactiva?: "));
                    	JCheckBox cbEI = new JCheckBox();
                    	panelCrearAtraccionC.add(cbEI);
                    	
                    	JButton btnCrearAC = new JButton("Crear Atracción Cultural");
                    	panelCrearAtraccionC.add(btnCrearAC, BorderLayout.SOUTH);
                        
                        btnCrearAC.addActionListener(evt -> {
                            String nombre   = tfNombreAC.getText();
                            String capacidads = tfCapacidad.getText();
                            int capacidad = Integer.parseInt(capacidads);
                            String emins = tfEminimos.getText();
                            int emin  = Integer.parseInt(emins);
                            String ubicacion = tfUbicacionAC.getText();
                            String exclusividads = tfExclusividad.getText();
                            int exclusividad = Integer.parseInt(exclusividads);
                            String edmins = tfEdMin.getText();
                            int edmin = Integer.parseInt(edmins);
                            boolean ei = cbEI.isSelected();
							Administrador.crearAtraccionCultural(nombre, capacidad, emin, ubicacion, exclusividad, edmin, ei);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se creo correctamente la Atracción Cultural", JOptionPane.INFORMATION_MESSAGE);
							});
                    	
                    	panelCentralAtracciones.removeAll();
                    	panelCentralAtracciones.add(panelCrearAtraccionC);
                    	panelCentralAtracciones.revalidate();
                    	panelCentralAtracciones.repaint();
                        break;
                    case 2:
                    	JPanel panelVerAtr = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelVerAtr.add(new JLabel("Nombre: "));
                        JTextField tfNombre2 = new JTextField();
                        panelVerAtr.add(tfNombre2);
                        JButton btnCrear2 = new JButton("Ver");
                    	panelVerAtr.add(btnCrear2, BorderLayout.SOUTH);
                    	
                    	btnCrear2.addActionListener(evt -> {

                    		panelVerAtr.removeAll();
                    		
                    		panelVerAtr.add(new JLabel("Nombre: "));
                            panelVerAtr.add(tfNombre2);
                        	panelVerAtr.add(btnCrear2, BorderLayout.SOUTH);
                    		
                    		String nombreA = tfNombre2.getText();
                            Atraccion atr = Administrador.getAtraccion(nombreA);
							panelVerAtr.add(new JLabel("Nombre: "+ atr.getNombre()));
	                        panelVerAtr.add(new JLabel("Ubicación: "+ atr.getUbicacion()));
	                        panelVerAtr.add(new JLabel("Empleados Minimos: "+ atr.getEmpleadosMinimos()));
	                        panelVerAtr.add(new JLabel("Nivel Exclusividad: "+ atr.getNivelExclusividad()));
	                        panelVerAtr.add(new JLabel("Capcidad Máxima: "+ atr.getCapacidadMaxima()));

	                    	panelCentralAtracciones.revalidate();
	                    	panelCentralAtracciones.repaint();
							});
                    	panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelVerAtr);
                    	panelCentralAtracciones.revalidate();
                    	panelCentralAtracciones.repaint();
                        break;
                    case 3:
                        JPanel panelModCap = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModCap.add(new JLabel("Nombre: "));
                        JTextField tfNombreCap = new JTextField();
                        panelModCap.add(tfNombreCap);
                    	
                    	panelModCap.add(new JLabel("Capacidad: "));
                        JTextField tfCapacidad1 = new JTextField();
                        panelModCap.add(tfCapacidad1);
                        
                        JButton btnModificar = new JButton("Modificar");
                    	panelModCap.add(btnModificar, BorderLayout.SOUTH);
                    	
                        btnModificar.addActionListener(evt -> {
                    		String nombreCap = tfNombreCap.getText();
                            String capacidadc = tfCapacidad1.getText();
                            int capacidad = Integer.parseInt(capacidadc);
                            Administrador.setCapacidadMaxima(nombreCap, capacidad);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se modificó correctamente la capacidad máxima", JOptionPane.INFORMATION_MESSAGE);

							});
                    	panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModCap);
                    	panelCentralAtracciones.revalidate();
                    	panelCentralAtracciones.repaint();
                        break;
                    case 4:
                    	JPanel panelModEmp = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModEmp.add(new JLabel("Nombre: "));
                        JTextField tfNombreEmp = new JTextField();
                        panelModEmp.add(tfNombreEmp);
                    	
                    	panelModEmp.add(new JLabel("Numero de Empleados: "));
                        JTextField tfnEmp = new JTextField();
                        panelModEmp.add(tfnEmp);
                        
                        JButton btnModificar1 = new JButton("Modificar");
                    	panelModEmp.add(btnModificar1, BorderLayout.SOUTH);
                    	
                        btnModificar1.addActionListener(evt -> {
                    		String nombreEmp = tfNombreEmp.getText();
                            String nEmps = tfnEmp.getText();
                            int nEmp = Integer.parseInt(nEmps);
                            Administrador.setEmpleadosMinimos(nombreEmp, nEmp);
                            JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se modificó correctamente los empleados mínimos", JOptionPane.INFORMATION_MESSAGE);
							});
                    	panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModEmp);
                    	panelCentralAtracciones.revalidate();
                    	panelCentralAtracciones.repaint();
                        break;
                    case 5:
                        JPanel panelModEstado = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModEstado.add(new JLabel("Nombre: "));
                        JTextField tfNombreEst = new JTextField();
                        panelModEstado.add(tfNombreEst);
                        
                        panelModEstado.add(new JLabel("¿Está en servicio?"));
                        JCheckBox cbestado = new JCheckBox();
                        panelModEstado.add(cbestado);
                        
                        JButton btnModificar2 = new JButton("Modificar");
                    	panelModEstado.add(btnModificar2, BorderLayout.SOUTH);
                        
                        btnModificar2.addActionListener(evt -> {
                    		String nombreEmp = tfNombreEst.getText();
                            boolean ees = cbestado.isSelected();
                            Administrador.setEnServicio(nombreEmp, ees);
                            JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se modificó correctamente los empleados mínimos", JOptionPane.INFORMATION_MESSAGE);
							});
                    	panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModEstado);
                    	panelCentralAtracciones.revalidate();
                    	panelCentralAtracciones.repaint();
                        break;
                    case 6:
                    	JPanel panelModExcl = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModExcl.add(new JLabel("Nombre:"));
                        JTextField tfNombreEx = new JTextField();
                        panelModExcl.add(tfNombreEx);

                        panelModExcl.add(new JLabel("Exclusividad:"));
                        JTextField tfExcl = new JTextField();
                        panelModExcl.add(tfExcl);

                        JButton btnModExcl = new JButton("Modificar exclusividad");
                        panelModExcl.add(btnModExcl, BorderLayout.SOUTH);

                        btnModExcl.addActionListener(evt -> {
                            String nombre = tfNombreEx.getText();
                            int exclusividad = Integer.parseInt(tfExcl.getText());
                            Administrador.setExclusividad(nombre, exclusividad);
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Se modificó la exclusividad correctamente",JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModExcl);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 7:
                    	JPanel panelVerOperadores = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelVerOperadores.add(new JLabel("Nombre de la atracción:"));
                        JTextField tfNombreOp = new JTextField();
                        panelVerOperadores.add(tfNombreOp);

                        JButton btnVerOp = new JButton("Ver operadores");
                        panelVerOperadores.add(btnVerOp, BorderLayout.SOUTH);

                        btnVerOp.addActionListener(evt -> {
                        	
                        	panelVerOperadores.removeAll();
                        	
                        	panelVerOperadores.add(new JLabel("Nombre de la atracción:"));
                            panelVerOperadores.add(tfNombreOp);
                            panelVerOperadores.add(btnVerOp, BorderLayout.SOUTH);
                        	
                            String nombre = tfNombreOp.getText();
                            int[] op = Administrador.getOperadoresAtraccion(nombre);
                            panelVerOperadores.add(new JLabel("Día: " + op[0]));
                            panelVerOperadores.add(new JLabel("Noche: " + op[1]));

                            panelCentralAtracciones.revalidate();
                            panelCentralAtracciones.repaint();
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelVerOperadores);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 8:
                    	JPanel panelGestOp = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelGestOp.add(new JLabel("Login operador:"));
                        JTextField tfLoginOp = new JTextField();
                        panelGestOp.add(tfLoginOp);

                        panelGestOp.add(new JLabel("Turno (diurno/nocturno):"));
                        JTextField tfTurnoOp = new JTextField();
                        panelGestOp.add(tfTurnoOp);

                        panelGestOp.add(new JLabel("Atracción:"));
                        JTextField tfAtraccion = new JTextField();
                        panelGestOp.add(tfAtraccion);

                        JButton btnAgregarOp = new JButton("Agregar");
                        panelGestOp.add(btnAgregarOp, BorderLayout.SOUTH);
                        JButton btnEliminarOp = new JButton("Eliminar");
                        panelGestOp.add(btnEliminarOp, BorderLayout.SOUTH);

                        btnAgregarOp.addActionListener(evt -> {
                            Administrador.addOperadorAAtraccion(
                                tfLoginOp.getText(),
                                tfTurnoOp.getText(),
                                tfAtraccion.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Se agregó el operador",JOptionPane.INFORMATION_MESSAGE);
                        });

                        btnEliminarOp.addActionListener(evt -> {
                            Administrador.deleteOperadorAAtraccion(
                                tfLoginOp.getText(),
                                tfTurnoOp.getText(),
                                tfAtraccion.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa","Éxito",JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelGestOp);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 9:
                    	JPanel panelResSalud = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelResSalud.add(new JLabel("Atracción:"));
                        JTextField tfAtr = new JTextField();
                        panelResSalud.add(tfAtr);

                        panelResSalud.add(new JLabel("Restricción salud:"));
                        JTextField tfRes = new JTextField();
                        panelResSalud.add(tfRes);

                        JButton btnAgregarRes = new JButton("Agregar");
                        panelResSalud.add(btnAgregarRes, BorderLayout.SOUTH);
                        JButton btnEliminarRes = new JButton("Eliminar");
                        panelResSalud.add(btnEliminarRes, BorderLayout.SOUTH);

                        btnAgregarRes.addActionListener(evt -> {
                            Administrador.addRestriccionSalud(tfAtr.getText(), tfRes.getText());
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        btnEliminarRes.addActionListener(evt -> {
                            Administrador.removeRestriccionSalud(tfAtr.getText(), tfRes.getText());
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelResSalud);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 10:
                    	JPanel panelClimasAtr = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelClimasAtr.add(new JLabel("Atracción:"));
                        JTextField tfAtr1 = new JTextField();
                        panelClimasAtr.add(tfAtr1);

                        panelClimasAtr.add(new JLabel("Clima:"));
                        JTextField tfClima = new JTextField();
                        panelClimasAtr.add(tfClima);

                        JButton btnAgregarClima = new JButton("Agregar");
                        panelClimasAtr.add(btnAgregarClima, BorderLayout.SOUTH);
                        JButton btnEliminarClima = new JButton("Eliminar");
                        panelClimasAtr.add(btnEliminarClima, BorderLayout.SOUTH);

                        btnAgregarClima.addActionListener(evt -> {
                            Administrador.addClimasRestringidos(tfAtr1.getText(), tfClima.getText());
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });
                        btnEliminarClima.addActionListener(evt -> {
                            Administrador.removeClimasRestringidos(tfAtr1.getText(), tfClima.getText());
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelClimasAtr);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 11:
                    	JPanel panelAltPes = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelAltPes.add(new JLabel("Nombre de la atracción:"));
                        JTextField tfNombreAP = new JTextField();
                        panelAltPes.add(tfNombreAP);
                        JButton btnVerAltPes = new JButton("Ver");
                        panelAltPes.add(btnVerAltPes, BorderLayout.SOUTH);

                        btnVerAltPes.addActionListener(evt -> {
                            int[] datos = Administrador.getAlturasPesos(tfNombreAP.getText());
                            panelAltPes.add(new JLabel("AltMax: " + datos[0]));
                            panelAltPes.add(new JLabel("AltMin: " + datos[1]));
                            panelAltPes.add(new JLabel("PesMax: " + datos[2]));
                            panelAltPes.add(new JLabel("PesMin: " + datos[3]));
                            panelAltPes.revalidate();
                            panelAltPes.repaint();
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelAltPes);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 12:
                    	JPanel panelModAltMax = new JPanel(new GridLayout(0, 2, 5, 5));

                        panelModAltMax.add(new JLabel("Atracción:"));
                        JTextField tfAtrModAlt = new JTextField();
                        panelModAltMax.add(tfAtrModAlt);
                        
                        panelModAltMax.add(new JLabel("Altura máxima (cm):"));
                        JTextField tfAltMax1 = new JTextField();
                        panelModAltMax.add(tfAltMax1);

                        JButton btnModAltMax = new JButton("Modificar");
                        panelModAltMax.add(btnModAltMax, BorderLayout.SOUTH);

                        btnModAltMax.addActionListener(evt -> {
                            int nuevaAltura = Integer.parseInt(tfAltMax1.getText());
                            String nombreAtr = tfAtrModAlt.getText();
                            Administrador.setAlturaMaxima(nuevaAltura, nombreAtr);
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModAltMax);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 13:
                        JPanel panelModAltMin = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModAltMin.add(new JLabel("Atracción:"));
                        JTextField tfAtrModAltMin = new JTextField();
                        panelModAltMin.add(tfAtrModAltMin);
                        
                        panelModAltMin.add(new JLabel("Altura mín (cm):"));
                        JTextField tfAltMin1 = new JTextField();
                        panelModAltMin.add(tfAltMin1);
                        
                        JButton btnModAltMin = new JButton("Modificar altura mínima");
                        panelModAltMin.add(btnModAltMin, BorderLayout.SOUTH);

                        btnModAltMin.addActionListener(evt -> {
                            Administrador.setAlturaMinima(
                                Integer.parseInt(tfAltMin1.getText()),
                                tfAtrModAltMin.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModAltMin);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 14:
                        JPanel panelModPesMax = new JPanel(new GridLayout(0, 2, 5, 5));

                        panelModPesMax.add(new JLabel("Atracción:"));
                        JTextField tfAtrModPesMax = new JTextField();
                        panelModPesMax.add(tfAtrModPesMax);

                        panelModPesMax.add(new JLabel("Peso máx (kg):"));
                        JTextField tfPesMax1 = new JTextField();
                        panelModPesMax.add(tfPesMax1);

                        JButton btnModPesMax = new JButton("Modificar peso máximo");
                        panelModPesMax.add(btnModPesMax, BorderLayout.SOUTH);

                        btnModPesMax.addActionListener(evt -> {
                            Administrador.setPesoMaximo(
                                Integer.parseInt(tfPesMax1.getText()),
                                tfAtrModPesMax.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModPesMax);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 15:
                        JPanel panelModPesMin = new JPanel(new GridLayout(0, 2, 5, 5));

                        panelModPesMin.add(new JLabel("Atracción:"));
                        JTextField tfAtrModPesMin = new JTextField();
                        panelModPesMin.add(tfAtrModPesMin);

                        panelModPesMin.add(new JLabel("Peso mín (kg):"));
                        JTextField tfPesMin1 = new JTextField();
                        panelModPesMin.add(tfPesMin1);

                        JButton btnModPesMin = new JButton("Modificar peso mínimo");
                        panelModPesMin.add(btnModPesMin, BorderLayout.SOUTH);

                        btnModPesMin.addActionListener(evt -> {
                            Administrador.setPesoMinimo(
                                Integer.parseInt(tfPesMin1.getText()),
                                tfAtrModPesMin.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModPesMin);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;
                    case 16:
                        JPanel panelModRiesgo = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModRiesgo.add(new JLabel("Atraccion:"));
                        JTextField tfAtrRiesgo = new JTextField();
                        panelModRiesgo.add(tfAtrRiesgo);

                        panelModRiesgo.add(new JLabel("¿Riesgo alto?:"));
                        JCheckBox chkRiesgo = new JCheckBox();
                        panelModRiesgo.add(chkRiesgo);

                        JButton btnModRiesgo = new JButton("Modificar riesgo alto");
                        panelModRiesgo.add(btnModRiesgo, BorderLayout.SOUTH);

                        btnModRiesgo.addActionListener(evt -> {
                            Administrador.setRiesgoAlto(chkRiesgo.isSelected(), tfAtrRiesgo.getText());
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModRiesgo);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 17:
                        JPanel panelVerEdad = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelVerEdad.add(new JLabel("Atraccion:"));
                        JTextField tfAtrVerEdad = new JTextField();
                        panelVerEdad.add(tfAtrVerEdad);

                        JButton btnVerEdad = new JButton("Ver edad mínima");
                        panelVerEdad.add(btnVerEdad, BorderLayout.SOUTH);

                        btnVerEdad.addActionListener(evt -> {
                        	
                        	panelVerEdad.removeAll();

                            panelVerEdad.add(new JLabel("Atraccion:"));
                            panelVerEdad.add(tfAtrVerEdad);
                            panelVerEdad.add(btnVerEdad, BorderLayout.SOUTH);
                        	
                            int edadMin = Administrador.getEdadMinima(tfAtrVerEdad.getText());
                            panelVerEdad.add(new JLabel("Edad mínima: " + edadMin));
                            panelVerEdad.revalidate();
                            panelVerEdad.repaint();

                            panelCentralAtracciones.revalidate();
                            panelCentralAtracciones.repaint();
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelVerEdad);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 18:
                        JPanel panelVerInteract = new JPanel(new GridLayout(0, 1, 5, 5));
                        panelVerInteract.add(new JLabel("Atraccion:"));
                        JTextField tfAtrVerInt = new JTextField();
                        panelVerInteract.add(tfAtrVerInt);

                        JButton btnVerInt = new JButton("Ver interactividad");
                        panelVerInteract.add(btnVerInt, BorderLayout.SOUTH);

                        btnVerInt.addActionListener(evt -> {
                        	
                        	panelVerInteract.removeAll();
                        	

                            panelVerInteract.add(new JLabel("Atraccion:"));
                            panelVerInteract.add(tfAtrVerInt);
                            panelVerInteract.add(btnVerInt, BorderLayout.SOUTH);
                        	
                            boolean interact = Administrador.isEsInteractiva(tfAtrVerInt.getText());
                            panelVerInteract.add(new JLabel("Es interactiva: " + interact));
                            panelVerInteract.revalidate();
                            panelVerInteract.repaint();
                            

                            panelCentralAtracciones.revalidate();
                            panelCentralAtracciones.repaint();
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelVerInteract);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 19:
                        JPanel panelModEdadMin = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModEdadMin.add(new JLabel("Atraccion:"));
                        JTextField tfAtrModEdad = new JTextField();
                        panelModEdadMin.add(tfAtrModEdad);

                        panelModEdadMin.add(new JLabel("Edad mínima:"));
                        JTextField tfNuevaEdad = new JTextField();
                        panelModEdadMin.add(tfNuevaEdad);

                        JButton btnModEdadMin = new JButton("Modificar edad mínima");
                        panelModEdadMin.add(btnModEdadMin, BorderLayout.SOUTH);

                        btnModEdadMin.addActionListener(evt -> {
                            Administrador.setEdadMinima(
                                Integer.parseInt(tfNuevaEdad.getText()),
                                tfAtrModEdad.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModEdadMin);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    case 20:
                        JPanel panelModInteract = new JPanel(new GridLayout(0, 2, 5, 5));
                        panelModInteract.add(new JLabel("Atraccion:"));
                        JTextField tfAtrModInt = new JTextField();
                        panelModInteract.add(tfAtrModInt);

                        panelModInteract.add(new JLabel("¿Es interactiva?:"));
                        JCheckBox chkInteract = new JCheckBox();
                        panelModInteract.add(chkInteract);

                        JButton btnModInteract = new JButton("Modificar interactividad");
                        panelModInteract.add(btnModInteract, BorderLayout.SOUTH);

                        btnModInteract.addActionListener(evt -> {
                            Administrador.setEsInteractiva(
                                chkInteract.isSelected(),
                                tfAtrModInt.getText()
                            );
                            JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        });

                        panelCentralAtracciones.removeAll();
                        panelCentralAtracciones.add(panelModInteract);
                        panelCentralAtracciones.revalidate();
                        panelCentralAtracciones.repaint();
                        break;

                    default:
                        panelCentralAtracciones.add(new JLabel("Opción no implementada"));
                }
                panelCentralAtracciones.revalidate();
                panelCentralAtracciones.repaint();
            }
        });

        tabbedpane.addTab("Atracciones", panel2);

        // ——— Pestaña Empleados ———
        String[] metodos3 = {
            "1. Ver lugar de trabajo",
            "2. Modificar lugar de trabajo",
            "3. Ver rol",
            "4. Ver turnos",
            "5. Modificar turno día",
            "6. Modificar turno noche",
            "7. Crear cafetería",
            "8. Crear tienda",
            "9. Ver menú",
            "10. Ver items",
            "11. Gestionar comidas del menú",
            "12. Gestionar items de tienda",
            "13. Crear cajero atracción",
            "14. Crear cajero taquilla",
            "15. Crear cocinero",
            "16. Añadir comida a cocinero",
            "17. Crear operador atracción",
            "18. Crear servicio general"
        };
        JList<String> lista3 = new JList<>(metodos3);
        JScrollPane scroll3 = new JScrollPane(lista3);
        scroll3.setPreferredSize(new Dimension(300, 600));

        JPanel panel3 = new JPanel(new GridLayout(1, 2));
        panelCentralEmpleados = new JPanel();
        panelCentralEmpleados.add(new JLabel("Seleccione una opción"));
        panel3.add(scroll3);
        panel3.add(panelCentralEmpleados);

        lista3.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                panelCentralEmpleados.removeAll();
                switch (lista3.getSelectedIndex()) {
                case 0: {
                    JPanel panelVerLugar = new JPanel(new GridLayout(0, 1, 5, 5));
                    panelVerLugar.add(new JLabel("Login:"));
                    JTextField tfLoginVerLugar = new JTextField();
                    panelVerLugar.add(tfLoginVerLugar);
                    JButton btnVerLugar = new JButton("Ver lugar de trabajo");
                    panelVerLugar.add(btnVerLugar, BorderLayout.SOUTH);
                    btnVerLugar.addActionListener(evt -> {
                    	
                    	panelVerLugar.removeAll();

                        panelVerLugar.add(new JLabel("Login:"));
                        panelVerLugar.add(tfLoginVerLugar);
                        panelVerLugar.add(btnVerLugar, BorderLayout.SOUTH);
                    	
                        String login = tfLoginVerLugar.getText();
                        String lugar = Administrador.getLugarDeTrabajo(login);
                        panelVerLugar.add(new JLabel("Lugar: " + lugar));
                        panelVerLugar.revalidate();
                        panelVerLugar.repaint();
                    });
                    panelCentralEmpleados.add(panelVerLugar);
                    break;
                }
                case 1: {
                    JPanel panelModLugar = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelModLugar.add(new JLabel("Login:"));
                    JTextField tfLoginModLugar = new JTextField();
                    panelModLugar.add(tfLoginModLugar);
                    panelModLugar.add(new JLabel("Lugar nuevo:"));
                    JTextField tfNewLugar = new JTextField();
                    panelModLugar.add(tfNewLugar);
                    JButton btnModLugar = new JButton("Modificar lugar");
                    panelModLugar.add(btnModLugar, BorderLayout.SOUTH);
                    btnModLugar.addActionListener(evt -> {
                        Administrador.setLugarDeTrabajo(tfNewLugar.getText(), tfLoginModLugar.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelModLugar);
                    break;
                }
                case 2: {
                    JPanel panelVerRol = new JPanel(new GridLayout(0, 1, 5, 5));
                    panelVerRol.add(new JLabel("Login:"));
                    JTextField tfLoginVerRol = new JTextField();
                    panelVerRol.add(tfLoginVerRol);
                    JButton btnVerRol = new JButton("Ver rol");
                    panelVerRol.add(btnVerRol, BorderLayout.SOUTH);
                    btnVerRol.addActionListener(evt -> {
                    	
                    	panelVerRol.removeAll();

                        panelVerRol.add(new JLabel("Login:"));
                        panelVerRol.add(tfLoginVerRol);
                        panelVerRol.add(btnVerRol, BorderLayout.SOUTH);
                    	
                    	
                        String rol = Administrador.getRol(tfLoginVerRol.getText());
                        panelVerRol.add(new JLabel("Rol: " + rol));
                        panelVerRol.revalidate();
                        panelVerRol.repaint();
                    });
                    panelCentralEmpleados.add(panelVerRol);
                    break;
                }
                case 3: {
                    JPanel panelVerTurnos = new JPanel(new GridLayout(0, 1, 5, 5));
                    panelVerTurnos.add(new JLabel("Login:"));
                    JTextField tfLoginVerTurnos = new JTextField();
                    panelVerTurnos.add(tfLoginVerTurnos);
                    JButton btnVerTurnos = new JButton("Ver turnos");
                    panelVerTurnos.add(btnVerTurnos, BorderLayout.SOUTH);
                    btnVerTurnos.addActionListener(evt -> {
                    	
                    	panelVerTurnos.removeAll();

                        panelVerTurnos.add(new JLabel("Login:"));
                        panelVerTurnos.add(tfLoginVerTurnos);
                        panelVerTurnos.add(btnVerTurnos, BorderLayout.SOUTH);
                    	
                        boolean[] t = Administrador.getTurnos(tfLoginVerTurnos.getText());
                        panelVerTurnos.add(new JLabel("Día: " + t[0] + ", Noche: " + t[1]));
                        panelVerTurnos.revalidate();
                        panelVerTurnos.repaint();
                    });
                    panelCentralEmpleados.add(panelVerTurnos);
                    break;
                }
                case 4: {
                    JPanel panelModTurnoDia = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelModTurnoDia.add(new JLabel("Login:"));
                    JTextField tfLoginModDia = new JTextField();
                    panelModTurnoDia.add(tfLoginModDia);
                    panelModTurnoDia.add(new JLabel("¿Turno diurno?"));
                    JCheckBox chkTurnoDia = new JCheckBox();
                    panelModTurnoDia.add(chkTurnoDia);
                    JButton btnModTurnoDia = new JButton("Modificar turno día");
                    panelModTurnoDia.add(btnModTurnoDia, BorderLayout.SOUTH);
                    btnModTurnoDia.addActionListener(evt -> {
                        Administrador.setTurnoDia(chkTurnoDia.isSelected(), tfLoginModDia.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelModTurnoDia);
                    break;
                }
                case 5: {
                    JPanel panelModTurnoNoche = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelModTurnoNoche.add(new JLabel("Login:"));
                    JTextField tfLoginModNoche = new JTextField();
                    panelModTurnoNoche.add(tfLoginModNoche);
                    panelModTurnoNoche.add(new JLabel("¿Turno nocturno?"));
                    JCheckBox chkTurnoNoche = new JCheckBox();
                    panelModTurnoNoche.add(chkTurnoNoche);
                    JButton btnModTurnoNoche = new JButton("Modificar turno noche");
                    panelModTurnoNoche.add(btnModTurnoNoche, BorderLayout.SOUTH);
                    btnModTurnoNoche.addActionListener(evt -> {
                        Administrador.setTurnoNoche(chkTurnoNoche.isSelected(), tfLoginModNoche.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelModTurnoNoche);
                    break;
                }
                case 6: {
                    JPanel panelCrearCafe = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearCafe.add(new JLabel("Nombre:"));
                    JTextField tfNombreCafe = new JTextField();
                    panelCrearCafe.add(tfNombreCafe);
                    panelCrearCafe.add(new JLabel("Ubicación:"));
                    JTextField tfUbicCafe = new JTextField();
                    panelCrearCafe.add(tfUbicCafe);
                    JButton btnCrearCafe = new JButton("Crear cafetería");
                    panelCrearCafe.add(btnCrearCafe, BorderLayout.SOUTH);
                    btnCrearCafe.addActionListener(evt -> {
                        Administrador.crearCafeteria(tfNombreCafe.getText(), tfUbicCafe.getText(), "");
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearCafe);
                    break;
                }
                case 7: {
                    JPanel panelCrearTienda = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearTienda.add(new JLabel("Nombre:"));
                    JTextField tfNombreTienda = new JTextField();
                    panelCrearTienda.add(tfNombreTienda);
                    panelCrearTienda.add(new JLabel("Ubicación:"));
                    JTextField tfUbicTienda = new JTextField();
                    panelCrearTienda.add(tfUbicTienda);
                    JButton btnCrearTienda = new JButton("Crear tienda");
                    panelCrearTienda.add(btnCrearTienda, BorderLayout.SOUTH);
                    btnCrearTienda.addActionListener(evt -> {
                        Administrador.crearTienda(tfNombreTienda.getText(), tfUbicTienda.getText(), "");
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearTienda);
                    break;
                }
                case 8: {
                    JPanel panelVerMenu = new JPanel(new GridLayout(0, 1, 5, 5));
                    JButton btnVerMenu = new JButton("Ver menú");
                    panelVerMenu.add(btnVerMenu, BorderLayout.SOUTH);

                    btnVerMenu.addActionListener(evt -> {
                        panelVerMenu.removeAll();
                        panelVerMenu.add(btnVerMenu, BorderLayout.SOUTH);
                        Set<String> menu = Administrador.getMenu();  // HashSet<String>
                        for (String item : menu) {
                            panelVerMenu.add(new JLabel(item));
                        }
                        panelVerMenu.revalidate();
                        panelVerMenu.repaint();
                    });

                    panelCentralEmpleados.removeAll();
                    panelCentralEmpleados.add(panelVerMenu);
                    panelCentralEmpleados.revalidate();
                    panelCentralEmpleados.repaint();
                    break;
                }

                case 9: {
                    JPanel panelVerItems = new JPanel(new GridLayout(0, 1, 5, 5));
                    JButton btnVerItems = new JButton("Ver items");
                    panelVerItems.add(btnVerItems, BorderLayout.SOUTH);

                    btnVerItems.addActionListener(evt -> {
                        panelVerItems.removeAll();
                        panelVerItems.add(btnVerItems, BorderLayout.SOUTH);
                        Set<String> items = Administrador.getItems();  // HashSet<String>
                        for (String it : items) {
                            panelVerItems.add(new JLabel(it));
                        }
                        panelVerItems.revalidate();
                        panelVerItems.repaint();
                    });

                    panelCentralEmpleados.removeAll();
                    panelCentralEmpleados.add(panelVerItems);
                    panelCentralEmpleados.revalidate();
                    panelCentralEmpleados.repaint();
                    break;
                }
                case 10: {
                    JPanel panelGestComidas = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelGestComidas.add(new JLabel("Comida:"));
                    JTextField tfComida = new JTextField();
                    panelGestComidas.add(tfComida);
                    JButton btnAgComida = new JButton("Agregar");
                    panelGestComidas.add(btnAgComida, BorderLayout.SOUTH);
                    JButton btnElComida = new JButton("Eliminar");
                    panelGestComidas.add(btnElComida, BorderLayout.SOUTH);
                    btnAgComida.addActionListener(evt -> {
                        Administrador.addComia(tfComida.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    btnElComida.addActionListener(evt -> {
                        Administrador.removeComia(tfComida.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelGestComidas);
                    break;
                }
                case 11: {
                    JPanel panelGestItems = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelGestItems.add(new JLabel("Item:"));
                    JTextField tfItem = new JTextField();
                    panelGestItems.add(tfItem);
                    JButton btnAgItem = new JButton("Agregar");
                    panelGestItems.add(btnAgItem, BorderLayout.SOUTH);
                    JButton btnElItem = new JButton("Eliminar");
                    panelGestItems.add(btnElItem, BorderLayout.SOUTH);
                    btnAgItem.addActionListener(evt -> {
                        Administrador.additem(tfItem.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    btnElItem.addActionListener(evt -> {
                        Administrador.removeitem(tfItem.getText());
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelGestItems);
                    break;
                }
                case 12: {
                    JPanel panelCrearCajAtr = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearCajAtr.add(new JLabel("Nombre:"));
                    JTextField tfNombreCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfNombreCajAtr);
                    panelCrearCajAtr.add(new JLabel("Login:"));
                    JTextField tfLoginCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfLoginCajAtr);
                    panelCrearCajAtr.add(new JLabel("Password:"));
                    JTextField tfPassCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfPassCajAtr);
                    panelCrearCajAtr.add(new JLabel("Altura:"));
                    JTextField tfAltCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfAltCajAtr);
                    panelCrearCajAtr.add(new JLabel("Peso:"));
                    JTextField tfPesCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfPesCajAtr);
                    panelCrearCajAtr.add(new JLabel("Lugar de trabajo:"));
                    JTextField tfLugarCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfLugarCajAtr);
                    panelCrearCajAtr.add(new JLabel("Turno:"));
                    JTextField tfTurnoCajAtr = new JTextField();
                    panelCrearCajAtr.add(tfTurnoCajAtr);
                    JButton btnCrearCajAtr = new JButton("Crear cajero atracción");
                    panelCrearCajAtr.add(btnCrearCajAtr, BorderLayout.SOUTH);
                    btnCrearCajAtr.addActionListener(evt -> {
                        Administrador.crearCajeroAtraccion(
                            tfNombreCajAtr.getText(),
                            tfLoginCajAtr.getText(),
                            tfPassCajAtr.getText(),
                            Integer.parseInt(tfAltCajAtr.getText()),
                            Integer.parseInt(tfPesCajAtr.getText()),
                            tfLugarCajAtr.getText(),
                            tfTurnoCajAtr.getText()
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearCajAtr);
                    break;
                }
                case 13: {
                    JPanel panelCrearCajTaq = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearCajTaq.add(new JLabel("Nombre:"));
                    JTextField tfNombreCajTaq = new JTextField();
                    panelCrearCajTaq.add(tfNombreCajTaq);
                    panelCrearCajTaq.add(new JLabel("Login:"));
                    JTextField tfLoginCajTaq = new JTextField();
                    panelCrearCajTaq.add(tfLoginCajTaq);
                    panelCrearCajTaq.add(new JLabel("Password:"));
                    JTextField tfPassCajTaq = new JTextField( );
                    panelCrearCajTaq.add(tfPassCajTaq);
                    panelCrearCajTaq.add(new JLabel("Altura:"));
                    JTextField tfAltCajTaq = new JTextField();
                    panelCrearCajTaq.add(tfAltCajTaq);
                    panelCrearCajTaq.add(new JLabel("Peso:"));
                    JTextField tfPesCajTaq = new JTextField();
                    panelCrearCajTaq.add(tfPesCajTaq);
                    panelCrearCajTaq.add(new JLabel("Turno:"));
                    JTextField tfTurnoCajTaq = new JTextField();
                    panelCrearCajTaq.add(tfTurnoCajTaq);
                    JButton btnCrearCajTaq = new JButton("Crear cajero taquilla");
                    panelCrearCajTaq.add(btnCrearCajTaq, BorderLayout.SOUTH);
                    btnCrearCajTaq.addActionListener(evt -> {
                        Administrador.crearCajeroTaquilla(
                            tfNombreCajTaq.getText(),
                            tfLoginCajTaq.getText(),
                            tfPassCajTaq.getText(),
                            Integer.parseInt(tfAltCajTaq.getText()),
                            Integer.parseInt(tfPesCajTaq.getText()),
                            tfTurnoCajTaq.getText()
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);  
                    });
                    panelCentralEmpleados.add(panelCrearCajTaq);
                    break;
                }
                case 14: {
                    JPanel panelCrearCocinero = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearCocinero.add(new JLabel("Nombre:"));
                    JTextField tfNombreCoc = new JTextField();
                    panelCrearCocinero.add(tfNombreCoc);
                    panelCrearCocinero.add(new JLabel("Login:"));
                    JTextField tfLoginCoc = new JTextField();
                    panelCrearCocinero.add(tfLoginCoc);
                    panelCrearCocinero.add(new JLabel("Password:"));
                    JTextField tfPassCoc = new JTextField();
                    panelCrearCocinero.add(tfPassCoc);
                    panelCrearCocinero.add(new JLabel("Altura:"));
                    JTextField tfAltCoc = new JTextField();
                    panelCrearCocinero.add(tfAltCoc);
                    panelCrearCocinero.add(new JLabel("Peso:"));
                    JTextField tfPesCoc = new JTextField();
                    panelCrearCocinero.add(tfPesCoc);
                    panelCrearCocinero.add(new JLabel("Turno:"));
                    JTextField tfTurnoCoc = new JTextField();
                    panelCrearCocinero.add(tfTurnoCoc);
                    panelCrearCocinero.add(new JLabel("Lugar de trabajo:"));
                    JTextField tfLugarCoc = new JTextField();
                    panelCrearCocinero.add(tfLugarCoc);
                    JButton btnCrearCoc = new JButton("Crear cocinero");
                    panelCrearCocinero.add(btnCrearCoc, BorderLayout.SOUTH);
                    btnCrearCoc.addActionListener(evt -> {
                        Administrador.crearCocinero(
                            tfNombreCoc.getText(),
                            tfLoginCoc.getText(),
                            tfPassCoc.getText(),
                            Integer.parseInt(tfAltCoc.getText()),
                            Integer.parseInt(tfPesCoc.getText()),
                            tfTurnoCoc.getText(),
                            tfLugarCoc.getText()
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearCocinero);
                    break;
                }
                case 15: {
                    JPanel panelAddComidaCoc = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelAddComidaCoc.add(new JLabel("Login del cocinero:"));
                    JTextField tfLoginAddComida = new JTextField();
                    panelAddComidaCoc.add(tfLoginAddComida);
                    panelAddComidaCoc.add(new JLabel("Nombre de la comida:"));
                    JTextField tfComidaAdd = new JTextField();
                    panelAddComidaCoc.add(tfComidaAdd);
                    JButton btnAddComida = new JButton("Añadir comida");
                    panelAddComidaCoc.add(btnAddComida, BorderLayout.SOUTH);
                    btnAddComida.addActionListener(evt -> {
                        Administrador.addComidaCocinero(
                            tfLoginAddComida.getText(),
                            tfComidaAdd.getText()
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelAddComidaCoc);
                    break;
                }
                case 16: {
                    JPanel panelCrearOpAtr = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearOpAtr.add(new JLabel("Nombre:"));
                    JTextField tfNombreOpAtr = new JTextField();
                    panelCrearOpAtr.add(tfNombreOpAtr);
                    panelCrearOpAtr.add(new JLabel("Login:"));
                    JTextField tfLoginOpAtr = new JTextField();
                    panelCrearOpAtr.add(tfLoginOpAtr);
                    panelCrearOpAtr.add(new JLabel("Password:"));
                    JTextField tfPassOpAtr = new JTextField();
                    panelCrearOpAtr.add(tfPassOpAtr);
                    panelCrearOpAtr.add(new JLabel("Altura:"));
                    JTextField tfAltOpAtr = new JTextField();
                    panelCrearOpAtr.add(tfAltOpAtr);
                    panelCrearOpAtr.add(new JLabel("Peso:"));
                    JTextField tfPesOpAtr = new JTextField();
                    panelCrearOpAtr.add(tfPesOpAtr);
                    JButton btnCrearOpAtr = new JButton("Crear operador atracción");
                    panelCrearOpAtr.add(btnCrearOpAtr, BorderLayout.SOUTH);
                    btnCrearOpAtr.addActionListener(evt -> {
                        Administrador.crearOperadorAtraccion(
                            tfNombreOpAtr.getText(),
                            tfLoginOpAtr.getText(),
                            tfPassOpAtr.getText(),
                            Integer.parseInt(tfAltOpAtr.getText()),
                            Integer.parseInt(tfPesOpAtr.getText())
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearOpAtr);
                    break;
                }
                case 17: {
                    JPanel panelCrearSvcGen = new JPanel(new GridLayout(0, 2, 5, 5));
                    panelCrearSvcGen.add(new JLabel("Nombre:"));
                    JTextField tfNombreSvc = new JTextField();
                    panelCrearSvcGen.add(tfNombreSvc);
                    panelCrearSvcGen.add(new JLabel("Login:"));
                    JTextField tfLoginSvc = new JTextField();
                    panelCrearSvcGen.add(tfLoginSvc);
                    panelCrearSvcGen.add(new JLabel("Password:"));
                    JTextField tfPassSvc = new JTextField();
                    panelCrearSvcGen.add(tfPassSvc);
                    panelCrearSvcGen.add(new JLabel("Altura:"));
                    JTextField tfAltSvc = new JTextField();
                    panelCrearSvcGen.add(tfAltSvc);
                    panelCrearSvcGen.add(new JLabel("Peso:"));
                    JTextField tfPesSvc = new JTextField();
                    panelCrearSvcGen.add(tfPesSvc);
                    panelCrearSvcGen.add(new JLabel("Lugar de trabajo:"));
                    JTextField tfLugarSvc = new JTextField();
                    panelCrearSvcGen.add(tfLugarSvc);
                    panelCrearSvcGen.add(new JLabel("Turno (DIURNO/NOCTURNO):"));
                    JTextField tfTurnoSvc = new JTextField();
                    panelCrearSvcGen.add(tfTurnoSvc);
                    JButton btnCrearSvcGen = new JButton("Crear servicio general");
                    panelCrearSvcGen.add(btnCrearSvcGen, BorderLayout.SOUTH);
                    btnCrearSvcGen.addActionListener(evt -> {
                        Administrador.crearServicioGeneral(
                            tfNombreSvc.getText(),
                            tfLoginSvc.getText(),
                            tfPassSvc.getText(),
                            Integer.parseInt(tfAltSvc.getText()),
                            Integer.parseInt(tfPesSvc.getText()),
                            tfLugarSvc.getText(),
                            tfTurnoSvc.getText()
                        );
                        JOptionPane.showMessageDialog(null, "Operación Exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelCentralEmpleados.add(panelCrearSvcGen);
                    break;
                }
                    default:
                        panelCentralEmpleados.add(new JLabel("Opción no implementada"));
                }
                panelCentralEmpleados.revalidate();
                panelCentralEmpleados.repaint();
            }
        });

        tabbedpane.addTab("Empleados", panel3);

        // ——— Frame principal ———
        JFrame ventana = new JFrame("Ventana Administrador");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.add(tabbedpane);
        ventana.setSize(1000, 800);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

}
