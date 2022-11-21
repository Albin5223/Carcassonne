package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDominosCarres extends Plateau {

    public PlateauDominosCarres(int hauteur, int longueur){
        super(hauteur, longueur);
    }

    public PlateauDominosCarres(int i){
        super(i);
    }

    public PlateauDominosCarres(){
        super();
    }

    private void printLines(){
        for (int i = 0; i < longueur; i++) {
            System.out.print("+");
            for (int j = 0; j < ((PieceDomino.length()+2)*2-1)+2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    @Override
    public void afficher() {

        printLines();

        // On parcours chaque ligne du plateau
        for (int i = 0; i < cases.length; i++) {

            // On print le haut de chaque ligne
            for (int j = 0; j < cases[i].length; j++) {
                if(cases[i][j] != null){
                    TuileDominosCarres.printHaut(((TuileDominosCarres) cases[i][j].getTuile()));
                }
                else{
                    TuileDominosCarres.printHaut(null);
                }
            }
            // On passe à la ligne
            System.out.println();

            // On print les parties intermediaires
            for(int l = 0; l<PieceDomino.length();l++){
                for (int j = 0; j < cases[i].length; j++) {
                    if(cases[i][j] != null){
                        TuileDominosCarres.printInterm(((TuileDominosCarres) cases[i][j].getTuile()),l);
                    }
                    else{
                        TuileDominosCarres.printInterm(null,l);
                    }
                }
                // On passe à la ligne
                System.out.println();
            }

            // On print le bas de chaque ligne
            for (int j = 0; j < cases[i].length; j++) {
                if(cases[i][j] != null){
                    TuileDominosCarres.printBas(((TuileDominosCarres) cases[i][j].getTuile()));
                }
                else{
                    TuileDominosCarres.printBas(null);
                }
            }
            System.out.println();
            printLines();            
        }
    }
}
