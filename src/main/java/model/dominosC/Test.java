package src.main.java.model.dominosC;

import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.CaseVideException;

public class Test {
    public static void main(String[] args) throws CaseVideException, CasePleineException {

        PieceDomino a = new PieceDomino(0, 1, 2);
        PieceDomino b = new PieceDomino(3, 4, 5);
        PieceDomino c = new PieceDomino(0, 1, 2);
        PieceDomino d = new PieceDomino(3, 4, 5);

        TuileDominosCarres t = new TuileDominosCarres(a, b, c, d);

        //J'ai mis en paramètre ce que tu as fait vu qu'on a mis dictionnaire mtn
        /*
        // On pose au moins une tuile sur le plateau pour pouvoir placer le reste
        p.getCase(27, 27).poserTuile(t);p.getCase(28, 27).poserTuile(t);
        p.getCase(24, 28).poserTuile(t);p.getCase(25, 28).poserTuile(t);p.getCase(26, 28).poserTuile(t);
        p.getCase(27, 29).poserTuile(t);p.getCase(28, 29).poserTuile(t);

        /* 
        // Et le reste se placera vu que c'est la même tuile à chaque fois, et que le haut = bas et droite = gauche
        for (int i = 0; i < p.getLongueur(); i++) {
            for (int j = 1; j < p.getHauteur(); j++) {
                p.poserTuile(t, i, j);
            }
        }
        //p.poserTuile(t, 1, 4);
        
        
        p.afficher();


        DominosCarres domino = new DominosCarres();
        //domino.lancerPartie();
        */
        
        PlateauDominosCarres plateau = new PlateauDominosCarres();
		plateau.setTuile(t, 0, 0);
        plateau.setTuile(t, 0, 1);
        plateau.afficher();
    }
}
