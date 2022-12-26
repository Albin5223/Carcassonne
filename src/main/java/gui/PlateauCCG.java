package src.main.java.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.main.java.model.CC.TuileCC;
import src.main.java.model.general.Tuile;

public class PlateauCCG extends PlateauG {

    public PlateauCCG(JeuCCGraphique j) {
        super(j);
    }

	@Override
	public void initJeu(){
		conteneur = new JPanel();
		conteneur.setLayout(null);
		
		information = new JPanel();
		information.setLayout(null);
		
        info = new Information(800,0);
        conteneur.add(info);
        
        Tuile t = ((JeuCCGraphique) jeu).setPlateau();
        placerTuile(t,0,0);
        
        this.add(conteneur);
	}

	@Override
	public void placerTuile(Tuile t,int x,int y) {
		TuileCCG t1 = new TuileCCG((TuileCC)t,x,y);
		if (info.getCurrentTuile()!=null){
			t1.setImage(((TuileCCG) info.getCurrentTuile()).getImage());
		}
		
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		tuilesPlateau.add(t1);
	}
	
	@Override
	public TuileCCG positionner(Tuile t,int x,int y) {
		TuileCCG t1 = new TuileCCG((TuileCC)t,x,y);
		conteneur.add(t1,BorderLayout.CENTER);
		conteneur.repaint();
		
		return t1;
	}
    

    public class TuileCCG extends TuileG {

        BufferedImage imageR;

		public TuileCCG (TuileCC tuile,int x,int y) {
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

        @Override
        public void init() {}

        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imageR, 0, 0, null);
		}

        @Override
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
					buffered2.setRGB(buffered.getHeight()-y-1, x,buffered.getRGB(x, y));
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
