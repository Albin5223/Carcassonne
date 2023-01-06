package src.main.java.model.CC;

import src.main.java.model.general.Pion;

public abstract class Paysage {

    protected Pion pion;
    protected int ID;
    protected int points;

    public int points(){return points;}

    public boolean equals(Paysage p){
        return ID == p.ID;
    }

    public void poserPion(Pion p) throws IllegalPlacementPionException {
    	if(pion != null) {
    		throw new IllegalPlacementPionException();
    	}
    	pion = p;
    }

    public static class Route extends Paysage {
        public Route(){
            ID = 1;
            // TODO : Voir comment gérer les points
        }
    }

    public static class Pre extends Paysage {
        public Pre(){
            ID = 2;
            // TODO : Voir comment gérer les points
        }
    }

    public static class Ville extends Paysage {

        protected boolean bouclier;

        public Ville(boolean bouclier){
            ID = 4;
            this.bouclier = bouclier;
            // TODO : Voir comment gérer les points
        }
    }
    
}
