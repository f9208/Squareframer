import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Start {
    static ImageReader imageReader = new ImageReader();
    static ImageWrapper imageWrapper = new ImageWrapper();

    public static void main(String[] args) throws IOException {
//        String filePath = args[0];
        String filePath = "H:/75.JPG";

        Image result = imageWrapper.wrapImage(imageReader.openImage(filePath), 80);
        saveImage(result);
    }

    public static void saveImage(Image image) {
        File output = new File("big.jpg");
        try {
            ImageIO.write((RenderedImage) image, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
