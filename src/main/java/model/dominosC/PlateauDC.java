package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDC extends Plateau {

    private void printLines(int n){
        for (int i = 0; i < n; i++) {
            System.out.print("+");
            for (int j = 0; j < ((PieceDC.length()+2)*2-1)+2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    @Override
    public void afficher() {
        // On print la ligne du haut vide
        printLines(getLongueur() + 2);
        System.out.print("\n\n\n\n\n");
        printLines(getLongueur()+2);

        for (int y = hauteur.get(0); y <= hauteur.get(getHauteur()-1); y++) {    // On va printer chaque ligne
            
            TuileDC.printHaut(null);     // On print d'abord un haut vide
            for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {     // On va printer chaque colonne
                try {
                    TuileDC t = (TuileDC) getTuile(x, y);
                    TuileDC.printHaut(t);
                } catch (CaseVideException e) {
                    TuileDC.printHaut(null);
                }
            }

            System.out.println();       // On passe aux intermediaires

            // On print les parties intermediaires
            for(int l = 0; l<PieceDC.length();l++){
                TuileDC.printInterm(null,l);
                for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {
                    try {
                        TuileDC t = (TuileDC) getTuile(x, y);
                        TuileDC.printInterm(t, l);
                    } catch (Exception e) {
                        TuileDC.printInterm(null,l);
                    }
                }
                // On passe à la ligne
                System.out.println();
            }

            TuileDC.printBas(null);     // On print d'abord un haut vide
            for (int x = longueur.get(0); x <= longueur.get(getLongueur()-1); x++) {     // On va printer chaque colonne
                try {
                    TuileDC t = (TuileDC) getTuile(x, y);
                    TuileDC.printBas(t);
                } catch (CaseVideException e) {
                    TuileDC.printBas(null);
                }
            }
            System.out.println();
            printLines(getLongueur()+2);
        }

        // On print la ligne du bas vide
        System.out.print("\n\n\n\n\n");
        printLines(getLongueur()+2);
    }
    
    public int poserTuile(Tuile t, int x,int y) throws ActionImpossibleException, CasePleineException{
    	int point = 0;
    	Tuile gauche = null;
    	Tuile droit = null;
    	Tuile haut = null;
    	Tuile bas = null;
    	try {
			 gauche = getTuile(x-1,y);
		} catch (CaseVideException e) {
			gauche = null;
		}
    	try {
			droit = getTuile(x+1,y);
		} catch (CaseVideException e) {
			droit = null;
		}
    	try {
			 haut = getTuile(x,y-1);
		} catch (CaseVideException e) {
			haut = null;
		}
    	try {
			 bas = getTuile(x,y+1);
		} catch (CaseVideException e) {
			bas = null;
		}
    	
    	if (gauche != null && gaucheConforme(t,gauche)==0) {
    		throw new ActionImpossibleException();
    	}
    	else {
    		point+=gaucheConforme(t,gauche);
    	}
    	
    	if (droit != null && droitConforme(t,droit)==0) {
    		throw new ActionImpossibleException();
    	}
    	else {
    		point+=droitConforme(t,droit);
    	}
    	
    	if (haut != null && hautConforme(t,haut)==0) {
    		throw new ActionImpossibleException();
    	}
    	else {
    		point+=hautConforme(t,haut);
    	}
    	
    	if (bas != null && basConforme(t,bas)==0) {
    		throw new ActionImpossibleException();
    	}
    	else {
    		point+=basConforme(t,bas);
    	}
    	
    	if(point == 0) {
    		throw new ActionImpossibleException();
    	}
    	else {
    		setTuile(t,x,y);
    	}
    	
    	return point;
    }
    
    public int hautConforme(Tuile t,Tuile haut) {
    	if (haut == null) {
    		return 0;
    	}
    	else {
    		System.out.println("On compare le haut avec le bas");
    		return TuileDC.comparer(t, haut, 'h', 'b');
    	}
    }
    
    public int basConforme(Tuile t,Tuile bas) {
    	if (bas == null) {
    		return 0;
    	}
    	else {
    		System.out.println("On compare le bas avec le haut");
    		return TuileDC.comparer(t, bas, 'b', 'h');
    	}
    }
    
    public int gaucheConforme(Tuile t,Tuile gauche) {
    	if (gauche == null) {
    		return 0;
    	}
    	else {
    		System.out.println("On compare la gauche avec la droite");
    		return TuileDC.comparer(t, gauche, 'g', 'd');
    	}
    }
    
    public int droitConforme(Tuile t,Tuile droit) {
    	if (droit == null) {
    		return 0;
    	}
    	else {
    		System.out.println("On compare la droite avec la gauche");
    		return TuileDC.comparer(t, droit, 'd', 'g');
    	}
    }
}
