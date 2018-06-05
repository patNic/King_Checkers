package checkers_gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import network.Receiver;
import network.Sender;

public class Launcher extends JDialog implements MouseListener, ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JProgressBar progressBar;
		private JPanel bg, leftPanel, rightPanel, oval;
		private JLabel load, checkers, loading, playerInfo, name, playWith,
			about, help, exit, status, connect, actAs, redKing, blackKing;
		private JTextField nameTF, playWithTF, statusTF;
		private JComboBox<String> combo;
		
		private Socket clientSocket;
		private ServerSocket serverSocket;
		private final int myPort = 9999;
		
		private final int MAX = 100;
		
		public static String playerName;
		public static String opponent;
		public static JFrame f;
		
		private Receiver myReceiver;
		private Sender mySender;
		
		public Launcher() {
			setLayout(null);
			setSize(800, 350);
			setLocationRelativeTo(null);
			setUndecorated(true);
			
			init();
			addComponents();
			 setVisible(true);
			  
	        for (int i = 0; i <= MAX; i++) {
	            final int currentValue = i;
	            try {
	                SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                        progressBar.setValue(currentValue);
	                    }
	                });
	                java.lang.Thread.sleep(15);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	        }
	        getContentPane().removeAll();
	        repaint();
	       
	        mainMenu();
	        repaint();
	        revalidate();
		}
		
		public void gameProper(String player1, String player2, Receiver r, Sender s, int turn) {
			this.dispose();
			
			f = new JFrame();
			MainPanel b = new MainPanel(player1, player2, r, s, turn);
			f.add(b);
			f.setSize(1020,730);
				
			f.setVisible(true);
			f.setResizable(false);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			b.checkIfPlayerStarted();
			b.getBoard().getPieceListener().checkIndexReceived();
		}
		public void init() {
			bg = new BGPanel();
			progressBar = new JProgressBar();
			progressBar.setMinimum(0);
			progressBar.setMaximum(MAX);
			progressBar.setStringPainted(true);
			progressBar.setForeground(new Color(139,69,19));
			progressBar.setBorderPainted(false);
			progressBar.setBounds(3, 320, 793, 28);
			
			ImageIcon icon = new ImageIcon("src/images/train.gif");
			load = new JLabel(icon);
			load.setBounds(10, 15, icon.getIconWidth(), icon.getIconHeight());
			
			checkers = new JLabel("KING CHECKERS", SwingConstants.CENTER);
			checkers.setBounds(290, 120, 500, 90);
			checkers.setFont(new Font("Elephant", Font.BOLD, 45));
			
			loading = new JLabel("Loading ...");
			loading.setBounds(480, 200, 500, 30);
			loading.setFont(new Font("Elephant", Font.BOLD, 25));
			
			redKing = new JLabel(new ImageIcon("src/images/blackKingLaunch.png"));
			redKing.setBounds(215, 43, 200, 150);
			
			blackKing = new JLabel(new ImageIcon("src/images/redKingLaunch.png"));
			blackKing.setBounds(550, 43, 200, 150);
		}
		public void addComponents() {
			add(bg);
			
			bg.add(progressBar);
			bg.add(load);
			bg.add(checkers);
			bg.add(loading);
			bg.add(redKing);
			bg.add(blackKing);
		}
		
		public void mainMenu() {
			initMainMenu();
			addMainMenu();
		}
		public void initMainMenu() {
			bg = new BGPanel();
			
			leftPanel = new JPanel(){
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
			    	g.drawImage(new ImageIcon("src/images/border.png").getImage(), 0, 0, getWidth(), getHeight(), leftPanel);
			     }
			  };
			leftPanel.setSize(400, 300);
			leftPanel.setLayout(null);
			leftPanel.setBounds(25, 25, 390, 300);
			
			rightPanel = new JPanel(){
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
			    	g.drawImage(new ImageIcon("src/images/playerInfo.png").getImage(), 0, 0, getWidth(), getHeight(), rightPanel);
			     }
			  };
			rightPanel.setSize(300, 300);
			rightPanel.setLayout(null);
			rightPanel.setBounds(475, 25, 280, 300);
			
			playerInfo = new JLabel("PLAYER INFORMATION");
			playerInfo.setBounds(25, 22, 290, 30);
			playerInfo.setFont(new Font("Arial", Font.BOLD, 20));
			playerInfo.setForeground(new Color(139,69,19));
			
			oval = new JPanel(){
				private static final long serialVersionUID = 1L;

				@Override
			     protected void paintComponent(Graphics g) {
			    	g.drawImage(new ImageIcon("src/images/download.jpg").getImage(), 0, 0, getWidth(), getHeight(), oval);
			     }
			  };
			oval.setBounds(25, 55, 225, 220);
			oval.setLayout(null);
			
			name = new JLabel("Name");
			name.setBounds(23, 15, 70, 30);
			name.setFont(new Font("Arial", Font.BOLD, 20));
			
			nameTF = new JTextField();
			nameTF.setBounds(95, 15, 108, 25);
			
			actAs = new JLabel("Act As");
			actAs.setBounds(23, 50, 70, 30);
			actAs.setFont(new Font("Arial", Font.BOLD, 20));
			
			String[] cs = {"CLIENT", "SERVER"};
			combo = new JComboBox<String>(cs);
			combo.setBounds(95, 50, 108, 30);
			combo.addActionListener(this);
			
			playWith = new JLabel("IP");
			playWith.setBounds(23, 85, 70, 30);
			playWith.setFont(new Font("Arial", Font.BOLD, 20));
			
			playWithTF = new JTextField();
			playWithTF.setBounds(95, 85, 108, 25);
			
			status = new JLabel("Status");
			status.setBounds(23, 125, 70, 30);
			status.setFont(new Font("Arial", Font.BOLD, 20));
			
			statusTF = new JTextField("Not Connected");
			statusTF.setBounds(95, 125, 108, 30);
			statusTF.setEditable(false);
			
			connect = new JLabel("Connect", SwingConstants.CENTER);
			connect.setBounds(23, 165, 180, 30);
			//connect.setForeground(Color.white);
			connect.setFont(new Font("Elephant", Font.BOLD, 20));
			connect.addMouseListener(this);
			
			checkers = new JLabel("KING CHECKERS");
			checkers.setBounds(22, 15, 500, 50);
			checkers.setFont(new Font("Elephant", Font.BOLD, 30));
			
			about = new JLabel(new ImageIcon("src/images/about.png"), SwingConstants.CENTER);
			about.setBounds(20, 80, 330, 50);
			about.addMouseListener(this);
			
			help = new JLabel(new ImageIcon("src/images/help.png"), SwingConstants.CENTER);
			help.setBounds(20, 140, 330, 50);
			help.addMouseListener(this);
			
			exit = new JLabel(new ImageIcon("src/images/exitMenu.png"), SwingConstants.CENTER);
			exit.setBounds(20, 200, 330, 50);
			exit.addMouseListener(this);
		}
		public void addMainMenu() {
			add(bg);
			bg.add(leftPanel);
			bg.add(rightPanel);
			
			leftPanel.add(checkers);
			leftPanel.add(about);
			leftPanel.add(help);
			leftPanel.add(exit);
			
			rightPanel.add(playerInfo);
			rightPanel.add(oval);
			
			oval.add(name);
			oval.add(nameTF);
			oval.add(playWith);
			oval.add(actAs);
			oval.add(combo);
			
			oval.add(playWithTF);
			oval.add(status);
			oval.add(statusTF);
			oval.add(connect);
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
			Object obj = arg0.getSource();
			if(obj == about) {
				new About_Help_Dialog("ABOUT");
			}
			else if(obj == exit) {
				System.exit(0);
			}
			else if(obj == help) {
				new About_Help_Dialog("HELP");
			}
			else if(obj == connect) {
				if(combo.getSelectedItem().toString().equals("CLIENT")) {
					try {
						clientSocket = new Socket(playWithTF.getText(), myPort);
					
						statusTF.setText("Connected");
						myReceiver = new Receiver(clientSocket);
						mySender = new Sender(clientSocket);
						
						 mySender.send("NAME_"+ nameTF.getText());
						 myReceiver.start();
			             checkIfNameIsSent();
			             gameProper(opponent, nameTF.getText(), myReceiver, mySender, 2);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Failed to connect...");
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			ImageIcon exitIcon2= new ImageIcon("src/images/exit3.png");
			ImageIcon helpIcon2= new ImageIcon("src/images/help3.png");
			ImageIcon newGameIcon2= new ImageIcon("src/images/about3.png");
			
			Object obj = arg0.getSource();
			
			if(obj == about) {
				about.setIcon(newGameIcon2);
			}
			else if(obj == help) {
				help.setIcon(helpIcon2);
			}
			else if(obj == exit) {
				exit.setIcon(exitIcon2);
			}
			else if(obj == connect) {
				connect.setFont(new Font("Elephant", Font.BOLD, 25));
			}
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			ImageIcon exitIcon= new ImageIcon("src/images/exitMenu.png");
			ImageIcon helpIcon= new ImageIcon("src/images/help.png");
			ImageIcon newGameIcon= new ImageIcon("src/images/about.png");
			
			Object obj = arg0.getSource();
			
			if(obj == about) {
				about.setIcon(newGameIcon);
			}
			else if(obj == help) {
				help.setIcon(helpIcon);
			}
			else if(obj == exit) {
				exit.setIcon(exitIcon);
			}
			else if(obj == connect) {
				connect.setFont(new Font("Elephant", Font.BOLD, 20));
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

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object obj = arg0.getSource();
			
			if(obj == combo) {
				if(combo.getSelectedItem().toString().equals("SERVER")) {
					playWithTF.setEditable(false);
					connect.setEnabled(false);
					
					Thread t1 = new Thread ((new Runnable() {
						@Override
					    public void run()
					    {
					         try {
					              serverSocket = new ServerSocket(myPort);
					              while (clientSocket == null){
							             try{
							               clientSocket = serverSocket.accept();
							             }catch(IOException e ){
							            	 System.out.println("At server socket accept "+ e.getMessage());
							             }
							         }
					              
					              myReceiver = new Receiver(clientSocket);
					              mySender = new Sender(clientSocket);
					               
					              mySender.send("NAME_"+ nameTF.getText());
					              statusTF.setText("Connected");
					              
					              myReceiver.start();
					              checkIfNameIsSent();
					              gameProper(nameTF.getText(), opponent, myReceiver, mySender, 1);
					              
					          }catch(IOException e ){
					             System.out.println("At server socket instantiation "+ e.getMessage());
					          }
					    }
					}));
					t1.start();
				}
			}
			
		}
		
	public void checkIfNameIsSent() {
		while(true) {
			Object obj = myReceiver.getObjectReceived();
			
			if(obj != null) {
				if(obj instanceof String && obj.toString().startsWith("NAME_")) {
					opponent = obj.toString().substring(5);
					statusTF.setText("To play with "+opponent);
					
					try {
						Thread.sleep(2000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				
				myReceiver.setObjectNull();
			}
			else {
				System.out.print("");
			}
		}
	}
}