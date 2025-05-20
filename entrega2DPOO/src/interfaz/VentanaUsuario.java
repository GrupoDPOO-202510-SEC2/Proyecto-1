package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
                "Ver todos los tiquetes comprados",
                "Ver tiquete en uso",
                "Agregar restricción",
                "Eliminar restricción",
                "Ver restricciones",
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
		                        	
		                        	if (fechaf.equals("") || fechaf.equals("")) {
		                        		JOptionPane.showMessageDialog(null,  "Porfavor ingresar las fechas del tiquete","Error de Construccion", JOptionPane.INFORMATION_MESSAGE);
		                        	}else {
		                        		usuario.comprarTiquete("TiqueteTemporada", fechai , fechaf , exclusividad, fazpas);
		                        	}
								}else {
									JOptionPane.showMessageDialog(null,  "Porfavor seleccione un nivel de exclusividad","Error de Construccion", JOptionPane.INFORMATION_MESSAGE);
								}
							} else if (tipo == 2) {
								if (exclusividad != 0) {
									usuario.comprarTiquete("TiqueteExclusivo", "", "", exclusividad, fazpas);
								}else {
									JOptionPane.showMessageDialog(null, "Porfavor seleccione un nivel de exclusividad","Error de Construccion",  JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Porfavor seleccione un tipo de tiquete","Error de Construccion",  JOptionPane.INFORMATION_MESSAGE);
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
                    	JLabel instrucciones1 = new JLabel("Dale una sola vez a uno de los codigos de los tiquetes.", SwingConstants.CENTER);
                    	instrucciones1.setFont(instrucciones1.getFont().deriveFont(15f).deriveFont(Font.BOLD));
                        JLabel instrucciones2 = new JLabel("te mostrara el tiquete en pantalla.", SwingConstants.CENTER);
                        instrucciones2.setFont(instrucciones2.getFont().deriveFont(15f).deriveFont(Font.BOLD));
                        
                        panelUsuario.add(instrucciones1);
                        panelUsuario.add(instrucciones2);
                    	panelUsuario.add(scrollTiquetes, BorderLayout.CENTER);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                    case 2:
                    	tiquetes = this.usuario.getTiquetesComprados();
                    	tiquetesString = new String[tiquetes.size()];
                    	
                    	for(int i = 0; i < tiquetes.size(); i++) {
                    		tiquetesString[i] = (tiquetes.get(i).toString());
                    	}
                    	listaTiquetes = new JList<String>(tiquetesString);
                    	scrollTiquetes = new JScrollPane(listaTiquetes);
                    	scrollTiquetes.setPreferredSize(new Dimension(300, 600));
                    	
                    	listaTiquetes.addListSelectionListener(ev -> {
						    if (!ev.getValueIsAdjusting()) {
						    	String codigoTiquete = listaTiquetes.getSelectedValue();
						    	Tiquete tiquete = Usuario.parque.tiquetes.get(Double.parseDouble(codigoTiquete));
						    	
						    	new VentanaBoleta(tiquete);						    	
								
							}
		                });
		                    	
                    	panelUsuario.removeAll();
                    	instrucciones1 = new JLabel("Dale una sola vez a uno de los codigos de los tiquetes.", SwingConstants.CENTER);
                    	instrucciones1.setFont(instrucciones1.getFont().deriveFont(15f).deriveFont(Font.BOLD));
                        instrucciones2 = new JLabel("te mostrara el tiquete en pantalla.", SwingConstants.CENTER);
                        instrucciones2.setFont(instrucciones2.getFont().deriveFont(15f).deriveFont(Font.BOLD));
                        
                        panelUsuario.add(instrucciones1);
                        panelUsuario.add(instrucciones2);
                    	panelUsuario.add(scrollTiquetes, BorderLayout.CENTER);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                    case 3:
                        
                    	Tiquete tiquete = usuario.getTiqueteEnUso();
                    	
                    	if(tiquete != null) {
                    		new VentanaBoleta(tiquete);	
                    	}else {
                    		JOptionPane.showMessageDialog(null,  "El usuario no esta utilizando ningun tiquete actualmente","Error Tiquete no encontrado", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    	
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(new JLabel("El tiquete se esta mostrando en otra ventana"));
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                    case 4:
                        
                    	JPanel panelAgregarRest = new JPanel(new GridLayout(2, 2, 5, 10)); 
                    	
						
						panelAgregarRest.add(new JLabel("Restriccion:"));
                    	JTextField jRestriccion = new JTextField();
                    	panelAgregarRest.add(jRestriccion);
                    	
                    	JButton btnCrearRestriccion = new JButton("Agregar Restriccion");
                    	panelAgregarRest.add(btnCrearRestriccion, BorderLayout.SOUTH);
                        
                    	btnCrearRestriccion.addActionListener(evt -> {
                            String restriccion   = jRestriccion.getText();
							this.usuario.agregarRestriccion(restriccion);
							});
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(panelAgregarRest);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                    case 5:
                        
                    	panelAgregarRest = new JPanel(new GridLayout(2, 2, 5, 10)); 
                    	
						
						panelAgregarRest.add(new JLabel("Restriccion:"));
                    	jRestriccion = new JTextField();
                    	panelAgregarRest.add(jRestriccion);
                    	
                    	btnCrearRestriccion = new JButton("Eliminar Restriccion");
                    	panelAgregarRest.add(btnCrearRestriccion, BorderLayout.SOUTH);
                        
                    	btnCrearRestriccion.addActionListener(evt -> {
                            String restriccion   = jRestriccion.getText();
							this.usuario.eliminarRestriccion(restriccion);
							});
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(panelAgregarRest);
                    	panelUsuario.revalidate();
                    	panelUsuario.repaint();
                        break;
                      
                    case 6:
                    	
                    	Object[] restriccionesA = this.usuario.getRestricciones().toArray();
                    	JList jRestricciones = new JList(restriccionesA); 
                    	
                    	panelUsuario.removeAll();
                    	panelUsuario.add(jRestricciones);
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
