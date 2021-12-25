package base.fx;

import base.utils.IFrameWrapper;
import base.utils.ImageFileHandler;
import base.utils.RectangularWrapper;
import javafx.application.Platform;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConverterBindFx {
    ImageFileHandler imageFileHandler = new ImageFileHandler();
    IFrameWrapper imageWrapper;
    MainController controller;

    public ConverterBindFx(MainController controller) {
        this.imageWrapper = new RectangularWrapper();
        this.controller = controller;
    }

    public ConverterBindFx(MainController controller, IFrameWrapper imageWrapper) {
        this.imageWrapper = imageWrapper;
        this.controller = controller;
    }


    public void convert(List<File> listFiles, int sizeFrame) {
        List<String> invalidFiles = new ArrayList<>();
        for (File file : listFiles
        ) {
            Platform.runLater(() -> controller.getStatus().setText("обрабатываю " + file.getName()));
            Image source = null;
            try {
                source = imageFileHandler.openImageFile(file);
            } catch (FileNotFoundException fileNotFoundException) {
                invalidFiles.add(file.getName());
            }
            Image result = imageWrapper.wrapImage(source, sizeFrame);
            Path dest = Paths.get("framed_img");
            boolean successful = imageFileHandler.saveImage(result, dest, file.getName(), "jpg");
            System.out.println(successful ? "image have been saved" : "something get wrong");
        }
        if (!invalidFiles.isEmpty()) {
            Platform.runLater(() -> controller.getStatus().setText("некоторые из файлов не удалось открыть"));
        } else {
            Platform.runLater(() -> controller.getStatus().setText("закончил"));
        }
    }
}
