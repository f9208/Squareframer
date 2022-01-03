package base.console;

import base.utils.wrapers.IFrameWrapper;
import base.utils.ImageFileHandler;
import base.utils.wrapers.RectangularWrapper;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartConsoleView {
    static ImageFileHandler fileHandler = new ImageFileHandler();
    static IFrameWrapper imageWrapper = new RectangularWrapper();

    public static void main(String[] args) {
        double frameSize = 50;
        String fileName = "76.jpg";
        Path pathToFile = Paths.get(fileName);
        Image source = fileHandler.openImage(pathToFile);
        Image result = imageWrapper.wrapImage(source, frameSize, frameSize, frameSize, frameSize);
        Path dest = Paths.get("framed_img");
        boolean successful = fileHandler.saveImage(result, dest, fileName, "jpg");
        System.out.println(successful ? "image have been saved" : "something get wrong");
    }
}