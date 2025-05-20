package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import atraccion.Espectaculo;
import tiquete.Tiquete;
import usuario.Administrador;
import usuario.Usuario;

public class VentanaUsuario extends JFrame{
	private JPanel panelUsuario;
	private Usuario usuario;

	int tipo = 0;
	int exclusividad = 0;
	String fechai;
	String fechaf;
	
	
	
	
	public VentanaUsuario(Usuario usuario) {
		
		this.usuario = usuario;
		
		
		// ——— Pestaña Espectáculos ———
        String[] metodos1 = {
        		"Comprar tiquete",
                "Ver tiquetes funcionales",
                "Agregar restricción",
                "Eliminar restricción",
                "Ver restricciones",
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
                    	
                    	JPanel izquierda = new JPanel(); 
                    	izquierda.setLayout(new BoxLayout(izquierda ,BoxLayout.Y_AXIS));
                    	
                        
                    	JPanel temporada = new JPanel(new GridLayout(0, 2, 5, 5)); 
                    	
                    	JPanel panelTipos = new JPanel(new GridLayout(3, 0, 5, 5));
                    	
                    	JPanel panelExclusividades = new JPanel(new GridLayout(4, 0, 5, 5));
                    	
                    	
                    	
                    	JRadioButton tiqueteIndividualRadio = new JRadioButton("Tiquete individual");
                    	JRadioButton tiqueteTemporadaRadio = new JRadioButton("Tiquete temporada");
                    	JRadioButton tiqueteExclusividadRadio = new JRadioButton("Tiquete exclusividad");
                    	
                    	panelTipos.add(tiqueteIndividualRadio);
                    	panelTipos.add(tiqueteExclusividadRadio);
                    	panelTipos.add(tiqueteTemporadaRadio);
                    	
                    	JRadioButton basico = new JRadioButton("Tiquete Basico ");
                    	JRadioButton familiar = new JRadioButton("Tiquete Familiar");
                    	JRadioButton oro = new JRadioButton("Tiquete Oro");
                    	JRadioButton diamante = new JRadioButton("Tiquete Diamante");
                    	

                    	JTextField fechaInicial = new JTextField();
                    	JTextField fechaFinal = new JTextField();

                        basico.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {exclusividad = 1;}});

