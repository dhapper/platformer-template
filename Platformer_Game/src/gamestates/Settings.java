package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import enums.BgColour;
import graphics.BackgroundHelper;
import graphics.TextWriter;
import graphics.TextWriter.TextColour;
import main.Game;
import ui.Slider;
import utilz.Constants;

public class Settings extends State implements Statemethods{
	
	private TextWriter textWriter;
	private BufferedImage title;
	private int textScale = (int) (2 * Constants.General.SCALE);
	private int titleMarginX, titleMarginY;
	private int verticalMargin = (int) (Constants.General.SCREEN_HEIGHT * 0.2);
	
	private Slider[] sliders;

	public Settings(Game game) {
		super(game);
		
		initTitleVars();
		
		loadUI();
	}
	
	private void loadUI() {
		sliders = new Slider[2];
		
		int xPos = (int) (Constants.General.SCREEN_WIDTH * 0.33);
		int yPos1 = (int) (Constants.General.SCREEN_HEIGHT * 0.3);
		int yPos2 = yPos1 + Constants.TileConstants.TERRAIN_TILE_SIZE * 2;
		sliders[0] = new Slider(xPos, yPos1, "Volume:", 5, 3);
		sliders[1] = new Slider(xPos, yPos2, "Scale:", 3, 1);
				
	}
	
	private void initTitleVars() {
		textWriter = new TextWriter();
		title = textWriter.GetTextImage("Settings", TextColour.BLACK);
		titleMarginX = (Constants.General.SCREEN_WIDTH - title.getWidth() * textScale) / 2;
		titleMarginY = (verticalMargin - title.getHeight() * textScale) / 2;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		BackgroundHelper.paintBackground(g, BgColour.YELLOW);
		
		g.drawImage(title, titleMarginX, titleMarginY, title.getWidth() * textScale, title.getHeight() * textScale, null);
		
		for(Slider s : sliders)
			s.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Slider s : sliders)
			s.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(Slider s : sliders)
			s.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		for(Slider s : sliders)
			s.mouseDragged(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
