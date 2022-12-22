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
    
    protected ArrayList <Tuile> sac;    // Cet attribut représente les tuiles stockées dans le sac du jeu
    protected int maxScore = 20;        // Représente le nombre de points à avoir pour finir la partie
    protected final int maxPlayers = 4;   // Représente le nombre de joueurs maximal que peut accueillir notre jeu (on peut l'augmenter si besoin)
    protected int tour; 

    // Méthodes nécessaires pour un jeu de plateau
    
    public abstract void lancerPartie();
    public abstract void setJoueur();
    public abstract void jouerTour();
    public ArrayList<Joueur> getJoueurs(){return joueurs;}
    public abstract Plateau getPlateau();

}
