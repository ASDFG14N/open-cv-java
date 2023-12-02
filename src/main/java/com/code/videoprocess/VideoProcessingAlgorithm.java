package com.code.videoprocess;

import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 
 * @author Gian
 */
public abstract class VideoProcessingAlgorithm {
    public abstract Mat processFrame(Mat frame, int w, int h);
}