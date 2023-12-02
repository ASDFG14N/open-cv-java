package com.code.components;

import com.code.algorithms.CollectFaces;
import com.code.algorithms.FaceRecognizer;
import com.code.model.VideoPlayerModel;
import com.code.routes.Routes;
import static com.code.routes.Routes.PATH_MODEL_XML;
import static com.code.routes.Routes.PATH_TRAINING_DIR;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;

/**
 *
 * @author Gian
 */
public class PanelPreparation extends javax.swing.JPanel {

    private PanelExport panelExport;
    String video = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\quiensera.mp4";

    public PanelPreparation() {
        initComponents();
    }

    public void setPanelExport(PanelExport panelExport) {
        this.panelExport = panelExport;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prs = new com.code.components.PanelRoundShadow();
        jLabel1 = new javax.swing.JLabel();
        btn = new com.code.components.PanelRound2();
        btnPreparation = new javax.swing.JLabel();

        setOpaque(false);

        prs.setShadowColor(new java.awt.Color(0, 0, 0));
        prs.setShadowOpacity(0.6F);
        prs.setShadowSize(6);
        prs.setShadowType(com.code.components.ShadowType.BOT);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Preparación");

        btn.setBackground(new java.awt.Color(153, 153, 153));
        btn.setRoundBottomLeft(10);
        btn.setRoundBottomRight(10);
        btn.setRoundTopLeft(10);
        btn.setRoundTopRight(10);

        btnPreparation.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnPreparation.setForeground(new java.awt.Color(51, 51, 51));
        btnPreparation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPreparation.setText("Preparar modelo");
        btnPreparation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreparation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreparationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreparationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreparationMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnLayout = new javax.swing.GroupLayout(btn);
        btn.setLayout(btnLayout);
        btnLayout.setHorizontalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPreparation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btnLayout.setVerticalGroup(
            btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPreparation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout prsLayout = new javax.swing.GroupLayout(prs);
        prs.setLayout(prsLayout);
        prsLayout.setHorizontalGroup(
            prsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(prsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        prsLayout.setVerticalGroup(
            prsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prsLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreparationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreparationMouseClicked

        String trainingDir = PATH_TRAINING_DIR.getRoute() + CollectFaces.folderName;
        System.out.println(trainingDir);
        String modelFileName = "Nomse";
        FaceRecognizer fr = new FaceRecognizer();

        this.setPreferredSize(
                new Dimension(
                        this.getWidth(),
                        this.getHeight()
                )
        );

        try {
            JLabel xd = new JLabel();
            xd.setIcon(new ImageIcon("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\spinner.gif"));
            xd.setHorizontalAlignment(CENTER);
            xd.setBounds(btn.getBounds());
            Container parent = btn.getParent();
            btn.setVisible(false);
            parent.add(xd);
            parent.revalidate();
            parent.repaint();
            asyncTask(fr, trainingDir, modelFileName)
                    .thenRun(() -> {
                        SwingUtilities.invokeLater(() -> {
                            xd.setVisible(false);
                            btn.setVisible(true);
                            btnPreparation.setText("Modelo preparado");
                            panelExport.setPreferredSize(
                                    new Dimension(
                                            panelExport.getWidth(),
                                            panelExport.getHeight() + 350
                                    )
                            );
                            panelExport.renderView();
                            panelExport.revalidate();
                            panelExport.repaint();
                            VideoPlayerModel vpm = new VideoPlayerModel(
                                    video,
                                    panelExport.getScreenTest()
                            );
                            fr.setModelDir(PATH_MODEL_XML.getRoute() + modelFileName);
                            new Thread(() -> {
                                vpm.playVideo(fr);
                            }).start();
                        });
                    });
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }//GEN-LAST:event_btnPreparationMouseClicked
    private CompletableFuture<Boolean> asyncTask(FaceRecognizer fr, String trainingDir, String modelFileName) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            fr.trainModel(trainingDir, modelFileName);
            completableFuture.complete(true);
            executor.shutdown();
            return null;
        });
        return completableFuture;
    }

    private void btnPreparationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreparationMouseEntered
        btn.setBackground(new Color(130, 130, 130));
    }//GEN-LAST:event_btnPreparationMouseEntered

    private void btnPreparationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreparationMouseExited
        btn.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_btnPreparationMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.code.components.PanelRound2 btn;
    private javax.swing.JLabel btnPreparation;
    private javax.swing.JLabel jLabel1;
    private com.code.components.PanelRoundShadow prs;
    // End of variables declaration//GEN-END:variables
}
