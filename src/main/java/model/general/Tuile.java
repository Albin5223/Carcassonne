package src.main.java.model.general;
/*
 * Classe abstraite représentant une tuile
 */
public abstract class Tuile {

    // Attributs
    protected Joueur titulaire;
    public Cote haut;     // Domino qui se situe en haut de la tuile
    public Cote droite;   // Domino qui se situe à droite de la tuile
    public Cote bas;      // Domino qui se situe en bas de la tuile
    public Cote gauche;   // Domino qui se situe à gauche de la tuile

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public Tuile(Cote haut, Cote droite, Cote bas, Cote gauche){
        this.haut = haut;
        this.droite = droite;
        this.bas = bas;
        this.gauche = gauche;
    }

    // Méthodes getteurs
    public Joueur getTitulaire(){return titulaire;}

    // Méthodes setteurs
    public void setTitulaire(Joueur titulaire){this.titulaire = titulaire;}

    public void rotation(){
        Cote h = gauche.inversePiece();
        Cote d = haut;
        Cote b = droite.inversePiece();
        Cote g = bas;

        this.haut = h;
        this.droite = d;
        this.bas = b;
        this.gauche = g;
    }
}
