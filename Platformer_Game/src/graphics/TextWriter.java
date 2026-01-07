package graphics;

import static utilz.Constants.ResourcePaths.BLACK_TEXT;
import static utilz.Constants.ResourcePaths.WHITE_TEXT;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import utilz.LoadSave;

public class TextWriter {
	
    public enum TextColour {
        BLACK,
        WHITE
    }
	
	private BufferedImage[] textSheets;
	private BufferedImage[][] sprites;
	
	private int charsWide = 10;
	private int charsHigh = 5;
	private int totalChars = 50;
	
	private int spriteWidth = 8;
	private int spriteHeight = 10;
	
	private HashMap<Character, Integer> charIndex;
	
	public TextWriter() {
		loadSheets();
		
		sprites = new BufferedImage[TextColour.values().length][totalChars];
		prepSprites(TextColour.BLACK);
		prepSprites(TextColour.WHITE);
		
		initHashMap();
	}

	private void loadSheets() {
		textSheets = new BufferedImage[2];
		textSheets[0] = LoadSave.ImportImg(BLACK_TEXT);
		textSheets[1] = LoadSave.ImportImg(WHITE_TEXT);
	}
	
	private void prepSprites(TextColour colour) {
		for(int i = 0; i < charsHigh; i++) {
			for(int j = 0; j < charsWide; j++) {
				sprites[colour.ordinal()][i*charsWide + j] = textSheets[colour.ordinal()].getSubimage(j * spriteWidth, i * spriteHeight, spriteWidth, spriteHeight);
			}
		}
	}
	
	// one line
	public BufferedImage GetTextImage(String text, TextColour colour) {
	    if (text == null || text.isEmpty()) return null;

	    String textUpperCase = text.toUpperCase();

	    // Calculate total width and max height
	    int totalWidth = 0;
	    int maxHeight = 0;

	    for (char c : textUpperCase.toCharArray()) {
	        if (charIndex.containsKey(c)) {
	            int index = charIndex.get(c);
	            BufferedImage charImage = sprites[colour.ordinal()][index];
	            totalWidth += charImage.getWidth();
	            maxHeight = Math.max(maxHeight, charImage.getHeight());
	        }
	    }

	    if (totalWidth == 0 || maxHeight == 0) return null; // no valid chars

	    // Create the final text image
	    BufferedImage textImage = new BufferedImage(totalWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics g = textImage.getGraphics();

	    // Draw each character
	    int x = 0;
	    for (char c : textUpperCase.toCharArray()) {
	        if (charIndex.containsKey(c)) {
	            int index = charIndex.get(c);
	            BufferedImage charImage = sprites[colour.ordinal()][index];
	            g.drawImage(charImage, x, 0, null);
	            x += charImage.getWidth(); // move x to the next character
	        }
	    }

	    g.dispose(); // clean up
	    return textImage;
	}

//	public BufferedImage GetTextImage(String text, TextColour colour) {
//		
//		BufferedImage textImage = null;
//		
//	    int totalWidth = 0;
//	    int maxHeight = 0;
//		
//		Graphics g = textImage.getGraphics();
//		
//		String textUpperCase = text.toUpperCase();
//
//		for(char c : textUpperCase.toCharArray()) {
//			if(charIndex.containsKey(c)) {
//				int index = charIndex.get(c);
//				BufferedImage charImage = sprites[colour.ordinal()][index];
//
//			}
//		}
//		
//		return null;
//	}
	
	private void initHashMap() {
		charIndex = new HashMap<>();
		
		charIndex.put('A', 0);
		charIndex.put('B', 1);
		charIndex.put('C', 2);
		charIndex.put('D', 3);
		charIndex.put('E', 4);
		charIndex.put('F', 5);
		charIndex.put('G', 6);
		charIndex.put('H', 7);
		charIndex.put('I', 8);
		charIndex.put('J', 9);
		
		charIndex.put('K', 10);
		charIndex.put('L', 11);
		charIndex.put('M', 12);
		charIndex.put('N', 13);
		charIndex.put('O', 14);
		charIndex.put('P', 15);
		charIndex.put('Q', 16);
		charIndex.put('R', 17);
		charIndex.put('S', 18);
		charIndex.put('T', 19);
		
		charIndex.put('U', 20);
		charIndex.put('V', 21);
		charIndex.put('W', 22);
		charIndex.put('X', 23);
		charIndex.put('Y', 24);
		charIndex.put('Z', 25);
		charIndex.put(' ', 26);
//		charIndex.put('A', 27);
//		charIndex.put('A', 28);
//		charIndex.put('A', 29);
		
		charIndex.put('0', 30);
		charIndex.put('1', 31);
		charIndex.put('2', 32);
		charIndex.put('3', 33);
		charIndex.put('4', 34);
		charIndex.put('5', 35);
		charIndex.put('6', 36);
		charIndex.put('7', 37);
		charIndex.put('8', 38);
		charIndex.put('9', 39);
		
		charIndex.put('.', 40);
		charIndex.put(',', 41);
		charIndex.put(':', 42);
		charIndex.put('?', 43);
		charIndex.put('!', 44);
		charIndex.put('(', 45);
		charIndex.put(')', 46);
		charIndex.put('+', 47);
		charIndex.put('-', 48);
//		charIndex.put('A', 49);
	}

}
