package src.main.java.model.CC;

import src.main.java.model.general.Tuile;

public class TuileCC extends Tuile {

    protected Centre centre;

    public TuileCC(CoteCC haut, CoteCC droite, CoteCC bas, CoteCC gauche) {
        super(haut, droite, bas, gauche);
    }

    @Override
    public void rotation(){
        CoteCC h = (CoteCC) gauche.inversePiece();
        CoteCC d = (CoteCC) haut;
        CoteCC b = (CoteCC) droite.inversePiece();
        CoteCC g = (CoteCC) bas;

        this.haut = h;
        this.droite = d;
        this.bas = b;
        this.gauche = g;
    }  

    public class Centre {

        // TODO : A voir leurs utilités après. Ils vont surement servir pour les points ou autre

        public class Abbaye extends Centre {}

        public class Carrefour extends Centre {}

    }
}
