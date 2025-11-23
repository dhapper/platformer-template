package main;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Constants;

import static utilz.Constants.CharacterAnimations.*;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
//	private float xDir = 2f, yDir = 2f;
//	private BufferedImage img;
//	private BufferedImage[] idleAni;
	private int aniTick, aniSpeed = 10;
//	private int playerAction = Index.IDLE;
	
	public GamePanel() {
		
		setPanelSize();
		
//		importImg();
//		loadAnimations();
		
		loadAnims();
		
		// adding input listeners
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
//	Animation anim;
	Animation[] anims;
	int currentAnimation = 0;
	private void loadAnims() {
		anims = new Animation[3];
		
		String idle = Constants.ResourcePaths.MainCharacters + "Ninja Frog" + Paths.IDLE;
		String run = Constants.ResourcePaths.MainCharacters + "Ninja Frog" + Paths.RUN;
		String hit = Constants.ResourcePaths.MainCharacters + "Ninja Frog" + Paths.HIT;
		
		anims[0] = new Animation(idle, 11);
		anims[1] = new Animation(run, 12);
		anims[2] = new Animation(hit, 5);
		
	}


//	private void importImg() {
//		
//		String[] actions = {};
//		
//		String path = "/Pixel Adventure Assets/Main Characters/Mask Dude/Idle (32x32).png";
//		InputStream is = getClass().getResourceAsStream(path);
//		
//		try {
//			img = ImageIO.read(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
		
	}
	
	public void updateGame() {
		updateAnimationTick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(anims[currentAnimation].getCurrentSprite(), (int) xDelta, (int) yDelta, 64, 64, null);
	}
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			anims[currentAnimation].nextFrame();
		}
		
	}
	
}
