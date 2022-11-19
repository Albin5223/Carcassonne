package src.main.java.model;

import java.util.InputMismatchException;

public class TuileDominosCarres extends Tuile {

    // Attributs
    private int taille;
    private int[] haut;
    private int[] droite;
    private int[] bas;
    private int[] gauche;

    // Constructeur : pour le moment, on force les tuiles à avoir des cotés avec 3 valeurs, on pourra potentiellement augmenter la taille après
    public TuileDominosCarres(int taille, int[] haut, int[] droite, int[] bas, int[] gauche) throws InputMismatchException {
        if(haut.length != taille || droite.length != taille || bas.length != taille || gauche.length != taille){
            throw new InputMismatchException("Les dimensions de la tuile ne sont pas correctes");
        }
        else{
            this.taille = taille;
            this.haut = new int[taille]; this.droite = new int[taille]; this.bas = new int[taille]; this.gauche = new int[taille];
            for(int i = 0; i<taille;i++){
                this.haut[i] = haut[i];
                this.droite[i] = droite[i];
                this.bas[i] = bas[i];
                this.gauche[i] = gauche[i];
            }
        }
    }

    @Override
    public void afficheTuile() {
        // On affiche le haut
        System.out.print("# ");
        for(int i = 0;i<taille;i++){
            System.out.print(haut[i] + " ");
        }
        System.out.println("#");

        // On affiche les côtés
        for (int i = 0; i < taille; i++) {
            System.out.print(gauche[i]+" ");
            for (int j = 0; j < taille; j++) {
                System.out.print("# ");
            }
            System.out.println(droite[i]);
        }

        // On affiche le bas
        System.out.print("# ");
        for(int i = 0;i<taille;i++){
            System.out.print(bas[i] + " ");
        }
        System.out.println("#");
    }

    

}
