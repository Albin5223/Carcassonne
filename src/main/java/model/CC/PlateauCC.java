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
            if(!hautConforme(t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!basConforme(t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!droiteConforme(t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        try {
            if(!gaucheConforme(t, x, y)){
                throw new ActionImpossibleException();
            }
        } catch (CaseVideException e) {casesVide++;}

        if(casesVide != 4){
            setTuile(t, x, y);
        }
        
    }

    private boolean hautConforme(Tuile tuileCC, int x, int y) throws CaseVideException {
        Tuile haut = plateau.get(new Coordonnee(x, y+1));
        if(haut == null){
            throw new CaseVideException();
        }
        if(tuileCC.haut.equals(haut.bas)){
            return true;
        }
        return false;
    }

    private boolean basConforme(Tuile tuileCC, int x, int y) throws CaseVideException {
        Tuile bas = plateau.get(new Coordonnee(x, y-1));
        if(bas == null){
            throw new CaseVideException();
        }
        if(tuileCC.bas.equals(bas.haut)){
            return true;
        }
        return false;
    }

    private boolean droiteConforme(Tuile tuileCC, int x, int y) throws CaseVideException {
        Tuile droite = plateau.get(new Coordonnee(x+1, y));
        if(droite == null){
            throw new CaseVideException();
        }
        if(tuileCC.droite.equals(droite.gauche)){
            return true;
        }
        return false;
    }

    private boolean gaucheConforme(Tuile tuileCC, int x, int y) throws CaseVideException {
        Tuile gauche = plateau.get(new Coordonnee(x-1, y));
        if(gauche == null){
            throw new CaseVideException();
        }
        if(tuileCC.gauche.equals(gauche.droite)){
            return true;
        }
        return false;
    }
}
