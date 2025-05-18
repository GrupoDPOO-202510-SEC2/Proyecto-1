package interfaz;

import javax.swing.*;

import control.Persistencia;
import usuario.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana extends JFrame {

    private JPanel panelInicial;
    private JPanel panelAdmin;
    private CardLayout cardLayout;

    public Ventana() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        panelInicial = new JPanel(new GridBagLayout());
        panelAdmin = new JPanel(new GridBagLayout());

        GridBagConstraints espacioFields = new GridBagConstraints();
        espacioFields.gridx = 0;
        espacioFields.gridy = GridBagConstraints.RELATIVE;
        espacioFields.insets = new Insets(10, 0, 10, 0);
        espacioFields.anchor = GridBagConstraints.CENTER;

        GridBagConstraints espacioLables = new GridBagConstraints();
        espacioLables.gridx = 0;
        espacioLables.gridy = GridBagConstraints.RELATIVE;
        espacioLables.insets = new Insets(0, 10, 0, 10);
        espacioLables.anchor = GridBagConstraints.CENTER;

        
//----------------------Configuracion del panel inicial-----------------------------//

        JButton botonUsuario = new JButton("Usuario");
        botonUsuario.setPreferredSize(new Dimension(150, 30));
        
        JButton botonAdministrador = new JButton("Administrador");
        botonAdministrador.setPreferredSize(new Dimension(150, 30));

        botonUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Panel Usuario");
            }
        });

        botonAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Panel Admin");
            }
        });

        panelInicial.add(botonUsuario, espacioFields);
        panelInicial.add(botonAdministrador, espacioFields);

      
//---------------------------------Login admin---------------------------------------//
      
        panelAdmin.add(new JLabel("Login:"), espacioLables);
        JTextField tfLogin = new JTextField();
        tfLogin.setColumns(20);
        panelAdmin.add(tfLogin, espacioFields);

        panelAdmin.add(new JLabel("Contraseña:"), espacioLables);
        JTextField tfContrsena = new JTextField(100);
        tfContrsena.setColumns(20);
        panelAdmin.add(tfContrsena, espacioFields);

        JButton iniciarSesion = new JButton("Iniciar sesión");
        panelAdmin.add(iniciarSesion, espacioFields);

        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
                new VentanaAdministrador();
                cardLayout.show(getContentPane(), "Panel Inicial");
            }
        });

        add(panelInicial, "Panel Inicial");
        add(panelAdmin, "Panel Admin");

        setSize(400, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    Ventana.this,
                    "¿Quieres guardar antes de salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                	try {
            			Persistencia.guardarParque(Usuario.parque);
            		} catch (Exception ex) {
            			// TODO Auto-generated catch block
            			ex.printStackTrace();
            		}
                    System.exit(0); // Si deseas finalizar la aplicación
                } else if
                (confirm == JOptionPane.NO_OPTION) {
                    System.exit(0); // Si deseas finalizar la aplicación
                }
            }
        });
        
    }

}





