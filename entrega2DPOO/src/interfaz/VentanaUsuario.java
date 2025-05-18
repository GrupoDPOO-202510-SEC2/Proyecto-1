package interfaz;

import javax.swing.*;

import usuario.Usuario;

public class VentanaUsuario extends JFrame{
	private JPanel panelUsuario;
	private Usuario usuario;
	
	public VentanaUsuario(Usuario usuario) {
		
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
}
