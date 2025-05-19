package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import atraccion.Espectaculo;
import usuario.Administrador;
import usuario.Usuario;

public class VentanaUsuario extends JFrame{
	private JPanel panelUsuario;
	private Usuario usuario;
	
	public VentanaUsuario(Usuario usuario) {
		
		
		
		// ——— Pestaña Espectáculos ———
        String[] metodos1 = {
        		"Comprar tiquete",
                "Ver restricciones",
                "Agregar restricción",
                "Eliminar restricción",
                "Ver tiquetes funcionales",
                "Asignar tiquete en uso",
                "Invalidar tiquete en uso",
                "Ver tiquete en uso",
                "Ver historial de compras",
        };
        JList<String> lista1 = new JList<>(metodos1);
        JScrollPane scroll1 = new JScrollPane(lista1);
        scroll1.setPreferredSize(new Dimension(300, 600));

        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        panelUsuario = new JPanel();
        panelUsuario.add(new JLabel("Seleccione una opción"));
        panel1.add(scroll1);
        panel1.add(panelUsuario);

        lista1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
            	panelUsuario.removeAll();
                switch (lista1.getSelectedIndex()) {
                    case 0:
                    	
                    	
                    	JPanel panelComprarTiquete = new JPanel(new GridLayout(0, 2, 5, 5)); 
                    	
                    	JPanel panelBotones = new JPanel(new GridLayout(3, 0, 5, 5));
                    	
                    	JRadioButton tiqueteIndividualRadio = new JRadioButton("Tiquete individual");
                    	JRadioButton tiqueteTemporadaRadio = new JRadioButton("Tiquete temporada");
                    	JRadioButton tiqueteExclusividadRadio = new JRadioButton("Tiquete exclusividad");
                    	
                    	ButtonGroup group = new ButtonGroup();
                    	group.add(tiqueteIndividualRadio);
                    	group.add(tiqueteExclusividadRadio);
                    	group.add(tiqueteTemporadaRadio);
                    	
                    	panelBotones.add(tiqueteIndividualRadio);
                    	panelBotones.add(tiqueteExclusividadRadio);
                    	panelBotones.add(tiqueteTemporadaRadio);
                    	
                    	panelComprarTiquete.add(new JLabel("Nombre:"));
                    	JTextField tfNombre = new JTextField();
                    	panelComprarTiquete.add(tfNombre);

                    	panelComprarTiquete.add(new JLabel("Horario:"));
                    	JTextField tfHorario = new JTextField();
                    	panelComprarTiquete.add(tfHorario);

                    	panelComprarTiquete.add(new JLabel("Ubicación:"));
                    	JTextField tfUbicacion = new JTextField();
                    	panelComprarTiquete.add(tfUbicacion);
                    	
                    	JButton btnCrear = new JButton("Crear espectáculo");
                    	panelComprarTiquete.add(btnCrear, BorderLayout.SOUTH);
                        
                        btnCrear.addActionListener(evt -> {
                            String nombre   = tfNombre.getText();
                            String horario  = tfHorario.getText();
                            String ubicacion= tfUbicacion.getText();
							Administrador.crearEspectaculo(nombre, horario, ubicacion);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Se creo correctamente el espectaculo", JOptionPane.INFORMATION_MESSAGE);
							});
                    	
                        panelUsuario.removeAll();
                        panelUsuario.add(panelBotones);
                        panelUsuario.add(panelComprarTiquete);
                        panelUsuario.revalidate();
                        panelUsuario.repaint();
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
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(panelCambiarFecha);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
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
                    	panelUsuario.removeAll();
                    	panelUsuario.add(panelVerEsp);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
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
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(panelClimas);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                    default:
                    	panelUsuario.add(new JLabel("Opción no implementada"));
                }
                panelUsuario.revalidate();
                panelUsuario.repaint();
            }
        });

        add(panel1);		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
}
