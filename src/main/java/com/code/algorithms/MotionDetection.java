package com.code.algorithms;

import static com.code.algorithms.MotionDetection.RecognitionPlates.plateNumber;
import com.code.model.Time;
import com.code.routes.Routes;
import static com.code.routes.Routes.PATH_IMG_OV;
import static com.code.routes.Routes.PATH_IMG_PLATES;
import static com.code.routes.Routes.PATH_TESSERACT;
import static com.code.routes.Routes.VIDEO_P;
import static org.bytedeco.opencv.global.opencv_bgsegm.createBackgroundSubtractorMOG;
import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_core.bitwise_and;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_CHAIN_APPROX_SIMPLE;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_RETR_LIST;
import static org.bytedeco.opencv.global.opencv_imgproc.MORPH_CLOSE;
import static org.bytedeco.opencv.global.opencv_imgproc.MORPH_ELLIPSE;
import static org.bytedeco.opencv.global.opencv_imgproc.MORPH_OPEN;
import static org.bytedeco.opencv.global.opencv_imgproc.boundingRect;
import static org.bytedeco.opencv.global.opencv_imgproc.contourArea;
import static org.bytedeco.opencv.global.opencv_imgproc.dilate;
import static org.bytedeco.opencv.global.opencv_imgproc.findContours;
import static org.bytedeco.opencv.global.opencv_imgproc.getStructuringElement;
import static org.bytedeco.opencv.global.opencv_imgproc.line;
import static org.bytedeco.opencv.global.opencv_imgproc.morphologyEx;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_bgsegm.BackgroundSubtractorMOG;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.AbstractScalar;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import com.code.videoprocess.VideoProcessingAlgorithm;
import static com.code.view.Form1.modelMessages;
import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import static org.bytedeco.leptonica.global.leptonica.pixDestroy;
import static org.bytedeco.leptonica.global.leptonica.pixRead;
import static org.bytedeco.opencv.global.opencv_core.DFT_COMPLEX_INPUT;
import static org.bytedeco.opencv.global.opencv_core.bitwise_not;
import static org.bytedeco.opencv.global.opencv_highgui.destroyAllWindows;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_SIMPLEX;
import static org.bytedeco.opencv.global.opencv_imgproc.LINE_AA;
import static org.bytedeco.opencv.global.opencv_imgproc.THRESH_BINARY_INV;
import static org.bytedeco.opencv.global.opencv_imgproc.adaptiveThreshold;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.pointPolygonTest;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.tesseract.TessBaseAPI;

/**
 *
 * @author Gian
 */
public class MotionDetection extends VideoProcessingAlgorithm {

    private final BackgroundSubtractorMOG fgbg;
    private final Mat kernel;
    private Mat mask;
    private MatVector contours;
    private Mat imgArea;
    private Mat fgmask;
    private Mat PLATE_IMG;

    private final List<java.awt.Point> points;

    private int x0;
    private int y0;
    private int x2;
    private int y2;

    private int startPointX;
    private int startPointY;
    private int targetPointX;
    private int targetPointY;
    private int lineLimitInfX;
    private int lineLimitInfY;
    private int lineLimitSupX;

    private int countTotalCars = 0;
    private int countCarsInArea = 0;
    private int countOffendingVehicle = 0;

    private Map<Integer, int[]> centralCoordinates = new HashMap<>();
    private Map<Integer, Long> dicCar = new HashMap<>();
    private Map<Integer, Double> mapId_Speed = new HashMap<>();
    private List<Double> speedsToday = new ArrayList<>();
    private int idCount = 0;

    private int idCar;

    private Time time;

    public MotionDetection(List<java.awt.Point> points) {
        fgbg = createBackgroundSubtractorMOG();
        kernel = getStructuringElement(
                MORPH_ELLIPSE,
                new Size(3, 3)
        );
        this.points = points;
        this.time = new Time();
        //Lee la imagen de la placa
        PLATE_IMG = imread(Routes.PLATE_IMG.getRoute());
        resize(PLATE_IMG, PLATE_IMG, new Size(120, 60));
    }

