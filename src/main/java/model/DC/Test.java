package src.main.java.model.DC;

import src.main.java.model.general.CasePleineException;
import src.main.java.model.general.CaseVideException;

public class Test {
    public static void main(String[] args) throws CaseVideException, CasePleineException {


        CoteDC a = new CoteDC(0, 1, 2);
        CoteDC b = new CoteDC(3, 4, 5);
        CoteDC c = new CoteDC(0, 1, 2);
        CoteDC d = new CoteDC(3, 4, 5);

        TuileDC t = new TuileDC(a, b, c, d);
        
        CoteDC a1 = new CoteDC(0, 1, 2);
        CoteDC b1 = new CoteDC(3, 3, 3);
        CoteDC c1 = new CoteDC(0, 1, 2);
        CoteDC d1 = new CoteDC(6, 4, 5);

        TuileDC t1 = new TuileDC(a1, b1, c1, d1);
        
        CoteDC a2 = new CoteDC(0, 1, 2);
        CoteDC b2 = new CoteDC(3, 3, 3);
        CoteDC c2 = new CoteDC(0, 1, 2);
        CoteDC d2 = new CoteDC(3, 4, 5);

        TuileDC t2 = new TuileDC(a2, b2, c2, d2);
        
        CoteDC a3 = new CoteDC(0, 1, 2);
        CoteDC b3 = new CoteDC(3, 4, 3);
        CoteDC c3 = new CoteDC(0, 1, 2);
        CoteDC d3 = new CoteDC(3, 3, 3);

        TuileDC t3 = new TuileDC(a3, b3, c3, d3);
        
        PlateauDC plateau = new PlateauDC();
		plateau.setTuile(t, 0, 0);
        try {
			plateau.poserTuile(t1, 0, 1);
			plateau.poserTuile(t2, 1, 0);
			plateau.poserTuile(t3, 1, 1);
		} catch (Exception e) {
		}
			
        plateau.afficher();
    }
}
