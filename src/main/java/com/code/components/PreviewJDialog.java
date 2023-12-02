package com.code.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;

/**
 *
 * @author Gian
 */
public class PreviewJDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private final String message = "Area delimitada, prefiere modificarla o mantener el estado actual?";
    private List<java.awt.Point> points;
    public static boolean OPTION;

    public PreviewJDialog(JFrame parent, String title, List<java.awt.Point> points) {
        super(parent, title, true);
        this.points = points;
        initComponets();
        setLocationRelativeTo(parent);
    }

    private void initComponets() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(920, 520));
        drawRectInImage();
        ImageIcon newIcon = new ImageIcon(getClass().getResource("/preview/preview_2.jpg"));
        imageLabel.setIcon(newIcon);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(messageLabel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);

        okButton = new JButton("Mantener");
        okButton.addActionListener(PreviewJDialog.this::okButtonActionPerformed);
        cancelButton = new JButton("Modificar");
        cancelButton.addActionListener(PreviewJDialog.this::cancelButtonActionPerformed);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(19, 34, 111));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        pack();
    }

    private void drawRectInImage() {
        Mat img = imread("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\preview\\preview_1.jpg");
        rectangle(
                img,
                new Point(points.get(0).x, points.get(0).y),
                new Point(points.get(2).x, points.get(2).y),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        String filePath = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\preview\\preview_2.jpg";
        imwrite(filePath, img);
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        OPTION = true;
        this.dispose();
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        OPTION = false;
        this.dispose();
    }

}