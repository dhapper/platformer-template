package gamestates;

import java.util.HashMap;

import enums.Gamestate;

public class StateSwitchManager {
	
	private Gamestate lastState = Gamestate.state;
	
	private Playing playing;
	private Menu menu;
	private Levels levels;
	private Settings settings;
	
	private HashMap<Gamestate, State> menuStates;
	
	public StateSwitchManager (
			Playing playing,
			Menu menu,
			Levels levels,
			Settings settings
			) { 
		
		this.playing = playing;
		this.menu = menu;
		this.levels = levels;
		this.settings = settings;
		
		menuStates = new HashMap<>();
		menuStates.put(Gamestate.MENU, menu);
		menuStates.put(Gamestate.LEVELS, levels);
		menuStates.put(Gamestate.SETTINGS, settings);
	}
	
	public void update() {
		switch(Gamestate.state) {
		case PLAYING:
			if(menuStates.containsKey(lastState)) {
				menu.exitState();
				playing.enterState();
			}
			break;
		case MENU:
		case LEVELS:
		case SETTINGS:
			if(lastState == Gamestate.PLAYING) {
				playing.exitState();
				menu.enterState();
			}
			break;
		}
		
		lastState = Gamestate.state;
	}

}
