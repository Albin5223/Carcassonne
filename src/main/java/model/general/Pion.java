package src.main.java.model.general;

public class Pion {

	protected Joueur j;
	protected Coordonnee c;
	protected boolean estPose;
	public enum Couleurs{BLEU, JAUNE, VERT, ROUGE}
	protected Couleurs couleur;
	
	public Pion(Joueur j1,Coordonnee coord, Couleurs couleur) {
		j = j1;
		c = coord;
		this.couleur = couleur;
	}

	public void poser(Tuile t){
		estPose = true;
		c = t.getCoordonnee();
	}
	
	public Joueur getJoueur() {return j;}
	public Coordonnee getCoord() {return c;}
	public boolean estPose(){return estPose;}
	public Couleurs getCouleurs(){return couleur;}
}