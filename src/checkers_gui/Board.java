package checkers_gui;
import java.awt.*;

import javax.swing.*;

import checkers_entities.Piece;
import checkers_entities.PieceListener;
import checkers_entities.Tile;
import network.Receiver;
import network.Sender;

@SuppressWarnings("serial")
public class Board extends JPanel{
	public static Piece[] piece = new Piece[32];
	public static Tile[] tile = new Tile[32];
	private PieceListener pl;
	private static final int WHITEICON = 0;
	private static final int GREENICON = 1;
	private static final int TRANSPARENT_WHITEICON = 2;
	private static final int TRANSPARENT_GREENICON = 3;
	public static int blackEaten;
	public static int redEaten;
	public static Tile ownTile;
	private Receiver myReceiver;
	private Sender mySender;
	private int my_turn;
	
	public Board(Receiver r, Sender s, int turn) {
		setLayout(null);
		setPreferredSize(new Dimension(600,695));
		
		myReceiver = r;
		mySender = s;
		my_turn = turn;
		
		init();
	}
	private void init() {
		pl = new PieceListener(myReceiver, mySender, my_turn);
		placePiecesAndTiles();
	}
	
	private void placePiecesAndTiles() {
		int x = 80;
		int y = 125;
		int col = GREENICON;
		boolean isVisible = true;
		
		for(int i = 0;i<=31;i++) {
			
			if(i<=3) {
				x = (i==0) ? 90 : 142+x;
				
			}else if(i >= 4 && i< 8) {
				x = (i==4) ? 17 : 142+x;
				y = 180;
			}else if(i>=8 && i <12) {
				x = (i==8) ? 90 : 142+x;
				y = 235;
			}else if(i>=12 && i <16) {
				col = WHITEICON;
				x = (i==12) ? 17 : 142+x;
				y = 400;
			}else if(i>=16 && i <20) {
				col = 0;
				x = (i==16) ? 90 : 142+x;
				y = 455;
			}else if(i>=20 && i<24){
			
				col = 0;
				x = (i==20) ? 17 : 142+x;
				y = 510;
			}else if(i>=24 && i<28){
				col = TRANSPARENT_GREENICON;
				x = (i==24) ? 22 : 142+x;
				y = 290;
				isVisible = false;
			}else {
				col = TRANSPARENT_WHITEICON;
				x = (i==28) ? 90: 142+x;
				y = 345;
				isVisible = false;
			}
			
			if(i == 0 || i == 1|| i ==2 || i ==3 || i == 23 || i == 22 || i == 21 || i ==20)
				piece[i] = new Piece(col,x,y,i,isVisible, true, -1);
			else
				piece[i] = new Piece(col,x,y,i,isVisible, false, -1);
			
			if(col == 0 && isVisible)
				piece[i].addMouseListener(pl);
			
			tile[i] = new Tile(i,piece[i],-1);
			this.add(piece[i]);	
		}
	}
	public PieceListener getPieceListener() {
		return pl;
	}
	Image board_image = new ImageIcon("src/images/gameboard.png").getImage();
	public void paintComponent(Graphics g) {
		g.drawImage(board_image, 0, 0, getWidth(), getHeight(), this);
	}
}

