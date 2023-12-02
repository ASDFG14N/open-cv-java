package com.code.routes;

/**
 *
 * @author Gian
 */
public enum Routes {

    SEMAPHORE_IMG_RED("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\semaphore\\red.png"),
    SEMAPHORE_IMG_YELLOW("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\semaphore\\yellow.png"),
    SEMAPHORE_IMG_GREEN("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\semaphore\\green.png"),
    PLATE_IMG("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\plate.png"),
    VIDEO_P("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\vipP.mp4"),
    PATH_DATA_MONTH("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataMonths\\"),
    PATH_DATA_MONTH_OV("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataMonthsOV\\"),
    PATH_IMG_PLATES("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\img_plates\\"),
    PATH_PERSON_DB("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataPersons\\dataBasePersons.txt"),
    //==================================================
    HAARCASCADE_FOLDER("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\utils\\haarcascade_frontalface_default.xml"),
    FACE_DATA_FOLDER("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataFaces\\"),
    PATH_IMG_OV("/img_ov/"),
    PATH_TESSERACT("C:\\tesseract"),
    PATH_MODEL_XML("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\models_xml\\"),
    PATH_TRAINING_DIR("C:\\Users\\Gian\\Desktop\\CursoUCHJava\\pro\\src\\main\\resources\\dataFaces\\");

    private final String route;

    Routes(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
