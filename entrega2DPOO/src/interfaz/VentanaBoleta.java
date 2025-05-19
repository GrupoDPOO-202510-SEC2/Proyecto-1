package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class VentanaBoleta extends JFrame{
	public VentanaBoleta(String idboleta, String tipo, String fecha, double valor, ImageIcon iconoExclusividad, ImageIcon imagenCentro, ImageIcon qr) {
		super("Boleta");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);
        JPanel panelStub = new JPanel();
        panelStub.setPreferredSize(new Dimension(200, 0));
        panelStub.setLayout(new BoxLayout(panelStub, BoxLayout.Y_AXIS));
        panelStub.setBorder(new CompoundBorder(
            new MatteBorder(0, 0, 0, 2, Color.GRAY),
            new EmptyBorder(10, 10, 10, 10)
        ));
        panelStub.add(new JLabel("No. " + idboleta));
        panelStub.add(Box.createVerticalStrut(20));
        panelStub.add(new JLabel("Tiquete: " + tipo));
        panelStub.add(Box.createVerticalStrut(5));
        panelStub.add(new JLabel("Fecha Expedición: " + fecha));
        panelStub.add(Box.createVerticalStrut(5));
        panelStub.add(new JLabel(String.format("Valor: $%,.2f", valor)));
        panelStub.add(Box.createVerticalGlue());
        panelStub.add(new JLabel(iconoExclusividad));
        contenido.add(panelStub, BorderLayout.WEST);
        JPanel panelTicket = new JPanel(new BorderLayout());
        panelTicket.setBackground(Color.CYAN);
        JLabel lblTitulo = new JLabel("Parque de Atracciones de los Alpes", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(24f).deriveFont(Font.BOLD));
        panelTicket.add(lblTitulo, BorderLayout.NORTH);
        JLabel lblImagen = new JLabel(imagenCentro);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelTicket.add(lblImagen, BorderLayout.CENTER);
        JLabel lblQR = new JLabel(qr);
        lblQR.setBorder(new EmptyBorder(10,10,10,10));
        panelTicket.add(lblQR, BorderLayout.EAST);
        JLabel lblFecha = new JLabel(fecha,SwingConstants.CENTER);
        lblFecha.setFont(lblFecha.getFont().deriveFont(20f));
        panelTicket.add(lblFecha, BorderLayout.SOUTH);

        contenido.add(panelTicket, BorderLayout.CENTER);
    }

}
