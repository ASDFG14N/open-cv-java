/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.code.pro;

import com.code.routes.Routes;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class AutoScrollTableExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabla con Scroll Automático");
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Agrega datos de ejemplo a la tabla
        model.addColumn("Columna 1");
        model.addColumn("Columna 2");
        model.addColumn("Columna 3");

        frame.add(scrollPane);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Simula agregar filas automáticamente cada 2 segundos
        Timer timer = new Timer(2000, e -> {
            model.addRow(new Object[]{"Nueva Fila", "Más Contenido", "Otro Contenido"});
            int lastRow = table.getRowCount() - 1;
            table.scrollRectToVisible(table.getCellRect(lastRow, 0, true));
        });
        timer.setRepeats(true);
        timer.start();
    }
}