    @Override
    public Mat processFrame(Mat frame, int w, int h) {
        //Ubica los las coordenadas de entrada
        locatePoint();
        //Redimenaciona el video de entrada(frame)
        resize(frame, frame, new Size(w, h));
        //Crea una mascara negra con las mismas dimensiones del frame
        mask = Mat.zeros(frame.size(), CV_AA).asMat();
        //Dibuja un rectangulo de color blanco y delimita el area de influencia
        drawBoundingRect(mask, Scalar.WHITE, -1);
        imgArea = new Mat();
        bitwise_and(frame, mask, imgArea);
        //Sirve para a sustraccion de fondo y las transformaciones morfologicas
        fgmask = new Mat();
        fgbg.apply(imgArea, fgmask);
        morphologyEx(
                fgmask,
                fgmask,
                MORPH_OPEN,
                kernel
        );
        morphologyEx(
                fgmask,
                fgmask,
                MORPH_CLOSE,
                kernel
        );
        dilate(
                fgmask,
                fgmask,
                new Mat(),
                new Point(-1, -1),
                9,
                CV_8U,
                AbstractScalar.ZERO
        );
        //==================================================================
        //Busca contornos
        contours = new MatVector();
        findContours(
                fgmask,
                contours,
                CV_RETR_LIST,
                CV_CHAIN_APPROX_SIMPLE
        );
        List<Rect> detections = new ArrayList<>();
        long n = contours.size();
        for (long i = 0; i < n; i++) {
            Mat contour = contours.get(i);
            if (contourArea(contour) > 1600) {
                Rect boundingRect = boundingRect(contour);
                int x = boundingRect.x();
                int y = boundingRect.y();
                int width = boundingRect.width();
                int height = boundingRect.height();

                drawInitialLimit(x, width);
                drawFinalLimit(x, width, frame);

                detections.add(
                        new Rect(
                                x,
                                y,
                                width,
                                height
                        )
                );
            }
        }
        //Este metodo se utiliza para que dado un contorno se le asigne un
        //identificador unico
        List<Object[]> inf_id = carIdentifier(detections);
        System.out.println("El valor de inf_id es: " + inf_id.size());

        //Dibujar rectangulo y poner velocidad + id
        for (Object[] info : inf_id) {
            int x = (int) info[0];
            int y = (int) info[1];
            int width = (int) info[2];
            int height = (int) info[3];
            idCar = (int) info[4];
            System.out.println("El primer valor de idCar es: " + idCar);
            //Dibuja un rectagulo alrededor del vehiculo
            drawBorder(frame, new Point(x, y), new Point(x + width, y + height));

            //punto central de los objetos encontrados
            int cx = (int) (x + width / 2);
            int cy = (int) (y + height / 2);

            //Imagen auxiliar(todo de negro)
            Mat imgAux = Mat.zeros(frame.size(), CV_8U).asMat();
            //Primer area
            rectangle(imgAux,
                    new Point(lineLimitInfX, y0),
                    new Point(lineLimitInfX + 300, y2),
                    Scalar.WHITE,
                    1,
                    CV_AA,
                    0
            );
            //Segunda area
            rectangle(imgAux,
                    new Point(lineLimitInfX + 305, y0),
                    new Point(lineLimitInfX + 600, y2),
                    Scalar.WHITE,
                    1,
                    CV_AA,
                    0
            );
            //Punto central del rectangulo
            Point2f pt = new Point2f(cx, cy);
            MatVector contour = new MatVector();
            findContours(imgAux.clone(),
                    contour,
                    CV_RETR_LIST,
                    CV_CHAIN_APPROX_SIMPLE
            );
            //Este metodo sirve para saber si un punto está dentro de un area
            //determinada que se pasa como primer parametro
            double a1 = pointPolygonTest(
                    contour.get(1),
                    pt,
                    false
            );
            //Si es positivo, entonces cx y cy están en esa area
            if (a1 >= 0) {
                long startTimeMillis = currentTimeMillis();
                //se guarda en el hashmap con llave id y valor el tiempo actual
                dicCar.put(idCar, startTimeMillis);
            }
            //Si el hash tiene una llave igual a idCar
            if (dicCar.containsKey(idCar)) {
                double a2 = pointPolygonTest(
                        contour.get(2),
                        pt,
                        false
                );
                //Si entró al area 2
                if (a2 >= 0) {
                    //Tomar el tiempo
                    long finishTimeMillis = currentTimeMillis() - dicCar.get(idCar);
                    //Convierte a timeSeconds
                    double timeSeconds = (double) finishTimeMillis / 1000.0;
                    timeSeconds = timeSeconds + 0.55;
                    int velocity = 0;
                    if (!mapId_Speed.containsKey(idCar)) {
                        // La clave idCar NO existe en el HashMap
                        mapId_Speed.put(idCar, timeSeconds);
                    } else {
                        // La clave idCar YA existe en el HashMap
                        velocity = (int) (10 / mapId_Speed.get(idCar));
                        velocity = (int) (velocity * 3.6);
                    }
                    putText(
                            frame,
                            Integer.toString(velocity) + " km/h",
                            new Point(x + 30, y - 5),
                            DFT_COMPLEX_INPUT,
                            0.5,
                            Scalar.BLACK,
                            1,
                            LINE_AA,
                            false
                    );
                }

            }
            putText(
                    frame,
                    Integer.toString(idCar),
                    new Point(x, y - 5),
                    DFT_COMPLEX_INPUT,
                    0.5,
                    Scalar.BLACK,
                    1,
                    LINE_AA,
                    false
            );

        }//Fin del for idCar

        //Todos estos metodos sirven para vizualizar algunos datos en la pantalla
        drawBoundingRect(frame, Scalar.GREEN, 2);
        userCounterArea(frame, w);
        drawLine(frame, Scalar.YELLOW, 2);
        line(
                frame,
                new Point(lineLimitInfX, y0),
                new Point(lineLimitInfX, y2),
                Scalar.RED,
                2,
                CV_AA,
                0
        );
        line(
                frame,
                new Point(lineLimitInfX + 300, y0),
                new Point(lineLimitInfX + 300, y2),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        line(
                frame,
                new Point(lineLimitInfX + 600, y0),
                new Point(lineLimitInfX + 600, y2),
                Scalar.BLUE,
                2,
                CV_AA,
                0
        );
        putPlateImg(frame, w);
        putDateAndTime(frame);
        return frame;
    }//Fin del metodo abstracto

    //===================================================================
    //===================================================================
    //===================================================================
    private void locatePoint() {
        x0 = points.get(0).x;
        y0 = points.get(0).y;
        x2 = points.get(2).x;
        y2 = points.get(2).y;
        lineLimitInfX = x0 + 70;
        lineLimitInfY = y2;
        lineLimitSupX = x2;
        lineLimitSupX = y0;
        startPointX = x0 + (x2 - x0) - 50;
        startPointY = points.get(0).y;
        targetPointX = points.get(2).x - 50;
        targetPointY = points.get(2).y;
    }

    private void drawBoundingRect(Mat mat, Scalar color, int thickness) {
        rectangle(
                mat,
                new Point(x0, y0),
                new Point(x2, y2),
                color,
                thickness,
                CV_AA,
                0
        );
    }

    private void drawInitialLimit(int x, int wi) {
        if (lineLimitInfX - 15 < (x + wi) && (x + wi) < lineLimitInfX + 15) {
            countCarsInArea++;
        }
    }

    //Limite del semaforo
    private void drawFinalLimit(int x, int wi, Mat frame) {
        if (startPointX - 15 < (x + wi) && (x + wi) < startPointX + 15) {
            System.out.println("El id es: " + idCar);
            countTotalCars++;
            //Incrementar el valor de los vehiculos que cruzaron la linea amarilla
            countOffendingVehicle++;
            RecognitionPlates.isRuleViolation = true;
            Object[] rowData = {
                "[" + time.getCurrentTime2() + "]",
                "ID: " + idCar + " "
                + "N° Placa: " + plateNumber
            };
            modelMessages.addRow(rowData);
            //int lastRow = asyncMessagesBody.getRowCount() - 1;
            //asyncMessagesBody.scrollRectToVisible(asyncMessagesBody.getCellRect(lastRow, 0, true));
            if (countCarsInArea > 0) {
                countCarsInArea--;
            } else {
                countCarsInArea = 0;
            }
            drawLine(frame, Scalar.RED, 5);
        }
    }

    private void drawLine(Mat mat, Scalar color, int thickness) {
        line(
                mat,
                new Point(startPointX, startPointY),
                new Point(targetPointX, targetPointY),
                color,
                thickness,
                CV_AA,
                0
        );
    }

    private void drawBorder(Mat mat, Point topLeft, Point bottomRight) {
        int tl_x1 = topLeft.x();
        int tl_y1 = topLeft.y();
        int br_x2 = bottomRight.x();
        int br_y2 = bottomRight.y();

        //top-left
        line(
                mat,
                new Point(tl_x1, tl_y1),
                new Point(tl_x1, tl_y1 + 20),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(tl_x1, tl_y1),
                new Point(tl_x1 + 20, tl_y1),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );

        //bottom-left
        line(
                mat,
                new Point(tl_x1, br_y2),
                new Point(tl_x1, br_y2 - 20),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(tl_x1, br_y2),
                new Point(tl_x1 + 20, br_y2),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );

        //top-right
        line(
                mat,
                new Point(br_x2, tl_y1),
                new Point(br_x2 - 20, tl_y1),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(br_x2, tl_y1),
                new Point(br_x2, tl_y1 + 20),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );

        //bottom-right
        line(
                mat,
                new Point(br_x2, br_y2),
                new Point(br_x2, br_y2 - 20),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
        line(
                mat,
                new Point(br_x2, br_y2),
                new Point(br_x2 - 20, br_y2),
                Scalar.GREEN,
                2,
                CV_AA,
                0
        );
    }

    private void userCounterArea(Mat mat, int w) {
        rectangle(
                mat,
                new Point(w - 80, 3),
                new Point(w - 3, 85),
                Scalar.GREEN,
                3,
                CV_AA,
                0
        );
        putText(mat,
                String.valueOf(countCarsInArea),
                new Point(w - 80, 68),
                DFT_COMPLEX_INPUT,
                2,
                Scalar.GREEN,
                2,
                LINE_AA,
                false
        );
    }

    private void putDateAndTime(Mat frame) {
        putText(
                frame,
                time.getCurrentDate(),
                new Point(5, 20),
                FONT_HERSHEY_SIMPLEX,
                0.7,
                Scalar.BLACK,
                2,
                LINE_AA,
                false
        );
    }

    private List<Object[]> carIdentifier(List<Rect> detections) {
        List<Object[]> objetosId = new ArrayList<>();
        for (Rect rect : detections) {
            int x = rect.x();
            int y = rect.y();
            int w = rect.width();
            int h = rect.height();
            int cx = (x + x + w) / 2;
            int cy = (y + y + h) / 2;

            boolean objDet = false;

            for (Map.Entry<Integer, int[]> entry : centralCoordinates.entrySet()) {
                int id = entry.getKey();
                int[] pt = entry.getValue();
                double dist = Math.hypot(cx - pt[0], cy - pt[1]);

                if (dist < 45) {
                    centralCoordinates.put(id, new int[]{cx, cy});
                    objetosId.add(new Object[]{x, y, w, h, id});
                    objDet = true;
                    break;
                }
            }

            if (!objDet) {
                centralCoordinates.put(idCount, new int[]{cx, cy});
                objetosId.add(new Object[]{x, y, w, h, idCount});
                idCount++;
            }
        }

        Map<Integer, int[]> newCenterPoints = new HashMap<>();

        for (Object[] objBbId : objetosId) {
            int object_id = (int) objBbId[4];
            int[] center = centralCoordinates.get(object_id);
            newCenterPoints.put(object_id, center);
        }

        centralCoordinates = newCenterPoints;

        return objetosId;
    }

    private void putPlateImg(Mat frame, int w) {
        int x = w - 220;
        int y = 5;
        Mat plateCopy = PLATE_IMG.clone();
        putText(
                plateCopy,
                String.valueOf(plateNumber),
                new Point(12, 40),
                DFT_COMPLEX_INPUT,
                0.7,
                Scalar.BLACK,
                1,
                LINE_AA,
                false
        );
        plateCopy.copyTo(
                frame.rowRange(y, y + plateCopy.rows())
                        .colRange(x, x + plateCopy.cols())
        );
    }

    public int getCountTotalCars() {
        return countTotalCars;
    }

    public List<Double> getSpeedsToday() {
        return speedsToday;
    }

    /*
    ===============================================================
    ===============================================================
    ===============================================================
     */
    public class RecognitionPlates {

        private ArrayList<String> imgs = new ArrayList<>();

        private int min_w = 80;
        private int max_w = 110;
        private int min_h = 25;
        private int max_h = 52;
        private double ratio = 3.07692307692;
        public static boolean isRuleViolation = false;
        private Time time = new Time();
        public static String plateNumber = "";
        private int count = 0;

        public RecognitionPlates() {
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p1.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p2.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p3.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p4.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p5.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p6.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p7.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p8.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p9.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p10.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p11.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p13.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p14.jpeg");
            imgs.add("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\java\\dataset\\p15.jpeg");
        }

        public void initVideoCapture() {
            //Acá debe ir el video de los autos
            VideoCapture capture = new VideoCapture(VIDEO_P.getRoute());
            while (true) {
                Mat frame = new Mat();
                if (capture.read(frame)) {
                    resize(frame, frame, new Size(950, 550));
                    if ((isRuleViolation) && (count < imgs.size())) {
                        //String path = takePhoto(frame);
                        //Mat croppedRegion = croppPlates(path);
                        Mat plateImg = imread(imgs.get(count));
                        Mat binarizedImg = binarizePlateImg(plateImg);
                        saveImg(PATH_IMG_PLATES.getRoute() + count + ".jpg", binarizedImg);
                        plateNumber = characterRecognition(PATH_IMG_PLATES.getRoute() + count + ".jpg");
                        isRuleViolation = false;
                        count++;
                    }
                    System.out.println("video reproduciendo");
//                    imshow("Video", frame);
//                    int delay = 10;
//                    waitKey(delay);
                } else {
                    break;
                }
            }
            capture.release();
            destroyAllWindows();
        }

        private String takePhoto(Mat frame) {
            String filePath = PATH_IMG_OV.getRoute() + 15 + ".jpg";
            saveImg(filePath, frame);
            return filePath;
        }

        private Mat croppPlates(String imgPath) {
            Mat img = imread(imgPath);
            Mat imgGray = new Mat();
            cvtColor(img, imgGray, COLOR_BGR2GRAY);
            Mat thresh = new Mat();
            threshold(
                    imgGray,
                    thresh,
                    170,
                    255,
                    THRESH_BINARY_INV
            );
            MatVector contours = new MatVector();
            findContours(
                    thresh,
                    contours,
                    CV_RETR_LIST,
                    CV_CHAIN_APPROX_SIMPLE
            );
            long n = contours.size();
            MatVector contourVector = new MatVector(n);
            for (long i = 0; i < n; i++) {
                Mat contour = contours.get(i);

                Rect rect = boundingRect(contour);

                double aspect_ratio = (double) rect.width() / rect.height();

                if (Math.abs(aspect_ratio - ratio) < 0.7
                        && rect.width() > min_w && rect.width() < max_w
                        && rect.height() > min_h && rect.height() < max_h) {
                    contourVector.put(contour);
                }
            }
            Mat contour = contourVector.get(0);
            Rect boundingRect = boundingRect(contour);
            return new Mat(img, boundingRect);
        }

        private Mat binarizePlateImg(Mat img) {
            cvtColor(img, img, COLOR_BGR2GRAY);
            adaptiveThreshold(
                    img,
                    img,
                    255,
                    ADAPTIVE_THRESH_MEAN_C,
                    THRESH_BINARY_INV,
                    11,
                    2
            );
            bitwise_not(img, img);
            return img;
        }

        private void saveImg(String path, Mat img) {
            imwrite(path, img);
        }

        private String characterRecognition(String pathImg) {
            TessBaseAPI inst = new TessBaseAPI();
            if (inst.Init(PATH_TESSERACT.getRoute(), "eng") != 0) {
                System.out.println("No se pudo inicializar tesseract");
                System.exit(1);
            }
            PIX image = pixRead(pathImg);
            if (image == null) {
                System.err.println("Error xd");
            }
            inst.SetImage(image);
            BytePointer outText;
            outText = inst.GetUTF8Text();

            String output = outText.getString();

            inst.End();
            outText.deallocate();
            pixDestroy(image);
            return output;
        }

    }

}
