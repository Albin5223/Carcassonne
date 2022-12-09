package src.main.java.model.dominosC;

import java.util.Random;

/*
 * Cette classe va représenter un des domino qu'il y a sur les côtés d'une tuileDominosCarrées
 */
public class PieceDomino {
    
    // Attributs
    private int[] domino;
    // On met la valeur de taille à 3 puisque le jeu est défini comme cela
    private static final int taille = 3;
    
    public PieceDomino(int i, int j, int k){
        domino = new int[taille];
        domino[0] = i;
        domino[1] = j;
        domino[2] = k;
    }

    public boolean equals(PieceDomino p){
        return domino[0] == p.domino[0] && domino[1] == p.domino[1] && domino[2] == p.domino[2];
    }

    // Méthodes getteurs
    public int get(int i){return domino[i];}
    public static int length(){return taille;}
    
    
    public PieceDomino tirerHasard() {
    	Random r = new Random();
    	return new PieceDomino(r.nextInt(1, 4),r.nextInt(1, 4),r.nextInt(1, 4));
    }
}
