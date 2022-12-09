package src.main.java.model.dominosC;

public class Test {
    public static void main(String[] args) {

        PieceDomino a = new PieceDomino(0, 1, 2);
        PieceDomino b = new PieceDomino(3, 4, 5);
        PieceDomino c = new PieceDomino(0, 1, 2);
        PieceDomino d = new PieceDomino(3, 4, 5);

        TuileDominosCarres t = new TuileDominosCarres(a, b, c, d);
        
        PlateauDominosCarres p = new PlateauDominosCarres(10,7);

        // On pose au moins une tuile sur le plateau pour pouvoir placer le reste
        p.getCase(0, 0).poserTuile(t);p.getCase(1, 0).poserTuile(t);
        p.getCase(0, 1).poserTuile(t);
        p.getCase(0, 2).poserTuile(t);p.getCase(1, 2).poserTuile(t);

        /* 
        // Et le reste se placera vu que c'est la même tuile à chaque fois, et que le haut = bas et droite = gauche
        for (int i = 0; i < p.getLongueur(); i++) {
            for (int j = 1; j < p.getHauteur(); j++) {
                p.poserTuile(t, i, j);
            }
        }
        //p.poserTuile(t, 1, 4);
        */

        p.afficher();


        DominosCarres domino = new DominosCarres();
        //domino.lancerPartie();
    }
}
