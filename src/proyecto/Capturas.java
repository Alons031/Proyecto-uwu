package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Capturas extends JPanel {
    
    private JLabel labelCaptura;

    public Capturas() {
        // Configuramos el diseño del panel
        setLayout(new BorderLayout());
        setBackground(new Color(204, 204, 204));
        
        // Creamos la etiqueta que mostrará la imagen
        labelCaptura = new JLabel("", SwingConstants.CENTER);
        add(labelCaptura, BorderLayout.CENTER);
    }

    // Método que llamaremos desde la ventana principal
    public void mostrarCaptura(String zona, int ancho, int alto) {
        String rutaImagen = "";
        
        switch (zona) {
            case "Patio": rutaImagen = "/proyecto/patio1.jpg"; break;
            case "Sala": rutaImagen = "/proyecto/sala1.jpg"; break;
            case "Comedor": rutaImagen = "/proyecto/comedor1.jpg"; break;
        }

        try {
            java.net.URL imgUrl = getClass().getResource(rutaImagen);
            // Validamos que la imagen exista y el panel ya tenga un tamaño
            if (imgUrl != null && ancho > 0 && alto > 0) {
                ImageIcon iconoOriginal = new ImageIcon(imgUrl);
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                labelCaptura.setIcon(new ImageIcon(imgEscalada));
                labelCaptura.setText(""); // Quitamos el texto de error si lo había
            } else {
                labelCaptura.setIcon(null);
                labelCaptura.setText("Falta agregar la imagen: " + rutaImagen);
            }
        } catch (Exception e) {
            labelCaptura.setText("Error al cargar la imagen de la zona: " + zona);
        }
    }
}
