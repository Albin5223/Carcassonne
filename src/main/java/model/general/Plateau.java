package src.main.java.model.general;

import java.util.HashMap;

import src.main.java.model.dominosC.Coordonnee;

public abstract class Plateau {

    HashMap<Coordonnee,Tuile> plateau;
    
    public Plateau() {
    	plateau = new HashMap<Coordonnee,Tuile>();
    }
    
    // Méthodes getteurs
    public Tuile getCase(int x, int y)throws CaseVideException{
    	Coordonnee c = new Coordonnee (x,y);
    	Tuile t = plateau.get(c);
    	if (t == null) {
    		throw new CaseVideException();
    	}
    	else {
    		return t;
    	}
    	
    }
    
    
    // Méthodes setteurs
    public void setCase(Tuile t,int x, int y)throws CasePleineException{
    	Coordonnee c = new Coordonnee (x,y);
    	Tuile sub = plateau.get(c);
    	if (sub != null) {
    		throw new CasePleineException();
    	}
    	else {
    		plateau.put(c,t);
    	}
    }
   

    public abstract void afficher();

}
