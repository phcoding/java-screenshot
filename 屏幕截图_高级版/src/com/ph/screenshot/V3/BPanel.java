package com.ph.screenshot.V3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.ph.util.GameUtil;

@SuppressWarnings("serial")
public class BPanel extends JPanel {
	
	public int x,y,w,h,X=0,Y=0,W=0,H=0;
	
	public BufferedImage bg=null;
	
	public BPanel() {
		setLayout(null);
		bg=GameUtil.getFullScreenCapture();
	}
	
	protected void paintBorder(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.drawImage(bg, 0, 0, null);
	}
	
	public BufferedImage getBgImage(){
		return bg;
	}
}
