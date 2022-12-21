package src.main.java.model.general;

import java.util.ArrayList;

/*
 * Cette classe va représenter un des cote qu'il y a sur les côtés d'une tuileDominosCarrées
 */
public abstract class Cote<T> {
    
    // Attributs
    protected ArrayList<T> cote;
    // On met la valeur de taille à 3 puisque le jeu est défini comme cela
    protected static final int taille = 3;
    
    public Cote(T i, T j, T k){
        cote = new ArrayList<T>();
        cote.add(i);
        cote.add(j);
        cote.add(k);
    }

    public boolean equals(Cote<T> p){
        return cote.get(0).equals(p.cote.get(0)) && cote.get(1).equals(p.cote.get(1)) && cote.get(2).equals(p.cote.get(2));
    }

    // Méthodes getteurs
    public abstract T get(int i);
    public static int length(){return taille;}

    public abstract Cote<T> inversePiece();
}
