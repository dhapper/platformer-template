package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import enums.Gamestate;
import enums.Icon;
import main.Game;
import ui.Button;
import ui.IconButton;
import utilz.Constants;

public class Menu extends State implements Statemethods {
	
	private Button[] buttons;

	public Menu(Game game) {
		super(game);
		
		loadButtons();
		
	}
	
	private void loadButtons() {
		buttons = new Button[6];
		
		buttons[0] = new IconButton(100, 100, Icon.PLAY);
		buttons[1] = new IconButton(200, 100, Icon.NEXT);
		buttons[2] = new IconButton(300, 100, Icon.BACK);
		buttons[3] = new IconButton(400, 100, Icon.CLOSE);
		buttons[4] = new IconButton(500, 100, Icon.LEVELS);
		buttons[5] = new IconButton(600, 100, Icon.VOLUME);
	}

	@Override
	public void update() {
		
		for(Button b : buttons) {
			if(b instanceof IconButton)
				((IconButton) b).update();
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("MENU", Constants.General.SCREEN_WIDTH/2, Constants.General.SCREEN_HEIGHT/2);
	
		for(Button b : buttons) {
			if(b instanceof IconButton)
				((IconButton) b).draw(g);
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
				break;
				}
			}
		}
		
		resetButtons();
		
	}
	
	private void resetButtons() {
		for(Button b : buttons)
			b.setMouseOver(false);
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
