package src.main.java.guiV3;

import javax.swing.*;
import java.awt.*;

public class PlateauView extends JPanel {

    int dim = 5;   
    TuileDCView[][] panelHolder = new TuileDCView[dim][dim]; 

    public PlateauView(){

        this.setLayout(new GridLayout(dim,dim));

        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m] = new TuileDCView(Color.WHITE,this);
                panelHolder[n][m].setXY(n, m);
                this.add(panelHolder[n][m]);
            }
        }
    }

    public void setTuile(TuileDCView tuile, int x, int y){
        panelHolder[x+(this.dim/2)][y+(this.dim/2)].init(tuile.tuile);  
    }

    public void setTuileDrag(TuileDCView tuile, int x, int y){
        panelHolder[x][y].init(tuile.tuile);  
    }
    
}
