package src.main.java.model.general;

public abstract class Strategie {
    
    protected Jeu jeu;

    // Méthode pour faire jouer le tour d'un ordinateur, renvoie true si la tuile se place, false sinon
    public abstract boolean jouerTour(Tuile tuile);
    public void setJeu(Jeu jeu){this.jeu = jeu;}

}
