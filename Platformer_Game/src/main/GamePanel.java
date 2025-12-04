package main;

import java.awt.Dimension;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.Constants.General.*;

public class GamePanel extends JPanel {

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

	private void setPanelSize() {
		Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
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
