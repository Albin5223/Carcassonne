package src.main.java.model.dominosC;

public class Test {
    public static void main(String[] args) {

        PieceDomino a = new PieceDomino(0, 1, 2);
        PieceDomino b = new PieceDomino(3, 4, 5);
        PieceDomino c = new PieceDomino(6, 7, 8);
        PieceDomino d = new PieceDomino(9, 0, 1);

        TuileDominosCarres t = new TuileDominosCarres(a, b, c, d);
        
        PlateauDominosCarres p = new PlateauDominosCarres(3,4);
        for (int i = 0; i < p.getHauteur(); i++) {
            for (int j = 0; j < p.getLongueur(); j++) {
                p.getCase(i, j).poserTuile(t);
            }
        }

        p.afficher();

        DominosCarres domino = new DominosCarres();
        //domino.lancerPartie();
    }
}
