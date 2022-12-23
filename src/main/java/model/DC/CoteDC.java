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
    public CoteDC inversePiece() {
        ValeurDC t1 = new ValeurDC(get(2));
        ValeurDC t2 = new ValeurDC(get(1));
        ValeurDC t3 = new ValeurDC(get(0));
        return new CoteDC(t1, t2, t3);
    }    
}
