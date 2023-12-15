package com.code.view;

import com.code.algorithms.FaceDetect;
import com.code.algorithms.FaceRecognizer;
import com.code.algorithms.MotionDetection;
import com.code.algorithms.MotionDetection.RecognitionPlates;
import com.code.components.PanelShadow;
import com.code.model.Semaphore;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import com.code.model.VideoPlayerModel;
import static com.code.routes.Routes.PATH_MODEL_XML;
import static com.code.routes.Routes.VIDEO_P;
import com.code.statics.FileManagement;
import static com.code.statics.FileManagement.existFile;
import java.awt.FlowLayout;
import static java.awt.Image.SCALE_DEFAULT;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Gian
 */
public class Form1 extends javax.swing.JPanel implements FileManagement {

    private String path = VIDEO_P.getRoute();
    private VideoPlayerModel vpm;
    private Thread video;
    public static MotionDetection md;
    public static MotionDetection md2;

    private List<Point> points;

    public static DefaultTableModel modelMessages;

    private String vidA = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\amigos.mp4";

    private Semaphore semaphore;

    public static JLabel imgSem;

    public Form1() {
        initComponents();
        setScreenLayout();
        semaphore = new Semaphore();
        points = new ArrayList<>();
        modelMessages = (DefaultTableModel) asyncMessagesBody.getModel();
        if (existFile("PointsSave.txt")) {
            startVideo();
            semaphore.startSemaphore();
            //startFaceRecognizer();
            startFaceDetect();
        } else {
            mostrar.doClick();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScreen = new javax.swing.JPanel();
        mostrar = new javax.swing.JButton();
        screenTwo = new javax.swing.JPanel();
        screenThree = new javax.swing.JPanel();
        screenFour = new javax.swing.JPanel();
        asyncMessages = new javax.swing.JScrollPane();
        asyncMessagesBody = new com.code.components.TableCustom();

        mainScreen.setBackground(new java.awt.Color(0, 0, 0));
        mainScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainScreenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainScreenLayout = new javax.swing.GroupLayout(mainScreen);
        mainScreen.setLayout(mainScreenLayout);
        mainScreenLayout.setHorizontalGroup(
            mainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        mainScreenLayout.setVerticalGroup(
            mainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        mostrar.setText("jButton1");
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActionPerformed(evt);
            }
        });

        screenTwo.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout screenTwoLayout = new javax.swing.GroupLayout(screenTwo);
        screenTwo.setLayout(screenTwoLayout);
        screenTwoLayout.setHorizontalGroup(
            screenTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        screenTwoLayout.setVerticalGroup(
            screenTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        screenThree.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout screenThreeLayout = new javax.swing.GroupLayout(screenThree);
        screenThree.setLayout(screenThreeLayout);
        screenThreeLayout.setHorizontalGroup(
            screenThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        screenThreeLayout.setVerticalGroup(
            screenThreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        screenFour.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout screenFourLayout = new javax.swing.GroupLayout(screenFour);
        screenFour.setLayout(screenFourLayout);
        screenFourLayout.setHorizontalGroup(
            screenFourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        screenFourLayout.setVerticalGroup(
            screenFourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        asyncMessagesBody.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora", "Detalle"
            }
        ));
        asyncMessagesBody.setDragEnabled(true);
        asyncMessagesBody.setEnabled(false);
        asyncMessages.setViewportView(asyncMessagesBody);
        if (asyncMessagesBody.getColumnModel().getColumnCount() > 0) {
            asyncMessagesBody.getColumnModel().getColumn(0).setMinWidth(80);
            asyncMessagesBody.getColumnModel().getColumn(0).setPreferredWidth(80);
            asyncMessagesBody.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        setBackground(new java.awt.Color(19, 34, 111));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    private void setScreenLayout() {
        //Distribucion generarl de los componentes dentro del panel Form1
        this.setLayout(new MigLayout(
                "insets 5",
                "[grow][385px]",
                "[grow]")
        );
        //--------------------------------------------------------
        //Contenedor de semaforo y cronometro
        JPanel containerSemaphore = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //containerSemaphore.setBackground(new Color(255, 0, 0));
        containerSemaphore.setOpaque(false);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\semaphore\\red.png");
        Icon icon = new ImageIcon(
                imageIcon.getImage()
                        .getScaledInstance(
                                165,
                                40,
                                SCALE_DEFAULT
                        )
        );
        imgSem = new JLabel(icon);
        containerSemaphore.add(imgSem);
        //--------------------------------------------------------
        //Contenedor principal primera columna
        JPanel containerMain = new JPanel(new MigLayout(
                "insets 55 5 5 5",
                "[grow]",
                "[][]")
        );
        containerMain.setOpaque(false);
        PanelShadow ps = new PanelShadow();
        ps.setShadowColor(new Color(0, 152, 235));
        ps.setShadowOpacity((float) 2.0);
        ps.setShadowSize(15);
        ps.setLayout(new MigLayout("insets 15"));
        add(containerMain, "cell 0 0, width 960, height 100%");
        containerMain.add(containerSemaphore, "cell 0 0, width 100%, height 50");
        containerMain.add(ps, "cell 0 1, width 950, height 100%");
        ps.add(mainScreen, "width 950, height 100%");
        //--------------------------------------------------------
        //Contenedor de las demas pantallas
        JPanel containerScreens = new JPanel(new MigLayout(
                "insets 8 25 8 10",
                "[grow]",
                "[][][]")
        );
        containerScreens.setOpaque(false);
        add(containerScreens, "cell 1 0, grow");

        //Sombras para las pantallas
        PanelShadow ps2 = new PanelShadow();
        ps2.setShadowColor(new Color(0, 152, 235));
        ps2.setShadowOpacity((float) 2.0);
        ps2.setShadowSize(8);
        ps2.setLayout(new MigLayout("insets 8"));

        PanelShadow ps3 = new PanelShadow();
        ps3.setShadowColor(new Color(0, 152, 235));
        ps3.setShadowOpacity((float) 2.0);
        ps3.setShadowSize(8);
        ps3.setLayout(new MigLayout("insets 8"));

        PanelShadow ps4 = new PanelShadow();
        ps4.setShadowColor(new Color(0, 152, 235));
        ps4.setShadowOpacity((float) 2.0);
        ps4.setShadowSize(8);
        ps4.setLayout(new MigLayout("insets 8"));

        //Se agregan las pantallas a su contenedor, con dimensiones relativas
        containerScreens.add(ps2, "cell 0 0, width 100%, height 35%");
        //ps2.add(screenTwo, "width 100%, height 100%");
        ps2.add(asyncMessages, "width 100%, height 100%");
        asyncMessagesBody.fixTable(asyncMessages);
        containerScreens.add(ps3, "cell 0 1, width 100%, height 35%");
        ps3.add(screenThree, "width 100%, height 100%");
        containerScreens.add(ps4, "cell 0 2, width 100%, height 35%");
        ps4.add(screenFour, "width 100%, height 100%");
    }

    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarActionPerformed
        vpm = new VideoPlayerModel(path, mainScreen);
        video = new Thread(() -> {
            vpm.playVideo(true);
        });
        video.start();
        System.out.println("State 1: " + video.getState());
    }//GEN-LAST:event_mostrarActionPerformed

    private void mainScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainScreenMouseClicked
        //Obtener las coordenadas
        int x = evt.getX();
        int y = evt.getY();
        //=======================================
        if (!(points.size() == 4)) {
            points.add(new Point(x, y));
            Graphics2D g = (Graphics2D) mainScreen.getGraphics();
            g.setColor(Color.GREEN);
            g.fillOval(x - 10, y - 10, 20, 20);
        } else {
            vpm.stopVideo();
            video.interrupt();
            System.out.println("State 2: " + video.getState());

            String pointStr = "";
            for (var p : points) {
                int px = p.x;
                int py = p.y;
                pointStr += px + ";" + py + "\n";
            }
            saveTextFile("PointsSave.txt", pointStr);

            md = new MotionDetection(points);
            Thread video2 = new Thread(() -> {
                vpm.playVideo(md);
            });
            video2.start();
            System.out.println("State Video2: " + video2.getState());
            //PreviewJDialog dialog = new PreviewJDialog(null, "Vista previa", points);
            //dialog.setVisible(true);
//            if (PreviewJDialog.OPTION) {
//                vpm.stopVideo();
//                video.interrupt();
//                System.out.println("State 2: " + video.getState());
//
//                String pointStr = "";
//                for (var p : points) {
//                    int px = p.x;
//                    int py = p.y;
//                    pointStr += px + ";" + py + "\n";
//                }
//                Filesxd.saveTextFile(
//                        "PointsSave.txt",
//                        pointStr,
//                        true,
//                        null
//                );
//
//                MotionDetection md = new MotionDetection(points);
//                Thread video2 = new Thread(() -> {
//                    vpm.playVideo(md);
//                });
//                video2.start();
//                System.out.println("State Video2: " + video2.getState());
//            } else {
//                JOptionPane.showMessageDialog(null, "Selecciona el area que creas conveniente");
//                points.clear();
//            }
        }
    }//GEN-LAST:event_mainScreenMouseClicked

    @Override
    public final <T> T recoverData(String fileName, T data) {
        if (data instanceof List<?>) {
            List<Point> list = (List<Point>) data;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        Point point = new Point(x, y);
                        list.add(point);
                    }
                }
                return (T) list;
            } catch (IOException e) {
                System.out.println("Error: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("Tipo de dato no soportado");
        }
        return null;
    }

    private void startFaceRecognizer() {
        FaceRecognizer fr = new FaceRecognizer();
        VideoPlayerModel vpm2 = new VideoPlayerModel(vidA, screenThree);
        Thread video2 = new Thread(() -> {
            fr.setModelDir(PATH_MODEL_XML.getRoute() + "Nomse");
            vpm2.playVideo(fr);
        });
        video2.start();
    }

    private void startFaceDetect() {
        FaceDetect fd = new FaceDetect();
        VideoPlayerModel vpm2 = new VideoPlayerModel(vidA, screenThree);
        Thread video2 = new Thread(() -> {
            vpm2.playVideo(fd);
        });
        video2.start();
    }

    private void startVideo() {
        points = recoverData("PointsSave.txt", points);
        md2 = new MotionDetection(points);
        VideoPlayerModel vpm2 = new VideoPlayerModel(path, mainScreen);
        RecognitionPlates rp = md2.new RecognitionPlates();
        Thread video2 = new Thread(() -> {
            vpm2.playVideo(md2);
        });
        Thread video3 = new Thread(() -> {
            rp.initVideoCapture();
        });
        video3.start();
        video2.start();
        System.out.println("State Video2: " + video2.getState());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane asyncMessages;
    public static com.code.components.TableCustom asyncMessagesBody;
    private javax.swing.JPanel mainScreen;
    private javax.swing.JButton mostrar;
    private javax.swing.JPanel screenFour;
    private javax.swing.JPanel screenThree;
    private javax.swing.JPanel screenTwo;
    // End of variables declaration//GEN-END:variables
}
