package com.code.pro;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_face.BasicFaceRecognizer;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;

public class Face {

    public static void main(String[] args) {
        File root;
        MatVector images;
        String trainingDir = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\OpenCVPractice\\src\\rostros";
        root = new File(trainingDir);
        FilenameFilter imgFilter = (File dir, String name) -> {
            name = name.toLowerCase();
            return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
        };
        File[] imageFiles = root.listFiles(imgFilter);
        images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();
        int counter = 0;
        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), IMREAD_GRAYSCALE);
            imshow("img", img);
            waitKey(10);
            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);
            labelsBuf.put(counter, label);
            counter++;
        }
        
        System.out.println(counter);

        BasicFaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
        System.out.println("Entrenando");
        faceRecognizer.train(images, labels);
        //faceRecognizer.write("modeloAI.xml");
        System.out.println("Finalizado");

        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        Mat testImage = imread("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\OpenCVPractice\\src\\rostros\\2-kanon_kgw_127.jpg", IMREAD_GRAYSCALE);
        faceRecognizer.predict(testImage, label, confidence);
        int predictedLabel = label.get(0);

        System.out.println("Predicted label: " + predictedLabel);

    }

}
