package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDominosCarres extends Plateau {

    public PlateauDominosCarres(int longueur, int largeur){
        super(longueur, largeur);
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
            for (int j = 0; j < cases.length; j++) {
                if(cases[j][i] != null){
                    TuileDominosCarres.printHaut(((TuileDominosCarres) cases[j][i].getTuile()));
                }
                else{
                    TuileDominosCarres.printHaut(null);
                }
            }
            // On passe à la ligne
            System.out.println();

            // On print les parties intermediaires
            for(int l = 0; l<PieceDomino.length();l++){
                for (int j = 0; j < cases.length; j++) {
                    if(cases[j][i] != null){
                        TuileDominosCarres.printInterm(((TuileDominosCarres) cases[j][i].getTuile()),l);
                    }
                    else{
                        TuileDominosCarres.printInterm(null,l);
                    }
                }
                // On passe à la ligne
                System.out.println();
            }

            // On print le bas de chaque ligne
            for (int j = 0; j < cases.length; j++) {
                if(cases[j][i] != null){
                    TuileDominosCarres.printBas(((TuileDominosCarres) cases[j][i].getTuile()));
                }
                else{
                    TuileDominosCarres.printBas(null);
                }
            }
            System.out.println();
            printLines();            
        }
    }

    public boolean surPlateau(int x, int y){
        return x >= 0 && x < longueur && y >= 0 && y < largeur;
    }

    private boolean hautConforme(Tuile t, int x, int y){
        return false;
    }

    public boolean peutPoser(Tuile t,int x, int y){
        if(surPlateau(x, y)){
            if(!cases[x][y].isOccupee()){

            }
        }
        return false;
    }

    @Override
    public boolean poserTuile(Tuile tuile, int x, int y) {
        if(peutPoser(tuile, x, y)){

            return true;
        }
        return false;
    }
}
