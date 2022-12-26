package src.main.java.model.CC;

import src.main.java.model.DC.ActionImpossibleException;
import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.CaseVideException;
import src.main.java.model.general.Coordonnee;
import src.main.java.model.general.Plateau;
import src.main.java.model.general.Tuile;

public class PlateauCC extends Plateau {

    @Override
    public void poserTuile(Tuile t, int x, int y) throws ActionImpossibleException, CasePleineException {
        int casesVide = 0;
        try {
            if(!hautConforme((TuileCC) t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!basConforme((TuileCC) t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!droiteConforme((TuileCC) t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!gaucheConforme((TuileCC) t, x, y)){
            	
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        System.out.println(casesVide);
        if(casesVide != 4){
            setTuile(t, x, y);
            System.out.println("Pose");
        }
        else {
        	throw new ActionImpossibleException();
        }
    }

    private boolean hautConforme(TuileCC tuileCC, int x, int y) throws CaseVideException {
        TuileCC haut = (TuileCC)getTuile(x, y-1);
        if(haut == null){
        	System.out.println("Haut vide");
            throw new CaseVideException();
        }
        if(tuileCC.haut.comparer(haut.bas)!=0){
            return true;
        }
        return false;
    }

    private boolean basConforme(TuileCC t, int x, int y) throws CaseVideException {
        TuileCC bas = (TuileCC) getTuile(x, y+1);
        if(bas == null){
        	System.out.println("Bas vide");
            throw new CaseVideException();
        }
        if(t.bas.comparer(bas.haut)!=0){
            return true;
        }
        return false;
    }

    private boolean droiteConforme(TuileCC tuileCC, int x, int y) throws CaseVideException {
        TuileCC droite = (TuileCC) getTuile(x+1, y);
        if(droite == null){
        	System.out.println("droite vide");
            throw new CaseVideException();
        }
        if(tuileCC.droite.comparer(droite.gauche)!=0){
            return true;
        }
        return false;
    }

    private boolean gaucheConforme(TuileCC tuileCC, int x, int y) throws CaseVideException {
        TuileCC gauche = (TuileCC) getTuile(x-1, y);
        if(gauche == null){
        	System.out.println("Gauche vide");
            throw new CaseVideException();
        }
        if(tuileCC.gauche.comparer(gauche.droite)!=0){
            return true;
        }
        return false;
    }
}
