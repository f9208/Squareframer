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
    public Image wrapImage(Image source, double frameLeft, double frameRight, double frameTop, double frameBottom) {
        BufferedImage sourceImage = toBufferedImage(source);
        BufferedImage result = increaseImage(sourceImage, (int) (frameLeft + frameRight), (int) (frameTop + frameBottom));
        madeCustomFrame(sourceImage, result, (int) frameLeft, (int) frameTop);
        return result;
    }
}