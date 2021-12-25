package base.console;

import base.utils.IFrameWrapper;
import base.utils.ImageFileHandler;
import base.utils.RectangularWrapper;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StartConsoleView {
    static ImageFileHandler fileHandler = new ImageFileHandler();
    static IFrameWrapper imageWrapper = new RectangularWrapper();

    public static void main(String[] args) {
        int frameSize = 0;
        try {
            frameSize = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.err.println("первым аргументом должен сто€ть размер рамки");
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        String fileName;
        while (true) {
            fileName = scanner.nextLine();
            Path pathToFile = Paths.get(fileName);
            Image source = fileHandler.openImage(pathToFile);
            Image result = imageWrapper.wrapImage(source, frameSize);
            Path dest = Paths.get("framed_img");
            boolean successful = fileHandler.saveImage(result, dest, fileName, "jpg");
            System.out.println(successful ? "image have been saved" : "something get wrong");
        }
    }
}

