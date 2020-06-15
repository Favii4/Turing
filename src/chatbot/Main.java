package chatbot;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.applet.AudioClip;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends javax.swing.JFrame {

    String pregunta, respuesta,preguntagenerada;
    boolean reproducciendo=false;
     AudioClip sonido1, sonido2, alive;
     
    
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        texto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JTextArea();
        online = new javax.swing.JLabel();
        Namebot = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enviar.setBackground(new java.awt.Color(255, 255, 255));
        enviar.setText("enviar");
        enviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoActionPerformed(evt);
            }
        });

        pantalla.setEditable(false);
        pantalla.setColumns(20);
        pantalla.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 12)); // NOI18N
        pantalla.setRows(5);
        jScrollPane1.setViewportView(pantalla);

        online.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Namebot.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Namebot.setText("Gabi");

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Namebot)
                        .addGap(242, 242, 242))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(texto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(enviar))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 239, Short.MAX_VALUE)
                .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(222, 222, 222))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Namebot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviar)
                    .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
        pregunta=texto.getText();
        respuesta=(new BuscaDatos().translate(texto.getText()));
        preguntagenerada=(new BuscaDatos().translate(generarpregunta()));
        pantalla.append("Yo: "+texto.getText() +"\n");
        if (respuesta.equalsIgnoreCase("Vale")){
        pantalla.append("Gabi: Me enseñas que debo responder a: '"+pregunta+"' por favor?");
        String respuestUsuario= JOptionPane.showInputDialog("Me enseñas?(si/no)");
        if (respuestUsuario.equalsIgnoreCase("si")){
             String respuestUsuarioPregunta= JOptionPane.showInputDialog("Que responder a '"+pregunta+"'");
             Leer aprender =new Leer();
             String nuevapalabra=aprender.preguntanueva(pregunta,respuestUsuarioPregunta);
             aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);   
        }
    }
        texto.setText("");
        animacionEscribir(respuesta);
        int probabilidad=mitadProbabilidad();
        //System.out.println(probabilidad);
        if(probabilidad>5){
        //  System.out.println("entra");
             animacionpregunta(preguntagenerada);
             if (preguntagenerada.equalsIgnoreCase("") && reproducciendo != true){
                 alive =java.applet.Applet.newAudioClip(getClass().getResource(""));
                 alive.play();
             }
             
        }   
        } catch (Exception e) {
        }
        }
        });
        hilo.start();

    }//GEN-LAST:event_enviarActionPerformed

    public String generarpregunta(){
        int numero;
        numero = (int) (Math.random() * 9) + 1;
        String preguntaAleatoria = Integer.toString(numero);
        String preguntacompletada= preguntaAleatoria+"p";
        return preguntacompletada;
    }
    public int mitadProbabilidad(){
         int numero;
        numero = (int) (Math.random() * 9) + 1;
        return numero;
    }
    public void animacionEscribir(String respuestaxd) throws InterruptedException, URISyntaxException, IOException{
        sonido1 =java.applet.Applet.newAudioClip(getClass().getResource("./pop.wav"));
        sonido2 =java.applet.Applet.newAudioClip(getClass().getResource(""));
        Thread.sleep(generarRandom());
        online.setForeground(Color.black);
        sonido2.play();
        online.setText("");
        Thread.sleep(generarRandom());
        online.setText("Escribiendo..."); 
        Thread.sleep(generarRandom());
        online.setText("");
        sonido1.play();
        pantalla.append("Gabi: "+ respuestaxd+"\n");
          if(pregunta.equalsIgnoreCase("reproducir musica")){
            reproducciendo=true;
              Desktop.getDesktop().browse(new URI(""));
          //  alive =java.applet.Applet.newAudioClip(getClass().getResource("./alive.wav"));
           // alive.play();
        }
        //  System.out.println(generarRandom());
    }
      public void animacionpregunta(String respuestaxd) throws InterruptedException{
        sonido1 =java.applet.Applet.newAudioClip(getClass().getResource("./pop.wav"));
        Thread.sleep(1500);
        online.setText("Escribiendo..."); 
        Thread.sleep(1000);
        online.setText("");
        sonido1.play();
        pantalla.append("Gabi: "+ respuestaxd+"\n");
    }
    
    public int generarRandom(){
        int numero;
        numero = (int) (Math.random() * 4000) + 1000;
        return numero;
        
    }
    public void fijarTexto(){
         pantalla.append("Maquina: "+ respuesta+"\n");
        texto.setText("");
    }
    private void textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_textoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String UsuarioPregunta= JOptionPane.showInputDialog("Ingrese pregunta");
         String respuestUsuarioPregunta= JOptionPane.showInputDialog("Que responder a '"+ UsuarioPregunta+"'");
         Leer aprender =new Leer();
         String nuevapalabra=aprender.preguntanueva(UsuarioPregunta,respuestUsuarioPregunta);
         aprender.guardar(aprender.leertxt("datos.txt"), nuevapalabra);   
    }//GEN-LAST:event_jButton1ActionPerformed

    public JButton enviarPresionado(){
        return enviar;
    }
    public void setPanatalla(){
        pantalla.append("Maquina: "+ respuesta+"\n");
    }
    public JTextArea regresaPantalla(){
        return pantalla;
    }
    

    public JTextField regresaTexto(){
        return texto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Namebot;
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel online;
    private javax.swing.JTextArea pantalla;
    private javax.swing.JTextField texto;
    // End of variables declaration//GEN-END:variables
}
