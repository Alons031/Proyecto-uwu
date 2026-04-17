/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Acer Aspire 3
 */
public class NuevoBoton extends JButton {
    private Color colorBtnNormal = new Color(56,79,107);
    private Color colorTxtNormal = new Color(255,191,191);
    
    public NuevoBoton(){
        this.setText("Opciones avanzadas");
        this.setFont(new Font("Arial",Font.BOLD,10));
        this.setBackground(colorBtnNormal);
        this.setForeground(colorTxtNormal);
        this.setSize(200,60);
        this.setPreferredSize(new Dimension(100,60));
        this.setIcon(new ImageIcon(getClass().getResource("opcionAvanzadas.png")));
    
    }
    
}
