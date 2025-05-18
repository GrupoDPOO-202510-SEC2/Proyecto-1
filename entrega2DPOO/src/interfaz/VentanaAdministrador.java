package interfaz;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

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
                    		String nombre2 = tfNombre2.getText();
                            Espectaculo esp = Administrador.getEspectaculo(nombre2);
							panelVerEsp.add(new JLabel("Nombre: "+ esp.getNombre()));
	                        panelVerEsp.add(new JLabel("Ubicación: "+ esp.getUbicacion()));
	                        panelVerEsp.add(new JLabel("Horario: "+ esp.getHorario()));
	                        panelVerEsp.add(new JLabel("Fecha Inicio: "+ esp.getFechaInicio()));
	                        panelVerEsp.add(new JLabel("Fecha Fin: "+ esp.getFechaFin()));
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
            "1. Crear atracción cultural",
            "2. Ver atracción",
            "3. Modificar capacidad máxima",
            "4. Modificar empleados mínimos",
            "5. Modificar estado de servicio",
            "6. Modificar exclusividad",
            "7. Ver operadores de atracción",
            "8. Gestionar operadores",
            "9. Gestionar restricciones de salud",
            "10. Gestionar climas restringidos de atracción",
            "11. Ver alturas y pesos",
            "12. Modificar altura máxima",
            "13. Modificar altura mínima",
            "14. Modificar peso máximo",
            "15. Modificar peso mínimo",
            "16. Modificar riesgo alto",
            "17. Ver edad mínima",
            "18. Ver si es interactiva",
            "19. Modificar edad mínima",
            "20. Modificar interactividad"
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
                        JPanel pCrearAtr = new JPanel();
                        pCrearAtr.add(new JLabel("Formulario: Crear atracción cultural"));
                        panelCentralAtracciones.add(pCrearAtr);
                        break;
                    case 1:
                        JPanel pVerAtr = new JPanel();
                        pVerAtr.add(new JLabel("Pantalla: Ver atracción"));
                        panelCentralAtracciones.add(pVerAtr);
                        break;
                    case 2:
                        JPanel pModCap = new JPanel();
                        pModCap.add(new JLabel("Formulario: Modificar capacidad máxima"));
                        panelCentralAtracciones.add(pModCap);
                        break;
                    case 3:
                        JPanel pModEmp = new JPanel();
                        pModEmp.add(new JLabel("Formulario: Modificar empleados mínimos"));
                        panelCentralAtracciones.add(pModEmp);
                        break;
                    case 4:
                        JPanel pModEstado = new JPanel();
                        pModEstado.add(new JLabel("Formulario: Modificar estado de servicio"));
                        panelCentralAtracciones.add(pModEstado);
                        break;
                    case 5:
                        JPanel pModExcl = new JPanel();
                        pModExcl.add(new JLabel("Formulario: Modificar exclusividad"));
                        panelCentralAtracciones.add(pModExcl);
                        break;
                    case 6:
                        JPanel pVerOp = new JPanel();
                        pVerOp.add(new JLabel("Pantalla: Ver operadores de atracción"));
                        panelCentralAtracciones.add(pVerOp);
                        break;
                    case 7:
                        JPanel pGestOp = new JPanel();
                        pGestOp.add(new JLabel("Pantalla: Gestionar operadores"));
                        panelCentralAtracciones.add(pGestOp);
                        break;
                    case 8:
                        JPanel pResSalud = new JPanel();
                        pResSalud.add(new JLabel("Pantalla: Gestionar restricciones de salud"));
                        panelCentralAtracciones.add(pResSalud);
                        break;
                    case 9:
                        JPanel pClimasAtr = new JPanel();
                        pClimasAtr.add(new JLabel("Pantalla: Gestionar climas restringidos de atracción"));
                        panelCentralAtracciones.add(pClimasAtr);
                        break;
                    case 10:
                        JPanel pAltPes = new JPanel();
                        pAltPes.add(new JLabel("Pantalla: Ver alturas y pesos"));
                        panelCentralAtracciones.add(pAltPes);
                        break;
                    case 11:
                        JPanel pModAltMax = new JPanel();
                        pModAltMax.add(new JLabel("Formulario: Modificar altura máxima"));
                        panelCentralAtracciones.add(pModAltMax);
                        break;
                    case 12:
                        JPanel pModAltMin = new JPanel();
                        pModAltMin.add(new JLabel("Formulario: Modificar altura mínima"));
                        panelCentralAtracciones.add(pModAltMin);
                        break;
                    case 13:
                        JPanel pModPesoMax = new JPanel();
                        pModPesoMax.add(new JLabel("Formulario: Modificar peso máximo"));
                        panelCentralAtracciones.add(pModPesoMax);
                        break;
                    case 14:
                        JPanel pModPesoMin = new JPanel();
                        pModPesoMin.add(new JLabel("Formulario: Modificar peso mínimo"));
                        panelCentralAtracciones.add(pModPesoMin);
                        break;
                    case 15:
                        JPanel pModRiesgo = new JPanel();
                        pModRiesgo.add(new JLabel("Formulario: Modificar riesgo alto"));
                        panelCentralAtracciones.add(pModRiesgo);
                        break;
                    case 16:
                        JPanel pVerEdadMin = new JPanel();
                        pVerEdadMin.add(new JLabel("Pantalla: Ver edad mínima"));
                        panelCentralAtracciones.add(pVerEdadMin);
                        break;
                    case 17:
                        JPanel pVerInteract = new JPanel();
                        pVerInteract.add(new JLabel("Pantalla: Ver si es interactiva"));
                        panelCentralAtracciones.add(pVerInteract);
                        break;
                    case 18:
                        JPanel pModEdadMin = new JPanel();
                        pModEdadMin.add(new JLabel("Formulario: Modificar edad mínima"));
                        panelCentralAtracciones.add(pModEdadMin);
                        break;
                    case 19:
                        JPanel pModInteract = new JPanel();
                        pModInteract.add(new JLabel("Formulario: Modificar interactividad"));
                        panelCentralAtracciones.add(pModInteract);
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
                    case 0:
                        JPanel pVerLugar = new JPanel();
                        pVerLugar.add(new JLabel("Pantalla: Ver lugar de trabajo"));
                        panelCentralEmpleados.add(pVerLugar);
                        break;
                    case 1:
                        JPanel pModLugar = new JPanel();
                        pModLugar.add(new JLabel("Formulario: Modificar lugar de trabajo"));
                        panelCentralEmpleados.add(pModLugar);
                        break;
                    case 2:
                        JPanel pVerRol = new JPanel();
                        pVerRol.add(new JLabel("Pantalla: Ver rol"));
                        panelCentralEmpleados.add(pVerRol);
                        break;
                    case 3:
                        JPanel pVerTurnos = new JPanel();
                        pVerTurnos.add(new JLabel("Pantalla: Ver turnos"));
                        panelCentralEmpleados.add(pVerTurnos);
                        break;
                    case 4:
                        JPanel pModTurnoDia = new JPanel();
                        pModTurnoDia.add(new JLabel("Formulario: Modificar turno día"));
                        panelCentralEmpleados.add(pModTurnoDia);
                        break;
                    case 5:
                        JPanel pModTurnoNoche = new JPanel();
                        pModTurnoNoche.add(new JLabel("Formulario: Modificar turno noche"));
                        panelCentralEmpleados.add(pModTurnoNoche);
                        break;
                    case 6:
                        JPanel pCrearCafe = new JPanel();
                        pCrearCafe.add(new JLabel("Formulario: Crear cafetería"));
                        panelCentralEmpleados.add(pCrearCafe);
                        break;
                    case 7:
                        JPanel pCrearTienda = new JPanel();
                        pCrearTienda.add(new JLabel("Formulario: Crear tienda"));
                        panelCentralEmpleados.add(pCrearTienda);
                        break;
                    case 8:
                        JPanel pVerMenu = new JPanel();
                        pVerMenu.add(new JLabel("Pantalla: Ver menú"));
                        panelCentralEmpleados.add(pVerMenu);
                        break;
                    case 9:
                        JPanel pVerItems = new JPanel();
                        pVerItems.add(new JLabel("Pantalla: Ver items"));
                        panelCentralEmpleados.add(pVerItems);
                        break;
                    case 10:
                        JPanel pGestComidas = new JPanel();
                        pGestComidas.add(new JLabel("Pantalla: Gestionar comidas del menú"));
                        panelCentralEmpleados.add(pGestComidas);
                        break;
                    case 11:
                        JPanel pGestItems = new JPanel();
                        pGestItems.add(new JLabel("Pantalla: Gestionar items de tienda"));
                        panelCentralEmpleados.add(pGestItems);
                        break;
                    case 12:
                        JPanel pCrearCajAtr = new JPanel();
                        pCrearCajAtr.add(new JLabel("Formulario: Crear cajero atracción"));
                        panelCentralEmpleados.add(pCrearCajAtr);
                        break;
                    case 13:
                        JPanel pCrearCajTaq = new JPanel();
                        pCrearCajTaq.add(new JLabel("Formulario: Crear cajero taquilla"));
                        panelCentralEmpleados.add(pCrearCajTaq);
                        break;
                    case 14:
                        JPanel pCrearCocinero = new JPanel();
                        pCrearCocinero.add(new JLabel("Formulario: Crear cocinero"));
                        panelCentralEmpleados.add(pCrearCocinero);
                        break;
                    case 15:
                        JPanel pAñadirComida = new JPanel();
                        pAñadirComida.add(new JLabel("Formulario: Añadir comida a cocinero"));
                        panelCentralEmpleados.add(pAñadirComida);
                        break;
                    case 16:
                        JPanel pCrearOpAtr = new JPanel();
                        pCrearOpAtr.add(new JLabel("Formulario: Crear operador atracción"));
                        panelCentralEmpleados.add(pCrearOpAtr);
                        break;
                    case 17:
                        JPanel pCrearSvcGen = new JPanel();
                        pCrearSvcGen.add(new JLabel("Formulario: Crear servicio general"));
                        panelCentralEmpleados.add(pCrearSvcGen);
                        break;
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
