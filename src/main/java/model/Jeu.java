package src.main.java.model;
/*
 * Cette classe abstraite permettra de représenter un jeu de plateau de manière générale.
 * Pour définir nos deux autres jeux, on les fera hériter de cette classe
 */

public abstract class Jeu {

    // Attributs d'un jeu de plateau
    protected Joueur[] joueurs;
    protected Plateau plateau;

    // Méthodes nécessaires pour un jeu de plateau
    public abstract void lancerPartie();
    public abstract void ajouterJoueur();
    public abstract void jouerTour();

}
