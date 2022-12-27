package src.main.java.gui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public interface Deplacements extends KeyListener {

    @Override
    public void keyTyped(KeyEvent e);

    @Override
    public default void keyPressed(KeyEvent e) {}

    @Override
    public default void keyReleased(KeyEvent e) {}
    
}
