package proyecto;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * EQUIPO 2:
 *      Cruz Paz Imanol
 *      Espino Espino Lisandro
 *      Gonzalez Garcial Michelle
 *      Ortega Zarate Alonso
 *      Reyes Perez Jose Eduardo
 */
public class Proyect extends javax.swing.JFrame {
    Opciones_Avanzadas abrir = new Opciones_Avanzadas();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Proyect.class.getName());
    
    /// Card layout para mostrar solo un panel a la vez   ///
    private CardLayout cardLayoutCentral;
    ////        estado de la alarma falso= desactivada  true= activa
    private boolean seguridad = false;
    //      cuadros verde y rojo
    private List<JLabel> indicadorSeguridad = new ArrayList<>();

    private String password;
    
    private Capturas panelCapturas = new Capturas();
    private boolean viendoCaptura = false;

    public Proyect(String password) {
        this.password = password;
        initComponents();
        
        this.setLocationRelativeTo(null);
        /// metodos del panel central ///
        PanelCentral();
        EventosLista();
        // metodo del ctrl A
        AtajoTeclado();
        
        Timer timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
                jLabel3.setText(horaActual);
            }
        });
        timer.start();
    }
    
    
    private boolean verificarAcceso(){
  
    String pass = JOptionPane.showInputDialog("Ingresa la contraseña:");

    if (pass == null) return false;

    if (pass.equals(password)) {
        return true;
    } else {
        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
        return false;
        }
    
    }
    

    
    
    /////       metodo pra asignar el cardlayout al panel cwntral   /////
    private void PanelCentral() {
        cardLayoutCentral = new CardLayout();
        jPanel3.setLayout(cardLayoutCentral);

        ////        cartas de las zonas para el panel        ////
        jPanel3.add(PanelPatio(), "Patio");
        jPanel3.add(PanelSala(), "Sala");
        jPanel3.add(PanelComedor(), "Comedor");
        
        jPanel3.add(panelCapturas, "VistaCaptura");
    }
    
    /////       Metodo de la lista      /////
    private void EventosLista() {
        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String seleccion = jList1.getSelectedValue();
                    if (seleccion != null && !seleccion.isEmpty()) {
                        cardLayoutCentral.show(jPanel3, seleccion);
                    }
                }
            }
        });
    }
    
    /////       METODO DEL CTRL+q       /////
    private void AtajoTeclado() {
        // combinación de las teclas CTRL+q
        KeyStroke ctrlA = KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK
        );
        
        // Lo registramos en la ventana principal
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlA, "toggleSeguridad");
        getRootPane().getActionMap().put("toggleSeguridad", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // cambiamos el estado si era false ahora es true y viceversa
                seguridad = !seguridad;
                
                // asigna el color rojo si está activad veerde si no.
                Color colorNuevo = seguridad ? Color.RED : Color.GREEN;
                
                // Actualizamos todos los foquitos de todas las habitaciones
                for (JLabel indicador : indicadorSeguridad) {
                    indicador.setBackground(colorNuevo);
                }
            }
        });
    }
    
    // --- MÉTODO PARA CREAR LA COLUMNA DE SEGURIDAD EN CADA ZONA---
    private JPanel ColumnaSeguridad() {
        JPanel panelSeguridad = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSeguridad.setBackground(new Color(153, 153, 153));
        panelSeguridad.setBorder(BorderFactory.createTitledBorder("Seguridad (Ctrl + q):"));
        
        panelSeguridad.add(new javax.swing.JLabel("Estado:"));
        
        // Creamos el foquit 
        JLabel etiquetaEstado = new JLabel("      "); 
        etiquetaEstado.setOpaque(true); 
        etiquetaEstado.setBackground(seguridad ? Color.RED : Color.GREEN);
        etiquetaEstado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
        indicadorSeguridad.add(etiquetaEstado);
        
        panelSeguridad.add(etiquetaEstado);
        return panelSeguridad;
    }
    
    
    
    
    
    private JPanel PanelPatio() {
        JPanel panelBase = new JPanel(new BorderLayout());
        panelBase.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Patio", 0, 2, new Font("Segoe UI", 1, 18)));

        JPanel panelColumnas = new JPanel(new GridLayout(1, 3, 15, 0));
        panelColumnas.setBackground(new Color(204, 204, 204));
        panelColumnas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Luces Exteriores
        JPanel panelLuces = new JPanel(new GridLayout(0, 1));
        panelLuces.setBackground(new Color(153, 153, 153));
        panelLuces.setBorder(BorderFactory.createTitledBorder("Iluminación Exterior:"));
        panelLuces.add(new JCheckBox("Reflectores (Movimiento)"));
        panelLuces.add(new JCheckBox("Faroles del Camino"));

        // 2. Riego
        JPanel panelRiego = new JPanel(new GridLayout(0, 1));
        panelRiego.setBackground(new Color(153, 153, 153));
        panelRiego.setBorder(BorderFactory.createTitledBorder("Sistema de Riego:"));
        panelRiego.add(new JCheckBox("Aspersores Césped"));
        panelRiego.add(new JCheckBox("Riego por Goteo"));

        panelColumnas.add(panelLuces);
        panelColumnas.add(panelRiego);
        panelColumnas.add(ColumnaSeguridad()); // 3. Seguridad

        panelBase.add(panelColumnas, BorderLayout.CENTER);
        return panelBase;
    }
    
    
    // Meotodo del panel sala
    private JPanel PanelSala() {
        JPanel panelBase = new JPanel(new BorderLayout());
        panelBase.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Sala", 0, 2, new Font("Segoe UI", 1, 18)));

        JPanel panelColumnas = new JPanel(new GridLayout(1, 3, 15, 0));
        panelColumnas.setBackground(new Color(204, 204, 204));
        panelColumnas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //  Luces Sala
        JPanel panelLuces = new JPanel(new GridLayout(0, 1));
        panelLuces.setBackground(new Color(153, 153, 153));
        panelLuces.setBorder(BorderFactory.createTitledBorder("Luces Sala:"));
        panelLuces.add(new JCheckBox("Luz Principal"));
        panelLuces.add(new JCheckBox("Luces led"));

        // 2. tv y sonido
        JPanel panelExtra = new JPanel(new GridLayout(0, 1));
        panelExtra.setBackground(new Color(153, 153, 153));
        panelExtra.setBorder(BorderFactory.createTitledBorder("Multimedia:"));
        panelExtra.add(new JCheckBox("Smart TV"));
        panelExtra.add(new JCheckBox("Audio"));

        panelColumnas.add(panelLuces);
        panelColumnas.add(panelExtra);
        panelColumnas.add(ColumnaSeguridad());

        panelBase.add(panelColumnas, java.awt.BorderLayout.CENTER);
        return panelBase;
    }
    
    /// MÉTODO DEL COMEDOR
    private javax.swing.JPanel PanelComedor() {
        JPanel panelBase = new JPanel(new BorderLayout());
        panelBase.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Comedor", 0, 2, new Font("Segoe UI", 1, 18)));

        JPanel panelColumnas = new JPanel(new GridLayout(1, 3, 15, 0));
        panelColumnas.setBackground(new Color(204, 204, 204));
        panelColumnas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Luces Comedor
        JPanel panelLuces = new JPanel(new GridLayout(0, 1));
        panelLuces.setBackground(new Color(153, 153, 153));
        panelLuces.setBorder(BorderFactory.createTitledBorder("Luces Comedor:"));
        panelLuces.add(new JCheckBox("Lampara de techo"));
        panelLuces.add(new JCheckBox("Luces de vitrina "));

        // 2. Cortinas
        JPanel panelCortinas = new JPanel(new GridLayout(0, 1));
        panelCortinas.setBackground(new Color(153, 153, 153));
        panelCortinas.setBorder(BorderFactory.createTitledBorder("Cortinas Inteligentes:"));
        ButtonGroup bgCortinas = new ButtonGroup();
        JRadioButton rbCerradas = new JRadioButton("Cerradas", true);
        JRadioButton rbAbiertas = new JRadioButton("Abiertas");
        bgCortinas.add(rbCerradas); bgCortinas.add(rbAbiertas);
        panelCortinas.add(rbCerradas); panelCortinas.add(rbAbiertas);

        panelColumnas.add(panelLuces);
        panelColumnas.add(panelCortinas);
        panelColumnas.add(ColumnaSeguridad()); // 3. Seguridad

        panelBase.add(panelColumnas, java.awt.BorderLayout.CENTER);
        return panelBase;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Smart House");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("V 1.0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));

        jList1.setBackground(new java.awt.Color(153, 153, 153));
        jList1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jList1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Patio", "Comedor", "Sala", "" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Zona:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setText("Capturas");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Opciones Avanzadas");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton1)
                .addGap(84, 84, 84)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto/camara1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificarAcceso()){
        abrir.setVisible(true); 
        }
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (verificarAcceso()){
            String seleccion = jList1.getSelectedValue();
            if (seleccion == null || seleccion.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una zona primero.");
            return;
        }

        if (!viendoCaptura) {
            // Le mandamos la zona y el tamaño a nuestra nueva clase
            
            panelCapturas.mostrarCaptura(seleccion, jPanel3.getWidth(), jPanel3.getHeight());
            
            // Mostramos la carta
            cardLayoutCentral.show(jPanel3, "VistaCaptura");
            jButton2.setText("Volver");
            viendoCaptura = true;
            
        } else {
            // Regresar a la vista normal
            cardLayoutCentral.show(jPanel3, seleccion);
            jButton2.setText("Capturas");
            viendoCaptura = false;
        }
        
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        {
            System.exit(0);
            
        
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
