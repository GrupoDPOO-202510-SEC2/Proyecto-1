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
    private JPanel panelUsuario;
    private JPanel panelUsuarioLogin;
    private JPanel panelUsuarioRegister;  
    private CardLayout cardLayout;

    public Ventana() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        panelInicial = new JPanel(new GridBagLayout());
        panelAdmin = new JPanel(new GridBagLayout());
        panelUsuario = new JPanel(new GridBagLayout());
        panelUsuarioLogin = new JPanel(new GridBagLayout());
        panelUsuarioRegister = new JPanel(new GridBagLayout());
        

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
                cardLayout.show(getContentPane(), "Panel Menu Usuario");
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
        JTextField tfALogin = new JTextField();
        tfALogin.setColumns(20);
        panelAdmin.add(tfALogin, espacioFields);

        panelAdmin.add(new JLabel("Contraseña:"), espacioLables);
        JTextField tfAContrsena = new JTextField(100);
        tfAContrsena.setColumns(20);
        panelAdmin.add(tfAContrsena, espacioFields);

        JButton iniciarSesion = new JButton("Iniciar sesión");
        panelAdmin.add(iniciarSesion, espacioFields);

        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String login = tfALogin.getText();
            	String contrasena = tfAContrsena.getText();
            	
            	if (login.equals("sopa") && contrasena.equals("sopa")) {
	                new VentanaAdministrador();
	                cardLayout.show(getContentPane(), "Panel Inicial");
	                tfALogin.setText("");
	                tfAContrsena.setText("");
            	}else {
            		JOptionPane.showMessageDialog(null, "Error de Autenticacion", "Contraseña o usuario incorrecta", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            }
        });
        
//---------------------------------menu usuario---------------------------------------//

        
        JButton botonUsuarioLogin = new JButton("Inicir Sesion");
        botonUsuarioLogin.setPreferredSize(new Dimension(150, 30));
        
        JButton botonUsuarioSignIn = new JButton("Registrame");
        botonUsuarioSignIn.setPreferredSize(new Dimension(150, 30));

        botonUsuarioLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Panel Usuario login");
            }
        });

        botonUsuarioSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Panel Usuario register");
            }
        });

        panelUsuario.add(botonUsuarioLogin, espacioFields);
        panelUsuario.add(botonUsuarioSignIn, espacioFields);
        
//---------------------------------menu usuario login---------------------------------------//
       
        
        panelUsuarioLogin.add(new JLabel("Login:"), espacioLables);
        JTextField tfULoginLogin = new JTextField();
        tfULoginLogin.setColumns(20);
        panelUsuarioLogin.add(tfULoginLogin, espacioFields);

        panelUsuarioLogin.add(new JLabel("Contraseña:"), espacioLables);
        JTextField tfUContrsenaLogin = new JTextField(100);
        tfUContrsenaLogin.setColumns(20);
        panelUsuarioLogin.add(tfUContrsenaLogin, espacioFields);

        JButton iniciarSesionULogin = new JButton("Iniciar sesión");
        panelUsuarioLogin.add(iniciarSesionULogin, espacioFields);

        iniciarSesionULogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String login = tfULoginLogin.getText();
            	String contrasena = tfUContrsenaLogin.getText();
            	if(Usuario.parque.usuarios.containsKey(login)) {
            		Usuario usuario = Usuario.parque.usuarios.get(login);
            		if ( usuario.getPassword().equals(contrasena)) {
    	                cardLayout.show(getContentPane(), "Panel Inicial");
            			new VentanaUsuario(usuario);
            		}
            	}else {
            		JOptionPane.showMessageDialog(null, "Error de Autenticacion", "Contraseña o usuario incorrecta", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            }
        });
        
//---------------------------------menu usuario register---------------------------------------//
        
        
        
        
        
        
        panelUsuarioRegister.add(new JLabel("Nombre:"), espacioLables);
        JTextField tfUNombreRegister = new JTextField(100);
        tfUNombreRegister.setColumns(20);
        panelUsuarioRegister.add(tfUNombreRegister, espacioFields);
        
        panelUsuarioRegister.add(new JLabel("Login:"), espacioLables);
        JTextField tfULoginRegister = new JTextField();
        tfULoginRegister.setColumns(20);
        panelUsuarioRegister.add(tfULoginRegister, espacioFields);

        panelUsuarioRegister.add(new JLabel("Contraseña:"), espacioLables);
        JTextField tfUContrsenaRegister = new JTextField(100);
        tfUContrsenaRegister.setColumns(20);
        panelUsuarioRegister.add(tfUContrsenaRegister, espacioFields);
        
        panelUsuarioRegister.add(new JLabel("Altura (cm):"), espacioLables);
        JTextField tfUAlturaRegister = new JTextField(100);
        tfUAlturaRegister.setColumns(20);
        panelUsuarioRegister.add(tfUAlturaRegister, espacioFields);
        
        panelUsuarioRegister.add(new JLabel("Peso (kg):"), espacioLables);
        JTextField tfUPesoRegister = new JTextField(100);
        tfUPesoRegister.setColumns(20);
        panelUsuarioRegister.add(tfUPesoRegister, espacioFields);
        
        

        JButton iniciarSesionURegister = new JButton("Registrarme");
        panelUsuarioRegister.add(iniciarSesionURegister, espacioFields);

        iniciarSesionURegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String login = tfULoginRegister.getText();
            	String contrasena = tfUContrsenaRegister.getText();
            	String nombre = tfUNombreRegister.getText();
            	String peso = tfUPesoRegister.getText();
            	String altura = tfUAlturaRegister.getText();
            	
            	if(Usuario.parque.usuarios.containsKey(login)) {
            		JOptionPane.showMessageDialog(null, "Este login ya existe", "Error de Autenticacion", JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		if (isInteger(peso) && isInteger(altura)) {
            			Usuario usuario = new Usuario(nombre, login, contrasena, Integer.parseInt(altura), Integer.parseInt(peso));
            			Usuario.parque.usuarios.put(login, usuario);
            			JOptionPane.showMessageDialog(null, "Se creo correctamente su usuario", "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
    	                cardLayout.show(getContentPane(), "Panel Inicial");
            			new VentanaUsuario(usuario);
            		}else {
            			JOptionPane.showMessageDialog(null, "La altura y el peso tienen que ser enteros", "Error de Formato", JOptionPane.INFORMATION_MESSAGE);
            		}
            		
            	}
            }
        });
        
        
        
        
        add(panelInicial, "Panel Inicial");
        add(panelAdmin, "Panel Admin");
        add(panelUsuario, "Panel Menu Usuario");
        add(panelUsuarioLogin, "Panel Usuario login");
        add(panelUsuarioRegister, "Panel Usuario register");
        
         
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
    
    
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}





