package src.main.java.model.dominosC;

import src.main.java.model.general.*;

/*
 * Classe qui représente les tuiles du jeu des dominos carrés
 * Informations à savoir :
 *  - Les dominos horizontaux de la tuile se définissent de gauche à droite
 *  - Les dominos verticaux de la tuile se définissent de haut en bas
 */
public class TuileDominosCarres extends Tuile {

    // Attributs
    private PieceDomino haut;     // Domino qui se situe en haut de la tuile
    private PieceDomino droite;   // Domino qui se situe à droite de la tuile
    private PieceDomino bas;      // Domino qui se situe en bas de la tuile
    private PieceDomino gauche;   // Domino qui se situe à gauche de la tuile

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public TuileDominosCarres(PieceDomino haut, PieceDomino droite, PieceDomino bas, PieceDomino gauche){
        this.haut = haut;
        this.droite = droite;
        this.bas = bas;
        this.gauche = gauche;
    }

    public PieceDomino getHaut(){return haut;}
    public PieceDomino getDroite(){return droite;}
    public PieceDomino getBas(){return bas;}
    public PieceDomino getGauche(){return gauche;}

    @Override
    public void afficher() {
        // On affiche le haut
        System.out.print("# ");
        for(int i = 0;i < PieceDomino.length();i++){
            System.out.print(haut.get(i) + " ");
        }
        System.out.println("#");

        // On affiche les côtés
        for (int i = 0; i < PieceDomino.length(); i++) {
            System.out.print(gauche.get(i)+" ");
            for (int j = 0; j < PieceDomino.length(); j++) {
                System.out.print("# ");
            }
            System.out.println(droite.get(i));
        }

        // On affiche le bas
        System.out.print("# ");
        for(int i = 0;i<PieceDomino.length();i++){
            System.out.print(bas.get(i) + " ");
        }
        System.out.println("#");
    }

    @Override
    public void rotation(){
        PieceDomino tmp = haut;
        haut = gauche;
        gauche = bas;
        bas = droite;
        droite = tmp;
    }

    public static void printHaut(TuileDominosCarres t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i < PieceDomino.length();i++){
                System.out.print(t.haut.get(i)+" ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < PieceDomino.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printInterm(TuileDominosCarres t, int l){
        if(t != null){
            System.out.print("  "+t.gauche.get(l)+" ");
            for(int i = 0;i < PieceDomino.length();i++){
                System.out.print("# ");
            }
            System.out.print(t.droite.get(l) + " ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i < PieceDomino.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

    public static void printBas(TuileDominosCarres t){
        if(t != null){
            System.out.print("  # ");
            for(int i = 0;i<PieceDomino.length();i++){
                System.out.print(t.bas.get(i) + " ");
            }
            System.out.print("# ");
        }
        else{
            System.out.print("    ");
            for(int i = 0;i<PieceDomino.length();i++){
                System.out.print("  ");
            }
            System.out.print("  ");
        }
    }

}
