package src.main.java.gui;

import src.main.java.model.DC.TuileDC;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.main.java.model.DC.CoteDC;
import src.main.java.model.general.Tuile;

public class PlateauDCG extends PlateauG {

    public PlateauDCG(JeuDCGraphique j) {
        super(j);
        this.setTitle(("Domino Carree"));
    }

    @Override
    public void initJeu() {
		conteneur = new JPanel();
		conteneur.setLayout(null);
        
        info = new Information(800,0);
        conteneur.add(info);
        
        TuileDC t = (TuileDC) ((JeuDCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
          
        this.add(conteneur);
	}

    @Override
	public void placerTuile(Tuile t,int x,int y) {
		TuileDCG t1 = new TuileDCG((TuileDC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		t1.fixer();
		tuilesPlateau.add(t1);
	}
	
    @Override
	public TuileDCG positionner(Tuile t,int x,int y) {
		TuileDCG t1 = new TuileDCG((TuileDC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		
		return t1;
	}


    public class TuileDCG extends TuileG {

        public TuileDCG(TuileDC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			this.setBackground(Color.YELLOW);
			this.setLayout(new BorderLayout(5,5));
			this.tuile = tuile;
			this.x = x;
			this.y = y;
			init();
		}

        @Override
        public void init() {
			CoteDC gauche = (CoteDC) tuile.getGauche();
			CoteDC droite = (CoteDC) tuile.getDroite();
			CoteDC bas = (CoteDC) tuile.getBas();
			CoteDC haut = (CoteDC) tuile.getHaut();
			
			JLabel coin = new JLabel(" ");
			coin.setBounds(0, 0, 20, 20);
			this.add(coin);
			
			JLabel coin1 = new JLabel(" ");
			coin1.setBounds(80, 0, 20, 20);
			this.add(coin1);
			
			JLabel coin2 = new JLabel(" ");
			coin2.setBounds(0,80, 20, 20);
			this.add(coin2);
			
			JLabel coin3 = new JLabel(" ");
			coin3.setBounds(80,80, 20, 20);
			this.add(coin3);
			
			for (int i = 0;i<3;i++) {
				int intgauche = (int) gauche.get(i);
				int intdroit = (int) droite.get(i);
				int inthaut = (int) haut.get(i);
				int intbas = (int) bas.get(i);

				int ecartX = 5;
				
				JLabel jhaut = new JLabel(String.valueOf(inthaut));
				jhaut.setBounds(ecartX+20+20*i,0, 20, 20);
				this.add(jhaut);
				
				JLabel jbas = new JLabel(String.valueOf(intbas));
				jbas.setBounds(ecartX+20+20*i,80, 20, 20);
				this.add(jbas);
				
				JLabel jgauche = new JLabel(String.valueOf(intgauche));
				jgauche.setBounds(ecartX+0,20+20*i, 20, 20);
				this.add(jgauche);
				
				JLabel jdroite = new JLabel(String.valueOf(intdroit));
				jdroite.setBounds(ecartX+80,20+20*i, 20, 20);
				this.add(jdroite);	
			}
			
			
			JLabel coin4 = new JLabel("");
			coin4.setBounds(20,80, 20, 20);
			this.add(coin4);
        }

        @Override
        public void tourner() {
            this.removeAll();
			tuile.rotation();
			init();
			conteneur.repaint();
        }

        public void fixer() {
			this.setBackground(Color.RED);
		}
        
    }
    
}
