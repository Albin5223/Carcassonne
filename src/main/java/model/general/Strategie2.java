package src.main.java.model.general;

import src.main.java.model.dominosC.ActionImpossibleException;
import src.main.java.model.dominosC.Coordonnee;

public class Strategie2 extends Strategie {

    @Override
    public boolean jouerTour(Tuile tuile) {
        for (Coordonnee c : jeu.getPlateau().possibilites) {
            for (int i = 0; i < 4; i++) {
                tuile.rotation();
                try {
                    jeu.getPlateau().poserTuile(tuile, c.getX(), c.getY());
                    return true;
                } catch (ActionImpossibleException | CasePleineException e) {
                    // La tuile n'a pas été placé, on passe à la suite
                }
            }
        }
        return false;
    }
    
}
