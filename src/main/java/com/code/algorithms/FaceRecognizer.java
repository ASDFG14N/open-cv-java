package com.code.algorithms;

import static com.code.routes.Routes.HAARCASCADE_FOLDER;
import static com.code.routes.Routes.PATH_MODEL_XML;
import com.code.statics.FileManagement;
import static com.code.statics.FileManagement.existFile;
import com.code.videoprocess.VideoProcessingAlgorithm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.IntBuffer;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_core.DFT_COMPLEX_INPUT;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.INTER_CUBIC;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
import static org.bytedeco.opencv.global.opencv_imgproc.line;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_objdetect.CASCADE_SCALE_IMAGE;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.BasicFaceRecognizer;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

/**
 *
 * @author Gian
 */
public class FaceRecognizer extends VideoProcessingAlgorithm implements FileManagement {

    private final CascadeClassifier faceClassifier;
    private final BasicFaceRecognizer faceRecognizer;
    private Mat grayFilter;
    private RectVector faceDetections;
    private String trainingDir;
    private File root;
    private IntBuffer labelsBuf;
    private int counter;
    private int absoluteFaceSize = 0;
    private Map<Integer, String> labelName = new HashMap<>();

    public FaceRecognizer() {
        this.faceRecognizer = EigenFaceRecognizer.create();
        this.counter = recoverCounter();
        this.faceClassifier = new CascadeClassifier(HAARCASCADE_FOLDER.getRoute());
        labelName = existFile("Label-Name.txt")
                ? recoverData("Label-Name.txt", labelName)
                : labelName;
    }

    @Override
    public Mat processFrame(Mat frame, int w, int h) {
        resize(frame, frame, new Size(950, 600));
        grayFilter = new Mat();
        cvtColor(frame, grayFilter, COLOR_BGR2GRAY);
        equalizeHist(grayFilter, grayFilter);
        if (absoluteFaceSize == 0) {
            int height = grayFilter.rows();
            if (Math.round(height * 0.2f) > 0) {
                absoluteFaceSize = Math.round(height * 0.2f);
            }
        }
        Mat auxFrame = grayFilter.clone();
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
            Mat face = new Mat(
                    auxFrame,
                    new Rect(
                            rect.x(),
                            rect.y(),
                            rect.width(),
                            rect.height()
                    )
            );
            resize(face,
                    face,
                    new Size(150, 150),
                    0,
                    0,
                    INTER_CUBIC
            );
            IntPointer label = new IntPointer(1);
            DoublePointer confidence = new DoublePointer(0);
            faceRecognizer.predict(face, label, confidence);
            int predictedLabel = label.get(0);
            double confidenceValue = confidence.get(0);
            String lbl;
            Scalar rectColor;

            if (confidenceValue < 7500) {
                lbl = labelName.get(predictedLabel);
                rectColor = Scalar.GREEN;
            } else {
                lbl = "Desconocido";
                rectColor = Scalar.RED;
            }

            Point textPosition = new Point(rect.x(), rect.y() - 8);

            putText(frame,
                    lbl,
                    textPosition,
                    DFT_COMPLEX_INPUT,
                    0.8,
                    rectColor,
                    2,
                    LINE_AA,
                    false
            );
            drawBorder(
                    frame,
                    new Point(rect.x(), rect.y()),
                    new Point(rect.x() + rect.width(), rect.y() + rect.height()),
                    rectColor
            );
        }
        resize(frame, frame, new Size(w, h));
        return frame;
    }

    private void drawBorder(Mat mat, Point topLeft, Point bottomRight, Scalar color) {
        int tl_x1 = topLeft.x();
        int tl_y1 = topLeft.y();
        int br_x2 = bottomRight.x();
        int br_y2 = bottomRight.y();

        //top-left
        line(
                mat,
                new Point(tl_x1, tl_y1),
                new Point(tl_x1, tl_y1 + 40),
                color,
                3,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(tl_x1, tl_y1),
                new Point(tl_x1 + 40, tl_y1),
                color,
                3,
                CV_AA,
                0
        );

        //bottom-left
        line(
                mat,
                new Point(tl_x1, br_y2),
                new Point(tl_x1, br_y2 - 40),
                color,
                3,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(tl_x1, br_y2),
                new Point(tl_x1 + 40, br_y2),
                color,
                3,
                CV_AA,
                0
        );

        //top-right
        line(
                mat,
                new Point(br_x2, tl_y1),
                new Point(br_x2 - 40, tl_y1),
                color,
                3,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(br_x2, tl_y1),
                new Point(br_x2, tl_y1 + 40),
                color,
                3,
                CV_AA,
                0
        );

        //bottom-right
        line(
                mat,
                new Point(br_x2, br_y2),
                new Point(br_x2, br_y2 - 40),
                color,
                3,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(br_x2, br_y2),
                new Point(br_x2 - 40, br_y2),
                color,
                3,
                CV_AA,
                0
        );
    }

    private File[] searchImageDirectory() {
        root = new File(trainingDir);
        if (!root.exists() || !root.isDirectory()) {
            throw new RuntimeException("El directorio de entrenamiento no existe o no es un directorio válido.");
        }

        FilenameFilter imgFilter = (File dir, String name) -> {
            name = name.toLowerCase();
            return name.endsWith(".jpg")
                    || name.endsWith(".pgm")
                    || name.endsWith(".png");
        };
        return root.listFiles(imgFilter);
    }

    private ArrayList tagImages() {
        ArrayList mat = new ArrayList();
        File[] imageFiles = searchImageDirectory();
        MatVector images = new MatVector(imageFiles.length);
        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        labelsBuf = labels.createBuffer();
        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), IMREAD_GRAYSCALE);
            int label = Integer.parseInt(image.getName().split("\\-")[0]);
            System.out.println("El valor de label es: " + label);
            images.put(counter, img);
            labelsBuf.put(counter, label);
            counter++;
        }
        mat.add(images);
        mat.add(labels);
        saveCounter(counter);
        return mat;
    }

    public void trainModel(String trainingDir, String modelFileName) {
        this.trainingDir = trainingDir;
        ArrayList mat = tagImages();
        MatVector images = (MatVector) mat.get(0);
        Mat labels = (Mat) mat.get(1);
        System.out.println("Entrenando...");
        faceRecognizer.train(images, labels);
        faceRecognizer.write(PATH_MODEL_XML.getRoute() + modelFileName + ".xml");
        System.out.println("Entrenamiento finalizado");
    }

    private void saveCounter(int counter) {
        try {
            FileWriter fr = new FileWriter("CounterValue.txt");
            try (PrintWriter pw = new PrintWriter(fr)) {
                pw.println(counter);
            }
        } catch (IOException ex) {
            System.out.println("Error en la grabación: " + ex.toString());
        }
    }

    private int recoverCounter() {
        int count = 0;
        try {
            FileReader fr = new FileReader("CounterValue.txt");
            try (BufferedReader br = new BufferedReader(fr)) {
                String linea = br.readLine();
                if (linea != null) {
                    count = Integer.parseInt(linea);
                } else {
                    count = 0;
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error en la lectura: " + ex.toString());
        }

        return count;
    }

    public void setModelDir(String modelDir) {
        this.faceRecognizer.read(modelDir + ".xml");
    }

    @Override
    public final <T> T recoverData(String fileName, T data) {
        Map<Integer, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                int labelPriv = Integer.parseInt(st.nextToken());
                String namePriv = st.nextToken();
                map.put(labelPriv, namePriv);
            }
            return data = (T) map;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        return data;
    }
}
