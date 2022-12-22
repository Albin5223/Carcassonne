package src.main.java.model.general;

/*
 * Cette classe va représenter un des cote qu'il y a sur les côtés d'une tuileDominosCarrées
 */
public abstract class Cote {
    
    // Attributs
    protected Valeur[] cote;
    // On met la valeur de taille à 3 puisque le jeu est défini comme cela
    protected static final int taille = 3;
    
    public Cote(Valeur i, Valeur j, Valeur k){
        cote = new Valeur[taille];
        cote[0] = i;
        cote[1] = j;
        cote[2] = k;
    }

    public boolean equals(Cote p){
        return cote[0].equals(p.cote[0]) && cote[1].equals(p.cote[1]) && cote[2].equals(p.cote[2]);
    }

    // Méthodes getteurs
    public abstract Object get(int i);
    public abstract Valeur getObject(int i);
    public static int length(){return taille;}
    public abstract Cote inversePiece();

    public static int comparer(Cote p1,Cote p2) {
    	int point = 0;
    	for (int i = 0;i<3;i++) {
    		if(!p1.get(i).equals(p2.get(i))) {
    			return 0;
    		}
    		else {
    			point += p1.getObject(i).getPoints();
    		}
    	}
    	return point;
    }
}
