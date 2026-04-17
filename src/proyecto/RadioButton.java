/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 *
 * @author Acer Aspire 3
 */
public class RadioButton extends JRadioButton {
    private Color colorTxt = new Color(70,79,107);
    public RadioButton(){
        this.setRolloverEnabled(false);
        this.setBackground(colorTxt);
        this.setIcon(new ImageIcon(getClass().getResource("buttonRojo.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("buttonVerde.png")));
        this.setFont(new Font("Arial",Font.PLAIN,16));
    }
    
}
