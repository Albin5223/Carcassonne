package src.main.java.model;

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

    @Override
    public void afficher() {
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
            System.out.println("\n");            
        }
    }
}
