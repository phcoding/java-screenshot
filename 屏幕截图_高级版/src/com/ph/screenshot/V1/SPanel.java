package com.ph.screenshot.V1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SPanel extends JPanel {
	
	public int stroke=1;


	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(stroke));
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.setColor(Color.RED);
		g2d.drawRect(stroke/2, stroke/2, getWidth()-stroke-1, getHeight()-stroke-1);
		
		g2d.drawRect(stroke/2, stroke/2, 4*stroke, 4*stroke);
		g2d.drawRect(stroke/2+getWidth()-5*stroke-1, stroke/2, 4*stroke, 4*stroke);
		g2d.drawRect(stroke/2, stroke/2+getHeight()-5*stroke-1, 4*stroke, 4*stroke);
		g2d.drawRect(stroke/2+getWidth()-5*stroke-1,stroke/2+getHeight()-5*stroke-1, 4*stroke, 4*stroke);
		
	}
}
