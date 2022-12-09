package src.main.java.model.dominosC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import src.main.java.model.general.*;

public class PlateauDominosCarres extends Plateau {

    public PlateauDominosCarres(ArrayList<TuileDominosCarres> sac){
    	super();
    	Random r = new Random();
    	int n = r.nextInt(0,sac.size());
    	Tuile t = sac.get(n);
    	Case c = new Case(t);
    	LinkedList<Case> l = new LinkedList<Case>();
    	l.add(c);
    	l.addFirst(null);
    	l.addLast(null);
    	plateau.add(l);
    	sac.remove(n);
    	
    }

    private void printLines(){
        for (int i = 0; i < getLongueur(); i++) {
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
        for (int i = 0; i < getHauteur(); i++) {

            // On print le haut de chaque ligne
            for (int j = 0; j < getLongueur(); j++) {
                if(plateau.get(j).get(i) != null){
                    TuileDominosCarres.printHaut(((TuileDominosCarres) plateau.get(j).get(i).getTuile()));
                }
                else{
                    TuileDominosCarres.printHaut(null);
                }
            }
            // On passe à la ligne
            System.out.println();

            // On print les parties intermediaires
            for(int l = 0; l<PieceDomino.length();l++){
                for (int j = 0; j < getLongueur(); j++) {
                    if(plateau.get(j).get(i) != null){
                        TuileDominosCarres.printInterm(((TuileDominosCarres) plateau.get(j).get(i) .getTuile()),l);
                    }
                    else{
                        TuileDominosCarres.printInterm(null,l);
                    }
                }
                // On passe à la ligne
                System.out.println();
            }

            // On print le bas de chaque ligne
            for (int j = 0; j < getLongueur(); j++) {
                if(plateau.get(j).get(i) != null){
                    TuileDominosCarres.printBas(((TuileDominosCarres) plateau.get(j).get(i).getTuile()));
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
        return x >= 0 && x < getLongueur() && y >= 0 && y < getHauteur();
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
            if(!plateau.get(x).get(y).isOccupee()){
                if(hautConforme(t, x, y) || droiteConforme(t, x, y) || basConforme(t, x, y) || gaucheConforme(t, x, y)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean poserTuile(TuileDominosCarres tuile, int x, int y) {
        if(peutPoser(tuile, x, y)){
        	plateau.get(x).get(y).poserTuile(tuile);
            return true;
        }
        return false;
    }
}
