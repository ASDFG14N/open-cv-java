package com.code.view;

import com.code.chart.blankchart.ModelChart;
import com.code.components.CardLayout;
import com.code.components.PanelSlide;
import com.code.controller.PersonFile;
import com.code.controller.VehicleController;
import com.code.model.Date;
import com.code.model.Person;
import com.code.model.Time;
import com.code.model.Vehicle;
import com.code.routes.Routes;
import static com.code.routes.Routes.PATH_DATA_MONTH_OV;
import static com.code.routes.Routes.PATH_PERSON_DB;
import com.code.statics.FileManagement;
import com.code.statics.Filesxd;

import static com.code.view.Form1.md2;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gian
 */
public class Form2 extends javax.swing.JPanel implements FileManagement {

    private Time time;

    private double vpM1Value;
    private double vpM2Value;
    private double vpM3Value;

    private int viM1Value;
    private int viM2Value;
    private int viM3Value;

    private int poM1Value;
    private int poM2Value;
    private int poM3Value;

    private int totalCars = 0;
    private int totalCars1 = 0;
    private int totalCars2 = 0;
    private int totalCars3 = 0;
    private int totalCars4 = 0;
    private int totalCars5 = 0;
    private int totalCars6 = 0;

    private int totalOffendingCars1 = 0;
    private int totalOffendingCars2 = 0;
    private int totalOffendingCars3 = 0;
    private int totalOffendingCars4 = 0;
    private int totalOffendingCars5 = 0;
    private int totalOffendingCars6 = 0;

    private ArrayList<String> array = new ArrayList();

    private DecimalFormat df = new DecimalFormat("#.##");

    private DefaultTableModel model;

    private VehicleController vc;

    private PersonFile pf;

    private int currentIndex = 0;
    private int nextIndex = 0;

    private int monthSelect = 0;

    private Vehicle v = null;

