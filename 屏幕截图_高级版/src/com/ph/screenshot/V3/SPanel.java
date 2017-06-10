package com.ph.screenshot.V3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SPanel extends JPanel {
	
	public int stroke=1;
	public int side=5;
	
	public Color COLOR_BORDER=Color.blue;
	public Color COLOR_BLOKE =Color.blue;
	
	int x,y,w,h,X,Y,W,H,x1,y1;
	
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	
	public SPanel() {
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		setOpaque(false);
		setLayout(null);
		
		panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
		panel.setLayout(null);
		panel.setBackground(COLOR_BLOKE);
		panel.setBounds(0, 0, side, side);
		add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
		panel_1.setLayout(null);
		panel_1.setBackground(COLOR_BLOKE);
		panel_1.setBounds(0, 0, side, side);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
		panel_2.setLayout(null);
		panel_2.setBackground(COLOR_BLOKE);
		panel_2.setBounds(0, 0, side, side);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		panel_3.setLayout(null);
		panel_3.setBackground(COLOR_BLOKE);
		panel_3.setBounds(0, 0, side, side);
		add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		panel_4.setLayout(null);
		panel_4.setBackground(COLOR_BLOKE);
		panel_4.setBounds(0, 0, side, side);
		add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
		panel_5.setLayout(null);
		panel_5.setBackground(COLOR_BLOKE);
		panel_5.setBounds(0, 0, side, side);
		add(panel_5);
		
		panel_6 = new JPanel();
		panel_6.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
		panel_6.setLayout(null);
		panel_6.setBackground(COLOR_BLOKE);
		panel_6.setBounds(0, 0, side, side);
		add(panel_6);
		
		panel_7 = new JPanel();
		panel_7.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
		panel_7.setLayout(null);
		panel_7.setBackground(COLOR_BLOKE);
		panel_7.setBounds(0, 0, side, side);
		add(panel_7);
		
		//鼠标事件
		//公共事件
		MouseAdapter ma=new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x=e.getXOnScreen();
				y=e.getYOnScreen();
				w=getWidth();
				h=getHeight();
				x1=getX();
				y1=getY();
			}
		};
		panel.addMouseListener(ma);
		panel_1.addMouseListener(ma);
		panel_2.addMouseListener(ma);
		panel_3.addMouseListener(ma);
		panel_4.addMouseListener(ma);
		panel_5.addMouseListener(ma);
		panel_6.addMouseListener(ma);
		panel_7.addMouseListener(ma);
		//独立事件
		//NW
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w+x-e.getXOnScreen();
				H=h+y-e.getYOnScreen();
				X=x1-W+w;
				Y=y1-H+h;
				
				if(W<0){
					W=-W;
					X=x1+w-(side%2==0?side:side+1);
				}
				if(H<0){
					H=-H;
					Y=y1+h-(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//N
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w;
				H=h+y-e.getYOnScreen();
				X=x1;
				Y=y1-H+h;
				
				if(H<0){
					H=-H;
					Y=y1+h-(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//NE
		panel_2.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				X=x1;
				Y=y1+e.getYOnScreen()-y;
				W=w+e.getXOnScreen()-x;
				H=h+y-e.getYOnScreen();
				
				if(W<0){
					W=-W;
					X=x1-W+(side%2==0?side:side+1);
				}
				if(H<0){
					H=-H;
					Y=y1+h-(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//W
		panel_3.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w+x-e.getXOnScreen();
				H=h;
				X=x1-W+w;
				Y=y1;
				
				if(W<0){
					W=-W;
					X=x1+w-(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//E
		panel_4.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w+e.getXOnScreen()-x;
				H=h;
				X=x1;
				Y=y1;
				
				if(W<0){
					W=-W;
					X=x1-W+(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//SW
		panel_5.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w+x-e.getXOnScreen();
				H=h+e.getYOnScreen()-y;
				X=x1-W+w;
				Y=y1;
				
				if(W<0){
					W=-W;
					X=x1+w-(side%2==0?side:side+1);
				}
				if(H<0){
					H=-H;
					Y=y1-H+(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//S
		panel_6.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w;
				H=h+e.getYOnScreen()-y;
				X=x1;
				Y=y1;
				
				if(H<0){
					H=-H;
					Y=y1-H+(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
		//SE
		panel_7.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				W=w+e.getXOnScreen()-x;
				H=h+e.getYOnScreen()-y;
				X=x1;
				Y=y1;
				
				if(W<0){
					W=-W;
					X=x1-W+(side%2==0?side:side+1);
				}
				if(H<0){
					H=-H;
					Y=y1-H+(side%2==0?side:side+1);
				}
				setBounds(X, Y, W, H);
			}
		});
	}


	@Override
	protected void paintBorder(Graphics g) {
		
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(stroke));
		
		g2d.setColor(COLOR_BORDER);
		g2d.drawRect(side/2, side/2, getWidth()-side-1, getHeight()-side-1);
	}

	protected void paintComponent(Graphics g) {
			panel.setLocation(0,0);
			panel_1.setLocation(getWidth()/2-(side%2==0?side/2:(side-1)/2),0);
			panel_2.setLocation(getWidth()-(side%2==0?side:side+1), 0);
			panel_3.setLocation(0,getHeight()/2-(side%2==0?side/2:(side+1)/2));
			panel_4.setLocation(getWidth()-(side%2==0?side:side+1), getHeight()/2-(side%2==0?side/2:(side+1)/2));
			panel_5.setLocation(0, getHeight()-(side%2==0?side:side+1));
			panel_6.setLocation(getWidth()/2-(side%2==0?side/2:(side-1)/2),  getHeight()-(side%2==0?side:side+1));
			panel_7.setLocation(getWidth()-(side%2==0?side:side+1),  getHeight()-(side%2==0?side:side+1));
	}
	
}
