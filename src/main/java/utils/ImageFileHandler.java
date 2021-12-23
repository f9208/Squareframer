package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageFileHandler {
    public Image openImage(Path path) {
        BufferedImage result = null;
        try {
            result = ImageIO.read(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveImage(Image image, Path destinationFolder, String fileName, String formatName) {
        boolean result = false;
        File folder = destinationFolder.toFile();
        if (!folder.exists()) {
            folder.mkdir();
        }
        Path inFolder = Paths.get(destinationFolder.toString(), fileName);
        File output = inFolder.toFile();
        try {
            result = ImageIO.write((RenderedImage) image, formatName, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