    public Form2() {
        initComponents();
        time = new Time();
        setOpaque(false);
        collectResults();
        setAverageSpeedsForMonth();
        tableCustom.fixTable(scroll);
        tableCustom.fixTable(scrollTable);
        model = (DefaultTableModel) tableCustom.getModel();
        chartBar.addLegend("Total de Vehículos", new Color(245, 189, 135));
        chartBar.addLegend("Vehículos infractores", new Color(135, 189, 245));
        chartBar.addLegend("Profit", new Color(189, 135, 245));
        chartBar.addLegend("Cost", new Color(139, 229, 222));
        setLabelsOnChart();
        renderDataTable(time.getCurrentMonth() + 1);
        rankPeople();
        ListSelectionModel selectionModel = tableCustom.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableCustom.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtiene los datos de la fila seleccionada
                    Object id = tableCustom.getValueAt(selectedRow, 1);
                    Object name = tableCustom.getValueAt(selectedRow, 2);
                    Object plate = tableCustom.getValueAt(selectedRow, 3);
                    Object state = tableCustom.getValueAt(selectedRow, 5);

                    v = vc.searchReference((int) id, name.toString(), plate.toString());
                    System.out.println(v.toString());
                    //buscarYModificar((int) id, name.toString(), plate.toString(), monthSelect, v);

                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelShadow1 = new com.code.components.PanelShadow();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        viM1 = new javax.swing.JLabel();
        viM2 = new javax.swing.JLabel();
        viM3 = new javax.swing.JLabel();
        panelShadow2 = new com.code.components.PanelShadow();
        jLabel6 = new javax.swing.JLabel();
        vpM1 = new javax.swing.JLabel();
        vpM2 = new javax.swing.JLabel();
        vpM3 = new javax.swing.JLabel();
        gaugeChartVP1 = new com.code.chart.GaugeChartVP();
        panelShadow3 = new com.code.components.PanelShadow();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        poM1 = new javax.swing.JLabel();
        poM2 = new javax.swing.JLabel();
        poM3 = new javax.swing.JLabel();
        containerCartBar = new javax.swing.JPanel();
        chartBar = new com.code.chart.ChartBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        tableCustom = new com.code.components.TableCustom();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbYear = new com.code.components.Combobox();
        cmbState = new com.code.components.Combobox();
        cmbMonth = new com.code.components.Combobox();
        search = new com.code.components.TextField();
        cmbDay = new com.code.components.Combobox();
        rbName = new javax.swing.JRadioButton();
        rbPlate = new javax.swing.JRadioButton();
        btnSave = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        container2 = new javax.swing.JPanel();
        container1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setOpaque(false);

        scroll.setBackground(new java.awt.Color(19, 34, 111));

        bg.setBackground(new java.awt.Color(19, 34, 111));

        jPanel1.setOpaque(false);

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setShadowColor(new java.awt.Color(0, 152, 235));
        panelShadow1.setShadowOpacity(2.0F);
        panelShadow1.setShadowSize(10);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Vehículos infractores");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/car.png"))); // NOI18N

        viM1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viM1.setForeground(new java.awt.Color(102, 102, 102));
        viM1.setText("Septiembre:");

        viM2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viM2.setForeground(new java.awt.Color(102, 102, 102));
        viM2.setText("Octubre:");

        viM3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viM3.setForeground(new java.awt.Color(102, 102, 102));
        viM3.setText("Noviembre:");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viM1)
                    .addComponent(viM2)
                    .addComponent(viM3))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(viM1)
                        .addGap(18, 18, 18)
                        .addComponent(viM2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viM3))
                    .addComponent(jLabel1))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        panelShadow2.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow2.setShadowColor(new java.awt.Color(0, 152, 235));
        panelShadow2.setShadowOpacity(2.0F);
        panelShadow2.setShadowSize(10);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Velocidad promedio");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));

        vpM1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vpM1.setForeground(new java.awt.Color(102, 102, 102));
        vpM1.setText("Septiembre:");

        vpM2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vpM2.setForeground(new java.awt.Color(102, 102, 102));
        vpM2.setText("Octubre:");

        vpM3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vpM3.setForeground(new java.awt.Color(102, 102, 102));
        vpM3.setText("Noviembre:");

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(gaugeChartVP1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vpM2)
                            .addComponent(vpM1)
                            .addComponent(vpM3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addComponent(vpM1)
                        .addGap(30, 30, 30)
                        .addComponent(vpM2)
                        .addGap(27, 27, 27)
                        .addComponent(vpM3))
                    .addComponent(gaugeChartVP1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelShadow3.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow3.setShadowColor(new java.awt.Color(0, 152, 235));
        panelShadow3.setShadowOpacity(2.0F);
        panelShadow3.setShadowSize(10);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Puntos otorgados");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/star.png"))); // NOI18N

        poM1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        poM1.setForeground(new java.awt.Color(102, 102, 102));
        poM1.setText("Septiembre:");

        poM2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        poM2.setForeground(new java.awt.Color(102, 102, 102));
        poM2.setText("Octubre:");

        poM3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        poM3.setForeground(new java.awt.Color(102, 102, 102));
        poM3.setText("Noviembre:");

        javax.swing.GroupLayout panelShadow3Layout = new javax.swing.GroupLayout(panelShadow3);
        panelShadow3.setLayout(panelShadow3Layout);
        panelShadow3Layout.setHorizontalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poM1)
                    .addComponent(poM2)
                    .addComponent(poM3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow3Layout.setVerticalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelShadow3Layout.createSequentialGroup()
                        .addComponent(poM1)
                        .addGap(34, 34, 34)
                        .addComponent(poM2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(poM3)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        containerCartBar.setOpaque(false);

        javax.swing.GroupLayout containerCartBarLayout = new javax.swing.GroupLayout(containerCartBar);
        containerCartBar.setLayout(containerCartBarLayout);
        containerCartBarLayout.setHorizontalGroup(
            containerCartBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerCartBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        containerCartBarLayout.setVerticalGroup(
            containerCartBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerCartBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartBar, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Historial de Vehículos");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reporte Diario");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jPanel3.setOpaque(false);

        tableCustom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID", "Nombre", "Placa Vehícular", "Fecha", "Estado"
            }
        ));
        scrollTable.setViewportView(tableCustom);

        jPanel4.setOpaque(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Filtros:");

        cmbYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2020", "2021", "2022", "2023" }));
        cmbYear.setSelectedIndex(-1);
        cmbYear.setLabeText("Año");
        cmbYear.setLineColor(new java.awt.Color(0, 152, 235));
        cmbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbYearItemStateChanged(evt);
            }
        });

        cmbState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Procesado", "Pendiente" }));
        cmbState.setSelectedIndex(-1);
        cmbState.setLabeText("Estado");
        cmbState.setLineColor(new java.awt.Color(0, 152, 235));
        cmbState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStateItemStateChanged(evt);
            }
        });

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre" }));
        cmbMonth.setSelectedIndex(-1);
        cmbMonth.setLabeText("Mes");
        cmbMonth.setLineColor(new java.awt.Color(0, 152, 235));
        cmbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMonthItemStateChanged(evt);
            }
        });

        search.setBackground(new java.awt.Color(255, 255, 255));
        search.setForeground(new java.awt.Color(153, 153, 153));
        search.setText("Buscar por nombre");
        search.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        search.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        search.setSelectionColor(new java.awt.Color(0, 152, 235));
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchMousePressed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDay.setSelectedIndex(-1);
        cmbDay.setLabeText("Dia");
        cmbDay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDayItemStateChanged(evt);
            }
        });

        rbName.setForeground(new java.awt.Color(255, 255, 255));
        rbName.setSelected(true);
        rbName.setText("Nombre");
        rbName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNameActionPerformed(evt);
            }
        });

        rbPlate.setForeground(new java.awt.Color(255, 255, 255));
        rbPlate.setText("No Placa");
        rbPlate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlateActionPerformed(evt);
            }
        });

        btnSave.setText("jButton2");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(103, 103, 103)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rbPlate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                .addComponent(cmbYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rbName)
                                .addGap(12, 12, 12)
                                .addComponent(rbPlate))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnSave)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Estadística Mensual");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ranking buenos ciudanos");
        jLabel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ranking malos ciudanos");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        container2.setOpaque(false);

        javax.swing.GroupLayout container2Layout = new javax.swing.GroupLayout(container2);
        container2.setLayout(container2Layout);
        container2Layout.setHorizontalGroup(
            container2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );
        container2Layout.setVerticalGroup(
            container2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        container1.setBackground(new java.awt.Color(19, 34, 111));
        container1.setOpaque(false);

        javax.swing.GroupLayout container1Layout = new javax.swing.GroupLayout(container1);
        container1.setLayout(container1Layout);
        container1Layout.setHorizontalGroup(
            container1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        container1Layout.setVerticalGroup(
            container1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/panda_preview_rev_1.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/utils/p2_preview_rev_1.png"))); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerCartBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(container1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(89, 89, 89)))
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(container2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)))))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(containerCartBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(container2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(container1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );

        scroll.setViewportView(bg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarYModificar(int id, String name, String plate, int monthCurrent, Vehicle v) {
        try {
            Path filePath = Paths.get(PATH_DATA_MONTH_OV.getRoute(), monthCurrent + ".txt");
            List<String> lines = Files.readAllLines(filePath, UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                StringTokenizer st = new StringTokenizer(line, ";");
                int ID = Integer.parseInt(st.nextToken());
                String nam = st.nextToken();
                String plat = st.nextToken();

                if (ID == id && nam.equals(name) && plat.equals(plate)) {
                    lines.remove(i);
                    ////id;nombre;placa;fecha;estado
                    String nuevoCont = v.getID()
                            + ";" + v.getOwnerName()
                            + ";" + v.getLicensePlate()
                            + ";" + v.getDate().format() + "";
                    lines.add(i, nuevoCont);
                    break;
                }
            }
            Files.write(filePath, lines, UTF_8);
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }


    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        if (rbName.isSelected()) {
            model.setRowCount(0);
            vc.searchForName(model, search.getText());
        } else if (rbPlate.isSelected()) {
            System.out.println(search.getText());
            model.setRowCount(0);
            vc.searchForPlate(model, search.getText());
        }
    }//GEN-LAST:event_searchKeyTyped

    private void cmbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMonthItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //Mes selccionado
            monthSelect = cmbMonth.getSelectedIndex() + 1;
            //Limpia la cola de objetos
            vc.clearQueue();
            //actualiza la vista de la tabla
            renderDataTable(monthSelect);
        }
    }//GEN-LAST:event_cmbMonthItemStateChanged

    private void cmbYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbYearItemStateChanged
        int yearSelect = cmbYear.getSelectedIndex();
        System.out.println(yearSelect);
    }//GEN-LAST:event_cmbYearItemStateChanged

    private void cmbStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStateItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String stateSelect = (String) cmbState.getSelectedItem();
            model.setRowCount(0);
            vc.searchForState(model, stateSelect);
        }
    }//GEN-LAST:event_cmbStateItemStateChanged

    private void cmbDayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDayItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int daySelect = cmbDay.getSelectedIndex() + 1;
            model.setRowCount(0);
            vc.searchForDay(model, daySelect);
        }
    }//GEN-LAST:event_cmbDayItemStateChanged

    private void searchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMousePressed
        if (search.getText().equals("Buscar por nombre")
                || search.getText().equals("Buscar por placa")) {
            search.setText("");
            search.setForeground(Color.black);
        }
    }//GEN-LAST:event_searchMousePressed

    private void rbNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNameActionPerformed
        if (rbPlate.isSelected()) {
            rbPlate.setSelected(false);
        }
        if (search.getText().equals("") || search.getText().length() > 0) {
            search.setText("");
            search.setForeground(Color.GRAY);
            search.setText("Buscar por nombre");
        }
    }//GEN-LAST:event_rbNameActionPerformed

    private void rbPlateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlateActionPerformed
        if (rbName.isSelected()) {
            rbName.setSelected(false);
        }
        if (search.getText().equals("") || search.getText().length() > 0) {
            search.setText("");
            search.setForeground(Color.GRAY);
            search.setText("Buscar por placa");
        }
    }//GEN-LAST:event_rbPlateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void collectResults() {
        vc = new VehicleController();
        int currentMonthInt = time.getCurrentMonth() + 1;
        int currentDay = time.getCurrentDay();

        //Si el objeto motion detect es diferente de null, escribe nuevos datos
        if (md2 != null) {
            totalCars = md2.getCountTotalCars();
            double averageSpeedsToday = vc.averageSpeeds(md2.getSpeedsToday());

            String line = currentDay + ";"
                    + totalCars + ";"
                    + 15 + ";"
                    + averageSpeedsToday + ";"
                    + 180 + ";"
                    + 15;
            array.add(line);

            Filesxd.saveTextFile(
                    Routes.PATH_DATA_MONTH.getRoute() + currentMonthInt + ".txt",
                    line,
                    true,
                    array
            );
        }

        //Recupera la informacion de los 6 ultimos meses
        List<List> obj1 = recoverData((currentMonthInt - 5) + ".txt");
        List<List> obj2 = recoverData((currentMonthInt - 4) + ".txt");
        List<List> obj3 = recoverData((currentMonthInt - 3) + ".txt");
        List<List> obj4 = recoverData((currentMonthInt - 2) + ".txt");
        List<List> obj5 = recoverData((currentMonthInt - 1) + ".txt");
        List<List> obj6 = recoverData(currentMonthInt + ".txt");

        //Velocidad promedio de los tres ultimos meses
        vpM1Value = vc.averageSpeeds(obj4.get(2));
        vpM2Value = vc.averageSpeeds(obj5.get(2));
        vpM3Value = vc.averageSpeeds(obj6.get(2));
        //Animacion para el mes actual
        gaugeChartVP1.setValueWithAnimation((int) vpM3Value);

        //Vehiculos infractores de los 3 ultimos meses, las sumas
        viM1Value = vc.sumOfElements(obj4.get(1));
        viM2Value = vc.sumOfElements(obj5.get(1));
        viM3Value = vc.sumOfElements(obj6.get(1));

        //Puntos otorgados los utimos 3 meses
        poM1Value = vc.sumOfElements(obj4.get(3));
        poM2Value = vc.sumOfElements(obj5.get(3));
        poM3Value = vc.sumOfElements(obj6.get(3));
        //==========================================================
        //==========================================================

        totalCars1 = vc.sumOfElements(obj1.get(0));
        totalCars2 = vc.sumOfElements(obj2.get(0));
        totalCars3 = vc.sumOfElements(obj3.get(0));
        totalCars4 = vc.sumOfElements(obj4.get(0));
        totalCars5 = vc.sumOfElements(obj5.get(0));
        totalCars6 = vc.sumOfElements(obj6.get(0));

        totalOffendingCars1 = vc.sumOfElements(obj1.get(3));
        totalOffendingCars2 = vc.sumOfElements(obj2.get(3));
        totalOffendingCars3 = vc.sumOfElements(obj3.get(3));
        totalOffendingCars4 = vc.sumOfElements(obj4.get(3));
        totalOffendingCars5 = vc.sumOfElements(obj5.get(3));
        totalOffendingCars6 = vc.sumOfElements(obj6.get(3));
    }

    private void setAverageSpeedsForMonth() {
        for (int i = -2; i < 1; i++) {
            int currentMonthInt = time.getCurrentMonth() + i;
            String month = time.monthName(currentMonthInt);
            month = month.substring(0, 1).toUpperCase()
                    + month.substring(1).toLowerCase();
            switch (i) {
                case -2 -> {
                    vpM1.setText(month + ": " + df.format(vpM1Value) + " km/h");
                    viM1.setText(month + ": " + viM1Value);
                    poM1.setText(month + ": " + poM1Value);
                }
                case -1 -> {
                    vpM2.setText(month + ": " + df.format(vpM2Value) + " km/h");
                    viM2.setText(month + ": " + viM2Value);
                    poM2.setText(month + ": " + poM2Value);
                }
                case 0 -> {
                    vpM3.setText(month + ": " + df.format(vpM3Value) + " km/h");
                    viM3.setText(month + ": " + viM3Value);
                    poM3.setText(month + ": " + poM3Value);
                }
                default -> {
                }
            }
        }
    }

    private void setLabelsOnChart() {
        for (int i = -5; i < 1; i++) {
            int currentMonthInt = time.getCurrentMonth() + i;
            String month = time.monthName(currentMonthInt);
            month = month.substring(0, 1).toUpperCase()
                    + month.substring(1).toLowerCase();
            switch (i) {
                case -5 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{
                                        totalCars1,
                                        totalOffendingCars1,
                                        80,
                                        89
                                    }
                            )
                    );

                case -4 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{
                                        totalCars2,
                                        totalOffendingCars2,
                                        90,
                                        150
                                    }
                            )
                    );

                case -3 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{
                                        totalCars3,
                                        totalOffendingCars3,
                                        460,
                                        900
                                    }
                            )
                    );

                case -2 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{totalCars4,
                                        totalOffendingCars4,
                                        750,
                                        700
                                    }
                            )
                    );

                case -1 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{
                                        totalCars5,
                                        totalOffendingCars5,
                                        300,
                                        150
                                    }
                            )
                    );

                case 0 ->
                    chartBar.addData(
                            new ModelChart(
                                    month,
                                    new double[]{
                                        totalCars6,
                                        totalOffendingCars6,
                                        81,
                                        200
                                    }
                            )
                    );

                default -> {
                }
            }

        }

    }

    @Override
    public final <T> T recoverData(String fileName, T data) {
        ArrayList arrayAux = new ArrayList();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                arrayAux.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        return (T) arrayAux;
    }

    private void renderDataTable(int monthCurrent) {
        for (var line : recoverData(
                PATH_DATA_MONTH_OV.getRoute() + monthCurrent + ".txt",
                new ArrayList()
        )) {
            //id;nombre;placa;fecha;estado
            StringTokenizer st = new StringTokenizer((String) line, ";");
            int ID = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            String plate = st.nextToken();
            Date date = new Date(st.nextToken());
            String state = st.nextToken();
            Vehicle vehicle = new Vehicle(
                    ID,
                    plate,
                    plate,
                    name,
                    date,
                    state
            );
            vc.addLast(vehicle);
        }
        model.setRowCount(0);
        vc.showTable(model);
    }

    private void rankPeople() {
        pf = new PersonFile(PATH_PERSON_DB.getRoute());
        LinkedList<Person> xd = pf.rankPeopleMostQualification();
        LinkedList<Person> xd2 = pf.rankPeopleLeastQualification();
        ArrayList<CardLayout> acl = new ArrayList<>();
        ArrayList<CardLayout> acl2 = new ArrayList<>();
        int numCards = 15;

        for (int i = 0; i < numCards; i++) {
            if (i < xd.size()) {
                Person person = xd.get(i);
                CardLayout cardLayout = new CardLayout();
                cardLayout.setPerson(person);
                cardLayout.setRank(i + 1);
                acl.add(cardLayout);
            } else {
                acl.add(new CardLayout());
            }
        }
        for (int i = 0; i < numCards; i++) {
            if (i < xd2.size()) {
                Person person = xd2.get(i);
                CardLayout cardLayout = new CardLayout();
                cardLayout.setPerson(person);
                cardLayout.setRank(i + 1);
                acl2.add(cardLayout);
            } else {
                acl2.add(new CardLayout());
            }
        }

        PanelSlide psGood = new PanelSlide();
        psGood.setLocation(0, 0);
        psGood.setSize(430, 262);
        container1.add(psGood);
        psGood.init(acl.toArray(CardLayout[]::new));
        psGood.setAnimate(3);

        PanelSlide psBad = new PanelSlide();
        psBad.setLocation(0, 0);
        psBad.setSize(430, 262);
        container2.add(psBad);
        psBad.init(acl2.toArray(CardLayout[]::new));
        psBad.setAnimate(3);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                nextIndex = (currentIndex) % 15;
                psGood.show(nextIndex);
                psBad.show(nextIndex);
                if (currentIndex == 15) {
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
            }
        };
        timer.schedule(task, 0, 5000);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnSave;
    private com.code.chart.ChartBar chartBar;
    private com.code.components.Combobox cmbDay;
    private com.code.components.Combobox cmbMonth;
    private com.code.components.Combobox cmbState;
    private com.code.components.Combobox cmbYear;
    private javax.swing.JPanel container1;
    private javax.swing.JPanel container2;
    private javax.swing.JPanel containerCartBar;
    private com.code.chart.GaugeChartVP gaugeChartVP1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private com.code.components.PanelShadow panelShadow1;
    private com.code.components.PanelShadow panelShadow2;
    private com.code.components.PanelShadow panelShadow3;
    private javax.swing.JLabel poM1;
    private javax.swing.JLabel poM2;
    private javax.swing.JLabel poM3;
    private javax.swing.JRadioButton rbName;
    private javax.swing.JRadioButton rbPlate;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scrollTable;
    private com.code.components.TextField search;
    private com.code.components.TableCustom tableCustom;
    private javax.swing.JLabel viM1;
    private javax.swing.JLabel viM2;
    private javax.swing.JLabel viM3;
    private javax.swing.JLabel vpM1;
    private javax.swing.JLabel vpM2;
    private javax.swing.JLabel vpM3;
    // End of variables declaration//GEN-END:variables
}
