package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import level.LevelManager;
import main.Game;
import ui.Button;
import ui.IconButton;
import ui.LevelButton;
import utilz.Constants;

public class Levels extends State implements Statemethods{
	
	private Button[] buttons;

	public Levels(Game game) {
		super(game);

		loadButtons();
	}
	
	
	int tester1 = 127;
	int tester2 = 832;
	
	int tester3 = 130;
	int tester4 = 445;
	
	private void loadButtons() {
		buttons = new Button[50];
		LevelManager levelManager = game.getPlaying().getLevelManager();
		int levelsWide = 10;
		int levelsHigh = 5;
		
		int horizontalSpacing = (int) (5 * Constants.General.SCALE);
		int levelButtonsWidth = levelsWide * Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_WIDTH + (levelsWide - 1) * horizontalSpacing;
		int horizontalMargin = (int) ((Constants.General.SCREEN_WIDTH - levelButtonsWidth) / 2f);
		
		int verticalSpacing = (int) (5 * Constants.General.SCALE);
		int levelButtonsHeight = levelsHigh * Constants.UI.LEVEL_BUTTON.LEVEL_BUTTON_HEIGHT + (levelsHigh - 1) * verticalSpacing;
		int verticalMargin = (int) ((Constants.General.SCREEN_HEIGHT - levelButtonsHeight) / 2f);
		
		System.out.println(levelButtonsWidth + " | " + Constants.General.SCREEN_WIDTH + " | " + horizontalMargin);
		System.out.println(levelButtonsHeight + " | " + Constants.General.SCREEN_HEIGHT + " | " + verticalMargin);
		
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
		
	}

	@Override
	public void update() {
		
		for(Button b : buttons)
			b.update();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Constants.General.SCREEN_WIDTH, Constants.General.SCREEN_HEIGHT);
		
		g.setColor(Color.BLACK);
		g.drawString("LEVELS", Constants.General.SCREEN_WIDTH/2, Constants.General.SCREEN_HEIGHT/2);
		
		g.fillRect(tester1, 0, 1, 1000);
		g.fillRect(tester2, 0, 1, 1000);
		
		g.fillRect(0, tester3, 1000, 1);
		g.fillRect(0, tester4, 1000, 1);
	
		for(Button b : buttons) {
			if(b instanceof IconButton)
				((IconButton) b).draw(g);
			if(b instanceof LevelButton)
				((LevelButton) b).draw(g);
		}
		
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
					if(b instanceof IconButton)
						((IconButton) b).action();
					if(b instanceof LevelButton)
						((LevelButton) b).EnterLevel();
				break;
				}
			}
		}
		
		resetButtons();
		
	}
	
	private void resetButtons() {
		for(Button b : buttons)
			b.resetBools();
//			b.setMouseOver(false);
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
