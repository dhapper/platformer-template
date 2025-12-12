package graphics;

import java.awt.AlphaComposite;
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

}
