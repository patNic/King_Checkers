package checkers_gui;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import checkers_main.Main;
import network.Sender;

public class Dialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JLabel exit, exitQuestion, yes, no, settingsQuestion, settings, ok;
	private JPanel panel;
	private Image board_image;
	private MouseListener mouseListener;
	private String turn = "ON";
	private boolean isExit, isMenu;
	
	private Sender sender;
	public Dialog(){
		initComponents();
		
		setSize(1010,720);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 0, 0, 120));
		setLayout(null);
		
		addComponent();
	}
	public void initComponents(){
		panel = new JPanel(){
			private static final long serialVersionUID = 1L;

			@Override
		     protected void paintComponent(Graphics g) {
		    	g.drawImage(board_image, 0, 0, getWidth(), getHeight(), panel);
		     }
		  };
		setSize(700, 350);
		panel.setBounds(160, 200, 700, 350);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		board_image = new ImageIcon("src/images/exit_confirm.jpg").getImage();
		mouseListener = new MouseListener();
		
		yes = new JLabel("YES", SwingConstants.CENTER);
		yes.setBounds(90, 220, 200, 50);
		yes.setFont(new Font("Helvetica", Font.BOLD, 30));
		yes.setForeground(Color.WHITE);
		yes.setBackground(new Color(0, 0, 0, 100));
		yes.setOpaque(true);
		yes.addMouseListener(mouseListener);
		
		no = new JLabel("NO",SwingConstants.CENTER);
		no.setBounds(400, 220, 200, 50);
		no.setFont(new Font("Helvetica", Font.BOLD, 30));
		no.setForeground(Color.WHITE);
		no.setBackground(new Color(0, 0, 0, 100));
		no.setOpaque(true);
		no.addMouseListener(mouseListener);
		
		ok = new JLabel("OKAY",SwingConstants.CENTER);
		ok.setBounds(250, 240, 200, 50);
		ok.setFont(new Font("Helvetica", Font.BOLD, 30));
		ok.setForeground(Color.WHITE);
		ok.setBackground(new Color(0, 0, 0, 100));
		ok.setOpaque(true);
		ok.addMouseListener(mouseListener);
	}
	public void addComponent(){
		add(panel);
		panel.add(yes);
		panel.add(no);
	}
	public void exit(){
		isExit = true;
		exit = new JLabel("EXIT CONFIRMATION");
		exit.setBounds(90, 40, 600, 50);
		exit.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 50));
		exit.setForeground(Color.WHITE);
		
		exitQuestion = new JLabel("Are you sure you want to exit?");
		exitQuestion.setBounds(60, 125, 600, 50);
		exitQuestion.setFont(new Font("Helvetica", Font.BOLD, 40));
		exitQuestion.setForeground(Color.WHITE);
		
		panel.add(exit);
		panel.add(exitQuestion);
		setVisible(true);
	}
	public void settings(){
		panel.removeAll();
		addComponent();
		
		isExit = false;
		settings = new JLabel("TURN " +turn +  " BACKGROUND MUSIC", JLabel.CENTER);
		settings.setBounds(0, 40, 700, 50);
		settings.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 40));
		settings.setForeground(Color.WHITE);
		
		settingsQuestion = new JLabel("Are you sure you want to turn "+ turn +" background music?", JLabel.CENTER);
		settingsQuestion.setBounds(0, 125, 700, 50);
		settingsQuestion.setFont(new Font("Helvetica", Font.BOLD, 25));
		settingsQuestion.setForeground(Color.WHITE);
		
		panel.add(settings);
		panel.add(settingsQuestion);
		setVisible(true);
	}
	public Dialog outer(){
		return Dialog.this;
	}
	private class MouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent e){
			Object obj = e.getSource();
			
			if(obj == yes){
				yes.setFont(new Font("Helvetica", Font.BOLD, 35));
				yes.setForeground(Color.RED);
				yes.setOpaque(true);
			}
			else if(obj == no){
				no.setFont(new Font("Helvetica", Font.BOLD, 35));
				no.setForeground(Color.RED);
				no.setOpaque(true);
			}
			else if(obj == ok){
				ok.setFont(new Font("Helvetica", Font.BOLD, 35));
				ok.setForeground(Color.RED);
				ok.setOpaque(true);
			}
			repaint();
			revalidate();
		}
		public void mouseExited(MouseEvent e){
			Object obj = e.getSource();
			
			if(obj == yes){
				yes.setFont(new Font("Helvetica", Font.BOLD, 30));
				yes.setForeground(Color.WHITE);
				yes.setOpaque(true);
			}
			else if(obj == no){
				no.setFont(new Font("Helvetica", Font.BOLD, 30));
				no.setForeground(Color.WHITE);
				no.setOpaque(true);
			}
			else if(obj == ok) {
				ok.setFont(new Font("Helvetica", Font.BOLD, 30));
				ok.setForeground(Color.WHITE);
				ok.setOpaque(true);
			}
			repaint();
			revalidate();
		}
		public void mouseClicked(MouseEvent e){
			Object obj = e.getSource();
			
			if(obj == yes){
				if(isExit)
					System.exit(0);
				else {
					if(isMenu) {
						Launcher.f.dispose();
						Main.l = new Launcher();
						sender.send("Back at main menu");
					}
					else {
						if(turn.equals("OFF")) {
							Main.clip.stop();
						}
						else if(turn.equals("ON")) {
							Main.clip.start();
						}
					}
				}
				outer().dispose();
			}
			else if(obj == no){
				if(!isExit) {
					if(!isMenu) {
						if(turn.equals("OFF")) {
							turn = "ON";
						}
						else if(turn.equals("ON")) {
							turn = "OFF";
						}
					}
				}
				outer().dispose();
			}
			else if(obj == ok) {
				Launcher.f.dispose();
				Main.l = new Launcher();
			}
			repaint();
			revalidate();
		}
	}
	public void mainMenu(Sender send) {
		sender = send;
		
		isExit = false;
		isMenu = true;
		exit = new JLabel("BACK TO MAIN MENU");
		exit.setBounds(90, 40, 600, 50);
		exit.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 50));
		exit.setForeground(Color.WHITE);
		
		exitQuestion = new JLabel("Are you sure you want to go back to main menu?");
		exitQuestion.setBounds(60, 125, 600, 50);
		exitQuestion.setFont(new Font("Helvetica", Font.BOLD, 25));
		exitQuestion.setForeground(Color.WHITE);
		
		panel.add(exit);
		panel.add(exitQuestion);
		setVisible(true);
	}
	public void gotBackToMainMenu() {
	
		panel.removeAll();
		exit = new JLabel("       YOUR OPPONENT", JLabel.CENTER);
		exit.setBounds(0, 60, 600, 50);
		exit.setFont(new Font("Helvetica", Font.BOLD+Font.ITALIC, 50));
		exit.setForeground(Color.WHITE);
		
		exitQuestion = new JLabel("        EXITED THE GAME!", JLabel.CENTER);
		exitQuestion.setBounds(0, 145, 600, 50);
		exitQuestion.setFont(new Font("Helvetica", Font.BOLD,50));
		exitQuestion.setForeground(Color.WHITE);
		
		panel.add(exit);
		panel.add(exitQuestion);
		panel.add(ok);
		this.setAlwaysOnTop(true);
		setVisible(true);
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String t) {
		turn = t;
	}
	
	/*public static void main(String[] args) {
		new Dialog().gotBackToMainMenu();
	}*/
}