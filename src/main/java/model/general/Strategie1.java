package src.main.java.model.general;

import src.main.java.model.DC.ActionImpossibleException;

public class Strategie1 extends Strategie {

    @Override
    public boolean jouerTour(Tuile tuile) {
        for (Coordonnee c : jeu.getPlateau().possibilites) {
            try {
                jeu.getPlateau().poserTuile(tuile, c.getX(), c.getY());
                return true;
            } catch (ActionImpossibleException | CasePleineException e) {
                // La tuile n'a pas été placé, on passe à la suite
            }
        }
        return false;
    }
}
