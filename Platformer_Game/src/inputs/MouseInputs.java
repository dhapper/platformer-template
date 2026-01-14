package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import enums.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
	
private GamePanel gamePanel;
	
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		switch(Gamestate.state) {
    	case MENU:
    		gamePanel.getGame().getMenu().mouseDragged(e);
    		break;
    	case PLAYING:
    		gamePanel.getGame().getPlaying().mouseDragged(e);
    		break;
    	case LEVELS:
    		gamePanel.getGame().getLevels().mouseDragged(e);
    	case SETTINGS:
    		gamePanel.getGame().getSettings().mouseDragged(e);
    	default:
    		break;
    		
    	}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		switch(Gamestate.state) {
    	case MENU:
    		gamePanel.getGame().getMenu().mouseMoved(e);
    		break;
    	case PLAYING:
    		gamePanel.getGame().getPlaying().mouseMoved(e);
    		break;
    	case LEVELS:
    		gamePanel.getGame().getLevels().mouseMoved(e);
    	case SETTINGS:
    		gamePanel.getGame().getSettings().mouseMoved(e);
    	default:
    		break;
    		
    	}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("clicking");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(Gamestate.state) {
    	case MENU:
    		gamePanel.getGame().getMenu().mousePressed(e);
    		break;
    	case PLAYING:
    		gamePanel.getGame().getPlaying().mousePressed(e);
    		break;
    	case LEVELS:
    		gamePanel.getGame().getLevels().mousePressed(e);
    		break;
    	case SETTINGS:
    		gamePanel.getGame().getSettings().mousePressed(e);
    		break;
    	default:
    		break;
    	}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		switch(Gamestate.state) {
    	case MENU:
    		gamePanel.getGame().getMenu().mouseReleased(e);
    		break;
    	case PLAYING:
    		gamePanel.getGame().getPlaying().mouseReleased(e);
    		break;
    	case LEVELS:
    		gamePanel.getGame().getLevels().mouseReleased(e);
    		break;
    	case SETTINGS:
    		gamePanel.getGame().getSettings().mouseReleased(e);
    		break;
    	default:
    		break;
    	}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
