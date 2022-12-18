package src.main.java.model.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import src.main.java.model.dominosC.Coordonnee;

public abstract class Plateau {

    public HashMap<Coordonnee,Tuile> plateau;
	public ArrayList<Integer> hauteur;
	public ArrayList<Integer> longueur;
    
    public Plateau() {
    	plateau = new HashMap<Coordonnee,Tuile>();
		hauteur = new ArrayList<Integer>();
		longueur = new ArrayList<Integer>();
    }
    
    // Méthodes getteurs
    public Tuile getTuile(int x, int y) throws CaseVideException{
    	Coordonnee c = new Coordonnee (x,y);
		for (Coordonnee i : plateau.keySet()) {
			if(i.equals(c)){
				return plateau.get(i);
			}
		}
		throw new CaseVideException();
    }
    
    
    // Méthodes setteurs
    public void setTuile(Tuile t,int x, int y) throws CasePleineException {
    	Coordonnee c = new Coordonnee (x,y);
		for (Coordonnee i : plateau.keySet()) {
			if(i.equals(c)){
				throw new CasePleineException();
			}
		}
		if(!hauteur.contains(y)){
			hauteur.add(y);
			Collections.sort(hauteur);
		}
		if(!longueur.contains(x)){
			longueur.add(x);
			Collections.sort(longueur);
		}
		plateau.put(c, t);
    }

	public boolean isOccupee(int x, int y){
		Coordonnee c = new Coordonnee(x, y);
		Tuile sub = plateau.get(c);
		if(sub != null){
			return true;
		}
		return false;
	}
   
	public int getHauteur(){
		return hauteur.size();
	}

	public int getLongueur(){
		return longueur.size();
	}

    public abstract void afficher();

}
