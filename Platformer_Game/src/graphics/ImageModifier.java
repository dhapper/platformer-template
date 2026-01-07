package graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageModifier {
	
    public static BufferedImage MakeImageTransparent(BufferedImage image, float transparency) {
        // Create a new image with alpha channel
        BufferedImage transparentImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = transparentImage.createGraphics();

        // Set the composite to semi-transparent (50% opacity)
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
        g2d.setComposite(alphaComposite);

        // Draw the original image onto the new image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return transparentImage;
    }
    
    // white to yello (mainly for buttons)
    public static BufferedImage HighlightImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int whiteThreshold = 200; // tweak to adjust

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = image.getRGB(x, y);

                int a = (argb >> 24) & 0xff;
                int r = (argb >> 16) & 0xff;
                int g = (argb >> 8) & 0xff;
                int b = argb & 0xff;

                // Ignore fully transparent pixels
                if (a == 0) {
                    result.setRGB(x, y, argb);
                    continue;
                }

                // Detect "whitish" pixels
                if (r >= whiteThreshold && g >= whiteThreshold && b >= whiteThreshold) {
                    Color pastelYellow = new Color(255, 255, 125);
                    int pastelYellowRGB = pastelYellow.getRGB();
                    result.setRGB(x, y, pastelYellowRGB);
                } else {
                    result.setRGB(x, y, argb);
                }
            }
        }

        return result;
    }


}
