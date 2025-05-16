package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class Ventana extends JFrame {

    public Ventana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        JButton botonUsuario       = new JButton("Usuario");
        JButton botonAdministrador = new JButton("Administrador");

        botonUsuario.setForeground(Color.BLACK);
        botonAdministrador.setForeground(Color.BLACK);
        botonUsuario.setBackground(Color.WHITE);
        botonAdministrador.setBackground(Color.WHITE);
        panel.add(botonUsuario);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(botonAdministrador);
        add(panel, BorderLayout.CENTER);
        setSize(400, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaAdministrador();
        
    }
}






