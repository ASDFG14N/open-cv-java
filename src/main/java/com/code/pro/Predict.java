package com.code.pro;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_core.DFT_COMPLEX_INPUT;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_SIMPLEX;
import static org.bytedeco.opencv.global.opencv_imgproc.INTER_CUBIC;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_objdetect.CASCADE_SCALE_IMAGE;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.BasicFaceRecognizer;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

/**
 *
 * @author Gian
 */
public class Predict {

    public static void main(String[] args) {

        int absoluteFaceSize = 0;
        BasicFaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
        faceRecognizer.read("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\ia\\modeloAI.xml");
        VideoCapture capture = new VideoCapture("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\OpenCVPractice\\src\\img\\vid2.mp4");
        CascadeClassifier faceClassifier = new CascadeClassifier("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\OpenCVPractice\\src\\arcXml\\haarcascade_frontalface_default.xml");
        while (true) {
            Mat frame = new Mat();
            if (capture.read(frame)) {
                //resize(frame, frame, new Size(640, 400));
                Mat gray = new Mat();
                resize(frame, frame, new Size(335, 600));
                cvtColor(frame, gray, COLOR_BGR2GRAY);
                equalizeHist(gray, gray);
                if (absoluteFaceSize == 0) {
                    int height = gray.rows();
                    if (Math.round(height * 0.2f) > 0) {
                        absoluteFaceSize = Math.round(height * 0.2f);
                    }
                }
                Mat auxFrame = gray.clone();
                RectVector faceDetections = new RectVector();
                faceClassifier.detectMultiScale(gray, faceDetections, 1.3, 5, CASCADE_SCALE_IMAGE, new Size(absoluteFaceSize, absoluteFaceSize), new Size());
                for (int i = 0; i < faceDetections.size(); i++) {
                    Rect rect = faceDetections.get(i);
                    Mat rostro = new Mat(auxFrame, new Rect(rect.x(), rect.y(), rect.width(), rect.height()));
                    // Redimensionar el rostro a 150x150
                    resize(rostro, rostro, new Size(150, 150), 0, 0, INTER_CUBIC);
                    IntPointer label = new IntPointer(1);
                    DoublePointer confidence = new DoublePointer(0);
                    faceRecognizer.predict(rostro, label, confidence);
                    //System.out.println(confidence.get());
                    int predictedLabel = label.get(0);
                    double confidenceValue = confidence.get(0);
                    System.out.println(confidenceValue);

                    String lbl;
                    Scalar rectColor;

                    if (confidenceValue < 7500) {
                        lbl = String.valueOf("kanon_kgw"); // Nombre o etiqueta conocida
                        rectColor = Scalar.GREEN;
                    } else {
                        lbl = "Desconocido"; // Rostro desconocido
                        rectColor = Scalar.RED;
                    }

                    Point textPosition = new Point(rect.x(), rect.y() - 5);

                    putText(frame, lbl, textPosition, DFT_COMPLEX_INPUT, 0.8, rectColor, 2, LINE_AA, false);

                    rectangle(frame, new Point(rect.x(), rect.y()), new Point(rect.x() + rect.width(), rect.y() + rect.height()), rectColor, 3, LINE_AA, 0);
                }

                imshow("Video", frame);
                int delay = 10;
                waitKey(delay);
            }

        }

    }

}
