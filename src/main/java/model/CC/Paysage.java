package src.main.java.model.CC;

public abstract class Paysage {

    protected int ID;
    protected int points;

    public int points(){return points;}

    public boolean equals(Paysage p){
        return ID == p.ID;
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

    public static class Mur extends Paysage {
        public Mur(){
            ID = 3;
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
