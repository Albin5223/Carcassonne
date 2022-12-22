package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.TuileDC;
import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Tuile;

public class PlateauDominoCarre extends JFrame{
	
	JPanel conteneur;
	JPanel information;
	JeuDCGraphique jeu;
	
	public PlateauDominoCarre(JeuDCGraphique j) {
		this.setSize(new Dimension(1000,800));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jeu = j;
		initJeu();
	}
	
	public void initJeu() {
		conteneur = new JPanel();
		conteneur.setLayout(null);
		
		information = new JPanel();
		information.setLayout(null);
		

        
        Information i = new Information(800,0);
        conteneur.add(i);
        
        Tuile t = ((JeuDCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
        
        
        this.add(conteneur);
	}
	
	public void placerTuile(Tuile t,int x,int y) {
		TuileDominoCarree t1 = new TuileDominoCarree((TuileDC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
	}
	
	
	class Information extends JPanel{
		
		TuileDominoCarree t1;
		Tuile tuile;
		JButton piocher;
		JButton placer;
		JButton defausser;
		JButton tourner;
		JButton suivant;
		JButton abandonner;
		JoueurView jv1;
		
		JPanel panneauButton;
		
		public Information(int x,int y) {
			this.setBounds(x, y, 200, 800);
			this.setBackground(Color.BLUE);
			this.setLayout(new GridLayout(4,1,100,100));
			
			jv1 = new JoueurView(jeu.getCurrentJoueur());
			panneauButton = new JPanel();
			panneauButton.setBackground(Color.BLUE);
			
			
			piocher = new JButton("Piocher");
			panneauButton.add(piocher,BorderLayout.NORTH);
			
			piocher.addActionListener((ActionEvent e) ->{
				TuileDC t = jeu.piocher(jeu.getCurrentJoueur());
				tuile = t;
				t1 = new TuileDominoCarree(t,0,0);
				this.add(t1);
				this.setVisible(false);
				this.setVisible(true);
				tourner.setEnabled(true);
				defausser.setEnabled(true);
				placer.setEnabled(true);
			});
			
			placer = new JButton("Placer");
			placer.setEnabled(false);
			placer.addActionListener((ActionEvent e) ->{
				placer(1,0);
			});
			panneauButton.add(placer,BorderLayout.NORTH);
			
			suivant = new JButton("Suivant");
			suivant.setEnabled(false);
			suivant.addActionListener((ActionEvent e) ->{
				jeu.joueurSuivant();
				jv1.replace(jeu.getCurrentJoueur());
				piocher.setEnabled(true);
				placer.setEnabled(false);
				
			});
			panneauButton.add(suivant);
			
			defausser = new JButton("Defausser");
			defausser.setEnabled(false);
			panneauButton.add(defausser);
			
			tourner = new JButton("Rotation");
			tourner.setEnabled(false);
			panneauButton.add(tourner);
			
			abandonner = new JButton("Abandonner");
			panneauButton.add(abandonner);
			
			this.add(jv1);
			this.add(panneauButton);
		}
			
		public void placer(int x, int y) {
			if (tuile != null) {
				TuileDominoCarree t1 = new TuileDominoCarree((TuileDC)tuile,x,y);
				conteneur.add(t1,BorderLayout.CENTER);
				conteneur.repaint();
			}			
		}
		
		public void piocher() {
			this.add(t1);
			t1.setVisible(true);
			this.repaint();
			information.repaint();
			
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
			CoteDC gauche = tuile.getGauche();
			CoteDC droite = tuile.getDroite();
			CoteDC bas = tuile.getBas();
			CoteDC haut = tuile.getHaut();
			
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
				int intgauche = (int) gauche.get(i);
				int intdroit = (int) droite.get(i);
				int inthaut = (int) haut.get(i);
				int intbas = (int) bas.get(i);
				
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
