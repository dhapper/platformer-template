package main;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.pack();	// fit window size to components (ie to panel size)
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		jframe.setVisible(true);
	}

}
