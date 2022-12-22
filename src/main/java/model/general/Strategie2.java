package src.main.java.model.general;

import src.main.java.model.DC.ActionImpossibleException;

public class Strategie2 extends Strategie {

    @Override
    public boolean jouerTour(Tuile tuile) {
        for (Coordonnee c : jeu.getPlateau().possibilites) {
            for (int i = 0; i < 4; i++) {
                tuile.rotation();
                try {
                    jeu.getPlateau().poserTuile(tuile, c.getX(), c.getY());
                    return true;
                } catch (ActionImpossibleException | CasePleineException | TitulaireAbsentException e) {
                    // La tuile n'a pas été placé, on passe à la suite
                }
            }
        }
        return false;
    }
    
}
