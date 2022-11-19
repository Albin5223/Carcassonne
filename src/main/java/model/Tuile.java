package src.main.java.model;
/*
 * Classe abstraite représentant une tuile
 */
public abstract class Tuile {

    // Attributs
    protected Joueur titulaire;

    // Méthodes getteurs
    public Joueur getTitulaire(){return titulaire;}

    // Méthodes setteurs
    public void setTitulaire(Joueur titulaire){this.titulaire = titulaire;}

}
