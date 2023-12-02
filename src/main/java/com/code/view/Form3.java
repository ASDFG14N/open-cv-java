package com.code.view;

import com.code.components.PanelClassName;
import com.code.components.PanelExport;
import com.code.components.PanelPreparation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Gian
 */
public class Form3 extends javax.swing.JPanel {

    private PanelClassName pcn;
    private PanelPreparation pp;
    private PanelExport pe;

    public Form3() {
        initComponents();
        setScreenLayout();
    }

    private void setScreenLayout() {
        this.setLayout(new MigLayout(
                "insets 65 50 30 50, center"
        ));
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new MigLayout(
                "fillx",
                "[]",
                "[]"
        ));

        JPanel panel1 = new JPanel(new GridBagLayout());
        //panel1.setBackground(Color.RED);
        panel1.setOpaque(false);
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.gridx = 0;
        gbc0.gridy = 0;
        gbc0.weightx = 1;
        gbc0.weighty = 1;
        pcn = new PanelClassName();
        panel1.add(pcn, gbc0);
        container.add(panel1, "grow, width 50%, height 100%");

        JPanel panel2 = new JPanel(new GridBagLayout());
        //panel2.setBackground(Color.RED);
        panel2.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        pp = new PanelPreparation();
        panel2.add(pp, gbc);
        container.add(panel2, "grow, width 20%, height 100%");

        JPanel panel3 = new JPanel(new GridBagLayout());
        //panel3.setBackground(Color.RED);
        panel3.setOpaque(false);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        pe = new PanelExport();
        pp.setPanelExport(pe);
        panel3.add(pe, gbc2);
        container.add(panel3, "grow, width 30%, height 100%");

        add(container, "width 100%, height 100%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(19, 34, 111));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
