package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import enums.BgColour;
import enums.Gamestate;
import enums.Icon;
import graphics.BackgroundHelper;
import graphics.TextWriter;
import graphics.TextWriter.TextColour;
import level.LevelManager;
import main.Game;
import ui.Button;
import ui.IconButton;
import ui.LevelButton;
import utilz.Constants;
import utilz.Constants.UI.RED_BUTTON;

import static utilz.Constants.General.*;

public class Levels extends State implements Statemethods{
	
	private Button[] buttons;
	
	private TextWriter textWriter;
	private BufferedImage title;
	private int textScale = (int) (2 * Constants.General.SCALE);
	private int titleMarginX, titleMarginY;
	private int verticalMargin;

	public Levels(Game game) {
		super(game);

		loadButtons();
		initTitleVars();
	}
	
	private void initTitleVars() {
		textWriter = new TextWriter();
		title = textWriter.GetTextImage("Select Level", TextColour.BLACK);
		titleMarginX = (Constants.General.SCREEN_WIDTH - title.getWidth() * textScale) / 2;
		titleMarginY = (verticalMargin - title.getHeight() * textScale) / 2;
	}
	
	
	private void loadButtons() {
		buttons = new Button[51];
		LevelManager levelManager = game.getPlaying().getLevelManager();
		int levelsWide = 10;
		int levelsHigh = 5;
		
		int horizontalSpacing = (int) (5 * Constants.General.SCALE);
		int levelButtonsWidth = levelsWide * Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_WIDTH + (levelsWide - 1) * horizontalSpacing;
		int horizontalMargin = (int) ((Constants.General.SCREEN_WIDTH - levelButtonsWidth) / 2f);
		
		int verticalSpacing = (int) (5 * Constants.General.SCALE);
		int levelButtonsHeight = levelsHigh * Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_HEIGHT + (levelsHigh - 1) * verticalSpacing;
		verticalMargin = (int) ((Constants.General.SCREEN_HEIGHT - levelButtonsHeight) / 2f);
		
		for(int i = 0; i < levelsHigh; i++) {
			for(int j = 0; j < levelsWide; j++) {
				int levelNum = i*levelsWide + j + 1;
				buttons[levelNum - 1] = new LevelButton(
						horizontalMargin + j*(Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_WIDTH + horizontalSpacing),
						verticalMargin + i*(Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_HEIGHT + verticalSpacing),
						levelNum,
						levelManager
						);
			}
		}
		
		// back button
		buttons[50] = new IconButton(
				(int) (SCREEN_WIDTH * 0.97 - RED_BUTTON.ICON_BUTTON_WIDTH),
				(int) (SCREEN_HEIGHT * 0.97 - RED_BUTTON.ICON_BUTTON_HEIGHT),
				Icon.BACK);
		((IconButton) buttons[50]).setLastState(Gamestate.MENU);
		
	}

	@Override
	public void update() {
		
		for(Button b : buttons)
			b.update();
		
	}

	@Override
	public void render(Graphics g) {
		
		BackgroundHelper.paintBackground(g, BgColour.GREEN);
		
		g.drawImage(title, titleMarginX, titleMarginY, title.getWidth() * textScale, title.getHeight() * textScale, null);
	
		for(Button b : buttons)
			b.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Button b : buttons) {
			if(isIn(e, b)) {
				b.setMousePressed(true);
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(Button b : buttons) {
			if(isIn(e,b)) {
				if(b.isMousePressed()) {
					b.action();
				break;
				}
			}
		}
		
		resetButtons();
		
	}
	
	private void resetButtons() {
		for(Button b : buttons)
			b.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(Button b : buttons)
			b.setMouseOver(false);
		
		for(Button b : buttons) {
			if(isIn(e, b)) {
				b.setMouseOver(true);
				break;
			}
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		switch(e.getKeyCode()) {
//			case KeyEvent.VK_ENTER:
//				Gamestate.state = Gamestate.PLAYING;
//				break;
//			default:
//				break;
//		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
