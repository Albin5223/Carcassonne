package src.main.java.model.general;

public class Coordonnee {

	protected int x;
	protected int y;
	
	public Coordonnee(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Coordonnee c1) {
		if (c1.x == this.x && c1.y == this.y) {
			return true;
		}
		return false;
	}
}
