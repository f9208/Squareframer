package base.utils.wrapers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangularWrapper extends AbstractWrapper {
    public RectangularWrapper() {
    }

    public RectangularWrapper(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    @Override
    public Image wrapImage(Image source, double frameX, double frameY) {
        BufferedImage sourceImage = toBufferedImage(source);
        BufferedImage result = increaseImage(sourceImage, (int) frameX, (int) frameY);
        wrapperSameFrame(sourceImage, result, (int) frameX, (int) frameY);
        return result;
    }
}