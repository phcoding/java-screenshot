package com.ph.screenshot.UI_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BPanel extends JPanel {
	
	protected void paintBorder(Graphics g) {
		
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.setColor(new Color(0,0,0,80));
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

}
