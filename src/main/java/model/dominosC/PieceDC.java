package src.main.java.model.dominosC;

/*
 * Cette classe va représenter un des domino qu'il y a sur les côtés d'une tuileDominosCarrées
 */
public class PieceDC {
    
    // Attributs
    private int[] domino;
    // On met la valeur de taille à 3 puisque le jeu est défini comme cela
    private static final int taille = 3;
    
    public PieceDC(int i, int j, int k){
        domino = new int[taille];
        domino[0] = i;
        domino[1] = j;
        domino[2] = k;
    }

    public boolean equals(PieceDC p){
        return domino[0] == p.domino[0] && domino[1] == p.domino[1] && domino[2] == p.domino[2];
    }

    // Méthodes getteurs
    public int get(int i){return domino[i];}
    public static int length(){return taille;}

    public PieceDC inversePiece(){
        PieceDC inverse = new PieceDC(0, 0,0);
        inverse.domino[0] = this.get(2);
        inverse.domino[1] = this.get(1);
        inverse.domino[2] = this.get(0);
        return inverse;
    }
    
    public static int comparer(PieceDC p1,PieceDC p2) {
    	int point = 0;
    	for (int i = 0;i<3;i++) {
    		if (p1.domino[i]!=p2.domino[i]) {
    			return 0;
    		}
    		else {
    			point +=p1.domino[i];
    		}
    	}
    	return point;
    }
}
