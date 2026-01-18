package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import enums.Gamestate;
import level.LevelManager;
import main.Audio;
import main.Game;

public class Playing extends State implements Statemethods {

	private LevelManager levelManager;
	private Player player;

	public Playing(Game game) {
		super(game);

		initClasses();
	}

	private void initClasses() {
		player = new Player(50, 50);
		levelManager = new LevelManager(player);
		
		initAudio();
	}
	
	public void initAudio() {
		String filepath = "res/game_music/Sketchbook 2025-11-12.wav";
		background = new Audio(filepath);
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
	}

	@Override
	public void render(Graphics g) {
		levelManager.render(g);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W -> player.setUpPressed(true);
		case KeyEvent.VK_A -> player.setLeftPressed(true);
		case KeyEvent.VK_S -> player.setDownPressed(true);
		case KeyEvent.VK_D -> player.setRightPressed(true);
		// case KeyEvent.VK_I -> player.setHit(true);
		case KeyEvent.VK_I -> player.triggerHit();
		case KeyEvent.VK_H -> LevelManager.SHOW_HITBOXES = !LevelManager.SHOW_HITBOXES;
		case KeyEvent.VK_SPACE -> player.getMovement().jump();
		case KeyEvent.VK_R -> player.respawn();

		case KeyEvent.VK_4 -> levelManager.loadLevel(4);
		case KeyEvent.VK_5 -> levelManager.loadLevel(5);

		}

		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			Gamestate.state = Gamestate.MENU;
			player.resetDirBools();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_W -> player.setUpPressed(false);
		case KeyEvent.VK_A -> player.setLeftPressed(false);
		case KeyEvent.VK_S -> player.setDownPressed(false);
		case KeyEvent.VK_D -> player.setRightPressed(false);
		}
	}

	// getters and setters

	public Player getPlayer() {
		return player;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

}
