package src.main.java.model.DC;

import src.main.java.model.general.Cote;

public class PieceDC extends Cote<Integer> {

    public PieceDC(int i, int j, int k) {
        super(i, j, k);
    }

    public Integer get(int i){return cote.get(i);}

    @Override
    public Cote<Integer> inversePiece() {
        int t1 = this.get(2);
        int t2 = this.get(1);
        int t3 = this.get(0);
        return new PieceDC(t1, t2, t3);
    }    

    public static int comparer(PieceDC p1,PieceDC p2) {
    	int point = 0;
    	for (int i = 0;i<3;i++) {
    		if (p1.cote.get(i) != p2.cote.get(i)) {
    			return 0;
    		}
    		else {
    			point += p1.cote.get(i);
    		}
    	}
    	return point;
    }
}
