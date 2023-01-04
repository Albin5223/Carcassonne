package src.main.java.model.general;

import java.util.ArrayList;
import java.util.Scanner;

import src.main.java.model.general.Pion.Couleurs;

public class Joueur {

    // Attributs nécessaires pour définir un joueur
    protected String nom;
    protected int id;
    protected int score;
    protected boolean abandon;
    protected boolean ordinateur;

    protected ArrayList<Pion> pionsDispo = new ArrayList<Pion>();
    protected ArrayList<Pion> pionsPlaces = new ArrayList<Pion>();
    protected int nb_pions = 8;

    // Constructeur
    public Joueur(String nom, int id){
        this.nom = nom;
        this.id = id;
        score = 0;
        abandon = false;
        ordinateur = false;
        setPions(id);
    }

    // Constructeur avec un scanner
    @SuppressWarnings("resource")
    public Joueur(int id){
        Scanner sc = new Scanner(System.in);
        nom = sc.nextLine();
        score = 0;
        this.id = id;
        abandon = false;
        ordinateur = false;
    }

    // Méthodes getteurs
    public String getNom(){return nom;}
    public int getID(){return id;}
    public int getScore(){return score;}

    public String toString(){
        return "Nom : "+nom+" / ID : "+id+" / Score : "+score;
    }

    public boolean isBot(){
        return ordinateur;
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

    public void setPions(int id){
        Couleurs c = getCouleursPion();
        for (int i = 0; i < nb_pions; i++) {
            pionsDispo.add(new Pion(this, null, c));
        }
    }

    public Couleurs getCouleursPion(){
        switch (id) {
            default:
                return Couleurs.BLEU;
            case 1:
                return Couleurs.JAUNE;
            case 2:
                return Couleurs.VERT;
            case 3:
                return Couleurs.ROUGE;
        }
    }

    public boolean pionsIsEmpty(){return pionsDispo.isEmpty();}

    public Pion popPion() throws PionsVideException {
        if(pionsIsEmpty()){throw new PionsVideException();}
        Pion p = pionsDispo.remove(0);
        pionsPlaces.add(p);
        return p;
    }
}
