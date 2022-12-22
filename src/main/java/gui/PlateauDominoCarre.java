package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.TuileDC;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
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
		t1.fixer();
	}
	
	public TuileDominoCarree positionner(Tuile t,int x,int y) {
		TuileDominoCarree t1 = new TuileDominoCarree((TuileDC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		
		return t1;
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
		
		JTextField xCord;
		JTextField yCord;
		
		JPanel panneauButton;
		JPanel infoCoord;
		JLabel message;
		
		JButton gauche;
		JButton droit;
		JButton haut;
		JButton bas;
		
		public Information(int x,int y) {
			this.setBounds(x, y, 200, 800);
			this.setBackground(Color.BLUE);
			this.setLayout(new GridLayout(4,1,100,25));
			
			jv1 = new JoueurView(jeu.getCurrentJoueur());
			panneauButton = new JPanel();
			panneauButton.setBackground(Color.BLUE);
			
			
			
			piocher = new JButton("Piocher");
			panneauButton.add(piocher,BorderLayout.NORTH);
			
			piocher.addActionListener((ActionEvent e) ->{
				piocher();
			});
			
			placer = new JButton("Placer");
			placer.setEnabled(false);
			placer.addActionListener((ActionEvent e) ->{
				placer();
			});
			panneauButton.add(placer,BorderLayout.NORTH);
			
			suivant = new JButton("Suivant");
			suivant.setEnabled(false);
			suivant.addActionListener((ActionEvent e) ->{
				if (jeu.partieFinie()) {
					jv1.annonceVainqueur();
					piocher.setEnabled(false);
					placer.setEnabled(false);
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					suivant.setEnabled(false);
					abandonner.setEnabled(false);
					quitter();
				}
				else {
					suivant();
				}
			});
			panneauButton.add(suivant);
			
			defausser = new JButton("Defausser");
			defausser.setEnabled(false);
			defausser.addActionListener((ActionEvent e) ->{
				defausser();
				
			});
			panneauButton.add(defausser);
			
			tourner = new JButton("Rotation");
			tourner.setEnabled(false);
			tourner.addActionListener((ActionEvent e) ->{
				t1.tourner();
			});
			panneauButton.add(tourner);
			
			
			
			
			abandonner = new JButton("Abandonner");
			panneauButton.add(abandonner);
			abandonner.addActionListener((ActionEvent e) ->{
				jeu.abandonner();
				if(t1 != null) {
					defausser();
				}
				
				if (jeu.partieFinie()) {
					jv1.annonceVainqueur();
					piocher.setEnabled(false);
					placer.setEnabled(false);
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					suivant.setEnabled(false);
					abandonner.setEnabled(false);
					quitter();
				}
				else {
					suivant();
				}
				
			});
			
			this.add(jv1);
			this.add(panneauButton);
			
			infoCoord = new JPanel();
			infoCoord.setBackground(Color.BLUE);
			infoCoord.setLayout(new GridLayout(2,2));
			
			
			haut = new JButton("Haut");
			infoCoord.add(haut);
			haut.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					t1.setLocation(t1.getX(),t1.getY()-100);
				}
			});
			
			bas = new JButton("Bas");
			infoCoord.add(bas);
			bas.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					t1.setLocation(t1.getX(),t1.getY()+100);
				}
			});
			
			gauche = new JButton("Gauche");
			infoCoord.add(gauche);
			gauche.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					t1.setLocation(t1.getX()-100,t1.getY());
				}
			});
			
			droit = new JButton("Droit");
			infoCoord.add(droit);
			droit.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					t1.setLocation(t1.getX()+100,t1.getY());
				}
			});
			
			this.add(infoCoord);
			
			message = new JLabel();
			panneauButton.add(message);
			
		}
		
		public void defausser() {
			conteneur.remove(t1);
			conteneur.repaint();
			tourner.setEnabled(false);
			suivant.setEnabled(true);
			placer.setEnabled(false);
			defausser.setEnabled(false);
		}
		
		public void quitter() {
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					PlateauDominoCarre.this.dispose();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		
		public void suivant() {
			jeu.joueurSuivant();
			if (jeu.partieFinie()) {
				jv1.annonceVainqueur();
				piocher.setEnabled(false);
				placer.setEnabled(false);
				defausser.setEnabled(false);
				tourner.setEnabled(false);
				suivant.setEnabled(false);
				abandonner.setEnabled(false);
				quitter();
				return;
			}
			jv1.replace(jeu.getCurrentJoueur());
			piocher.setEnabled(true);
			placer.setEnabled(false);
			abandonner.setEnabled(true);
			suivant.setEnabled(false);
			if (message!=null) {
				panneauButton.remove(message);
			}
			if (jeu.getCurrentJoueur().isBot()) {
				Tuile tuileOrdi = jeu.piocher(jeu.getCurrentJoueur());
				Joueur j = jeu.getCurrentJoueur();
				if (((Ordinateur) j).jouerTour(tuileOrdi)) {
					PlateauDominoCarre.this.placerTuile(tuileOrdi,tuileOrdi.getCoordonnee().getX(),tuileOrdi.getCoordonnee().getY());
				}
				suivant();
			}
		}
			
		public void placer() {
			int x = (t1.getX()-400)/100;
			int y = (t1.getY()-400)/100;
			if (tuile != null) {
				if (jeu.placer(tuile,x, y)){
					conteneur.remove(t1);
					conteneur.repaint();
					t1 = null;
					placerTuile(tuile,x,y);
					conteneur.repaint();
					message.setText("Bien joue");
					message.setForeground(Color.WHITE );
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					placer.setEnabled(false);
					abandonner.setEnabled(false);
					suivant.setEnabled(true);
					jv1.refresh();
		
					}
				else {
					message.setText("Erreur dans le placement");
					message.setForeground(Color.RED);
		
				}
			}		
		}
		
		public void piocher() {
			Tuile t = jeu.piocher(jeu.getCurrentJoueur());
			tuile = t;
			t1 = positionner(t,-3,-3);
			
			tourner.setEnabled(true);
			defausser.setEnabled(true);
			placer.setEnabled(true);
			piocher.setEnabled(false);
			
		}
	}
	
	class TuileDominoCarree extends JPanel{
		
		TuileDC tuile;
		int x;
		int y;
		
		public TuileDominoCarree (TuileDC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			this.setBackground(Color.YELLOW);
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
		
		public void fixer() {
			this.setBackground(Color.RED);
		}
		
		public void tourner() {
			this.removeAll();
			tuile.rotation();
			init();
			conteneur.repaint();
			
		}
	}
	
}
