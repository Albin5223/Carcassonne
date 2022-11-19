package src.main.java.model;

public class Test {
    public static void main(String[] args) {

        PieceDomino a = new PieceDomino(0, 0, 1);
        PieceDomino b = new PieceDomino(0, 0, 2);
        PieceDomino c = new PieceDomino(0, 0, 3);
        PieceDomino d = new PieceDomino(0, 0, 4);

        TuileDominosCarres t = new TuileDominosCarres(a, b, c, d);
        t.afficher();

        DominosCarres domino = new DominosCarres();
        domino.lancerPartie();
    }
}
