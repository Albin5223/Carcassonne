package src.main.java.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class ButtonGraphic extends JButton{
	
	public ButtonGraphic(String string) {
		this.setText(string);
		this.setFont(new Font("Blackadder ITC",Font.BOLD,25));
		this.setPreferredSize(new Dimension(250,50));
		this.setForeground(new Color(255,255,255));
		setFocusPainted(false);
		setBorderPainted(false);
		setBackground(new Color(0,153,0));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void  paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), 25, 25);
	}

}
