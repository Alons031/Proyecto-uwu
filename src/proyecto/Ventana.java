
package proyecto;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPanel panel = new JPanel() {
           
        };

        panel.setLayout(new FlowLayout());

        JLabel titulo = new JLabel("Bienvenido");
        panel.add(titulo);

        JTextField txtUsuario = new JTextField(12);
        panel.add(txtUsuario);

        JPasswordField txtContraseña = new JPasswordField(12);
        panel.add(txtContraseña);

        JButton btnIngresar = new JButton("Entrar");
        panel.add(btnIngresar);

        
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
