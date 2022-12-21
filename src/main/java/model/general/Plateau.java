package src.main.java.model.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import src.main.java.model.DC.ActionImpossibleException;

public abstract class Plateau {

    protected HashMap<Coordonnee,Tuile> plateau;
	protected ArrayList<Coordonnee> possibilites;
	protected ArrayList<Integer> hauteur;
	protected ArrayList<Integer> longueur;
    
    public Plateau() {
    	plateau = new HashMap<Coordonnee,Tuile>();
		possibilites = new ArrayList<Coordonnee>(); 
		hauteur = new ArrayList<Integer>();
		longueur = new ArrayList<Integer>();
    }

	public ArrayList<Coordonnee> getPossibilites(){
		return possibilites;
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
		possibilitesMAJ(c);
    }

	protected void possibilitesMAJ(Coordonnee c){
		possibilites.remove(c);
		Coordonnee bas = new Coordonnee(c.getX(), c.getY()+1);
		Coordonnee haut = new Coordonnee(c.getX(), c.getY()-1);
		Coordonnee droite = new Coordonnee(c.getX()+1, c.getY());
		Coordonnee gauche = new Coordonnee(c.getX()-1, c.getY());
		if(!isOccupee(bas)){
			possibilites.add(bas);
		}
		if(!isOccupee(haut)){
			possibilites.add(haut);
		}
		if(!isOccupee(droite)){
			possibilites.add(droite);
		}
		if(!isOccupee(gauche)){
			possibilites.add(gauche);
		}
	}

	public boolean isOccupee(int x, int y){
		Coordonnee c = new Coordonnee(x, y);
		Tuile sub = plateau.get(c);
		if(sub != null){
			return true;
		}
		return false;
	}

	public boolean isOccupee(Coordonnee c){
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

	public abstract void poserTuile(Tuile t, int x,int y) throws ActionImpossibleException, CasePleineException;

}