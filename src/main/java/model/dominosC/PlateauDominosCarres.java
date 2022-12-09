package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDominosCarres extends Plateau {

    public PlateauDominosCarres(int longueur, int hauteur){
        super(longueur, hauteur);
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
        for (int i = 0; i < hauteur; i++) {
            if(!ligneVide(i)){
                // On print le haut de chaque ligne
                for (int j = 0; j < longueur; j++) {
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
                    for (int j = 0; j < longueur; j++) {
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
                for (int j = 0; j < longueur; j++) {
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
    }

    public boolean ligneVide(int l){
        for (int i = 0; i < longueur; i++) {
            if(cases[i][l].isOccupee()){
                return false;
            }
        }
        return true;
    }

    public boolean surPlateau(int x, int y){
        return x >= 0 && x < longueur && y >= 0 && y < hauteur;
    }

    private boolean hautConforme(TuileDominosCarres t, int x, int y){
        if(surPlateau(x, y+1) && getCase(x, y+1).isOccupee() && 
        ((TuileDominosCarres) getCase(x, y+1).getTuile()).getBas().equals(((TuileDominosCarres) t).getHaut())){
            return true;
        }
        return false;
    }

    private boolean droiteConforme(TuileDominosCarres t, int x, int y){
        if(surPlateau(x+1, y) && getCase(x+1, y).isOccupee() && 
        ((TuileDominosCarres) getCase(x+1, y).getTuile()).getGauche().equals(((TuileDominosCarres) t).getDroite())){
            return true;
        }
        return false;
    }

    private boolean gaucheConforme(TuileDominosCarres t, int x, int y){
        if(surPlateau(x-1, y) && getCase(x-1, y).isOccupee() && 
        ((TuileDominosCarres) getCase(x-1, y).getTuile()).getDroite().equals(((TuileDominosCarres) t).getGauche())){
            return true;
        }
        return false;
    }

    private boolean basConforme(TuileDominosCarres t, int x, int y){
        if(surPlateau(x, y-1) && getCase(x, y-1).isOccupee() && 
        ((TuileDominosCarres) getCase(x, y-1).getTuile()).getHaut().equals(((TuileDominosCarres) t).getBas())){
            return true;
        }
        return false;
    }

    public boolean peutPoser(TuileDominosCarres t,int x, int y){
        if(surPlateau(x, y)){
            if(!cases[x][y].isOccupee()){
                if(hautConforme(t, x, y) || droiteConforme(t, x, y) || basConforme(t, x, y) || gaucheConforme(t, x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean poserTuile(TuileDominosCarres tuile, int x, int y) {
        if(peutPoser(tuile, x, y)){
            cases[x][y].poserTuile(tuile);
            return true;
        }
        return false;
    }
}
