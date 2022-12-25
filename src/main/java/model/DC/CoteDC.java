package src.main.java.model.DC;

import src.main.java.model.general.Cote;

public class CoteDC extends Cote {

    public CoteDC(int i, int j, int k) {
        super(i, j, k);
    }

    public Integer get(int i){return (Integer) cote[i];}

    @Override
    public CoteDC inversePiece() {
        int t1 = get(2);
        int t2 = get(1);
        int t3 = get(0);
        return new CoteDC(t1, t2, t3);
    }    
}
