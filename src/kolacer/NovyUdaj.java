/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kolacer;

import java.awt.Color;

/**
 *
 * @author Kůň
 */
public class NovyUdaj extends javax.swing.JDialog {
    
    Udaj result;
    Color barva;
    /**
     * Creates new form NovyUdaj
     * @param parent
     * @param modal
     * @param u
     */
    public NovyUdaj(java.awt.Frame parent, boolean modal, Udaj u) {
        super(parent, modal);
        this.setResizable(false);
        this.setTitle(u==null?"Nový údaj":"Úprava údaje");
        result = u;
        initComponents();
        this.setLocation(parent.getX()+parent.getWidth()/2-this.getWidth()/2, parent.getY()+parent.getHeight()/2-this.getHeight()/2);
        if(u!=null){
            jF_jmeno.setText(u.getHodnota());
            jF_pocet.setText(u.getPocet()+"");
            barva = u.getBarva();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBut_ok = new javax.swing.JButton();
        jF_jmeno = new javax.swing.JTextField();
        jF_pocet = new javax.swing.JTextField();
        jBut_zrusit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBut_ok.setText("Potvrdit");
        jBut_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBut_okActionPerformed(evt);
            }
        });

        jF_jmeno.setText("Název údaje");

        jF_pocet.setText("0");

        jBut_zrusit.setText("Zrušit");
        jBut_zrusit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBut_zrusitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jF_jmeno, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jF_pocet, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBut_zrusit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBut_ok)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jF_jmeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jF_pocet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBut_ok)
                    .addComponent(jBut_zrusit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBut_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBut_okActionPerformed
        boolean uspech = vytvoreniUdaje();
        if(uspech)
            this.setVisible(false);
    }//GEN-LAST:event_jBut_okActionPerformed

    private void jBut_zrusitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBut_zrusitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jBut_zrusitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBut_ok;
    private javax.swing.JButton jBut_zrusit;
    private javax.swing.JTextField jF_jmeno;
    private javax.swing.JTextField jF_pocet;
    // End of variables declaration//GEN-END:variables
    
    public Udaj showDialog(){
        this.setVisible(true);
        return result;
    }
    
    private boolean vytvoreniUdaje() {
        try{
            int pocet = Integer.parseInt(jF_pocet.getText());
            result = new Udaj(jF_jmeno.getText(), pocet, barva);
            return true;
        }
        catch(NumberFormatException e){
            System.err.println("Chyba pri prevadeni textu na cislo");
            return false;
        }
    }
}