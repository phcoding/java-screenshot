package com.ph.screenshot.UI_5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BPanel extends JPanel {
	
	private BufferedImage bgimg=null;
	
	private int x,y,w,h,X,Y,W,H,x1,y1;
	
	private Rect r=new Rect();
	
	private SPanel panel;
	
	private Color COLOR_BG=new Color(0,0,0,100);
	
	public BPanel() {
		setOpaque(false);
		setLayout(null);
		
		panel = new SPanel(){
			public void MouseClicked(MouseEvent e) {
				MouseClicked_selectPanel(e);
			}

			@Override
			public void sizeChanged(SizeChangedEvent se) {
				r.setBounds(panel.getX()+panel.getDev()/2, panel.getY()+panel.getDev()/2, se.getWidth(), se.getHeight());
			}
			
		};
		panel.setBounds(0, 0, 0, 0);
		add(panel);
		
		//鼠标监听事件
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getX();
				y=e.getY();
			}		
			@Override
			public void mouseClicked(MouseEvent e) {
				MouseClicked_this(e);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				w=e.getX()-x;
				h=e.getY()-y;
				
				W=w>0?w:-w;
				H=h>0?h:-h;
				X=w>0?x:x-W;
				Y=h>0?y:y-H;
				
				panel.setBounds(X-panel.getDev()/2, Y-panel.getDev()/2, W+panel.getDev(), H+panel.getDev());
				r.setBounds(X, Y, W, H);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x1=e.getXOnScreen();
				y1=e.getYOnScreen();
				x=panel.getX();
				y=panel.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				X=x+e.getXOnScreen()-x1+panel.getDev()/2;
				Y=y+e.getYOnScreen()-y1+panel.getDev()/2;
				
				panel.setLocation(X-panel.getDev()/2, Y-panel.getDev()/2);
				r.setLocation(X, Y);
			}
		});
	}
	
	//获取选择位置
	
	public int getSelectedX() {
		return X;
	}



	public int getSelectedY() {
		return Y;
	}



	public int getSelectedW() {
		return W;
	}



	public int getSelectedH() {
		return H;
	}



	//获取背景图
	public BufferedImage getBgimg() {
		return bgimg;
	}
	//设置背景图
	public void setBgimg(BufferedImage bgimg) {
		this.bgimg = bgimg;
	}

	//画背景
	public void paintComponent(Graphics g){
		if(bgimg!=null){
			g.drawImage(bgimg, 0, 0, getWidth(),getHeight(),null);
		}
		
		g.setColor(COLOR_BG);
		
		g.fillRect(0, 0, r.getX(), r.getY());
		g.fillRect(r.getX(), 0, r.getW(), r.getY());
		g.fillRect(r.getR(), 0, getWidth()-r.getR(), r.getY());
		
		g.fillRect(0, r.getY(), r.getX(), r.getH());
		g.fillRect(r.getR(), r.getY(), getWidth()-r.getR(), r.getH());
		
		g.fillRect(0, r.getB(), r.getX(), getHeight()-r.getB());
		g.fillRect(r.getX(), r.getB(), r.getW(), getHeight()-r.getB());
		g.fillRect(r.getR(), r.getB(), getWidth()-r.getR(), getHeight()-r.getB());
	}
	
	//选择框鼠标单击事件
	public void MouseClicked_selectPanel(MouseEvent e){}
	
	//底板鼠标单击事件
	public void MouseClicked_this(MouseEvent e) {}
}
