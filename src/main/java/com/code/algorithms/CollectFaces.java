package com.code.algorithms;

import static com.code.routes.Routes.FACE_DATA_FOLDER;
import static com.code.routes.Routes.HAARCASCADE_FOLDER;
import com.code.statics.FileManagement;
import static com.code.statics.FileManagement.existFile;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author Gian
 */
public class CollectFaces extends VideoProcessingAlgorithm implements FileManagement {

    private final CascadeClassifier faceClassifier;

    private final int LIMIT = 500;
    private int absoluteFaceSize = 0;
    private int count = 0;
    private int label = 0;

    private String name;
    private String folderPath;
    public static String folderName;
    private final String pathDataFaces = FACE_DATA_FOLDER.getRoute();

    private Mat grayFilter;
    private Mat face;
    private Mat auxFrame;

    private RectVector faceDetections;

    private Map<Integer, String> labelName = new HashMap<>();

    public CollectFaces() {
        this.faceClassifier = new CascadeClassifier(HAARCASCADE_FOLDER.getRoute());
        if (existFile("Label-Name.txt") && existFile("LabelValue.txt")) {
            labelName = recoverData("Label-Name.txt", labelName);
            label = recoverData("LabelValue.txt", label);
        }
    }

    @Override
    public Mat processFrame(Mat frame, int w, int h) {
        //redimenciona el video original
        resize(frame, frame, new Size(w, h));
        //Convierte el video a esacala de grises
        grayFilter = new Mat();
        cvtColor(frame, grayFilter, COLOR_BGR2GRAY);
        auxFrame = frame.clone();
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

        //Si encuatra rostros en el video dibuja un rectangulo
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
            if (count == 0) {
                label++;
                saveTextFile(
                        "LabelValue.txt",
                        String.valueOf(label)
                );
                labelName.put(label, name);
                saveTextFile("Label-Name.txt", labelName);
            }
            if (count < LIMIT) {
                face = new Mat(auxFrame, rect);
                resize(face, face, new Size(150, 150));
                String filePath = folderPath + "\\" + label + "-" + name + "_" + (count + 1) + ".jpg";
                imwrite(filePath, face);
                count++;
            }
        }
        return frame;
    }

    public void setName(String name) {
        this.name = name;
        folderName = (label + 1) + "_" + name;
        folderPath = pathDataFaces + folderName;
        File newFolder = new File(folderPath);
        newFolder.mkdir();
    }

    @Override
    public final <T> T recoverData(String fileName, T data) {
        Map<Integer, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (data instanceof Map) {
                    int labelPriv = Integer.parseInt(st.nextToken());
                    String namePriv = st.nextToken();
                    map.put(labelPriv, namePriv);
                } else if (data instanceof Integer lbl) {
                    lbl = Integer.valueOf(line);
                    return data = (T) lbl;
                } else {
                    throw new IllegalArgumentException("Tipo de dato no soportado");
                }
            }
            return data = (T) map;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
        return data;
    }

    public String getFolderPath() {
        return folderPath;
    }

}
