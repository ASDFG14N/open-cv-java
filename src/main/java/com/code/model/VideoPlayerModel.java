package com.code.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import com.code.videoprocess.VideoProcessingAlgorithm;
import java.awt.Color;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Size;

/**
 *
 * @author Gian
 */
public class VideoPlayerModel {

    private final FFmpegFrameGrabber grabber;
    private final JPanel screen;
    private boolean playing = true;
    private int count = 0;
    private Mat imgPrevMat;

    public VideoPlayerModel(String videoPath, JPanel screen) {
        this.grabber = new FFmpegFrameGrabber(videoPath);
        this.screen = screen;
    }

    /**
     *
     * @param imgPrev
     */
    public void playVideo(boolean imgPrev) {
        try {
            grabber.start();
            int w = screen.getWidth();
            int h = screen.getHeight();

            while (playing) {
                Frame frame = grabber.grab();
                if (frame == null) {
                    System.out.println("El frame es nulo");
                    break;
                }
                if (frame.image != null) {
                    BufferedImage bufferedImage = Java2DFrameUtils.toBufferedImage((frame));
                    drawImageOnPanel(bufferedImage);
                    if (imgPrev == true && count < 1) {
                        imgPrevMat = Java2DFrameUtils.toMat(frame);
                        resize(imgPrevMat, imgPrevMat, new Size(w, h));
                        count++;
                        String filePath = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\target\\classes\\preview\\preview_1.jpg";
                        imwrite(filePath, imgPrevMat);
                    }
                }
            }

        } catch (FrameGrabber.Exception e) {
        }
    }

    /**
     *
     * @param videoProcessor
     */
    public void playVideo(VideoProcessingAlgorithm videoProcessor) {
        try {
            grabber.start();
            int w = screen.getWidth();
            int h = screen.getHeight();

            while (playing) {
                Frame frame = grabber.grab();
                if (frame == null) {
                    break;
                }

                if (frame.image != null) {
                    Mat matFrame = Java2DFrameUtils.toMat(frame);
                    Mat processedFrame = videoProcessor.processFrame(matFrame, w, h);
                    BufferedImage bufferedImage = Java2DFrameUtils.toBufferedImage(processedFrame);
                    drawImageOnPanel(bufferedImage);
                }
            }

        } catch (FrameGrabber.Exception e) {
            System.out.println(e.toString());
        }
    }

    private void drawImageOnPanel(BufferedImage image) {
        Graphics g = screen.getGraphics();
        g.drawImage(
                image,
                0,
                0,
                screen.getWidth(),
                screen.getHeight(),
                0,
                0,
                image.getWidth(),
                image.getHeight(),
                null
        );
        g.dispose();
    }

    public void stopVideo() {
        try {
            grabber.stop();
            screen.setBackground(Color.BLACK);
            screen.repaint();
        } catch (FrameGrabber.Exception e) {
        }
    }

}
