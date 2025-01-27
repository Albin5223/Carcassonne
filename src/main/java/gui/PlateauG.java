package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.model.general.Jeu;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
import src.main.java.model.general.Tuile;

public abstract class PlateauG extends JFrame {

    int dx;
	int dy;
	ArrayList<TuileG> tuilesPlateau = new ArrayList<TuileG>();
	JPanel conteneur;
	Jeu jeu;
	Information info;
	Image imageBackground;
	
	public PlateauG(Jeu j) {
		this.setSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		jeu = j;
		jeu.initSac();
		initJeu();
		this.setVisible(true);
	}
	
	public abstract void initJeu();
	
	public abstract void placerTuile(Tuile t,int x,int y);
	
	public abstract TuileG positionner(Tuile t,int x,int y);

    public class Information extends JPanel {
		
		TuileG currentTuile;
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

		static BufferedImage bg;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, null);
		}

		
		public Information(int x,int y) {
			this.setBounds(x, y, 200, PlateauG.this.getHeight());
			this.setLayout(new GridLayout(4,1,100,25));

			try {
				bg = ImageIO.read(new File("src\\main\\java\\gui\\Resource\\fond_information.jpg"));
			} catch (IOException e1) {}
			
			jv1 = new JoueurView(jeu.getCurrentJoueur());
			panneauButton = new JPanel();
			panneauButton.setOpaque(false);

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
				currentTuile.tourner();
			});
			panneauButton.add(tourner);
			
			
			
			
			abandonner = new JButton("Abandonner");
			panneauButton.add(abandonner);
			abandonner.addActionListener((ActionEvent e) ->{
				jeu.abandonner();
				if(currentTuile != null) {
					defausser();
				}

				suivant();
				
			});
			
			this.add(jv1);
			this.add(panneauButton);
			
			infoCoord = new JPanel();
			infoCoord.setOpaque(false);
			infoCoord.setLayout(new GridLayout(3,3));
			
			JPanel vide1 = new JPanel();
			vide1.setOpaque(false);
			infoCoord.add(vide1); 
			
			haut = new JButton(new ImageIcon("src\\main\\java\\gui\\Resource\\up.png"));
			haut.setBorder(BorderFactory.createEmptyBorder());
			haut.setContentAreaFilled(false);
			infoCoord.add(haut);
			haut.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getY()<=0){
						glissePlateauBas();
					}
					else{
						currentTuile.setLocation(currentTuile.getX(),currentTuile.getY()-100);
					}
				}
				else{
					glissePlateauBas();
				}
			});

			JPanel vide2 = new JPanel();
			vide2.setOpaque(false);
			infoCoord.add(vide2);

			gauche = new JButton(new ImageIcon("src\\main\\java\\gui\\Resource\\left.png"));
			gauche.setBorder(BorderFactory.createEmptyBorder());
			gauche.setContentAreaFilled(false);
			infoCoord.add(gauche);
			gauche.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX()<=0){
						glissePlateauDroite();
					}
					else{
						currentTuile.setLocation(currentTuile.getX()-100,currentTuile.getY());
					}
				}
				else{
					glissePlateauDroite();
				}
			});

			JPanel vide3 = new JPanel();
			vide3.setOpaque(false);
			infoCoord.add(vide3);

			droit = new JButton(new ImageIcon("src\\main\\java\\gui\\Resource\\right.png"));
			droit.setBorder(BorderFactory.createEmptyBorder());
			droit.setContentAreaFilled(false);
			infoCoord.add(droit);
			droit.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX() + currentTuile.getWidth() >= PlateauG.this.getWidth() - this.getWidth()){
						glissePlateauGauche();
					}
					else{
						currentTuile.setLocation(currentTuile.getX()+100,currentTuile.getY());
					}
				}
				else{
					glissePlateauGauche();
				}
			});

			JPanel vide4 = new JPanel();
			vide4.setOpaque(false);
			infoCoord.add(vide4);
			
			bas = new JButton(new ImageIcon("src\\main\\java\\gui\\Resource\\down.png"));
			bas.setBorder(BorderFactory.createEmptyBorder());
			bas.setContentAreaFilled(false);
			infoCoord.add(bas);
			bas.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getY()+currentTuile.getHeight() >= this.getHeight()){
						glissePlateauHaut();
					}
					else{
						currentTuile.setLocation(currentTuile.getX(),currentTuile.getY()+100);
					}
				}
				else{
					glissePlateauHaut();
				}
			});

			JPanel vide5 = new JPanel();
			vide5.setOpaque(false);
			infoCoord.add(vide5);
			
			this.add(infoCoord);
			
			message = new JLabel();

			haut.setFocusable(false);
			bas.setFocusable(false);
			droit.setFocusable(false);
			gauche.setFocusable(false);

			piocher.setFocusable(false);
			placer.setFocusable(false);
			defausser.setFocusable(false);
			tourner.setFocusable(false);
			abandonner.setFocusable(false);
		}

		protected void glissePlateauHaut(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()-100);
			}
			dy += 1;
		}

		protected void glissePlateauBas(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()+100);
			}
			dy -= 1;
		}

		protected void glissePlateauDroite(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()+100, t.getY());
			}
			dx -= 1;
		}

		protected void glissePlateauGauche(){
			for (TuileG t : tuilesPlateau) {
				t.setLocation(t.getX()-100, t.getY());
			}
			dx += 1;
		}

		public TuileG getCurrentTuile() {
			return currentTuile;
		}
		
		public void defausser() {
			conteneur.remove(currentTuile);
			conteneur.repaint();
			tourner.setEnabled(false);
			placer.setEnabled(false);
			defausser.setEnabled(false);
			currentTuile = null;
		}
		
		public void quitter() {
			this.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PlateauG.this.dispose();
					javax.swing.SwingUtilities.invokeLater(
							new Runnable() {
								public void run() {
									new AcceuilView();
								}
							}
						);
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
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
				currentTuile = null;
				Tuile tuile = jeu.piocher(jeu.getCurrentJoueur());
				Joueur j = jeu.getCurrentJoueur();
				if (((Ordinateur) j).jouerTour(tuile)) {
					placerTuile(tuile,tuile.getCoordonnee().getX()-dx,tuile.getCoordonnee().getY()-dy);
				}
				suivant();
			}

			jv1.refresh();
			currentTuile = null;
		}
			
		public void placer() {
			int x = (currentTuile.getX()-400)/100;
			int y = (currentTuile.getY()-400)/100;
			if (tuile != null) {
				if (jeu.placer(tuile,x+dx, y+dy)){
					conteneur.remove(currentTuile);
					conteneur.repaint();
					placerTuile(tuile,x,y);
					conteneur.repaint();
					message.setText("Bien joue");
					message.setForeground(Color.WHITE);
					panneauButton.add(message);
					this.setVisible(false);
					this.setVisible(true);
					
					defausser.setEnabled(false);
					tourner.setEnabled(false);
					placer.setEnabled(false);
					abandonner.setEnabled(false);
					suivant();
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
			tuile = jeu.piocher(jeu.getCurrentJoueur());
			currentTuile = positionner(tuile,-3,-3);
			
			tourner.setEnabled(true);
			defausser.setEnabled(true);
			placer.setEnabled(true);
			piocher.setEnabled(false);
		}
	}
	
	public abstract class TuileG extends JPanel {
		
		Tuile tuile;
		int x;
		int y;
		
		public abstract void init();
		
		public abstract void tourner();
	}
    
}
