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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class About_Help_Dialog extends JDialog implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel background_panel;
	private String message;
	private JLabel messageTitle ,x_Button;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	public About_Help_Dialog(String what) {
		message =what;
		
		setSize(600, 450);
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
		
		
		x_Button = new JLabel("CLOSE", SwingConstants.CENTER);
		x_Button.setBounds(0, 410, 600, 30);
		x_Button.setForeground(Color.white);
		x_Button.setFont(new Font("Helvetica", Font.BOLD, 25));
		x_Button.addMouseListener(this);
		
		messageTitle = new JLabel(message, SwingConstants.CENTER);
		messageTitle.setBounds(0, 10, 600, 50);
		messageTitle.setForeground(Color.white);
		messageTitle.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Helvetica", Font.PLAIN, 15));
		textArea.setForeground(new Color(139,69,19));
		textArea.setLayout(null);
		textArea.setSize(560, 1000);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 70, 560,330);
		
		if(message.equals("ABOUT")) {
			aboutText();
		}
		else if(message.equals("HELP")) {
			helpText();
		}
	}
	public void helpText() {
		textArea.setText("	Checkers is played on a standard 64 square board. ONLY the 32    dark colored squares are used "+
				"in each play. Each player begins the game with 12   pieces or checkers, placed in the three rows closest "
				+ "to him or her, with the first row having the king checkers.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		JLabel movement = new JLabel("Movement", SwingConstants.CENTER);
		movement.setBounds(0, 65, 550, 30);
		movement.setFont(new Font("Helvetica", Font.BOLD, 20));
		movement.setForeground(new Color(139,69,19));
		
		textArea.add(movement);
		
		JPanel movementPanel = new JPanel();
		movementPanel.setLayout(null);
		movementPanel.setBounds(10, 95, 522, 40);
		movementPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(movementPanel);
		
		JTextArea movementTextArea = new JTextArea();
		movementTextArea.setBounds(5, 5,520, 75);
		movementTextArea.setOpaque(false);
		movementTextArea.setForeground(new Color(139, 69, 19));
		movementTextArea.setLineWrap(true);
		movementTextArea.setText("Basic movement is to move a checker one space diagonally forward. You cannot move a checkers"
				+ " backwards until it becomes a king. If a jump is available, you must take the jump.");
		movementTextArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
		movementTextArea.setEditable(false);
		
		movementPanel.add(movementTextArea);
		
		JLabel jumping = new JLabel("Jumping", SwingConstants.CENTER);
		jumping.setBounds(0, 140, 550, 30);
		jumping.setFont(new Font("Helvetica", Font.BOLD, 20));
		jumping.setForeground(new Color(139,69,19));
		
		textArea.add(jumping);
		
		JPanel jumpingPanel = new JPanel();
		jumpingPanel.setLayout(null);
		jumpingPanel.setBounds(10, 170, 522, 80);
		jumpingPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(jumpingPanel);
		
		JTextArea jumpingTextArea = new JTextArea();
		jumpingTextArea.setBounds(5, 5,520, 75);
		jumpingTextArea.setOpaque(false);
		jumpingTextArea.setForeground(new Color(139, 69, 19));
		jumpingTextArea.setLineWrap(true);
		jumpingTextArea.setText("If one of your opponent's checkers is on a forward diagonal next to one of your checkers, "
				+ "and the next space beyond the opponent's checkers is empty, then your checker must jump the opponent's "
				+ "checker and land in the space beyond. Your opponent's checkers is captured and removed from the board.");
		jumpingTextArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
		jumpingTextArea.setEditable(false);
		
		jumpingPanel.add(jumpingTextArea);
		
		JPanel jumpingPanel1 = new JPanel();
		jumpingPanel1.setLayout(null);
		jumpingPanel1.setBounds(10, 260, 522, 55);
		jumpingPanel1.setBackground(new Color(139, 69, 19, 70));
		textArea.add(jumpingPanel1);
		
		JTextArea jumpingTextArea1 = new JTextArea("After making one jump, your checker might have another jump available from it new "
				+ "position. Your checker must take that jump too. It must continue to jump until there are not more jumps available"
				+ ". Both men and king are allowed multiple jumps. ");
		
		jumpingTextArea1.setBounds(5, 5,520, 50);
		jumpingTextArea1.setOpaque(false);
		jumpingTextArea1.setForeground(new Color(139, 69, 19));
		jumpingTextArea1.setLineWrap(true);
		jumpingTextArea1.setFont(new Font("Helvetica", Font.PLAIN, 12));
		jumpingTextArea1.setEditable(false);
		
		jumpingPanel1.add(jumpingTextArea1);
		
		JPanel jumpingPanel2 = new JPanel();
		jumpingPanel2.setLayout(null);
		jumpingPanel2.setBounds(10, 320, 522, 55);
		jumpingPanel2.setBackground(new Color(139, 69, 19, 70));
		textArea.add(jumpingPanel2);
		
		JTextArea jumpingTextArea2 = new JTextArea("If, at the start of a turn, more than one of your checkers has a jump "
				+ "available, then you may decide which one you will move. But once you have chosen one, it must take all the "
				+ "jumps that it can.");
		
		jumpingTextArea2.setBounds(5, 5,520, 50);
		jumpingTextArea2.setOpaque(false);
		jumpingTextArea2.setForeground(new Color(139, 69, 19));
		jumpingTextArea2.setLineWrap(true);
		jumpingTextArea2.setFont(new Font("Helvetica", Font.PLAIN, 12));
		jumpingTextArea2.setEditable(false);
		
		jumpingPanel2.add(jumpingTextArea2);
		
		JLabel jumpPos = new JLabel("You Must Jump If Possible", SwingConstants.CENTER);
		jumpPos.setBounds(0, 375, 550, 30);
		jumpPos.setFont(new Font("Helvetica", Font.BOLD, 20));
		jumpPos.setForeground(new Color(139,69,19));
		
		textArea.add(jumpPos);
		
		JPanel jumpPosPanel = new JPanel();
		jumpPosPanel.setLayout(null);
		jumpPosPanel.setBounds(10, 405, 522, 120);
		jumpPosPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(jumpPosPanel);
		
		JTextArea jumpPosTextArea = new JTextArea();
		jumpPosTextArea.setBounds(5, 5,520, 115);
		jumpPosTextArea.setOpaque(false);
		jumpPosTextArea.setForeground(new Color(139, 69, 19));
		jumpPosTextArea.setLineWrap(true);
		jumpPosTextArea.setText("If a jump is available for one of your pieces, you must make that jump. If more jumps are available "
				+ "with that same piece, you must continue to jump with it until it can jump no more. To make the second and third jump"
				+ " with a piece, you need to click the piece again.Just click the next possible space to which it will jump.\n\n"
				+ "If more than one of your pieces has a jump available at the start of your turn, you can choose which piece you will"
				+ " move. But then, you must make all the available jumps for that piece.");
		jumpPosTextArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
		jumpPosTextArea.setEditable(false);
		
		jumpPosPanel.add(jumpPosTextArea);
		
		JLabel crowning = new JLabel("Crowning", SwingConstants.CENTER);
		crowning.setBounds(0, 522, 550, 30);
		crowning.setFont(new Font("Helvetica", Font.BOLD, 20));
		crowning.setForeground(new Color(139,69,19));
		
		textArea.add(crowning);
		
		JPanel crowningPanel = new JPanel();
		crowningPanel.setLayout(null);
		crowningPanel.setBounds(10, 555, 522, 180);
		crowningPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(crowningPanel);
		
		JTextArea crowningTextArea = new JTextArea();
		crowningTextArea.setBounds(5, 5,520, 170);
		crowningTextArea.setOpaque(false);
		crowningTextArea.setForeground(new Color(139, 69, 19));
		crowningTextArea.setLineWrap(true);
		crowningTextArea.setText("When one of your checkers reaches the opposite side of the board, it is crowned"
				+ " and becomes a King. Your turn ends there. \n\nA King can move backward as well as forward along the diagonals. It can only move a distance of one space. \r\n" + 
				"\r\n" + 
				"A King can also jump backward and forward. It must jump when possible, and it must take all jumps that are available to it. In each jump, the King can only jump over"
				+ " one opposing piece at a time, and it must land in the space just beyond the captured piece. The King can not move multiple spaces before or after jumping a piece. ");
		crowningTextArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
		crowningTextArea.setEditable(false);
		
		crowningPanel.add(crowningTextArea);
		
		JLabel winning = new JLabel("Winning or Losing the Game", SwingConstants.CENTER);
		winning.setBounds(0, 730, 550, 30);
		winning.setFont(new Font("Helvetica", Font.BOLD, 20));
		winning.setForeground(new Color(139,69,19));
		
		textArea.add(winning);
		
		JPanel winningPanel = new JPanel();
		winningPanel.setLayout(null);
		winningPanel.setBounds(10, 760, 522, 200);
		winningPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(winningPanel);
		
		JTextArea winningTextArea = new JTextArea();
		winningTextArea.setBounds(5, 5,520, 195);
		winningTextArea.setOpaque(false);
		winningTextArea.setForeground(new Color(139, 69, 19));
		winningTextArea.setLineWrap(true);
		winningTextArea.setText("You win the game if you have eaten all the kings of your opponent. You lose the game if you have no king checkers left."
				+ " You also lose the game if you lose all your  pieces. \t\nTo summarize, you can win the game in one of 3 ways:\r\n" + 
				"\tBy capturing all of your OPPONENT's pieces\r\n" + 
				"\tBy capturing all kings of your opponent, with regular pieces left\r\n\r\n" + 
				"\r\n" + 
				"Or, put another way, you lose the game in one of 3 ways:\r\n" + 
				"\tBy losing all of YOUR pieces\r\n" + 
				"\tBy losing all of your kings, with only regular pieces left\r\n" + 
				"\tBy promoting YOUR Mule (it ends up on the row furthest away from you)");
		winningTextArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
		winningTextArea.setEditable(false);
		
		winningPanel.add(winningTextArea);
	}
	public void aboutText() {
		textArea.setText("King Checkers is a game inspired by the classical game checkers with a very little   twist added."+
				"It is a two-player game with players interacting over a network.");
		
		JLabel images = new JLabel("Images Taken From The Following Sites", SwingConstants.CENTER);
		images.setBounds(0, 75, 550, 30);
		images.setFont(new Font("Helvetica", Font.BOLD, 20));
		images.setForeground(new Color(139,69,19));
		
		textArea.add(images);
		
		JPanel imagesPanel = new JPanel();
		imagesPanel.setLayout(null);
		imagesPanel.setBounds(10, 105, 522, 180);
		imagesPanel.setBackground(new Color(139, 69, 19, 70));
		textArea.add(imagesPanel);
		
		JLabel one = new JLabel("http://mazyod.com/images/img_1202.png",SwingConstants.CENTER);
		one.setBounds(0, 5, 550, 20);
		one.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		one.setForeground(new Color(139,69,19));
	
		imagesPanel.add(one);
		
		JLabel two = new JLabel("https://freemobileapk.com/category/board/page/2/",SwingConstants.CENTER);
		two.setBounds(0, 25, 550, 20);
		two.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		two.setForeground(new Color(139,69,19));
		
		imagesPanel.add(two);
		
		JLabel three = new JLabel("https://www.kisspng.com/png-green-yes-button-31170/download-png.html",SwingConstants.CENTER);
		three.setBounds(0, 45, 550, 20);
		three.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		three.setForeground(new Color(139,69,19));
		
		imagesPanel.add(three);
		
		JLabel four = new JLabel("https://codecanyon.net/item/master-checkers-html5-board-game/12469262",SwingConstants.CENTER);
		four.setBounds(0, 65, 550, 20);
		four.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		four.setForeground(new Color(139,69,19));
		
		imagesPanel.add(four);
		
		JLabel five = new JLabel("https://cdn.tutsplus.com/psd/uploads/2014/02/checkersGameInterface0.jpg",SwingConstants.CENTER);
		five.setBounds(0, 85, 550, 20);
		five.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		five.setForeground(new Color(139,69,19));
		
		imagesPanel.add(five);
		
		JLabel six = new JLabel("http://www.twoplayergames.org/files/mobile_games/o1/Master_Checkers",SwingConstants.CENTER);
		six.setBounds(0, 105, 550, 20);
		six.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		six.setForeground(new Color(139,69,19));
		
		imagesPanel.add(six);
		
		JLabel six1 = new JLabel("/sounds/king.mp3",SwingConstants.CENTER);
		six1.setBounds(0, 120, 550, 20);
		six1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		six1.setForeground(new Color(139,69,19));
		
		imagesPanel.add(six1);
		
		JLabel seven = new JLabel("https://www.pixlis.com/background-image-checkers-chequered-checkered",SwingConstants.CENTER);
		seven.setBounds(0, 140, 550, 20);
		seven.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		seven.setForeground(new Color(139,69,19));
		
		imagesPanel.add(seven);
		
		JLabel seven1 = new JLabel("-squares-seamless-tileable-porsche-saddle-brown-236nr7.html",SwingConstants.CENTER);
		seven1.setBounds(0, 155, 550, 20);
		seven1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		seven1.setForeground(new Color(139,69,19));
		
		imagesPanel.add(seven1);
		
	}
	public void addComponents() {
		add(background_panel);
	
		background_panel.add(x_Button);
		background_panel.add(messageTitle);
		background_panel.add(scrollPane);
	}
	private class BGPanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public BGPanel() {
			setSize(600, 450);
			setLayout(null);
		}
		Image board_image = new ImageIcon("src/images/main_menuBG.jpg").getImage();
		public void paintComponent(Graphics g) {
			g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		Object obj = arg0.getSource();
		
		if(obj == x_Button) {
			this.dispose();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		
		if(obj == x_Button) {
			x_Button.setFont(new Font("Helvetica", Font.BOLD, 30));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		
		if(obj == x_Button) {
			x_Button.setFont(new Font("Helvetica", Font.BOLD, 25));
		}
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
