package com.code.pro;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;
import static org.bytedeco.opencv.global.opencv_core.DFT_COMPLEX_INPUT;
import static org.bytedeco.opencv.global.opencv_core.bitwise_not;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_CHAIN_APPROX_SIMPLE;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_RETR_LIST;
import static org.bytedeco.opencv.global.opencv_imgproc.Canny;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_SIMPLEX;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.THRESH_BINARY_INV;
import static org.bytedeco.opencv.global.opencv_imgproc.adaptiveThreshold;
import static org.bytedeco.opencv.global.opencv_imgproc.boundingRect;
import static org.bytedeco.opencv.global.opencv_imgproc.contourArea;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.drawContours;
import static org.bytedeco.opencv.global.opencv_imgproc.findContours;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;
import org.bytedeco.opencv.opencv_core.AbstractScalar;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.tesseract.TessBaseAPI;

/**
 *
 * @author Gian
 */
public class RecoPla {

    public static void main(String[] args) {
//        int min_w = 50;
//        int max_w = 170;
//        int min_h = 25;
//        int max_h = 50;
//        double ratio = 2;
//        String imagePath = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\WhatsApp Image 2023-11-10 at 6.59.45 PM.jpeg";
//        Mat img = imread(imagePath);
//        resize(img, img, new Size(500, 500));
//        Mat imgGray = new Mat();
//        cvtColor(img, imgGray, COLOR_BGR2GRAY);
//        Mat thresh = new Mat();
//        threshold(imgGray, thresh, 140, 255, THRESH_BINARY_INV);
//        MatVector contours = new MatVector();
//        findContours(thresh, contours, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
//        Mat canvas = Mat.zeros(img.size(), CV_AA).asMat();
//        drawContours(canvas, contours, -1, Scalar.GREEN);
//
//        long n = contours.size();
//        MatVector contourVector = new MatVector(n);
//        for (long i = 0; i < n; i++) {
//            Mat contour = contours.get(i);
//
//            Rect rect = boundingRect(contour);
//
//            //Se calcula la relacion de aspecto de cada rectangulo
//            double aspect_ratio = (double) rect.width() / rect.height();
//
//            if ((Math.abs(aspect_ratio - ratio) < 0.5) && (rect.width() > min_w) && (rect.width() < max_w) && (rect.height() > min_h) && (rect.height() < max_h)) {
//                contourVector.put(contour);
//            }
//        }
//        Mat canvas2 = Mat.zeros(img.size(), CV_AA).asMat();
//        drawContours(canvas2, contourVector, -1, Scalar.GREEN);
//
//        Mat contour = contourVector.get(0);
//
//        Rect boundingRect = boundingRect(contour);
//        
//        Mat croppedRegion = new Mat(img, boundingRect);
//
//        cvtColor(croppedRegion, croppedRegion, COLOR_BGR2GRAY);
//        adaptiveThreshold(
//                croppedRegion,
//                croppedRegion,
//                255,
//                ADAPTIVE_THRESH_MEAN_C,
//                THRESH_BINARY_INV,
//                11,
//                2
//        );
//        bitwise_not(croppedRegion, croppedRegion);
//        int w = croppedRegion.cols() + 12;
//        int h = croppedRegion.rows() + 10;
//        resize(croppedRegion, croppedRegion, new Size(w, h));
//
//        String platePath = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\img_offenders\\placaPruebaOCR.jpg";
//        imwrite(platePath, croppedRegion);

        Mat plate = imread("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p10.jpeg");

        cvtColor(plate, plate, COLOR_BGR2GRAY);
        adaptiveThreshold(
                plate,
                plate,
                255,
                ADAPTIVE_THRESH_MEAN_C,
                THRESH_BINARY_INV,
                11,
                2
        );
        bitwise_not(plate, plate);
        String platePath = "C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\img_ov\\img.jpg";
        imwrite(platePath, plate);
        imshow("Video", plate);
        int delay = 0;
        waitKey(delay);
        TessBaseAPI inst = new TessBaseAPI();
        if (inst.Init("C:\\tesseract", "eng") != 0) {
            System.out.println("could not initialize tesseract");
            System.exit(1);
        }
        PIX image = pixRead("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\img_ov\\img.jpg");
        if (image == null) {
            System.err.println("Could not opened the image or Image not found ");

        }
        inst.SetImage(image);

        BytePointer outText;
        outText = inst.GetUTF8Text();

        String output = outText.getString();

        // Obtener la confianza de Tesseract
        float meanConfidence = inst.MeanTextConf();
        System.out.println("Mean confidence: " + meanConfidence);

        inst.End();
        outText.deallocate();
        pixDestroy(image);

        System.out.println(output);

    }

}
