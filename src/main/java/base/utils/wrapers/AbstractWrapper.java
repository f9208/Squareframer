package base.utils.wrapers;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractWrapper implements IFrameWrapper {
    Color defaultColor = Color.WHITE;

    protected BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bImage;
    }

    protected Image madeCustomFrame(BufferedImage sourceImage, BufferedImage result, int left, int top) {
        for (int x = 0; x < sourceImage.getWidth(); x++) {
            for (int y = 0; y < sourceImage.getHeight(); y++) {
                Color color = new Color(sourceImage.getRGB(x, y));
                result.setRGB(x + left, y + top, color.getRGB());
            }
        }
        return result;
    }

    protected BufferedImage increaseImage(BufferedImage sourceImage, int frameX, int frameY) {
        BufferedImage result = new BufferedImage(sourceImage.getWidth() + frameX,
                sourceImage.getHeight() + frameY,
                sourceImage.getType());
        Graphics graphics = result.createGraphics();
        graphics.setXORMode(defaultColor);
        graphics.drawImage(result, 0, 0, null);
        graphics.dispose();
        return result;
    }
}
