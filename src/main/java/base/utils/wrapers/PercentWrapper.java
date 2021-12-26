package base.utils.wrapers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PercentWrapper extends AbstractWrapper {
    public PercentWrapper(Color color) {
        this.defaultColor = color;
    }

    @Override
    public Image wrapImage(Image image, double frameX, double frameY) {
        BufferedImage source = toBufferedImage(image);
        int imageHeight = source.getHeight();
        int imageWidth = source.getWidth();
        int pixelX = (int) (imageWidth * frameX / 100);
//        int pixelY = (int) (imageHeight * frameY / 100);
        int pixelY = pixelX;
        BufferedImage result = increaseImage(source, pixelX, pixelY);
        wrapperSameFrame(source, result, pixelX, pixelY);
        return result;
    }
}
