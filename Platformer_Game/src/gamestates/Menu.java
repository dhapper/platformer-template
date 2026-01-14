package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import enums.BgColour;
import enums.Gamestate;
import graphics.BackgroundHelper;
import graphics.TextWriter;
import graphics.TextWriter.TextColour;
import main.Game;
import ui.Button;
import ui.MenuButton;
import ui.MenuButton.MenuButtonType;
import utilz.Constants;

import static utilz.Constants.UI.MENU_BUTTON.*;
import static utilz.Constants.General.*;

public class Menu extends State implements Statemethods {
	
	private Button[] buttons;
	
	private TextWriter textWriter;
	private BufferedImage title;
	private int textScale = (int) (2 * Constants.General.SCALE);
	private int titleMarginX, titleMarginY;
	private int verticalMargin;

	public Menu(Game game) {
		super(game);
		
		loadButtons();
		initTitleVars();
	}
	
	private void initTitleVars() {
		textWriter = new TextWriter();
		title = textWriter.GetTextImage("Name PlaceHolder", TextColour.BLACK);
		titleMarginX = (Constants.General.SCREEN_WIDTH - title.getWidth() * textScale) / 2;
		titleMarginY = (verticalMargin - title.getHeight() * textScale) / 2;
	}
	
	private void loadButtons() {
		
		int xPos = SCREEN_WIDTH / 2 - MENU_BUTTON_WIDTH / 2;
		
		int ySpacer = MENU_BUTTON_HEIGHT / 2;
		int buttonLayoutHeight = MENU_BUTTON_HEIGHT * 3 + ySpacer * 2;
		
		int yPos1 = SCREEN_HEIGHT / 2 - buttonLayoutHeight / 2;
		int yPos2 = yPos1 + MENU_BUTTON_HEIGHT + ySpacer;
		int yPos3 = yPos2 + MENU_BUTTON_HEIGHT + ySpacer;
		verticalMargin = yPos1;
		
		buttons = new Button[3];
		buttons[0] = new MenuButton(xPos, yPos1, MenuButtonType.PLAY);
		buttons[1] = new MenuButton(xPos, yPos2, MenuButtonType.LEVELS);
		buttons[2] = new MenuButton(xPos, yPos3, MenuButtonType.SETTINGS);
	}

	@Override
	public void update() {
		
		for(Button b : buttons)
			b.update();
		
	}

	@Override
	public void render(Graphics g) {
		BackgroundHelper.paintBackground(g, BgColour.PURPLE);
		
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
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				Gamestate.state = Gamestate.PLAYING;
				break;
			default:
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
