package checkers_entities;

import java.io.Serializable;

public class Eat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eater, food, jumpTo;
	public Eat(int eater, int food, int jumpTo) {
		this.eater = eater;
		this.food = food;
		this.jumpTo = jumpTo;
	}
	
	public int getEater() {
		return eater;
	}
	
	public int getFood() {
		return food;
	}
	
	public int getJumpTo() {
		return jumpTo;
	}
}
