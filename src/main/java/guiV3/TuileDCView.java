package src.main.java.guiV3;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.model.DC.CoteDC;
import src.main.java.model.DC.TuileDC;

public class TuileDCView extends JPanel implements MouseInputListener {
		
    int dim = 5;   
    JPanel[][] panelHolder = new JPanel[dim][dim]; 
    PlateauView plateau;
    TuileDC tuile;
    int cx;
    int cy;

    public TuileDCView(TuileDC tuile, Color background,PlateauView plateau) {
        this(background,plateau);
        init(tuile);
    }

    public TuileDCView(TuileDC tuile,PlateauView plateau) {
        this(Color.RED,plateau);
        init(tuile);
    }

    public TuileDCView(Color background, PlateauView plateau){
        this.setLayout(new GridLayout(dim,dim));
        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m] = new JPanel();
                panelHolder[n][m].setBackground(background);
                this.add(panelHolder[n][m]);
            }
        }
        this.plateau = plateau;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setXY(int x, int y){
        cx = x;
        cy = y;
    }

    public void addLabel(String label, int x, int y){
        panelHolder[x][y].add(new JLabel(label));
        panelHolder[x][y].repaint();
    }

    public void toNull(){
        this.tuile = null;
        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m].setBackground(Color.WHITE);
                panelHolder[n][m].removeAll();
            }
        }
    }
    
    public void init(TuileDC tuile) {
        this.tuile = tuile;
        
        for(int m = 0; m < dim; m++) {
            for(int n = 0; n < dim; n++) {
                panelHolder[n][m].setBackground(Color.RED);
                panelHolder[n][m].repaint();
            }
        }
        canDrag = true;

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

    private Point lastPoint = null;
    private boolean canDrag = false;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(canDrag){
            lastPoint = e.getLocationOnScreen();
            for (Component c : plateau.getComponents()) {
                if(c != this){
                    plateau.remove(c);
                    plateau.add(c);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(canDrag){
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            for (TuileDCView[] tab : plateau.panelHolder) {
                for (TuileDCView t : tab) {
                    System.out.println(x);
                    System.out.println(y);
                    System.out.println("------");
                    System.out.println(t.getX() + " | " + t.getX()+t.getWidth());
                    System.out.println(t.getY() + " | " + t.getY()+t.getHeight());
                    if(x >= t.getX() && x <= t.getX()+t.getWidth() && y >= t.getY() && y <= t.getY()+t.getHeight()){
                        t.init(this.tuile);
                        t.tuile = this.tuile;
                        this.toNull();
                        return;
                    }
                }
            }
            toNull();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(canDrag){
            Point point = e.getLocationOnScreen();
            int offsetX = point.x - lastPoint.x;
            int offsetY = point.y - lastPoint.y;
            Rectangle bounds = this.getBounds();
            bounds.x += offsetX;
            bounds.y += offsetY;
            // Pour que le carré ne sorte pas à droite
            if(bounds.x + this.getWidth() > plateau.getWidth()){
                bounds.x = plateau.getWidth() - this.getWidth();
            }
            // Pour que le carré ne sorte pas à gauche
            else if(bounds.x < 0){
                bounds.x = 0;
            }
            // Pour que le carré ne sorte pas en bas
            if(bounds.y + this.getHeight() > plateau.getHeight()){
                bounds.y = plateau.getHeight() - this.getHeight();
            }
            // Pour que le carré ne sorte pas en haut
            else if(bounds.y < 0){
                bounds.y = 0;
            }
            this.setBounds(bounds);
            lastPoint = point;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}