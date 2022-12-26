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
    public Coordonnee coordonnee;

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public Tuile(Cote haut, Cote droite, Cote bas, Cote gauche){
        this.haut = haut;
        this.droite = droite;
        this.bas = bas;
        this.gauche = gauche;
        coordonnee = null;
    }

    // Méthodes getteurs
    public Joueur getTitulaire(){return titulaire;}
    public Coordonnee getCoordonnee(){return coordonnee;}
    public Cote getHaut(){return haut;}
    public Cote getDroite(){return droite;}
    public Cote getBas(){return bas;}
    public Cote getGauche(){return gauche;}


    // Méthodes setteurs
    public void setTitulaire(Joueur titulaire){this.titulaire = titulaire;}
    public void setCordonnee(Coordonnee coordonnee){this.coordonnee = coordonnee;}

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

    public static int comparer(Tuile t1,Tuile t2, char c1,char c2) {
    	Cote partie1 = null;
    	Cote partie2 = null;
    	
    	switch (c1) {
    		case 'h': partie1 = t1.haut;break;
    		case 'b': partie1 = t1.bas;break;
    		case 'g': partie1 = t1.gauche;break;
    		case 'd': partie1 = t1.droite;break;
    	}
    	
    	switch (c2) {
			case 'h' :partie2 = t2.haut;break;
			case 'b': partie2 = t2.bas;break;
			case 'g': partie2 = t2.gauche;break;
			case 'd': partie2 = t2.droite;break;
    	}
    	
    	int point = 0;
    	point = partie1.comparer(partie2);
    	
    	return point;	
    }
}
