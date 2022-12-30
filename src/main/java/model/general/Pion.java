package src.main.java.model.general;

public class Pion {

	Joueur j;
	Coordonnee c;
	
	public Pion(Joueur j1,Coordonnee coord) {
		j = j1;
		c = coord;
	}
	
	public Joueur getJoueur() {
		return j;
	}
	
	public Coordonnee getCoord() {
		return c;
	}
}

