

package proyecto;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Proyecto {

   
    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(()->{
            Ventana ventana = new Ventana ();
            ventana.setVisible(true);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(600,400);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(true); 
        });
         
    }
    
}

