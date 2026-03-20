
package proyecto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Ventana extends JFrame {
    
   private static String passwordGuardada;
 

    public Ventana() {

   JPanel panel = new JPanel();
  
   panel.setLayout(new GridLayout(4, 1));
    panel.setBackground(Color.BLACK);
    
        JLabel titulo = new JLabel("BIENVENIDO");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);


        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelUsuario.setBackground(Color.black);

        JLabel usuario = new JLabel("Usuario:");
        usuario.setForeground(Color.WHITE);

        JTextField txtUsuario = new JTextField(12); 

        panelUsuario.add(usuario);
        panelUsuario.add(txtUsuario);


        JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelContraseña.setBackground(Color.black);

        JLabel contraseña = new JLabel("Contraseña:");
        contraseña.setForeground(Color.WHITE);

        JPasswordField txtContraseña = new JPasswordField(12);

        panelContraseña.add(contraseña);
        panelContraseña.add(txtContraseña);

        panel.add(titulo);
        panel.add(panelUsuario);
        panel.add(panelContraseña);

        JButton btnIngresar = new JButton("Entrar");
        JPanel panelBoton = new JPanel (new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(Color.lightGray);
        btnIngresar.setPreferredSize(new Dimension(120,30));
        panelBoton.add(btnIngresar);
        panel.add(panelBoton);

        btnIngresar.addActionListener(new ActionListener() {
         @Override
    public void actionPerformed(ActionEvent e) {

        String pass = new String(txtContraseña.getPassword());

        if (passwordGuardada == null) {
            passwordGuardada = pass;
            JOptionPane.showMessageDialog(null, "Contraseña guardada");
            abrirProyecto();
        } else {
            if (pass.equals(passwordGuardada)) {
                abrirProyecto();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            }
        }
    }        
        });

        add(panel);
    }

    private void abrirProyecto() {
        new Proyect(passwordGuardada).setVisible(true);
        
    }

 
    
}
