package src.main.java.guiV3;

import java.awt.*;
import javax.swing.*;

import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.TuileDC;

public class TuileDCView extends JPanel {
		
    int dim = 5;   
    JPanel[][] panelHolder = new JPanel[dim][dim]; 
    TuileDC tuile;

    public TuileDCView(TuileDC tuile, Color background) {
        this(background);
        init(tuile);
    }

    public TuileDCView(TuileDC tuile) {
        this(Color.RED);
        init(tuile);
    }

    public TuileDCView(Color background){
        this.setLayout(new GridLayout(dim,dim));
        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m] = new JPanel();
                panelHolder[n][m].setBackground(background);
                this.add(panelHolder[n][m]);
            }
        }
    }

    public void addLabel(String label, int x, int y){
        panelHolder[x][y].add(new JLabel(label));
    }
    
    public void init(TuileDC tuile) {
        this.tuile = tuile;
        
        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m].setBackground(Color.RED);
            }
        }

        CoteDC gauche = tuile.getGauche();
        CoteDC droite = tuile.getDroite();
        CoteDC bas = tuile.getBas();
        CoteDC haut = tuile.getHaut();
        
        for (int i = 0;i<3;i++) {
            String intgauche = gauche.get(i).toString();
            String intdroit = droite.get(i).toString();
            String inthaut = haut.get(i).toString();
            String intbas = bas.get(i).toString();

            addLabel(intgauche, 0, 1+i);
            addLabel(intdroit, 4, 1+i);

            addLabel(inthaut, 1+i, 0);
            addLabel(intbas, 1+i, 4);
        }
    }
}