                        familiar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {exclusividad = 2;}});

                        oro.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {exclusividad = 3;}});

                        diamante.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {exclusividad = 4;}});

                    	
                    	
                    	JButton btnCrear = new JButton("Comprar Tiquete");
                    	
                    	JCheckBox fastPass = new JCheckBox("Es Fast Pass");
                    	
                        btnCrear.addActionListener(evt -> {
                        	
                        	boolean fazpas = fastPass.isSelected();
                        	
							if (tipo == 3) {
							   usuario.comprarTiquete("TiqueteIndividual", "", "", 5, fazpas);
							} else if (tipo == 1) {
								if (exclusividad != 0) {
		                        	fechaf = fechaFinal.getText();
		                        	fechai = fechaInicial.getText();
		                        	
									usuario.comprarTiquete("TiqueteTemporada", fechai , fechaf , exclusividad, fazpas);
								}else {
									JOptionPane.showMessageDialog(null, "Error de Construccion", "Porfavor seleccione un nivel de exclusividad", JOptionPane.INFORMATION_MESSAGE);
								}
							} else if (tipo == 2) {
								if (exclusividad != 0) {
									usuario.comprarTiquete("TiqueteExclusivo", "", "", exclusividad, fazpas);
								}else {
									JOptionPane.showMessageDialog(null, "Error de Construccion", "Porfavor seleccione un nivel de exclusividad", JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Error de Construccion", "Porfavor seleccione un tipo de tiquete", JOptionPane.INFORMATION_MESSAGE);
							}
                        });
                    	
                    	tiqueteTemporadaRadio.addActionListener(evt -> {
                    		
                    		tipo = 1;
                    		panelUsuario.removeAll();
                    		
                    		panelExclusividades.removeAll();
                        	panelExclusividades.add(basico);
	                    	panelExclusividades.add(familiar);
	                    	panelExclusividades.add(oro);
	                    	panelExclusividades.add(diamante);
	                    	
	                    	temporada.removeAll();
	                    	
	                    	temporada.add(new JLabel("Fecha Inicial:"));
                        	temporada.add(fechaInicial);
                        	
                        	temporada.add(new JLabel("Fecha Fin:"));
                        	temporada.add(fechaFinal);
                        	
	                    	izquierda.add(panelExclusividades);
	                    	izquierda.add(temporada);
	                    	izquierda.add(fastPass);
	                    	izquierda.add(new JLabel(""));
	                    	izquierda.add(new JLabel(""));
	                    	izquierda.add(btnCrear);
	                    	
                            panelUsuario.add(izquierda);
                            panelUsuario.revalidate();
                            panelUsuario.repaint();
                            
                            
                    		
						});
                    	
                    	tiqueteExclusividadRadio.addActionListener(evt -> {
                    		
                    		tipo = 2;
                    		panelUsuario.removeAll();
                    		temporada.removeAll();
                    		panelExclusividades.removeAll();
                        	panelExclusividades.add(basico);
	                    	panelExclusividades.add(familiar);
	                    	panelExclusividades.add(oro);
	                    	panelExclusividades.add(diamante);
	                    	
	                    	izquierda.add(panelExclusividades);
	                    	izquierda.add(fastPass);
	                    	izquierda.add(new JLabel(""));
	                    	izquierda.add(new JLabel(""));
	                    	izquierda.add(btnCrear);
                            panelUsuario.add(izquierda);
                            panelUsuario.revalidate();
                            panelUsuario.repaint();
                            
                            
                    		
						});
                    	
                    	tiqueteIndividualRadio.addActionListener(evt -> {
                    		
                    		tipo = 3;
                    		temporada.removeAll();
                    		panelExclusividades.removeAll();
                    		izquierda.add(fastPass);
                    		izquierda.add(new JLabel(""));
                    		izquierda.add(btnCrear);
                    		panelUsuario.removeAll();
                            panelUsuario.add(izquierda);
                            panelUsuario.revalidate();
                            panelUsuario.repaint();
                            
                            
                            
                    		
						});
                        
                        ButtonGroup group = new ButtonGroup();
                    	group.add(tiqueteIndividualRadio);
                    	group.add(tiqueteExclusividadRadio);
                    	group.add(tiqueteTemporadaRadio);
                    	
                    	ButtonGroup exclusividades = new ButtonGroup();
                    	exclusividades.add(basico);
                    	exclusividades.add(familiar);
                    	exclusividades.add(oro);
                    	exclusividades.add(diamante);
                    	
                        panelUsuario.removeAll();
                        izquierda.removeAll();
                        izquierda.add(panelTipos);
                        izquierda.add(new JLabel(""));
                        izquierda.add(new JLabel("Configura tu tiquete"));
                        izquierda.add(new JLabel(""));
                        izquierda.add(panelExclusividades);
                        izquierda.add(temporada);
                        izquierda.add(fastPass);
                        izquierda.add(btnCrear);
                        panelUsuario.add(izquierda);
                        panelUsuario.revalidate();
                        panelUsuario.repaint();
                        
                        break;
                        
                    case 1:
                    	
                    	ArrayList<Double> tiquetes = this.usuario.getTiquetesFuncionales();
                    	String[] tiquetesString = new String[tiquetes.size()];
                    	
                    	for(int i = 0; i < tiquetes.size(); i++) {
                    		tiquetesString[i] = (tiquetes.get(i).toString());
                    	}
                    	JList<String> listaTiquetes = new JList<String>(tiquetesString);
                    	JScrollPane scrollTiquetes = new JScrollPane(listaTiquetes);
                    	scrollTiquetes.setPreferredSize(new Dimension(300, 600));
                    	
                    	listaTiquetes.addListSelectionListener(ev -> {
						    if (!ev.getValueIsAdjusting()) {
						    	String codigoTiquete = listaTiquetes.getSelectedValue();
						    	Tiquete tiquete = Usuario.parque.tiquetes.get(Double.parseDouble(codigoTiquete));
						    	
						    	new VentanaBoleta(tiquete);						    	
								
							}
		                });
		                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(scrollTiquetes, BorderLayout.CENTER);
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
