package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.main.java.model.CC.TuileCC;
import src.main.java.model.general.Joueur;
import src.main.java.model.general.Ordinateur;
import src.main.java.model.general.Tuile;

public class PlateauCarcassonne extends JFrame{
	
	int dx;
	int dy;
	ArrayList<TuileDominoCarcasonne> tuilesPlateau = new ArrayList<TuileDominoCarcasonne>();
	JPanel conteneur;
	JPanel information;
	JeuCCGraphique jeu;
	
	Information info;
	
	public PlateauCarcassonne(JeuCCGraphique j) {
		this.setSize(new Dimension(1000,800));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jeu = j;
		
		
		conteneur = new JPanel();
		conteneur.setLayout(null);
		
		information = new JPanel();
		information.setLayout(null);
		
        info = new Information(800,0);
        conteneur.add(info);
        
        Tuile t = jeu.setPlateau();
        placerTuile(t,0,0);
        
        
        this.add(conteneur);
      
	}
	
	public void placerTuile(Tuile t,int x,int y) {
		TuileDominoCarcasonne t1 = new TuileDominoCarcasonne((TuileCC)t,x,y);
		if (info.getCurrentTuile()!=null){
			t1.setImage(info.getCurrentTuile().getImage());
		}
		
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		tuilesPlateau.add(t1);
	}
	
	public TuileDominoCarcasonne positionner(Tuile t,int x,int y) {
		TuileDominoCarcasonne t1 = new TuileDominoCarcasonne((TuileCC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		
		return t1;
	}
	
	
	class Information extends JPanel{
		
		TuileDominoCarcasonne currentTuile;
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
			this.setBounds(x, y, 200, PlateauCarcassonne.this.getHeight());
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
			infoCoord.setBackground(Color.BLUE);
			infoCoord.setLayout(new GridLayout(2,6));
			
			JPanel vide1 = new JPanel();
			vide1.setBackground(this.getBackground());
			infoCoord.add(vide1); 
			
			haut = new JButton("^");
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
			});

			JPanel vide2 = new JPanel();
			vide2.setBackground(this.getBackground());
			infoCoord.add(vide2);

			gauche = new JButton("<");
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
			});
			
			bas = new JButton("v");
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
			});
			
			droit = new JButton(">");
			infoCoord.add(droit);
			droit.addActionListener((ActionEvent e) ->{
				if (currentTuile != null) {
					if(currentTuile.getX() + currentTuile.getWidth() >= PlateauCarcassonne.this.getWidth() - this.getWidth()){
						glissePlateauGauche();
					}
					else{
						currentTuile.setLocation(currentTuile.getX()+100,currentTuile.getY());
					}
				}
			});
			
			this.add(infoCoord);
			
			message = new JLabel();
			
		}

		private void glissePlateauHaut(){
			for (TuileDominoCarcasonne t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()-100);
			}
			dy += 1;
		}

		private void glissePlateauBas(){
			for (TuileDominoCarcasonne t : tuilesPlateau) {
				t.setLocation(t.getX(), t.getY()+100);
			}
			dy -= 1;
		}

		private void glissePlateauDroite(){
			for (TuileDominoCarcasonne t : tuilesPlateau) {
				t.setLocation(t.getX()+100, t.getY());
			}
			dx -= 1;
		}

		private void glissePlateauGauche(){
			for (TuileDominoCarcasonne t : tuilesPlateau) {
				t.setLocation(t.getX()-100, t.getY());
			}
			dx += 1;
		}
		
		public TuileDominoCarcasonne getCurrentTuile() {
			return currentTuile;
		}
		
		public void defausser() {
			conteneur.remove(currentTuile);
			conteneur.repaint();
			tourner.setEnabled(false);
			placer.setEnabled(false);
			defausser.setEnabled(false);
		}
		
		public void quitter() {
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					PlateauCarcassonne.this.dispose();
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
					PlateauCarcassonne.this.placerTuile(tuileOrdi,tuileOrdi.getCoordonnee().getX()+dx,tuileOrdi.getCoordonnee().getY()+dy);
				}
				suivant();
			}
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
					currentTuile = null;
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
			currentTuile = positionner(t,-3,-3);
			
			tourner.setEnabled(true);
			defausser.setEnabled(true);
			placer.setEnabled(true);
			piocher.setEnabled(false);
			
		}
	}
	
	class TuileDominoCarcasonne extends JPanel{
		
		TuileCC tuile;
		BufferedImage imageR;
		int x;
		int y;
		
		
		public TuileDominoCarcasonne (TuileCC tuile,int x,int y) {
			this.setBounds(x*100+400, y*100+400, 100, 100);
			this.setLayout(new BorderLayout(5,5));
			try {
				imageR = ImageIO.read(new File("src\\main\\java\\gui\\ImagesCC\\"+tuile.getName()));
				
			} catch (IOException e) {
				this.setBackground(Color.YELLOW);
				System.out.println(e);
			}
			this.tuile = tuile;
			this.x = x;
			this.y = y;
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imageR, 0, 0, null);
		}
	
		
		public void tourner() {
			this.removeAll();
			tuile.rotation();
			imageR = rotate(imageR);
			conteneur.repaint();
			
		}
		
		
		public BufferedImage rotate(BufferedImage image) {
	        BufferedImage buffered = (BufferedImage) image;
	        BufferedImage buffered2 = new BufferedImage(buffered.getHeight(),buffered.getWidth(),BufferedImage.TYPE_INT_RGB);
	        for(int x = 0;x < buffered.getWidth();x++) {
	            for(int y = 0;y < buffered.getHeight();y++) {
	                    buffered2.setRGB(buffered.getHeight()-y-1, x,
	                            buffered.getRGB(x, y));
	                 
	                 
	            }
	        }
	        return buffered2;
	    }
		
		public BufferedImage getImage() {
			return imageR;
		}
		
		public void setImage(BufferedImage i) {
			imageR = i;
		}
	}
	
}
