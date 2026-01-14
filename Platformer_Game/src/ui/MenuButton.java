package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Set;

import enums.Gamestate;
import graphics.ImageModifier;
import graphics.TextWriter;
import graphics.TextWriter.TextColour;

import static utilz.Constants.UI.MENU_BUTTON.*;

public class MenuButton extends Button {
	
	public enum MenuButtonType {
		PLAY,
		LEVELS,
		SETTINGS
	}
	
	private MenuButtonType menuButtonType;
	
	public MenuButton(int xPos, int yPos, MenuButtonType menuButtonType) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.menuButtonType = menuButtonType;
		
		width = MENU_BUTTON_WIDTH;
		height = MENU_BUTTON_HEIGHT;
		
		initBounds(width, height);
		
		createImage();
	}
	

	public void draw(Graphics g) {
		if (!mouseOver)
			g.drawImage(images[imageIndex], xPos, yPos, width, height, null);
		else
			g.drawImage(images[imageIndex], xPos - (int) (width*0.0625), yPos - (int) (height*0.0625), (int) (width*1.125), (int) (height*1.125), null);
	}
	
	public void action() {
		switch(menuButtonType) {
	    	case PLAY		-> Gamestate.state = Gamestate.PLAYING;
	    	case LEVELS		-> Gamestate.state = Gamestate.LEVELS;
	    	case SETTINGS	-> Gamestate.state = Gamestate.SETTINGS;
		}
	}
	
    public void createImage() {
    	images = new BufferedImage[2];
    	
        images[0] = new BufferedImage(
                DEFAULT_MENU_BUTTON_WIDTH,
                DEFAULT_MENU_BUTTON_HEIGHT,
                BufferedImage.TYPE_INT_ARGB
        );
        
        Color Outline = new Color(33, 31, 48);
        Color Background = new Color(255, 255, 255);
        
        Set<Point> emptyPixels = Set.of(
        		// top row
        	    new Point(0, 0),
        	    new Point(1, 0),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 1, 0),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 2, 0),
        	    // second row
        	    new Point(0, 1),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 1, 1),
        	    // bottom row
        	    new Point(0, DEFAULT_MENU_BUTTON_HEIGHT - 1),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 1, DEFAULT_MENU_BUTTON_HEIGHT - 1)
        	);
        
        Set<Point> extraOutlinePixels = Set.of(
        		// second row
        		new Point(1, 1),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 2, 1),
        	    // third last row
        	    new Point(1, DEFAULT_MENU_BUTTON_HEIGHT - 3),
        	    new Point(DEFAULT_MENU_BUTTON_WIDTH - 2, DEFAULT_MENU_BUTTON_HEIGHT - 3)
        	);

        for (int y = 0; y < DEFAULT_MENU_BUTTON_HEIGHT; y++) {
            for (int x = 0; x < DEFAULT_MENU_BUTTON_WIDTH; x++) {
 
                boolean isEdge =
                        x == 0 || x == DEFAULT_MENU_BUTTON_WIDTH - 1 ||
                        y == 0 || y == DEFAULT_MENU_BUTTON_HEIGHT - 1 || y == DEFAULT_MENU_BUTTON_HEIGHT - 2;
                
                if (emptyPixels.contains(new Point(x, y)))
                    continue;

                if (isEdge)
                    images[0].setRGB(x, y, Outline.getRGB());
                else
                	images[0].setRGB(x, y, Background.getRGB());
                
                if(extraOutlinePixels.contains(new Point(x, y)))
                	images[0].setRGB(x, y, Outline.getRGB());
            }
        }
        
        Graphics2D g2 = images[0].createGraphics();

	     // optional: smooth scaling
	     g2.setRenderingHint(
	         RenderingHints.KEY_INTERPOLATION,
	         RenderingHints.VALUE_INTERPOLATION_BILINEAR
	     );
	
	     // draw overlay image on top
	     TextWriter tw = new TextWriter();
	     BufferedImage textImage = tw.GetTextImage(menuButtonType.toString(), TextColour.BLACK);
	     
	     int xOffset = (images[0].getWidth() - textImage.getWidth()) / 2;
	     int yOffset = (images[0].getHeight() - textImage.getHeight()) / 2;
	     g2.drawImage(textImage, xOffset, yOffset, textImage.getWidth(), textImage.getHeight(), null);
	
	     g2.dispose();
	     
	     images[1] = ImageModifier.HighlightImage(images[0]);

    }

}
