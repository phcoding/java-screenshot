package com.ph.screenshot.V5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 截图底板，用于显示全屏截图。
 * Spanel作为其子组件绘制选择框。
 * @author ph
 *
 */
@SuppressWarnings("serial")
public class BPanel extends JPanel {
	
	private BufferedImage bgimg=null;
	
	private int x_select_temp,y_select_temp,
				 w_select_temp,h_select_temp,
				 x_select_calc,y_select_calc,
				 w_select_calc,h_select_calc,
				 x_mouse,y_mouse;
	
	//记录选择区域（不是选择框）的位置及尺寸
	private Rect select=new Rect();
	
	private SPanel panel;
	
	private Color COLOR_BG=new Color(0,0,0,150);
	
	public BPanel() {
		setOpaque(false);
		setLayout(null);
		
		panel = new SPanel(){
			public void MouseClicked(MouseEvent e) {
				mouseClickedSelePanel(e);
			}

			@Override
			public void sizeChanged(SizeChangedEvent se) {
				select.setBounds(panel.getX()+panel.getOffset()/2, panel.getY()+panel.getOffset()/2, se.getWidth(), se.getHeight());
			}
			
		};
		panel.setBounds(0, 0, 0, 0);
		add(panel);
		
		//底板鼠标监听事件，确定选择框初始位置
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//记录鼠标按下的位置
				x_mouse=e.getX();
				y_mouse=e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedBackPanel(e);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//记录拖出的选择区域的尺寸
				w_select_temp=e.getX()-x_mouse;
				h_select_temp=e.getY()-y_mouse;
				
				w_select_calc=w_select_temp>0?w_select_temp:-w_select_temp;
				h_select_calc=h_select_temp>0?h_select_temp:-h_select_temp;
				x_select_calc=w_select_temp>0?x_mouse:x_mouse-w_select_calc;
				y_select_calc=h_select_temp>0?y_mouse:y_mouse-h_select_calc;
				
				panel.setBounds(x_select_calc-panel.getOffset()/2, y_select_calc-panel.getOffset()/2, w_select_calc+panel.getOffset(), h_select_calc+panel.getOffset());
				select.setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//记录按下鼠标时鼠标位置
				x_mouse=e.getXOnScreen();
				y_mouse=e.getYOnScreen();
				//记录按下鼠标时选择区域的位置和尺寸
				x_select_temp=select.getX();
				y_select_temp=select.getY();
				w_select_temp=select.getW();
				h_select_temp=select.getH();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//计算选择区位置
				x_select_calc=x_select_temp+e.getXOnScreen()-x_mouse+panel.getOffset()/2;
				y_select_calc=y_select_temp+e.getYOnScreen()-y_mouse+panel.getOffset()/2;
				
				if(x_select_calc<0){
					x_select_calc = 0;
				}
				if(x_select_calc>BPanel.this.getWidth()-w_select_temp){
					x_select_calc = BPanel.this.getWidth()-w_select_temp;
				}
				if(y_select_calc<0){
					y_select_calc = 0;
				}
				if(y_select_calc>BPanel.this.getHeight()-h_select_temp){
					y_select_calc = BPanel.this.getHeight()-h_select_temp;
				}
				
				
				panel.setLocation(x_select_calc-panel.getOffset()/2, y_select_calc-panel.getOffset()/2);
				select.setLocation(x_select_calc, y_select_calc);
			}
		});
	}
	
	//获取选择位置
	
	public int getSelectedX() {
		return x_select_calc;
	}



	public int getSelectedY() {
		return y_select_calc;
	}



	public int getSelectedW() {
		return w_select_calc;
	}



	public int getSelectedH() {
		return h_select_calc;
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
			g.drawImage(bgimg, 0, 0, bgimg.getWidth(),bgimg.getHeight(),null);
		}
		
		g.setColor(COLOR_BG);
		
		g.fillRect(0, 0, select.getX(), select.getY());
		g.fillRect(select.getX(), 0, select.getW(), select.getY());
		g.fillRect(select.getR(), 0, getWidth()-select.getR(), select.getY());
		
		g.fillRect(0, select.getY(), select.getX(), select.getH());
		g.fillRect(select.getR(), select.getY(), getWidth()-select.getR(), select.getH());
		
		g.fillRect(0, select.getB(), select.getX(), getHeight()-select.getB());
		g.fillRect(select.getX(), select.getB(), select.getW(), getHeight()-select.getB());
		g.fillRect(select.getR(), select.getB(), getWidth()-select.getR(), getHeight()-select.getB());
	}
	
	//选择框鼠标单击事件
	public void mouseClickedSelePanel(MouseEvent e){}
	
	//底板鼠标单击事件
	public void mouseClickedBackPanel(MouseEvent e) {}
}
