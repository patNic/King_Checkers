package checkers_gui;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SidePanel extends JPanel{
	private ImageIcon background;
	public SidePanel(ImageIcon icon) {
		background = icon;
		setLayout(null);
	}
	Image board_image = background.getImage();
	public void paintComponent(Graphics g) {
		g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
	}
}

