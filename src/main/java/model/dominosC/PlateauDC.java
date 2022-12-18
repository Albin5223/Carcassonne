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
}
