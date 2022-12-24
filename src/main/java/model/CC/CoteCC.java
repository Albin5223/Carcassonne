package src.main.java.model.CC;

import src.main.java.model.general.Cote;

public class CoteCC extends Cote {

    public CoteCC(ValeurCC i, ValeurCC j, ValeurCC k) {
        super(i, j, k);
    }

    public CoteCC(Paysage i, Paysage j, Paysage k) {
        super(new ValeurCC(i), new ValeurCC(j), new ValeurCC(k));
    }

    public CoteCC(Paysage i) {
        super(new ValeurCC(i), new ValeurCC(i), new ValeurCC(i));
    }

    public ValeurCC getObject(int i){return (ValeurCC) cote[i];}
    public Paysage get(int i){return (Paysage) cote[i].getValeur();}

    @Override
    public CoteCC inversePiece() {
        ValeurCC t1 = new ValeurCC(get(2));
        ValeurCC t2 = new ValeurCC(get(1));
        ValeurCC t3 = new ValeurCC(get(0));
        return new CoteCC(t1, t2, t3);
    }    
}
