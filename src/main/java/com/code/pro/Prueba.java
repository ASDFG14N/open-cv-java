package com.code.pro;

import java.util.ArrayList;
import java.util.List;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
import static org.bytedeco.opencv.global.opencv_imgproc.line;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_objdetect.CASCADE_SCALE_IMAGE;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class Prueba {

    public static void main(String[] args) {
        int absoluteFaceSize = 0;
        List<Rect> previousFaceDetections = new ArrayList<>();
        int faceCount = 0;
        VideoCapture capture = new VideoCapture("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\amigos.mp4");
        CascadeClassifier faceClassifier = new CascadeClassifier("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\haarcascade_frontalface_default.xml");
        while (true) {
            Mat frame = new Mat();
            if (capture.read(frame)) {
                resize(frame, frame, new Size(950, 600));
                Mat gray = new Mat();
                cvtColor(frame, gray, COLOR_BGR2GRAY);
                equalizeHist(gray, gray);
                if (absoluteFaceSize == 0) {
                    int height = gray.rows();
                    if (Math.round(height * 0.2f) > 0) {
                        absoluteFaceSize = Math.round(height * 0.2f);
                    }
                }
                RectVector faceDetections = new RectVector();
                faceClassifier.detectMultiScale(
                        frame,
                        faceDetections,
                        1.3,
                        5,
                        CASCADE_SCALE_IMAGE,
                        new Size(absoluteFaceSize, absoluteFaceSize),
                        new Size()
                );
                for (int i = 0; i < faceDetections.size(); i++) {
                    Rect rect = faceDetections.get(i);

                    int tl_x1 = rect.x();
                    int tl_y1 = rect.y();
                    int br_x2 = rect.x() + rect.width();
                    int br_y2 = rect.y() + rect.height();

                    //top-left
                    line(
                            frame,
                            new Point(tl_x1, tl_y1),
                            new Point(tl_x1, tl_y1 + 40),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );
                    line(
                            frame,
                            new Point(tl_x1, tl_y1),
                            new Point(tl_x1 + 40, tl_y1),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );

                    //bottom-left
                    line(
                            frame,
                            new Point(tl_x1, br_y2),
                            new Point(tl_x1, br_y2 - 40),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );
                    line(
                            frame,
                            new Point(tl_x1, br_y2),
                            new Point(tl_x1 + 40, br_y2),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );

                    //top-right
                    line(
                            frame,
                            new Point(br_x2, tl_y1),
                            new Point(br_x2 - 40, tl_y1),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );
                    line(
                            frame,
                            new Point(br_x2, tl_y1),
                            new Point(br_x2, tl_y1 + 40),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );

                    //bottom-right
                    line(
                            frame,
                            new Point(br_x2, br_y2),
                            new Point(br_x2, br_y2 - 40),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );
                    line(
                            frame,
                            new Point(br_x2, br_y2),
                            new Point(br_x2 - 40, br_y2),
                            Scalar.GREEN,
                            2,
                            CV_AA,
                            0
                    );

                    faceCount++;

                }
                // Decrementar el contador por los rostros que ya no están presentes
                faceCount -= previousFaceDetections.size();

                // Almacenar los rostros detectados en fotogramas actuales para la próxima comparación
                previousFaceDetections.clear();
                for (int i = 0; i < faceDetections.size(); i++) {
                    previousFaceDetections.add(faceDetections.get(i));
                }

                // Puedes imprimir el contador o utilizarlo según sea necesario
                System.out.println("Número de rostros detectados en este fotograma: " + faceCount);

                imshow("Video", frame);
                int delay = 10;
                waitKey(delay);
            }
        }
    }

}
