package src.main.java.model;

public class Test {
    public static void main(String[] args) {
        
        int[] a = {0,0,1};
        int[] b = {1,1,2};
        int[] c = {2,2,3};
        int[] d = {3,3,4};
        TuileDominosCarres t = new TuileDominosCarres(3, a, b, c, d);
        t.afficheTuile();

        DominosCarres domino = new DominosCarres();
        domino.lancerPartie();
    }
}
