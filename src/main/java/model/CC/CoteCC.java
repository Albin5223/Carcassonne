package src.main.java.model.CC;

import src.main.java.model.general.Cote;

public class CoteCC extends Cote {

    public CoteCC(Paysage i, Paysage j, Paysage k) {
        super(i, j, k);
    }

    public CoteCC(Paysage i) {
        super(i, i, i);
    }

    public Paysage get(int i){return (Paysage) cote[i];}

    @Override
    public CoteCC inversePiece() {
        Paysage t1 = get(2);
        Paysage t2 = get(1);
        Paysage t3 = get(0);
        return new CoteCC(t1, t2, t3);
    }    
}
