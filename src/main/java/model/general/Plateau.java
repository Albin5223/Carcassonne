package src.main.java.model.general;

import java.util.LinkedList;

public abstract class Plateau {

	protected LinkedList<LinkedList<Case>> plateau;


    // Constructeur : avec taille limite
    public Plateau(){
        plateau = new LinkedList<LinkedList<Case>>();
    }


    // Méthodes getteurs
    public Case getCase(int x, int y){
    	if (plateau.get(x).get(y)==null) {
    		return plateau.get(x).get(y);
    	}
    	return null;
    }
    public int getLongueur(){
    	int taille = 0;
    	for (int i =0; i<plateau.size();i++) {
    		if (plateau.get(i).size()>taille) {
    			taille = plateau.get(i).size();
    		}
    	}
    	return taille;
    }
    
    public int getHauteur() {
    	return plateau.size();
    }
    
    
    public abstract void afficher();

}
