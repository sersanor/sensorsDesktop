/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uipackage;

import static com.intel.bluetooth.RemoteDeviceHelper.readRSSI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author sersanor
 */
public class MainFrame extends javax.swing.JFrame {

    Grafica g = null;
    // SENSORS CONSTANTS
    static final int ACCELEROMETER = 1;
    static final int LIGHT = 2;
    static final int ORIENTATION = 3;
    static final int PROXIMITY = 4;
    static final int TEMPERATURE = 5;
    static final int GYROSCOPE = 6;
    static final int SOUND = 7;
    static final int MAGNETIC = 8;
    static final int PRESSURE = 9;
    public int sensorNumber = 0;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        console.getDocument().addDocumentListener(new DocumentListener() { // LISTENER EDITTEXT

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (g != null) { // IF NOT GRAPH WINDOW CREATED DONT DO NOTHING
                    try {
                        //TEXTO MODIFICADO
                        int last = console.getLineCount() - 1;
                        String[] lines = console.getText().split("\\n");
                        g.updateData(lines[last - 1], sensorNumber);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        s_status = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        s_name = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        s_addr = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        d_name = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        d_addr = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        sensor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("FMM DESKTOP APP");

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("CON. STATUS:");

        s_status.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        s_status.setForeground(new java.awt.Color(255, 0, 0));
        s_status.setText("DISCONNECTED");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("SERVER NAME:");

        s_name.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        s_name.setText("SERVER_NAME.");
        s_name.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("SERVER ADDR:");

        s_addr.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        s_addr.setText("00:00:00:00:00.");

        jButton1.setText("SHOW GRAPH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("DEVICE NAME:");

        d_name.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        d_name.setText("DEVICE_NAME.");
        d_name.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("DEVICE ADDR:");

        d_addr.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        d_addr.setText("00:00:00:00:00.");

        label1.setText("label1");

        sensor.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        sensor.setForeground(new java.awt.Color(0, 0, 102));
        sensor.setText("SENSORNAME");
        sensor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(d_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(s_name, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(s_addr, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(d_name, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(s_status)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(134, 134, 134))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(sensor, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(s_addr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(s_name))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(s_status))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(d_addr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(d_name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(sensor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (sensorNumber != 0) {
            g = new Grafica("Grafica Tiempo Real", sensorNumber);
            g.showGrafica();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void run() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }

    public void writeConsole(String s) {
        // SET THE SENSOR ID
        String delims = "[ ]+";
        String[] tokens = s.split(delims);
        if (tokens.length > 5) {
            sensorNumber = Integer.parseInt(tokens[7]);
        } else {
            sensorNumber = Integer.parseInt(tokens[3]);
        }
        //ELIMINAR SENSOR
        String tmp = s.substring(0, s.length() - 7); // ELIMINAMOS INFO SENSOR
        this.console.append(tmp+"\n");//AÑADIMOS FINAL LINEA
        console.setCaretPosition(console.getDocument().getLength());
        setSensorName(sensorNumber);
    }

    public void clearConsole() {
        this.console.setText("");
        if (g != null) {
            g.frame.setVisible(false);
        }
        this.d_addr.setText("00:00:00:00:00.");
        this.d_name.setText("DEVICE_NAME");
        this.sensorNumber = 0;
        this.g = null;
        this.sensor.setText("NO-SENSOR");
    }

    public void setDevName(String s) {
        this.d_name.setText(s);
    }

    public void setDevAdd(String s) {
        this.d_addr.setText(s);
    }

    public void setServerAdd(String s) {
        this.s_addr.setText(s);
    }

    public void setServerName(String s) {
        this.s_name.setText(s);
    }

    public void setSensorName(int n) {
        switch(n){
            case 0: this.sensor.setText("NO-SENSOR"); break;
            case 1: this.sensor.setText("ACCELEROMETER"); break;
            case 2: this.sensor.setText("LIGHTSENSOR"); break;
            case 3: this.sensor.setText("ORIENTATION"); break;
            case 4: this.sensor.setText("PROXIMITY"); break;
            case 5: this.sensor.setText("TEMPERATURE"); break;
            case 6: this.sensor.setText("GYROSCOPE"); break;
            case 7: this.sensor.setText("SOUNDMETER"); break;
            case 8: this.sensor.setText("FIELDMETER"); break;
            case 9: this.sensor.setText("PRESSURE"); break;
        }
    }

    public void setStatus(boolean s) {
        if (s) {
            this.s_status.setText("CONNECTED");
            this.s_status.setForeground(Color.blue);
        } else {
            this.s_status.setText("DISCONNECTED");
            this.s_status.setForeground(Color.red);
        }
    }
    private JFreeChart Grafica;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea console;
    private javax.swing.JLabel d_addr;
    private javax.swing.JLabel d_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JLabel s_addr;
    private javax.swing.JLabel s_name;
    private javax.swing.JLabel s_status;
    private javax.swing.JLabel sensor;
    // End of variables declaration//GEN-END:variables

    public class Grafica { // ACELEROMETRO

        DefaultCategoryDataset data;
        JFreeChart chart;
        ChartFrame frame;
        private int X, Y, Z;
        private static final String datosx = "X";
        private static final String datosy = "Y";
        private static final String datosz = "Z";

        public Grafica(String title, int sen) {
            setName("GRAPH THREAD");
            X = Y = Z = 0;
            data = new DefaultCategoryDataset();
            if (sen == ACCELEROMETER || sen == ORIENTATION || sen == GYROSCOPE || sen == MAGNETIC) {
                // X
                data.addValue(0.0, datosx, Integer.toString(X));
                // Y
                data.addValue(0.0, datosy, Integer.toString(Y));
                // Z
                data.addValue(0.0, datosz, Integer.toString(Z));
            } else {
                data.addValue(0.0, datosx, Integer.toString(X));
            }

            chart = ChartFactory.createLineChart(
                    title, // chart title
                    "Seconds", // domain axis label
                    "Value", // range axis label
                    data, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );
            chart.setBackgroundPaint(Color.white);
            final CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.white);

            // customise the range axis...
            final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            rangeAxis.setAutoRangeIncludesZero(true);
            final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

            renderer.setSeriesStroke(
                    0, new BasicStroke(
                            2.0f
                    )
            );
            renderer.setSeriesStroke(
                    1, new BasicStroke(
                            2.0f
                    )
            );
            renderer.setSeriesStroke(
                    2, new BasicStroke(
                            2.0f
                    )
            );
        }

        public void showGrafica() {
            frame = new ChartFrame("GRAPH WINDOW", chart);
            frame.pack();
            frame.setVisible(true);
            frame.setSize(450, 500);

        }

        public void updateData(String values, int sen) throws InterruptedException {
            if (values.length() > 1) {
                String delims = "[ ]+";
                values = values.replace(",", ".");
                String[] tokens = values.split(delims);
                if (sen == ACCELEROMETER || sen == ORIENTATION || sen == GYROSCOPE || sen == MAGNETIC) {
                    data.addValue(Double.parseDouble(tokens[1]), datosx, Integer.toString(X + 1));
                    data.addValue(Double.parseDouble(tokens[3]), datosy, Integer.toString(Y + 1));
                    data.addValue(Double.parseDouble(tokens[5]), datosz, Integer.toString(Z + 1));
                    X += 1;
                    Y += 1;
                    Z += 1;
                } else {
                    data.addValue(Double.parseDouble(tokens[1]), datosx, Integer.toString(X + 1));
                    X += 1;
                }
                //frame.repaint();
            }
        }
    }

}
