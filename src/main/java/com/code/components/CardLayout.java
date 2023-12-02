package com.code.components;

import com.code.model.Person;
import javax.swing.ImageIcon;

/**
 *
 * @author Gian
 */
public class CardLayout extends javax.swing.JPanel {

    public CardLayout() {
        initComponents();
    }

    public void setPerson(Person person) {
        this.name.setText("Nombre: " + person.getName());
        this.lastName.setText("Apellido: " + person.getLastName());
        this.dni.setText("DNI: " + person.getDNI());
        this.points.setText("Puntos: " + person.getQualification());
    }

    public void setPhoto(String routePhoto) {
        if (routePhoto != null) {
            photo.setIcon(new ImageIcon(getClass().getResource(routePhoto)));
        }
    }

    public void setRank(int rank) {
        this.rank.setText(String.valueOf(rank));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound = new com.code.components.PanelRound2();
        photo = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        points = new javax.swing.JLabel();
        dni = new javax.swing.JLabel();
        lastName = new javax.swing.JLabel();
        panelRound21 = new com.code.components.PanelRound2();
        rank = new javax.swing.JLabel();

        setBackground(new java.awt.Color(19, 34, 111));

        panelRound.setBackground(new java.awt.Color(0, 152, 235));
        panelRound.setRoundBottomLeft(30);
        panelRound.setRoundBottomRight(30);
        panelRound.setRoundTopLeft(30);
        panelRound.setRoundTopRight(30);

        photo.setForeground(new java.awt.Color(0, 0, 0));
        photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/no.jpg"))); // NOI18N
        photo.setText("Foto");

        name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setText("Nombre:");

        points.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        points.setForeground(new java.awt.Color(51, 51, 51));
        points.setText("Puntos:");

        dni.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dni.setForeground(new java.awt.Color(51, 51, 51));
        dni.setText("DNI:");

        lastName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lastName.setForeground(new java.awt.Color(51, 51, 51));
        lastName.setText("Apellido:");

        panelRound21.setBackground(new java.awt.Color(0, 0, 0));
        panelRound21.setRoundBottomLeft(30);
        panelRound21.setRoundTopRight(30);

        rank.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rank.setForeground(new java.awt.Color(255, 255, 255));
        rank.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rank.setText("1");

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
            .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rank, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rank, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRoundLayout = new javax.swing.GroupLayout(panelRound);
        panelRound.setLayout(panelRoundLayout);
        panelRoundLayout.setHorizontalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastName, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(points, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panelRoundLayout.setVerticalGroup(
            panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLayout.createSequentialGroup()
                .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRoundLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRoundLayout.createSequentialGroup()
                                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(dni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(points, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(9, 9, 9)))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dni;
    private javax.swing.JLabel lastName;
    private javax.swing.JLabel name;
    private com.code.components.PanelRound2 panelRound;
    private com.code.components.PanelRound2 panelRound21;
    private javax.swing.JLabel photo;
    private javax.swing.JLabel points;
    private javax.swing.JLabel rank;
    // End of variables declaration//GEN-END:variables
}
