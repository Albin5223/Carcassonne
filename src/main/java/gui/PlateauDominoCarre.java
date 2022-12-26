package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
	
	int dx;
	int dy;
	ArrayList<TuileDominoCarree> tuilesPlateau = new ArrayList<TuileDominoCarree>();
	JPanel conteneur;
	JPanel information;
	JeuDCGraphique jeu;

	Information info;
	
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
		
		
        
        info = new Information(800,0);
        conteneur.add(info);
        
        Tuile t = ((JeuDCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
        
        
        this.add(conteneur);
      
	}
	
	public void placerTuile(Tuile t,int x,int y) {
		TuileDominoCarree t1 = new TuileDominoCarree((TuileDC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		t1.fixer();
		tuilesPlateau.add(t1);
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
			this.setBounds(x, y, 200, PlateauDominoCarre.this.getHeight());
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
			
			defausser = new JButton("Defausser");
			defausser.setEnabled(false);
			defausser.addActionListener((ActionEvent e) ->{
				defausser();
				suivant();
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

				suivant();
				
			});
			
			this.add(jv1);
			this.add(panneauButton);
			
			infoCoord = new JPanel();
			infoCoord.setBackground(Color.BLUE);
			infoCoord.setLayout(new GridLayout(2,6));
			
			JPanel vide1 = new JPanel();
			vide1.setBackground(this.getBackground());
			infoCoord.add(vide1); 
			
			haut = new JButton("^");
			infoCoord.add(haut);
			haut.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					if(t1.getY()<=0){
						glissePlateauBas();
					}
					else{
						t1.setLocation(t1.getX(),t1.getY()-100);
					}
				}
			});

			JPanel vide2 = new JPanel();
			vide2.setBackground(this.getBackground());
			infoCoord.add(vide2);

			gauche = new JButton("<");
			infoCoord.add(gauche);
			gauche.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					if(t1.getX()<=0){
						glissePlateauDroite();
					}
					else{
						t1.setLocation(t1.getX()-100,t1.getY());
					}
				}
			});
			
			bas = new JButton("v");
			infoCoord.add(bas);
			bas.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					if(t1.getY()+t1.getHeight() >= this.getHeight()){
						glissePlateauHaut();
					}
					else{
						t1.setLocation(t1.getX(),t1.getY()+100);
					}
				}
			});
			
			droit = new JButton(">");
			infoCoord.add(droit);
			droit.addActionListener((ActionEvent e) ->{
				if (t1 != null) {
					if(t1.getX() + t1.getWidth() >= PlateauDominoCarre.this.getWidth() - this.getWidth()){
						glissePlateauGauche();
					}
					else{
						t1.setLocation(t1.getX()+100,t1.getY());
					}
				}
			});
			
			this.add(infoCoord);
			
			message = new JLabel();
			
		}

		private void glissePlateauHaut(){
			for (TuileDominoCarree t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()-100);
			}
			dy += 1;
		}

		private void glissePlateauBas(){
			for (TuileDominoCarree t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()+100);
			}
			dy -= 1;
		}

		private void glissePlateauDroite(){
			for (TuileDominoCarree t : tuilesPlateau) {
				t.setLocation(t.getX()+100, t.getY());
			}
			dx -= 1;
		}

		private void glissePlateauGauche(){
			for (TuileDominoCarree t : tuilesPlateau) {
				t.setLocation(t.getX()-100, t.getY());
			}
			dx += 1;
		}
		
		public void defausser() {
			conteneur.remove(t1);
			conteneur.repaint();
			tourner.setEnabled(false);
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
				abandonner.setEnabled(false);
				quitter();
				return;
			}
			jv1.replace(jeu.getCurrentJoueur());
			piocher.setEnabled(true);
			placer.setEnabled(false);
			abandonner.setEnabled(true);
			if (message!=null) {
				panneauButton.remove(message);
			}
			if (jeu.getCurrentJoueur().isBot()) {
				Tuile tuileOrdi = jeu.piocher(jeu.getCurrentJoueur());
				Joueur j = jeu.getCurrentJoueur();
				if (((Ordinateur) j).jouerTour(tuileOrdi)) {
					PlateauDominoCarre.this.placerTuile(tuileOrdi,tuileOrdi.getCoordonnee().getX()+dx,tuileOrdi.getCoordonnee().getY()+dy);
				}
				suivant();
			}
		}
			
		public void placer() {
			int x = (t1.getX()-400)/100;
			int y = (t1.getY()-400)/100;
			if (tuile != null) {
				if (jeu.placer(tuile,x+dx, y+dy)){
					conteneur.remove(t1);
					conteneur.repaint();
					t1 = null;
					placerTuile(tuile,x,y);
					conteneur.repaint();
					message.setText("Bien joue");
					message.setForeground(Color.WHITE );
					panneauButton.add(message);
					this.setVisible(false);
					this.setVisible(true);
					
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					placer.setEnabled(false);
					abandonner.setEnabled(false);
					suivant();
					jv1.refresh();
		
					}
				else {
					message.setText("Erreur dans le placement");
					message.setForeground(Color.RED);
					panneauButton.add(message);
					this.setVisible(false);
					this.setVisible(true);
		
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
