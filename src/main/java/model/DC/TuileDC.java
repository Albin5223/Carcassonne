package src.main.java.model.DC;

import src.main.java.model.general.*;

/*
 * Classe qui représente les tuiles du jeu des dominos carrés
 * Informations à savoir :
 *  - Les dominos horizontaux de la tuile se définissent de gauche à droite
 *  - Les dominos verticaux de la tuile se définissent de haut en bas
 */
public class TuileDC extends Tuile<Integer> {

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public TuileDC(PieceDC haut, PieceDC droite, PieceDC bas, PieceDC gauche){
        super(haut, droite, bas, gauche);
    }

    public PieceDC getHaut(){return (PieceDC) haut;}
    public PieceDC getDroite(){return (PieceDC) droite;}
    public PieceDC getBas(){return (PieceDC) bas;}
    public PieceDC getGauche(){return (PieceDC) gauche;}

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
        PieceDC h = (PieceDC) gauche.inversePiece();
        PieceDC d = (PieceDC) haut;
        PieceDC b = (PieceDC) droite.inversePiece();
        PieceDC g = (PieceDC) bas;

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
    		case 'h': partie1 = (PieceDC) t1.haut;break;
    		case 'b': partie1 = (PieceDC) t1.bas;break;
    		case 'g': partie1 = (PieceDC) t1.gauche;break;
    		case 'd': partie1 = (PieceDC) t1.droite;break;
    	}
    	
    	switch (c2) {
			case 'h' :partie2 = (PieceDC) t2.haut;break;
			case 'b': partie2 = (PieceDC) t2.bas;break;
			case 'g': partie2 = (PieceDC) t2.gauche;break;
			case 'd': partie2 = (PieceDC) t2.droite;break;
    	}
    	
    	int point = 0;
    	point = PieceDC.comparer(partie1,partie2);
    	
    	return point;
    		
    }
}
