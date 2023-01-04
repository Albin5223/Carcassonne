package src.main.java.model.general;
/*
 * Cette classe abstraite permettra de représenter un jeu de plateau de manière générale.
 * Pour définir nos deux autres jeux, on les fera hériter de cette classe
 */

import java.util.ArrayList;

public abstract class Jeu {

    // Attributs d'un jeu de plateau
    protected ArrayList<Joueur> joueurs;
    protected Plateau plateau;

    protected int niveauOrdinateur = 2;
    protected boolean partieFinie;
    protected ArrayList<Tuile> sac;    // Cet attribut représente les tuiles stockées dans le sac du jeu
    protected int maxScore = 20;        // Représente le nombre de points à avoir pour finir la partie
    protected final int maxPlayers = 4;   // Représente le nombre de joueurs maximal que peut accueillir notre jeu (on peut l'augmenter si besoin)
    protected int tour;

    // Méthodes nécessaires pour un jeu de plateau
    public ArrayList<Joueur> getJoueurs(){return joueurs;}
    public abstract Plateau getPlateau();

    public void abandonner() {
		if (joueurs.size()<=1) {
      		partieFinie = true;
		}
		else {
			joueurs.remove(tour);
			tour-=1;
		}
	}
	
	public boolean partieFinie() {
		return partieFinie || sacVide();
	}

	public boolean sacVide(){
		return sac.size() == 0;
	}

	public Joueur getCurrentJoueur(){
		if(tour <= -1){
			tour = 0;
		}
		return joueurs.get(tour);
	}

	public ArrayList<Joueur> joueursPremier(){
        int max = 0;
        for (Joueur joueur : joueurs) {
            if(joueur.getScore() > max){
                max = joueur.getScore(); 
            }
        }

        ArrayList<Joueur> list = new ArrayList<Joueur>();

        for (Joueur joueur : joueurs) {
            if(joueur.getScore() >= max){
                list.add(joueur); 
            }
        }
        return list;
    }

	public void addJoueur(Joueur j) {
		joueurs.add(j);
	}
	
	public void setStrategieForBot() {
		for (int i = 0;i<joueurs.size();i++) {
			if (joueurs.get(i) instanceof Ordinateur) {
				((Ordinateur) joueurs.get(i)).setStrategie(this);
			}
		}
	}

	public void joueurSuivant() {
		if(tour == -1) {
			tour = 0;
		}
		else {
			if (joueurs.get(tour).getScore()>=maxScore) {
				partieFinie = true;
			}
			else {
				tour+=1;
				if (tour == joueurs.size()) {
					tour=0;
				}
			}
		}
	}

  public abstract boolean placer(Tuile t,int x, int y);
  public abstract Tuile piocher(Joueur j);
  public abstract void initSac();

}
