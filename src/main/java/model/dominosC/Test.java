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
        
        PieceDC a1 = new PieceDC(0, 1, 2);
        PieceDC b1 = new PieceDC(3, 3, 3);
        PieceDC c1 = new PieceDC(0, 1, 2);
        PieceDC d1 = new PieceDC(6, 4, 5);

        TuileDC t1 = new TuileDC(a1, b1, c1, d1);
        
        PieceDC a2 = new PieceDC(0, 1, 2);
        PieceDC b2 = new PieceDC(3, 3, 3);
        PieceDC c2 = new PieceDC(0, 1, 2);
        PieceDC d2 = new PieceDC(3, 4, 5);

        TuileDC t2 = new TuileDC(a2, b2, c2, d2);
        
        PieceDC a3 = new PieceDC(0, 1, 2);
        PieceDC b3 = new PieceDC(3, 4, 3);
        PieceDC c3 = new PieceDC(0, 1, 2);
        PieceDC d3 = new PieceDC(3, 3, 3);

        TuileDC t3 = new TuileDC(a3, b3, c3, d3);
        
        PlateauDC plateau = new PlateauDC();
		plateau.setTuile(t, 0, 0);
        try {
			plateau.poserTuile(t1, 0, 1);
			plateau.poserTuile(t2, 1, 0);
			plateau.poserTuile(t3, 1, 1);
		} catch (Exception e) {
			System.out.println(e);
		}
			
        plateau.afficher();
    }
}
