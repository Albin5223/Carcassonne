package src.main.java.model.CC;

import src.main.java.model.general.Tuile;

public class TuileCC extends Tuile {

    protected Centre centre;
    protected String name;

    public TuileCC(CoteCC haut, CoteCC droite, CoteCC bas, CoteCC gauche) {
        super(haut, droite, bas, gauche);
    }

    public CoteCC getHaut(){return (CoteCC) haut;}
    public CoteCC getDroite(){return (CoteCC) droite;}
    public CoteCC getBas(){return (CoteCC) bas;}
    public CoteCC getGauche(){return (CoteCC) gauche;}

    public void setCentre(Centre centre){
        this.centre = centre;
    }

    public void setName(String s){
        this.name = s;
    }
    
    public String getName() {
    	return name;
    }

    public static class Centre {

        public static class Abbaye extends Centre {}

        public static class Carrefour extends Centre {}

    }
}
