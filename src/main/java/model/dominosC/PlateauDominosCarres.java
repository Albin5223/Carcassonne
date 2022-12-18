package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDominosCarres extends Plateau {

    private void printLines(int n){
        for (int i = 0; i < n; i++) {
            System.out.print("+");
            for (int j = 0; j < ((PieceDomino.length()+2)*2-1)+2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    @Override
    public void afficher() {
        // On print la ligne du haut vide
        printLines(getLongueur() + 2);
        System.out.print("\n\n\n\n\n");
        printLines(getLongueur()+2);

        for (int y = hauteur.get(0); y <= hauteur.get(getHauteur()-1); y++) {    // On va printer chaque ligne
            
            TuileDominosCarres.printHaut(null);     // On print d'abord un haut vide
            for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {     // On va printer chaque colonne
                try {
                    TuileDominosCarres t = (TuileDominosCarres) getTuile(x, y);
                    TuileDominosCarres.printHaut(t);
                } catch (CaseVideException e) {
                    TuileDominosCarres.printHaut(null);
                }
            }

            System.out.println();       // On passe aux intermediaires

            // On print les parties intermediaires
            for(int l = 0; l<PieceDomino.length();l++){
                TuileDominosCarres.printInterm(null,l);
                for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {
                    try {
                        TuileDominosCarres t = (TuileDominosCarres) getTuile(x, y);
                        TuileDominosCarres.printInterm(t, l);
                    } catch (Exception e) {
                        TuileDominosCarres.printInterm(null,l);
                    }
                }
                // On passe à la ligne
                System.out.println();
            }

            TuileDominosCarres.printBas(null);     // On print d'abord un haut vide
            for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {     // On va printer chaque colonne
                try {
                    TuileDominosCarres t = (TuileDominosCarres) getTuile(x, y);
                    TuileDominosCarres.printBas(t);
                } catch (CaseVideException e) {
                    TuileDominosCarres.printBas(null);
                }
            }
            System.out.println();
            printLines(getLongueur()+2);
        }

        // On print la ligne du bas vide
        System.out.print("\n\n\n\n\n");
        printLines(getLongueur()+2);
    }

    public boolean ligneVide(int l){
        for (int i = 0; i < getLongueur(); i++) {
            if(isOccupee(i, l)){
                return false;
            }
        }
        return true;
    }

    public boolean colonneVide(int c){
        for (int i = 0; i < getHauteur(); i++) {
            if(isOccupee(c, i)){
                return false;
            }
        }
        return true;
    }

    public int nColonneNonVide(){
        int n = 0;
        for (int i = 0; i < getLongueur(); i++) {
            if(!colonneVide(i)){
                n++;
            }
        }
        return n;
    }

    public boolean surPlateau(int x, int y){
        return x >= 0 && x < getLongueur() && y >= 0 && y < getHauteur();
    }
	
    /*
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

    // TODO : Ne marche pas vraiment
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
    */
}
