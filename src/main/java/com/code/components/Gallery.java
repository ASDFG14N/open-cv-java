package com.code.components;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Gallery extends JPanel {

    private GridImages gridPanel;

    public Gallery() {
        gridPanel = new GridImages();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBar(new ScrollGrid());
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public GridImages getGridPanel() {
        return gridPanel;
    }

}
