package com.code.algorithms;

import static com.code.routes.Routes.HAARCASCADE_FOLDER;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
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
import com.code.videoprocess.VideoProcessingAlgorithm;

/**
 *
 * @author Gian
 */
public class FaceDetect extends VideoProcessingAlgorithm {

    private final CascadeClassifier faceClassifier;
    private Mat grayFilter;
    private RectVector faceDetections;
    private int absoluteFaceSize = 0;

    private int faceCount = 0;

    public FaceDetect() {
        this.faceClassifier = new CascadeClassifier(HAARCASCADE_FOLDER.getRoute());
    }

    @Override
    public Mat processFrame(Mat frame, int w, int h) {
        resize(frame, frame, new Size(w, h));
        grayFilter = new Mat();
        cvtColor(frame, grayFilter, COLOR_BGR2GRAY);
        equalizeHist(grayFilter, grayFilter);
        if (absoluteFaceSize == 0) {
            int height = grayFilter.rows();
            if (Math.round(height * 0.2f) > 0) {
                absoluteFaceSize = Math.round(height * 0.2f);
            }
        }
        faceDetections = new RectVector();
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
            rectangle(
                    frame,
                    new Point(rect.x(), rect.y()),
                    new Point(rect.x() + rect.width(), rect.y() + rect.height()),
                    Scalar.GREEN,
                    3,
                    LINE_AA,
                    0
            );
            faceCount++;
        }
        return frame;
    }

}
