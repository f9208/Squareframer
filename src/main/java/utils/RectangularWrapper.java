package utils;

import java.awt.*;
import java.awt.image.BufferedImage;


public class RectangularWrapper implements IFrameWrapper {
    Color defaultColor = Color.WHITE;

    public RectangularWrapper() {
    }

    public RectangularWrapper(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public Image wrapImage(Image source, int frame) {
        BufferedImage sourceImage = toBufferedImage(source);
        BufferedImage result = new BufferedImage(sourceImage.getWidth() + frame, sourceImage.getHeight() + frame, sourceImage.getType());
        Graphics graphics = result.createGraphics();
        graphics.setXORMode(defaultColor);
        graphics.drawImage(result, 0, 0, null);
        graphics.dispose();
        for (int x = 0; x < sourceImage.getWidth(); x++) {
            for (int y = 0; y < sourceImage.getHeight(); y++) {
                Color color = new Color(sourceImage.getRGB(x, y));
                result.setRGB(x + frame / 2, y + frame / 2, color.getRGB());
            }
        }
        return result;
    }

    private static BufferedImage toBufferedImage(Image img) {
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
}