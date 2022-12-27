package src.main.java.model.DC;

import src.main.java.model.general.*;

/*
 * Classe qui représente les tuiles du jeu des dominos carrés
 * Informations à savoir :
 *  - Les dominos horizontaux de la tuile se définissent de gauche à droite
 *  - Les dominos verticaux de la tuile se définissent de haut en bas
 */
public class TuileDC extends Tuile {

    public TuileDC(CoteDC haut, CoteDC droite, CoteDC bas, CoteDC gauche){
        super(haut, droite, bas, gauche);
    }

    public CoteDC getHaut(){return (CoteDC) haut;}
    public CoteDC getDroite(){return (CoteDC) droite;}
    public CoteDC getBas(){return (CoteDC) bas;}
    public CoteDC getGauche(){return (CoteDC) gauche;}

    public void afficher() {
        System.out.println();
        // On affiche le haut
        System.out.print("# ");
        for(int i = 0;i < CoteDC.length();i++){
            System.out.print(haut.get(i) + " ");
        }
        System.out.println("#");

        // On affiche les côtés
        for (int i = 0; i < CoteDC.length(); i++) {
            System.out.print(gauche.get(i)+" ");
            for (int j = 0; j < CoteDC.length(); j++) {
                System.out.print("# ");
            }
            System.out.println(droite.get(i));
        }

        // On affiche le bas
        System.out.print("# ");
        for(int i = 0;i<CoteDC.length();i++){
            System.out.print(bas.get(i) + " ");
        }
        System.out.println("#");
        System.out.println();
    }

    public static void printHaut(TuileDC t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i < CoteDC.length();i++){
                System.out.print(t.haut.get(i)+" ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < CoteDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printInterm(TuileDC t, int l){
        if(t != null){
            System.out.print("  "+t.gauche.get(l)+" ");
            for(int i = 0;i < CoteDC.length();i++){
                System.out.print("# ");
            }
            System.out.print(t.droite.get(l) + " ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < CoteDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printBas(TuileDC t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i<CoteDC.length();i++){
                System.out.print(t.bas.get(i) + " ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i<CoteDC.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }
}
