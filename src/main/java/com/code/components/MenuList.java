package com.code.components;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public final class MenuList extends javax.swing.JPanel {

    private final List<Event> evts;

    public MenuList() {
        initComponents();
        setOpaque(false);
        evts = new ArrayList<>();
        panelRound2.setLayout(new MigLayout(
                "fill, inset 0",
                "[center][center]",
                "[center]")
        );
        addSpace(20);
        addItem("camera", 0);
        addItem("location", 1);
        addItem("ai", 2);
        addItem("fold-op", 3);

        addSpace(20);
        repaint();
        revalidate();
    }

    public void addEvt(Event evt) {
        evts.add(evt);
    }

    public void runEvt(int index) {
        System.out.println(evts.size());
        for (Event evt : evts) {
            evt.menuSelectect(index);
        }
    }

    public void addItem(String iconImg, int index) {
        String url = "/images/" + iconImg + ".png";
        Item item = new Item();
        item.setIconImg(new ImageIcon(getClass().getResource(url)).getImage());
        item.addActionListener((ActionEvent e) -> {
            runEvt(index);
        });
        panelRound2.add(item, "w 50!, h 50!");
    }

    private void addSpace(int size) {
        this.add(new JLabel(), "f " + size + "!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new com.code.components.PanelRound();

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.code.components.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
