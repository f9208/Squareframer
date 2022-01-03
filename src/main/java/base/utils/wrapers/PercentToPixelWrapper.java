package base.utils.wrapers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PercentToPixelWrapper extends AbstractWrapper implements IFrameWrapper {
    IFrameWrapper wrapper;

    public PercentToPixelWrapper(IFrameWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public Image wrapImage(Image source, double left, double right, double top, double bottom) {
        BufferedImage sourceImage = toBufferedImage(source);
        int imageWidth = sourceImage.getWidth();
        int pixelLeft = (int) (imageWidth * left / 100);
        int pixelRight = (int) (imageWidth * right / 100);
        int pixelTop = (int) (imageWidth * top / 100);
        int pixelBottom = (int) (imageWidth * bottom / 100);
        return wrapper.wrapImage(source, pixelLeft, pixelRight, pixelTop, pixelBottom);
    }
}


