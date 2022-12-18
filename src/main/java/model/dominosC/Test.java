package src.main.java.model.dominosC;

import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.CaseVideException;

public class Test {
    public static void main(String[] args) throws CaseVideException, CasePleineException {

        PieceDC a = new PieceDC(0, 1, 2);
        PieceDC b = new PieceDC(3, 4, 5);
        PieceDC c = new PieceDC(0, 1, 2);
        PieceDC d = new PieceDC(3, 4, 5);

        TuileDC t = new TuileDC(a, b, c, d);
        
        PlateauDC plateau = new PlateauDC();
		plateau.setTuile(t, 0, 0);
        plateau.setTuile(t, 0, 1);
        plateau.afficher();
    }
}
