package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.google.zxing.WriterException;

import tiquete.Tiquete;
import tiquete.TiqueteExclusividad;
import tiquete.TiqueteTemporada;

public class VentanaBoleta extends JFrame{
	
	private static final Image diamanteR = new ImageIcon("src/data/exclusividad diamante.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private static final ImageIcon diamante = new ImageIcon(diamanteR);
    
    private static final Image oroR = new ImageIcon("src/data/exclusividad oro.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    private static final ImageIcon oro = new ImageIcon(oroR);
    
    private static final Image familiarR = new ImageIcon("src/data/exclusividad familiar.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    private static final ImageIcon familiar = new ImageIcon(oroR);
    
    private static final Image basicoR = new ImageIcon("src/data/exclusividad basico.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    private static final ImageIcon basico = new ImageIcon(basicoR);
    
    private static final Image imagenCentroR = new ImageIcon("src/data/Parque logo.png").getImage().getScaledInstance(400, 226, Image.SCALE_SMOOTH);
    private static final ImageIcon imagenCentro = new ImageIcon(imagenCentroR);
    
    private static ImageIcon qr;
	
	public VentanaBoleta(Tiquete tiquete) {
		
		super("Boleta");
		
		double valor = 42000;
		String fechas;
		
		boolean faspas = tiquete.isFastPass();
		String fazzballs = "no";
		
		if(faspas) {
			fazzballs = "si";
		}
		
		String exclusividad = "Diamante";
		
		Double idboleta = tiquete.getIdTiquete();
		String tipo = "Individual";
		fechas = "cualquier dia, solo una atraccion";
		ImageIcon iconoExclusividad = diamante;
		
		if(tiquete instanceof TiqueteTemporada ) {
			TiqueteTemporada tiquetet = (TiqueteTemporada) tiquete;
			tipo = "Temporada"; 
			fechas = tiquetet.getFechaInicio() + " - " + tiquetet.getFechaFin();
			if(tiquetet.getExclusividad() == 1) {
				iconoExclusividad = basico;
				exclusividad = "Basico";
			}else if(tiquetet.getExclusividad() == 2) {
				iconoExclusividad = familiar;
				exclusividad = "Familiar";
			}else if(tiquetet.getExclusividad() == 3) {
				iconoExclusividad = oro;
				exclusividad = "Oro";
			}
			
		}else if(tiquete instanceof TiqueteExclusividad ) {
			tipo = "Exclusividad"; 
			fechas = "cualquier dia, solo un dia";
			TiqueteExclusividad tiquetet = (TiqueteExclusividad) tiquete;
			if(tiquetet.getExclusividad() == 1) {
				iconoExclusividad = basico;
				exclusividad = "Basico";
			}else if(tiquetet.getExclusividad() == 2) {
				iconoExclusividad = familiar;
				exclusividad = "Familiar";
			}else if(tiquetet.getExclusividad() == 3) {
				iconoExclusividad = oro;
				exclusividad = "Oro";
			}
		}
		
		
		try {
			qr = GeneradorQR.generateQRCodeIcon("No. " + idboleta + "\n" +
					"Tiquete: " + tipo + "\n"+
					"Fechas de uso: " + fechas + "\n"+
					"Fastpass: " + fazzballs + "\n"+
					"Exclusividad: " + exclusividad
					);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);
        JPanel panelStub = new JPanel();
        panelStub.setPreferredSize(new Dimension(270, 0));
        panelStub.setLayout(new BoxLayout(panelStub, BoxLayout.Y_AXIS));
        panelStub.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 0, 2, Color.GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));
        panelStub.add(new JLabel("No. " + idboleta));
        panelStub.add(Box.createVerticalStrut(20));
        panelStub.add(new JLabel("Tiquete: " + tipo));
        panelStub.add(Box.createVerticalStrut(5));
        
        panelStub.add(new JLabel("Fechas de uso: " + fechas));
        panelStub.add(Box.createVerticalStrut(5));
        panelStub.add(new JLabel(String.format("Valor: $%,.2f", valor)));
        panelStub.add(Box.createVerticalStrut(5));
        panelStub.add(new JLabel("FastPass: " + fazzballs));
        panelStub.add(Box.createVerticalGlue());
        panelStub.add(new JLabel(iconoExclusividad));
        contenido.add(panelStub, BorderLayout.WEST);
        
        JPanel panelTicket = new JPanel(new BorderLayout());
        panelTicket.setBackground(Color.CYAN);
        JLabel lblTitulo = new JLabel("Parque de Atracciones de los Alpes                   ", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(24f).deriveFont(Font.BOLD));
        panelTicket.add(lblTitulo, BorderLayout.NORTH);
        JLabel lblImagen = new JLabel(imagenCentro);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelTicket.add(lblImagen, BorderLayout.CENTER);
        JLabel lblQR = new JLabel(qr);
        lblQR.setBorder(new EmptyBorder(10,10,10,10));
        panelTicket.add(lblQR, BorderLayout.EAST);
        JLabel lblFecha = new JLabel(fechas + "                      ",SwingConstants.CENTER);
        lblFecha.setFont(lblFecha.getFont().deriveFont(20f));
        panelTicket.add(lblFecha, BorderLayout.SOUTH);

        contenido.add(panelTicket, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
	}

}
