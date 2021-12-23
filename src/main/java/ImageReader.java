import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public Image openImage(String path) {
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
