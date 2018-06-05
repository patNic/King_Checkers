package checkers_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import checkers_main.Main;

public class Winner extends JDialog implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel background_panel;
	private String winner;
	private JLabel congratulations, winner_broadcast,x_Button;
	public Winner(String win) {
		winner =win;
		
		setSize(500, 350);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		initComponents();
		addComponents();
		setVisible(true);
	}
	public void initComponents() {
		background_panel = new BGPanel();
		background_panel.setLayout(null);
		
		congratulations = new JLabel(new ImageIcon("src/images/congratulations.gif"));
		congratulations.setBounds(50, -15, 400, 300);
		
		winner_broadcast = new JLabel(winner + " WINS!", SwingConstants.CENTER);
		winner_broadcast.setBounds(0, 265, 500, 45);
		if(winner.equals("RED"))
			winner_broadcast.setForeground(Color.RED);
		else if(winner.equals("BLACK"))
			winner_broadcast.setForeground(Color.BLACK);
		winner_broadcast.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		x_Button = new JLabel("CLOSE", SwingConstants.CENTER);
		x_Button.setBounds(0, 318, 500, 25);
		x_Button.setForeground(Color.white);
		x_Button.setFont(new Font("Helvetica", Font.BOLD, 20));
		x_Button.addMouseListener(this);
	}
	public void addComponents() {
		add(background_panel);
		
		background_panel.add(congratulations);
		background_panel.add(winner_broadcast);
		background_panel.add(x_Button);
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
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() == x_Button)
		{
			this.dispose();
			Launcher.f.dispose();
			Main.l = new Launcher();
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource() == x_Button)
			x_Button.setFont(new Font("Helvetica", Font.BOLD, 25));
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource() == x_Button)
			x_Button.setFont(new Font("Helvetica", Font.BOLD, 20));
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
