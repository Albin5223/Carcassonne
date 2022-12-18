package src.main.java.model.general;

import java.util.Scanner;

public class Joueur {

    // Attributs nécessaires pour définir un joueur
    private String nom;
    private int id;
    private int score;
    private boolean abandon;

    // Constructeur
    public Joueur(String nom, int id){
        this.nom = nom;
        this.id = id;
        score = 0;
        abandon = false;
    }

    // Constructeur avec un scanner
    public Joueur(int id){
        Scanner sc = new Scanner(System.in);
        this.nom = sc.nextLine();
        this.score = 0;
        this.id = id;
        sc.close();
    }

    // Méthodes getteurs
    public String getNom(){return nom;}
    public int getID(){return id;}
    public int getScore(){return score;}

    public String toString(){
        return "Nom : "+nom+" / ID : "+id+" / Score : "+score;
    }

    // Méthode demi-setteur : on augmente le score d'un certain nombre (non-négatif)
    public void ajouterScore(int score){this.score += (score >= 0) ? (score) : (0);}
    // Méthode demi-setteur : on baisse le score d'un certain nombre (non-négatif)
    public void enleverScore(int score){
        // On empèche les entiers négatifs
        this.score -= (score >= 0) ? (score) : (0);
        // Si le score du joueur est tombé dans les négatifs, on le remonte à zéro
        this.score = (this.score < 0) ? 0 : this.score;
    }
    // Méthode pour abandonner
    public void abandonne(){
        abandon = true;
    }
}
