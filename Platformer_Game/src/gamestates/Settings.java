package gamestates;

import static utilz.Constants.General.SCREEN_HEIGHT;
import static utilz.Constants.General.SCREEN_WIDTH;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.Player;
import enums.BgColour;
import enums.Characters;
import enums.Gamestate;
import enums.Icon;
import graphics.BackgroundHelper;
import graphics.TextWriter;
import graphics.TextWriter.TextColour;
import main.Game;
import ui.Button;
import ui.CharacterButton;
import ui.IconButton;
import ui.Slider;
import utilz.Constants;
import utilz.Constants.UI.RED_BUTTON;

public class Settings extends State implements Statemethods{
	
	private TextWriter textWriter;
	private BufferedImage title;
	private int textScale = (int) (2 * Constants.General.SCALE);
	private int titleMarginX, titleMarginY;
	private int verticalMargin = (int) (Constants.General.SCREEN_HEIGHT * 0.2);
	
	private Player player;
	
	private Slider[] sliders;
	private Button[] buttons;
	
	private Characters character;
	
	private final int volumeNotches = 5;
	private int volumeSelection = Constants.General.DEFAULT_VOLUME;

	public Settings(Game game) {
		super(game);
		
		player = game.getPlaying().getPlayer();
		
		initTitleVars();
		
		loadUI();
		loadButtons();
	}
	
	private void loadUI() {
		sliders = new Slider[2];
		
		int xPos = (int) (Constants.General.SCREEN_WIDTH * 0.33);
		int yPos1 = (int) (Constants.General.SCREEN_HEIGHT * 0.3);
		int yPos2 = yPos1 + Constants.TileConstants.TERRAIN_TILE_SIZE * 2;
		sliders[0] = new Slider(xPos, yPos1, "Volume:", volumeNotches, volumeSelection);
		sliders[1] = new Slider(xPos, yPos2, "Scale:", 3, 1);
	}
	
	private void loadButtons() {
		
		int spacer = (int) (10 * Constants.General.SCALE);
		int buttonWidth = (int) (32 * Constants.General.SCALE);
		int totalWidth = buttonWidth * 4 + spacer * 3;
		int xPos1 = Constants.General.SCREEN_WIDTH / 2 - totalWidth / 2;
		int xPos2 = xPos1 + buttonWidth + spacer;
		int xPos3 = xPos2 + buttonWidth + spacer;
		int xPos4 = xPos3 + buttonWidth + spacer;
		
		buttons = new Button[5];
		buttons[0] = new CharacterButton(xPos1, 400, Characters.MASK_DUDE);
		buttons[1] = new CharacterButton(xPos2, 400, Characters.NINJA_FROG);
		buttons[2] = new CharacterButton(xPos3, 400, Characters.PINK_MAN);
		buttons[3] = new CharacterButton(xPos4, 400, Characters.VIRTUAL_GUY);
		
		// default active character
		((CharacterButton) buttons[0]).setActive(true);
		character = ((CharacterButton) buttons[0]).getCharacter();
		player.updateCharacter(character);
		
		// back button
		buttons[4] = new IconButton(
				(int) (SCREEN_WIDTH * 0.97 - RED_BUTTON.ICON_BUTTON_WIDTH),
				(int) (SCREEN_HEIGHT * 0.97 - RED_BUTTON.ICON_BUTTON_HEIGHT),
				Icon.BACK);
		((IconButton) buttons[4]).setLastState(Gamestate.MENU);
	}
	
	private void initTitleVars() {
		textWriter = new TextWriter();
		title = textWriter.GetTextImage("Settings", TextColour.BLACK);
		titleMarginX = (Constants.General.SCREEN_WIDTH - title.getWidth() * textScale) / 2;
		titleMarginY = (verticalMargin - title.getHeight() * textScale) / 2;
	}

	@Override
	public void update() {
		
		for(Button b : buttons)
			b.update();
		
		int currentVolumeSelection = sliders[0].getCurrentNotch();
		if(volumeSelection != currentVolumeSelection)
			game.getMenu().background.setVolume(currentVolumeSelection);
		volumeSelection = currentVolumeSelection;
	}

	@Override
	public void render(Graphics g) {
		
		BackgroundHelper.paintBackground(g, BgColour.YELLOW);
		
		g.drawImage(title, titleMarginX, titleMarginY, title.getWidth() * textScale, title.getHeight() * textScale, null);
		
		for(Slider s : sliders)
			s.draw(g);
		
		for(Button b : buttons)
			b.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(Slider s : sliders)
			s.mousePressed(e);
		
		for(Button b : buttons) {
			if(isIn(e, b)) {
				b.setMousePressed(true);
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(Slider s : sliders)
			s.mouseReleased(e);
		
		for(Button b : buttons) {
			if(isIn(e,b)) {
				if(b.isMousePressed()) {
					
					if(b instanceof CharacterButton) {
						resetCharacterButtons();
						character = ((CharacterButton) b).getCharacter();
						player.updateCharacter(character);
					}
					
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
	
	private void resetCharacterButtons() {
		for(Button b : buttons)
			if(b instanceof CharacterButton)
				((CharacterButton) b).setActive(false);
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
	
	// getters and setters
	
	public int getVolumeSelection() {
		return volumeSelection;
	}
	


}
