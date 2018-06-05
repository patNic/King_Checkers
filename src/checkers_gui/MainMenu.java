package checkers_gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class MainMenu extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BGPanel panel;
	public MainMenu() {
		setSize(800, 350);
		setLayout(null);
		init();
		addComponents();
		
		setVisible(true);
	}
	public void init() {
		panel = new BGPanel();
	}
	public void addComponents() {
		add(panel);
	}
	private class BGPanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public BGPanel() {
			setSize(800, 350);
			setLayout(null);
		}
		Image board_image = new ImageIcon("src/images/main_menuBG.jpg").getImage();
		public void paintComponent(Graphics g) {
			g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
