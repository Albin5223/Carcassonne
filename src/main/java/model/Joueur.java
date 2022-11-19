package src.main.java.model;
public class Joueur {

    // Attributs nécessaires pour définir un joueur
    private String nom;
    private int id;
    private int score;

    // Constructeur
    public Joueur(String nom){
        this.nom = nom;
        score = 0;
    }

    // Méthodes getteurs
    public String getNom(){return nom;}
    public int getID(){return id;}
    public int getScore(){return score;}

    // Méthode demi-setteur : on augmente le score d'un certain nombre (non-négatif)
    public void ajouterScore(int score){this.score += (score >= 0) ? (score) : (0);}
    // Méthode demi-setteur : on baisse le score d'un certain nombre (non-négatif)
    public void enleverScore(int score){
        // On empèche les entiers négatifs
        this.score -= (score >= 0) ? (score) : (0);
        // Si le score du joueur est tombé dans les négatifs, on le remonte à zéro
        this.score = (this.score < 0) ? 0 : this.score;
    }
}
