package src.main.java.model.general;
/*
 * Classe abstraite représentant une tuile
 */
public abstract class Tuile<T> {

    // Attributs
    protected Joueur titulaire;
    public Cote<T> haut;     // Domino qui se situe en haut de la tuile
    public Cote<T> droite;   // Domino qui se situe à droite de la tuile
    public Cote<T> bas;      // Domino qui se situe en bas de la tuile
    public Cote<T> gauche;   // Domino qui se situe à gauche de la tuile

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public Tuile(Cote<T> haut, Cote<T> droite, Cote<T> bas, Cote<T> gauche){
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
        Cote<T> h = gauche.inversePiece();
        Cote<T> d = haut;
        Cote<T> b = droite.inversePiece();
        Cote<T> g = bas;

        this.haut = h;
        this.droite = d;
        this.bas = b;
        this.gauche = g;
    }
}
