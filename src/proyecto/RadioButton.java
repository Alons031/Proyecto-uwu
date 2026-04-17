/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 *
 * @author Acer Aspire 3
 */
public class RadioButton extends JRadioButton {
   
    public RadioButton(){
        this.setRolloverEnabled(false);
        this.setIcon(new ImageIcon(getClass().getResource("buttonRojo.png")));
        this.setSelectedIcon(new ImageIcon(getClass().getResource("buttonVerde.png")));
        this.setFont(new Font("Arial",Font.PLAIN,16));
    }
    
}
