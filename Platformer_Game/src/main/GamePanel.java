package main;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Constants;

import static utilz.Constants.CharacterAnimations.*;

public class GamePanel extends JPanel{

	private Game game;
	private MouseInputs mouseInputs;
	
	public GamePanel(Game game) {
		this.game = game;
		
		setPanelSize();
		
		// adding input listeners
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
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
	
	public void updateGame() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
	
}
