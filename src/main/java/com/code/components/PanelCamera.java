package com.code.components;

import com.code.algorithms.CollectFaces;
import static com.code.components.PanelClassName.container;
import com.code.model.VideoPlayerModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.Image.SCALE_DEFAULT;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author Gian
 */
public class PanelCamera extends javax.swing.JPanel {

    private PanelClassName pcn;
    String xd = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\quiensera.mp4";

    public PanelCamera(PanelClassName pcn) {
        initComponents();
        this.pcn = pcn;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelRound21 = new com.code.components.PanelRound2();
        btnGrabber = new javax.swing.JLabel();
        screen = new com.code.components.PanelRound2();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        panelRound22 = new com.code.components.PanelRound2();
        numImgs = new javax.swing.JLabel();
        nose = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setBackground(new java.awt.Color(180, 210, 252));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 386));

        panelRound21.setBackground(new java.awt.Color(25, 103, 210));
        panelRound21.setRoundBottomLeft(10);
        panelRound21.setRoundBottomRight(10);
        panelRound21.setRoundTopLeft(10);
        panelRound21.setRoundTopRight(10);

        btnGrabber.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnGrabber.setForeground(new java.awt.Color(255, 255, 255));
        btnGrabber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGrabber.setText("Mantén pulsado para grabar");
        btnGrabber.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGrabberMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGrabberMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGrabberMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGrabber, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGrabber, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        screen.setBackground(new java.awt.Color(0, 0, 0));
        screen.setRoundBottomLeft(10);
        screen.setRoundBottomRight(10);
        screen.setRoundTopLeft(10);
        screen.setRoundTopRight(10);

        javax.swing.GroupLayout screenLayout = new javax.swing.GroupLayout(screen);
        screen.setLayout(screenLayout);
        screenLayout.setHorizontalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        screenLayout.setVerticalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 103, 210));
        jLabel2.setText("Cámara");

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/btnClose.png"))); // NOI18N
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(103, 103, 103)
                        .addComponent(btnClose)
                        .addGap(1, 1, 1))
                    .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRound21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(50, 50, 50)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnClose)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        add(jPanel1);

        panelRound22.setBackground(new java.awt.Color(255, 255, 255));
        panelRound22.setRoundBottomRight(10);

        numImgs.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        numImgs.setForeground(new java.awt.Color(102, 102, 102));
        numImgs.setText("Añadir muestras de imagenes");

        nose.setOpaque(false);
        nose.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelRound22Layout = new javax.swing.GroupLayout(panelRound22);
        panelRound22.setLayout(panelRound22Layout);
        panelRound22Layout.setHorizontalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numImgs, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelRound22Layout.setVerticalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(numImgs, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelRound22);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        this.setVisible(false);
        container.setVisible(true);
        pcn.setPreferredSize(new Dimension(570, 167));
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnGrabberMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGrabberMousePressed
//        VideoPlayerModel vpm = new VideoPlayerModel(xd, screen);
//        CollectFaces cf = new CollectFaces();
//        new Thread(() -> {
//            vpm.playVideo(cf);
//        }).start();
//
//        cf.setName(pcn.getClassName());

        File folder = new File("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataFaces\\1_Class 1");
        File[] files = folder.listFiles();

        Gallery gallery = new Gallery();
        gallery.getGridPanel().setLayout(new GridLayout(250, 2, 10, 10));

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                    ImageIcon imageIcon = new ImageIcon(file.getPath());
                    Icon icon = new ImageIcon(
                            imageIcon.getImage()
                                    .getScaledInstance(
                                            100,
                                            100,
                                            SCALE_DEFAULT
                                    )
                    );
                    JLabel label = new JLabel(icon);
                    label.setHorizontalAlignment(CENTER);
                    gallery.getGridPanel().add(label);
                }
            }
        }
        nose.add(gallery);
        nose.revalidate();
        nose.repaint();

    }//GEN-LAST:event_btnGrabberMousePressed

    private void btnGrabberMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGrabberMouseExited
        panelRound21.setBackground(new Color(25, 103, 210));
    }//GEN-LAST:event_btnGrabberMouseExited

    private void btnGrabberMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGrabberMouseEntered
        panelRound21.setBackground(new Color(20, 75, 155));
    }//GEN-LAST:event_btnGrabberMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnGrabber;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel nose;
    private javax.swing.JLabel numImgs;
    private com.code.components.PanelRound2 panelRound21;
    private com.code.components.PanelRound2 panelRound22;
    private com.code.components.PanelRound2 screen;
    // End of variables declaration//GEN-END:variables
}
