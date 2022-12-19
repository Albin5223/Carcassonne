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

    // Méthodes nécessaires pour un jeu de plateau
    public abstract void lancerPartie();
    public abstract void setJoueur();
    public abstract void jouerTour();

}
