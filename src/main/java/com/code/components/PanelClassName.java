package com.code.components;

import java.awt.Container;
import java.awt.Dimension;

/**
 *
 * @author Gian
 */
public class PanelClassName extends javax.swing.JPanel {

    public PanelClassName() {
        initComponents();
    }

    public String getClassName() {
        return txtClassName.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prscn = new com.code.components.PanelRoundShadow();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        container = new javax.swing.JPanel();
        lbl = new javax.swing.JLabel();
        btnUpload = new javax.swing.JLabel();
        btnWc = new javax.swing.JLabel();

        setOpaque(false);

        prscn.setShadowColor(new java.awt.Color(0, 0, 0));
        prscn.setShadowOpacity(0.6F);
        prscn.setShadowSize(6);
        prscn.setShadowType(com.code.components.ShadowType.BOT);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setMinimumSize(new java.awt.Dimension(40, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/edit.png"))); // NOI18N

        txtClassName.setBackground(new java.awt.Color(255, 255, 255));
        txtClassName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        txtClassName.setForeground(new java.awt.Color(0, 0, 0));
        txtClassName.setText("Class 1");
        txtClassName.setBorder(null);
        txtClassName.setMargin(new java.awt.Insets(2, 2, 2, 2));

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setOpaque(false);

        lbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lbl.setForeground(new java.awt.Color(102, 102, 102));
        lbl.setText("Añadir muestras de imágenes:");

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/upload.png"))); // NOI18N
        btnUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUploadMouseClicked(evt);
            }
        });

        btnWc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/webCam.png"))); // NOI18N
        btnWc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnWc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnWcMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(btnWc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpload))
                    .addComponent(lbl))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnWc)
                    .addComponent(btnUpload)))
        );

        javax.swing.GroupLayout prscnLayout = new javax.swing.GroupLayout(prscn);
        prscn.setLayout(prscnLayout);
        prscnLayout.setHorizontalGroup(
            prscnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prscnLayout.createSequentialGroup()
                .addGroup(prscnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prscnLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prscnLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(prscnLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        prscnLayout.setVerticalGroup(
            prscnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prscnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prscnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prscn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prscn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnWcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWcMouseClicked
        this.setPreferredSize(
                new Dimension(
                        this.getWidth(),
                        this.getHeight() + 342
                )
        );

        PanelCamera pc = new PanelCamera(this);
        pc.setBounds(
                container.getBounds().x,
                container.getBounds().y,
                this.getWidth() - 12,
                450
        );
        Container parent = container.getParent();
        container.setVisible(false);
        parent.add(pc);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnWcMouseClicked

    private void btnUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMouseClicked
        System.out.println("Diste click en btnUpload");
    }//GEN-LAST:event_btnUploadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnUpload;
    private javax.swing.JLabel btnWc;
    public static javax.swing.JPanel container;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl;
    private com.code.components.PanelRoundShadow prscn;
    private javax.swing.JTextField txtClassName;
    // End of variables declaration//GEN-END:variables
}
