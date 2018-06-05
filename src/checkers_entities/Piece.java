package checkers_entities;

import javax.swing.*;

@SuppressWarnings("serial")
public class Piece extends JLabel{
	private ImageIcon blackIcon, redIcon, blackIcon3, redIcon3, redKing, redKing3,
		blackKing, blackKing3;
	private  int iconWidth, iconHeight;

	private int col;
	private final int id;
	private int x,y;
	private boolean isVisible, isKing;
	private int hasEaten;
	
	private static final int REDICON = 0;
	private static final int BLACKICON = 1;
	private static final int TRANSPARENT_REDICON = 2;
	private static final int TRANSPARENT_BLACKICON = 3;
	
	public Piece(int col, int x, int y, int id, boolean isVisible, boolean isKing, int hasEaten) {
		this.col = col;
		this.x=x;
		this.y=y;
		this.id = id;
		this.isVisible = isVisible;
		this.isKing = isKing;
		this.hasEaten = hasEaten;
		
		initIcons();
		
		iconDimension(createPieceIcon(this.col));
		this.setBounds(this.x,this.y,iconWidth, iconHeight);
		this.setEnabled(isVisible);
		this.setVisible(isVisible);
	}
	
	private void initIcons() {
		blackIcon = new ImageIcon("src/images/black.png");
		redIcon = new ImageIcon("src/images/red.png");
		redIcon3 = new ImageIcon("src/images/red3.png");
		blackIcon3 = new ImageIcon("src/images/black3.png");
		redKing = new ImageIcon("src/images/redKing.png");
		blackKing = new ImageIcon("src/images/blackKing.png");
		redKing3 = new ImageIcon("src/images/redKing3.png");
		blackKing3 = new ImageIcon("src/images/blackKing3.png");
		
	}
	
	private ImageIcon createPieceIcon(int col) {
		
		switch(col) {
		case REDICON:
			if(this.isKing){
				this.setIcon(redKing);
				return redKing;
			}
			
			this.setIcon(redIcon);
			return redIcon;
		case BLACKICON:
			if(this.isKing){
				this.setIcon(blackKing);
				return blackKing;
			}
			this.setIcon(blackIcon);
			return blackIcon;
		case TRANSPARENT_BLACKICON:
			if(this.isKing){
				this.setIcon(blackKing3);
				return blackKing3;
			}
			this.setIcon(blackIcon3);
			return blackIcon3;
		case TRANSPARENT_REDICON:
			if(this.isKing){
				this.setIcon(redKing3);
				return redKing3;
			}
			this.setIcon(redIcon3);
			return redIcon3;
		default:
			return null;
		}
	}
	private void iconDimension(ImageIcon icon) {
		
		iconWidth = icon.getIconWidth();
		iconHeight = icon.getIconHeight();
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setPieceIcon(ImageIcon icon) {
		this.setIcon(icon);
	}
	
	public void setPieceVisibility(boolean flag) {
		isVisible = flag;
	}
	public boolean getPieceVisibility() {
		return isVisible;
	}
	
	public void setIsKing(boolean isKing) {
		this.isKing = isKing;
	}
	
	public boolean getIsKing() {
		return isKing;
	}
	
	public ImageIcon getIcon(int col) {
		switch(col) {
		case REDICON:
			if(this.isKing){
				return redKing;
			}
			return redIcon;
		case BLACKICON:
			if(this.isKing){
				return blackKing;
			}
			return blackIcon;
		case TRANSPARENT_REDICON:
			if(this.isKing){
				return redKing3;
			}
			return redIcon3;
		case TRANSPARENT_BLACKICON:
			if(this.isKing){
				return blackKing3;
			}
			return blackIcon3;
		default:
			return null;
		}	
	}
	
	public int getPieceId() {
		return id;
	}
	
	public void setTileEaten(int hasEaten) {
		this.hasEaten = hasEaten;
	}
	
	public int getTileEaten(){
		return hasEaten;
	}
}
