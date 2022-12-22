package src.main.java.model.DC;

import src.main.java.model.general.Cote;

public class CoteDC extends Cote {

    public CoteDC(ValeurDC i, ValeurDC j, ValeurDC k) {
        super(i, j, k);
    }

    public CoteDC(int i, int j, int k) {
        super(new ValeurDC(i), new ValeurDC(j), new ValeurDC(k));
    }

    public ValeurDC getObject(int i){return (ValeurDC) cote[i];}
    public Integer get(int i){return (Integer) cote[i].getValeur();}

    @Override
    public Cote inversePiece() {
        ValeurDC t1 = new ValeurDC(get(2));
        ValeurDC t2 = new ValeurDC(get(1));
        ValeurDC t3 = new ValeurDC(get(0));
        return new CoteDC(t1, t2, t3);
    }    

    public static int comparer(CoteDC p1,CoteDC p2) {
    	int point = 0;
    	for (int i = 0;i<3;i++) {
    		if (p1.get(i) != p2.get(i)) {
    			return 0;
    		}
    		else {
    			point += p1.getObject(i).getPoints();
    		}
    	}
    	return point;
    }
}
