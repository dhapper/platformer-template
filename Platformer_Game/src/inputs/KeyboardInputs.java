package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;
import level.LevelManager;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    private Player player;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.player = gamePanel.getGame().getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setUpPressed(true);
            case KeyEvent.VK_A -> player.setLeftPressed(true);
            case KeyEvent.VK_S -> player.setDownPressed(true);
            case KeyEvent.VK_D -> player.setRightPressed(true);
//            case KeyEvent.VK_I -> player.setHit(true);
            case KeyEvent.VK_I -> player.triggerHit();
            case KeyEvent.VK_H -> LevelManager.SHOW_HITBOXES = !LevelManager.SHOW_HITBOXES;
            case KeyEvent.VK_SPACE -> player.getMovement().jump();
            case KeyEvent.VK_R -> player.respawn();
            
            case KeyEvent.VK_4 -> gamePanel.getGame().getLevelManager().loadLevel(4);
            case KeyEvent.VK_5 -> gamePanel.getGame().getLevelManager().loadLevel(5);
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W -> player.setUpPressed(false);
            case KeyEvent.VK_A -> player.setLeftPressed(false);
            case KeyEvent.VK_S -> player.setDownPressed(false);
            case KeyEvent.VK_D -> player.setRightPressed(false);
        }
    }
}
