package main;

import java.awt.Graphics;

import enums.Gamestate;
import gamestates.Levels;
import gamestates.Menu;
import gamestates.Playing;
import gamestates.Settings;
import gamestates.StateSwitchManager;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	private Playing playing;
	private Menu menu;
	private Levels levels;
	private Settings settings;
	
	private StateSwitchManager stateSwitchManager;
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		startGameLoop();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
		levels = new Levels(this);
		settings = new Settings(this);
		
		stateSwitchManager = new StateSwitchManager(playing, menu, levels, settings);
	}

	private void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {

		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case LEVELS:
			levels.update();
			break;
		case SETTINGS:
			settings.update();
			break;
		default:
			break;
		}
		
		// currently manages audio switches
		stateSwitchManager.update();
	}
	
	public void render(Graphics g) {
		switch(Gamestate.state) {
		case MENU:
			menu.render(g);
			break;
		case PLAYING:
			playing.render(g);
			break;
		case LEVELS:
			levels.render(g);
			break;
		case SETTINGS:
			settings.render(g);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
	
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;	// eg 105% - 100% = 5%, leftovers are carried over
			}
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				// System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			
			
		}
		
	}
	
	public void windowFocusLost() {
		if(Gamestate.state == Gamestate.PLAYING)
			playing.getPlayer().resetDirBools();
	}
	
	// getter sand setters
	
	public Playing getPlaying() {
		return playing;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Levels getLevels() {
		return levels;
	}
	
	public Settings getSettings() {
		return settings;
	}

}
