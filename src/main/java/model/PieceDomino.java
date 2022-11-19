package src.main.java.model;

/*
 * Cette classe va représenter un des domino qu'il y a sur les côtés d'une tuileDominosCarrées
 */
public class PieceDomino {
    
    // Attributs
    private int[] domino;
    // On met la valeur de taille à 3 puisque le jeu est défini comme cela
    private static final int taille = 3;
    
    public PieceDomino(int i, int j, int k){
        domino = new int[3];
        domino[0] = i;
        domino[1] = j;
        domino[2] = k;
    }

    // Méthodes getteurs
    public int get(int i){return domino[i];}
    public static int length(){return taille;}
}
