package src.main.java.model.dominosC;

import src.main.java.model.general.*;

public class PlateauDC extends Plateau {

    private void printLines(int n){
		System.out.print("      ");
        for (int i = 0; i < n; i++) {
            System.out.print("+");
            for (int j = 0; j < ((PieceDC.length()+2)*2-1)+2; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

	private void printX(){
		System.out.print("      ");
		int minX = longueur.get(0)-1;
		int maxX = longueur.get(getLongueur()-1)+1;

		int index = minX;

        for (int i = 0; i <= maxX-minX; i++) {
            System.out.print(" ");
			int l = ((PieceDC.length()+2)*2-1)+2;
            for (int j = 0; j < l; j++) {
				if(j == l/2-1){
					System.out.print(index);
					index++;
				}
				else{
					System.out.print(" ");
				}
            }
        }
        System.out.println();
	}

    @Override
    public void afficher() {
		System.out.println();
		printX();
		int minY = hauteur.get(0)-1;
		int maxY = hauteur.get(getHauteur()-1)+1;
        // On print la ligne du haut vide
        printLines(getLongueur() + 2);
        System.out.print("\n\n");
		int n = Integer.toString(minY).length();
		if(n == 1){
			System.out.print("| "+minY+" | ");
		}
		else if(n == 2){
			System.out.print("| "+minY+"| ");
		}
		else{
			System.out.print("|"+minY+"| ");
		}
		System.out.print("\n\n\n");
        printLines(getLongueur()+2);

        for (int y = hauteur.get(0); y <= hauteur.get(getHauteur()-1); y++) {    // On va printer chaque ligne
            
			System.out.print("      ");
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
				if(l != 1){
					System.out.print("      ");
				}
				else{
					n = Integer.toString(y).length();
					if(n == 1){
						System.out.print("| "+y+" | ");
					}
					else if(n == 2){
						System.out.print("| "+y+"| ");
					}
					else{
						System.out.print("|"+y+"| ");
					}
				}
                TuileDC.printInterm(null,l);	// On print d'abord une partie vide
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

			System.out.print("      ");
            TuileDC.printBas(null);     // On print d'abord un bas vide
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
		System.out.print("\n\n");
		n = Integer.toString(maxY).length();
		if(n == 1){
			System.out.print("| "+maxY+" | ");
		}
		else if(n == 2){
			System.out.print("| "+maxY+"| ");
		}
		else{
			System.out.print("|"+maxY+"| ");
		}
		System.out.print("\n\n\n");
        printLines(getLongueur()+2);
		System.out.println();
    }
    
    public void poserTuile(Tuile t, int x,int y) throws ActionImpossibleException, CasePleineException{
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
    	
    	t.getTitulaire().ajouterScore(point);
    }
    
    public int hautConforme(Tuile t,Tuile haut) {
    	if (haut == null) {
    		return 0;
    	}
    	else {
    		return TuileDC.comparer(t, haut, 'h', 'b');
    	}
    }
    
    public int basConforme(Tuile t,Tuile bas) {
    	if (bas == null) {
    		return 0;
    	}
    	else {
    		return TuileDC.comparer(t, bas, 'b', 'h');
    	}
    }
    
    public int gaucheConforme(Tuile t,Tuile gauche) {
    	if (gauche == null) {
    		return 0;
    	}
    	else {
    		return TuileDC.comparer(t, gauche, 'g', 'd');
    	}
    }
    
    public int droitConforme(Tuile t,Tuile droit) {
    	if (droit == null) {
    		return 0;
    	}
    	else {
    		return TuileDC.comparer(t, droit, 'd', 'g');
    	}
    }
}
