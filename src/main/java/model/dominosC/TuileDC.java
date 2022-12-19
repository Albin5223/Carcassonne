package src.main.java.model.dominosC;

import src.main.java.model.general.*;

/*
 * Classe qui représente les tuiles du jeu des dominos carrés
 * Informations à savoir :
 *  - Les dominos horizontaux de la tuile se définissent de gauche à droite
 *  - Les dominos verticaux de la tuile se définissent de haut en bas
 */
public class TuileDC extends Tuile {

    // Attributs
    private PieceDC haut;     // Domino qui se situe en haut de la tuile
    private PieceDC droite;   // Domino qui se situe à droite de la tuile
    private PieceDC bas;      // Domino qui se situe en bas de la tuile
    private PieceDC gauche;   // Domino qui se situe à gauche de la tuile

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public TuileDC(PieceDC haut, PieceDC droite, PieceDC bas, PieceDC gauche){
        this.haut = haut;
        this.droite = droite;
        this.bas = bas;
        this.gauche = gauche;
    }

    public PieceDC getHaut(){return haut;}
    public PieceDC getDroite(){return droite;}
    public PieceDC getBas(){return bas;}
    public PieceDC getGauche(){return gauche;}

    @Override
    public void afficher() {
        System.out.println();
        // On affiche le haut
        System.out.print("# ");
        for(int i = 0;i < PieceDC.length();i++){
            System.out.print(haut.get(i) + " ");
        }
        System.out.println("#");

        // On affiche les côtés
        for (int i = 0; i < PieceDC.length(); i++) {
            System.out.print(gauche.get(i)+" ");
            for (int j = 0; j < PieceDC.length(); j++) {
                System.out.print("# ");
            }
            System.out.println(droite.get(i));
        }

        // On affiche le bas
        System.out.print("# ");
        for(int i = 0;i<PieceDC.length();i++){
            System.out.print(bas.get(i) + " ");
        }
        System.out.println("#");
        System.out.println();
    }

    @Override
    public void rotation(){
        PieceDC h = gauche.inversePiece();
        PieceDC d = haut;
        PieceDC b = droite.inversePiece();
        PieceDC g = bas;

        this.haut = h;
        this.droite = d;
        this.bas = b;
        this.gauche = g;
    }

    public static void printHaut(TuileDC t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i < PieceDC.length();i++){
                System.out.print(t.haut.get(i)+" ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < PieceDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printInterm(TuileDC t, int l){
        if(t != null){
            System.out.print("  "+t.gauche.get(l)+" ");
            for(int i = 0;i < PieceDC.length();i++){
                System.out.print("# ");
            }
            System.out.print(t.droite.get(l) + " ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < PieceDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printBas(TuileDC t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i<PieceDC.length();i++){
                System.out.print(t.bas.get(i) + " ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i<PieceDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }
    
    public static int comparer(Tuile t1,Tuile t2, char c1,char c2) {
    	PieceDC partie1 = null;
    	PieceDC partie2 = null;
    	
    	switch (c1) {
    		case 'h': partie1 = ((TuileDC) t1).haut;break;
    		case 'b': partie1 = ((TuileDC) t1).bas;break;
    		case 'g': partie1 = ((TuileDC) t1).gauche;break;
    		case 'd': partie1 = ((TuileDC) t1).droite;break;
    	}
    	
    	switch (c2) {
			case 'h' :partie2 = ((TuileDC) t2).haut;break;
			case 'b': partie2 = ((TuileDC) t2).bas;break;
			case 'g': partie2 = ((TuileDC) t2).gauche;break;
			case 'd': partie2 = ((TuileDC) t2).droite;break;
    	}
    	
    	int point = 0;
    	point = PieceDC.comparer(partie1,partie2);
    	
    	return point;
    		
    }
}
