package src.main.java.model.Graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.model.dominosC.PieceDC;
import src.main.java.model.dominosC.TuileDC;

public class PlateauDominoCarre extends JFrame{
	
	JPanel conteneur;
	JPanel information;
	
	public PlateauDominoCarre() {
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		conteneur = new JPanel();
		conteneur.setLayout(null);
		
		information = new JPanel();
		information.setLayout(null);
		
		PieceDC a = new PieceDC(0, 1, 2);
        PieceDC b = new PieceDC(3, 4, 5);
        PieceDC c = new PieceDC(0, 1, 2);
        PieceDC d = new PieceDC(3, 4, 5);

        TuileDC t = new TuileDC(a, b, c, d);
        
        TuileDominoCarree t1 = new TuileDominoCarree(t,0,0);
        conteneur.add(t1);
        
        TuileDominoCarree t2 = new TuileDominoCarree(t,0,1);
        conteneur.add(t2);
        
        TuileDominoCarree t3 = new TuileDominoCarree(t,1,1);
        conteneur.add(t3);
        
        Information i = new Information(800,0);
        conteneur.add(i);
        
        this.add(conteneur);
	}
	
	class Information extends JPanel{
		
		JButton piocher;
		JButton placer;
		
		public Information(int x,int y) {
			this.setBounds(x, y, 200, 800);
			this.setBackground(Color.BLUE);
			
			
			piocher = new JButton("Piocher");
			this.add(piocher);
			
			placer = new JButton("Placer");
			this.add(placer);
			
			
		}
	}
	
	class TuileDominoCarree extends JPanel{
		
		TuileDC tuile;
		int x;
		int y;
		
		public TuileDominoCarree (TuileDC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			this.setBackground(Color.RED);
			this.setLayout(new BorderLayout(5,5));
			this.tuile = tuile;
			this.x = x;
			this.y = y;
			
			init();
		}
		
		public void init() {
			PieceDC gauche = tuile.getGauche();
			PieceDC droite = tuile.getDroite();
			PieceDC bas = tuile.getBas();
			PieceDC haut = tuile.getHaut();
			
			JLabel coin = new JLabel("A");
			coin.setBounds(0, 0, 20, 20);
			this.add(coin);
			
			JLabel coin1 = new JLabel("B");
			coin1.setBounds(80, 0, 20, 20);
			this.add(coin1);
			
			JLabel coin2 = new JLabel("C");
			coin2.setBounds(0,80, 20, 20);
			this.add(coin2);
			
			JLabel coin3 = new JLabel("D");
			coin3.setBounds(80,80, 20, 20);
			this.add(coin3);
			
			for (int i = 0;i<3;i++) {
				int intgauche = gauche.get(i);
				int intdroit = droite.get(i);
				int inthaut = haut.get(i);
				int intbas = bas.get(i);
				
				JLabel jhaut = new JLabel(String.valueOf(inthaut));
				jhaut.setBounds(20+20*i,0, 20, 20);
				this.add(jhaut);
				
				JLabel jbas = new JLabel(String.valueOf(intbas));
				jbas.setBounds(20+20*i,80, 20, 20);
				this.add(jbas);
				
				JLabel jgauche = new JLabel(String.valueOf(intgauche));
				jgauche.setBounds(0,20+20*i, 20, 20);
				this.add(jgauche);
				
				JLabel jdroite = new JLabel(String.valueOf(intdroit));
				jdroite.setBounds(80,20+20*i, 20, 20);
				this.add(jdroite);
				
			}
			
			
			JLabel coin4 = new JLabel("");
			coin4.setBounds(20,80, 20, 20);
			this.add(coin4);
			
		}
	}
	
}
