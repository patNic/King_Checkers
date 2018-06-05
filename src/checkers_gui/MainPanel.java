package checkers_gui;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import checkers_entities.PieceListener;
import network.Receiver;
import network.Sender;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable{
	public static Board board;
	private JPanel sidePanel1;
	private JPanel sidePanel2;
	
	private JLabel settings, exit, player1, player2, player1Eat, player2Eat, capt_player1, capt_player2,turn, mainmenu,
		 nameLabel1, nameLabel2, playerName1, playerName2;
	private JLabel[] eatenRedPieces, eatenBlackPieces;
	
	private MouseListener mouseListener;
	private Receiver myReceiver;
	private Sender mySender;
	private Dialog dialog;
	
	private int my_turn;
	
	public static JLabel whoseTurn;
	public static String player1Name, player2Name;
	
	public MainPanel(String player1, String player2, Receiver receive, Sender sender,int turn) {
		setLayout(null);
		setPreferredSize(new Dimension(1000,700));
		
		player1Name = player1;
		player2Name = player2;
		
		myReceiver = receive;
		mySender = sender;
		my_turn = turn;
		
		initComponents();
		addComponents();
	}
	public MainPanel returnMainPanel(){
		return this;
	}
	Image board_image = new ImageIcon("src/images/sidepanel.jpg").getImage();
	public void paintComponent(Graphics g) {
		g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
	}
	public void initComponents()
	{
		turn = new JLabel("TURN", SwingConstants.CENTER);
		turn.setBounds(10, 25, 180, 65);
		turn.setForeground(new Color(139,69,19));
		turn.setFont(new Font("Helvetica", Font.BOLD, 35));
		turn.setBackground(new Color(255, 255, 255, 125));
		turn.setOpaque(true);
		
		whoseTurn = new JLabel(player1Name, SwingConstants.CENTER);
		whoseTurn.setBounds(10, 90, 180, 50);
		whoseTurn.setForeground(Color.RED);
		whoseTurn.setFont(new Font("Helvetica", Font.BOLD, 25));
	
		board = new Board(myReceiver, mySender, my_turn);
		board.setBounds(200, 2, 600, 697);
		
		ImageIcon sp1=  new ImageIcon("src/images/player1info.png");
		ImageIcon sp2=  new ImageIcon("src/images/player2info.png");
		ImageIcon settingsIcon= new ImageIcon("src/images/settings.png");
		ImageIcon exitIcon= new ImageIcon("src/images/exit.png");
		
		sidePanel1 = new JPanel()
				{
					Image board_image = sp1.getImage();
					public void paintComponent(Graphics g) {
					g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
			       }
				};
		sidePanel1.setLayout(null);
		sidePanel1.setBounds(25, 170, 150, 356);
		
		sidePanel2 = new JPanel()
				{
					Image board_image = sp2.getImage();
					public void paintComponent(Graphics g) {
					g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
				}
			};
		sidePanel2.setLayout(null);
		sidePanel2.setBounds(825, 170, 150, 356);
		
		mouseListener = new MouseListener();
		settings = new JLabel(settingsIcon);
		settings.setBounds(50,570,settingsIcon.getIconWidth(), settingsIcon.getIconHeight());
		settings.addMouseListener(mouseListener);
		
		exit = new JLabel(exitIcon);
		exit.setBounds(850,570,settingsIcon.getIconWidth(), settingsIcon.getIconHeight());
		exit.addMouseListener(mouseListener);
		
		player1 = new JLabel(new ImageIcon("src/images/player1.png"));
		player1.setBounds(-5, -8, 160, 100);
		
		player2 = new JLabel(new ImageIcon("src/images/player2.png"));
		player2.setBounds(-5, -8, 160, 100);
		
		capt_player1 = new JLabel("Captured Pieces");
		capt_player1.setBounds(17, 108, 170, 100);
		capt_player1.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		capt_player2 = new JLabel("Captured Pieces");
		capt_player2.setBounds(17, 108, 170, 100);
		capt_player2.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		player1Eat = new JLabel("0", SwingConstants.CENTER);
		player1Eat.setBounds(0, 140, 150, 100);
		player1Eat.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		player2Eat = new JLabel("0", SwingConstants.CENTER);
		player2Eat.setBounds(0, 140, 150, 100);
		player2Eat.setFont(new Font("Helvetica", Font.BOLD, 40));
		
	/*	scoreLabel1 = new JLabel("Score");
		scoreLabel1.setBounds(53, 215, 170, 20);
		scoreLabel1.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		scoreLabel2 = new JLabel("Score");
		scoreLabel2.setBounds(53, 215, 170, 20);
		scoreLabel2.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		player1Score = new JLabel("0");
		player1Score.setBounds(60, 205, 50, 100);
		player1Score.setFont(new Font("Helvetica", Font.BOLD, 40));
		
		player2Score = new JLabel("0");
		player2Score.setBounds(60, 205, 50, 100);
		player2Score.setFont(new Font("Helvetica", Font.BOLD, 40));*/
		
		nameLabel1 = new JLabel("Name", SwingConstants.CENTER);
		nameLabel1.setBounds(0, 80, 150, 20);
		nameLabel1.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		nameLabel2 = new JLabel("Name", SwingConstants.CENTER);
		nameLabel2.setBounds(0, 80, 150, 20);
		nameLabel2.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		playerName1 = new JLabel(player1Name, SwingConstants.CENTER);
		playerName1.setBounds(0,105, 150, 35);
		playerName1.setFont(new Font("Helvetica", Font.BOLD, 30));
		
		playerName2 = new JLabel(player2Name, SwingConstants.CENTER);
		playerName2.setBounds(0,105, 150, 35);
		playerName2.setFont(new Font("Helvetica", Font.BOLD, 30));
		
		mainmenu = new JLabel(new ImageIcon("src/images/mainmenu.png"));
		mainmenu.setBounds(785, 30, 230, 130);
		mainmenu.addMouseListener(mouseListener);
		
		eatenRedPieces = new JLabel[12];
		eatenBlackPieces = new JLabel[12];
		
		dialog = new Dialog();
				
		int x = 20;
		ImageIcon redIcon = new ImageIcon("src/images/red.png");
		ImageIcon blackIcon = new ImageIcon("src/images/black.png");
		for(int i = 0; i < 12; i++) {
			eatenRedPieces[i] = new JLabel(redIcon);
			eatenBlackPieces[i] = new JLabel(blackIcon);
			
			eatenRedPieces[i].setBounds(x, 25, redIcon.getIconWidth(), redIcon.getIconHeight());
			eatenBlackPieces[i].setBounds(x, 610, blackIcon.getIconWidth(), blackIcon.getIconHeight());
			x+=redIcon.getIconWidth() -15;
			
			eatenRedPieces[i].setVisible(false);
			eatenBlackPieces[i].setVisible(false);
		}
	}
	public void addComponents()
	{
		add(board);
		add(sidePanel1);
		add(sidePanel2);
		add(settings);
		add(exit);
	
		sidePanel1.add(player1);
		sidePanel1.add(capt_player1);
		sidePanel1.add(player1Eat);
		sidePanel1.add(nameLabel1);
		sidePanel1.add(playerName1);
		//sidePanel1.add(scoreLabel1);
		//sidePanel1.add(player1Score);
		
		//sidePanel1.add(player1Name);
		sidePanel2.add(player2);
		sidePanel2.add(capt_player2);
		sidePanel2.add(player2Eat);
		sidePanel2.add(nameLabel2);
		sidePanel2.add(playerName2);
		//sidePanel2.add(scoreLabel2);
		//sidePanel2.add(player2Score);
		//sidePanel2.add(player2Name);
		add(mainmenu);
		add(turn);
		add(whoseTurn);
		
		for(int i =  0; i < 12; i++)
		{
			board.add(eatenRedPieces[i]);
			board.add(eatenBlackPieces[i]);
		}
		
		Thread t = new Thread(this);
		t.start();
	}
	private class MouseListener extends MouseAdapter
	{
		ImageIcon exitIcon2= new ImageIcon("src/images/exit2.png");
		ImageIcon exitIcon1= new ImageIcon("src/images/exit.png");
		ImageIcon settingsIcon2= new ImageIcon("src/images/settings2.png");
		ImageIcon settingsIcon1= new ImageIcon("src/images/settings.png");
		public void mouseClicked(MouseEvent e){
			Object obj = e.getSource();
			if(obj == exit){
				new Dialog().exit();
			}
			else if(obj == settings){
				String turn = dialog.getTurn();
				
				if(turn.equals("ON")) {
					dialog.setTurn("OFF");
					dialog.settings();
				}
				else if(turn.equals("OFF")){
					dialog.setTurn("ON");
					dialog.settings();
				}
			}
			else if(obj == mainmenu){
			     new Dialog().mainMenu(mySender);
			}
		}
		public void mouseEntered(MouseEvent e){
			Object obj = e.getSource();
			
			if(obj == exit){
				exit.setIcon(exitIcon2);
			}
			else if(obj == settings){
				settings.setIcon(settingsIcon2);
			}
			else if(obj == mainmenu){
				mainmenu.setIcon(new ImageIcon("src/images/mainmenu1.png"));
			}
			repaint();
			revalidate();
		}
		public void mouseExited(MouseEvent e){
			Object obj = e.getSource();
			
			if(obj == exit){
				exit.setIcon(exitIcon1);
			}
			else if(obj == settings){
				settings.setIcon(settingsIcon1);
			}
			else if(obj == mainmenu){
				mainmenu.setIcon(new ImageIcon("src/images/mainmenu.png"));
			}
			repaint();
			revalidate();
		}
	}
	@Override
	public void run() {
		int redcount = 12 - PieceListener.redPiecesCount;
		int blackcount = 12 - PieceListener.blackPiecesCount;
		while(true)
		{
			if(redcount == (12 - PieceListener.redPiecesCount) && blackcount == (12 - PieceListener.blackPiecesCount)) {
				System.out.print("");
			}
			else{
				redcount = 12 - PieceListener.redPiecesCount;
				blackcount = 12 - PieceListener.blackPiecesCount;
				
				player1Eat.setText(blackcount+"");
				player2Eat.setText(redcount+"");
			
				for(int i = 0; i < redcount; i++) {
					if(PieceListener.latestRed && i == (redcount -1)) {
							eatenRedPieces[i].setIcon(new ImageIcon("src/images/redKing.png"));
							eatenRedPieces[i].setVisible(true);
							break;
					}
					eatenRedPieces[i].setVisible(true);
				}
				for(int i = 0; i < blackcount; i++) {
					if(PieceListener.latestBlack && i == (blackcount -1)) {
						eatenBlackPieces[i].setIcon(new ImageIcon("src/images/blackKing.png"));
						eatenBlackPieces[i].setVisible(true);
						break;
					}
					eatenBlackPieces[i].setVisible(true);
				}
			
				board.repaint();
			}
		}
	}
	public void checkIfPlayerStarted() {
		mySender.send("I just started");
		while(true) {
			Object obj = myReceiver.getObjectReceived();
			
			if(obj != null) {
				if(obj.toString().equals("I just started")) {
					break;
				}
				
				myReceiver.setObjectNull();
			}
			else {
				System.out.print("");
			}
		}
	}
	public Board getBoard() {
		return board;
	}
}

