package src.main.java.model.general;

public abstract class Valeur {

    protected Object valeur;
    protected int points;

    public abstract Object getValeur();
    public abstract int getPoints();
    public boolean equals(Valeur v) {
        return valeur.equals(v);
    }    

}
