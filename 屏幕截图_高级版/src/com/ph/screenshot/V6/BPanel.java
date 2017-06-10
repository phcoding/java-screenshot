package com.ph.screenshot.V6;

import java.awt.Color;
import java.awt.Dimension;
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
	
	//记录选择区域（不是选择框）的位置及尺寸
	private Rect select_temp = new Rect();
	private Rect select_calc = new Rect();
	private Posi mouse_temp = new Posi();
	
	private SPanel spanel;
	
	private Color COLOR_BG=new Color(0,0,0,150);
	
	public BPanel() {
		setOpaque(false);
		setLayout(null);
		
		spanel = new SPanel(){
			public void mouseClicked(MouseEvent e) {
				mouseClickedSelePanel(e);
			}

			@Override
			public void sizeChanged(Dimension size) {
				select_temp.setBounds(spanel.getX()+spanel.getOffset()/2, 
						spanel.getY()+spanel.getOffset()/2, size.width, size.height);
				select_calc.setBounds(select_temp);
			}
			
		};
		spanel.setBounds(0, 0, 0, 0);
		add(spanel);
		
		//底板鼠标监听事件，确定选择区初始位置
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//记录鼠标按下的位置
				mouse_temp.setLocation(e.getX(), e.getY());
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
				select_temp.setSize(e.getX()-mouse_temp.getX(), e.getY()-mouse_temp.getY());			
				select_calc.setSize(Math.abs(select_temp.getWidth()), Math.abs(select_temp.getHeight()));
				select_calc.setLocation(
						select_temp.getWidth()>0?mouse_temp.getX():mouse_temp.getX()-select_calc.getWidth(), 
						select_temp.getHeight()>0?mouse_temp.getY():mouse_temp.getY()-select_calc.getHeight());	
				spanel.setBounds(select_calc.getMarginBounds(spanel.getOffset()));
			}
		});
		spanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//记录按下鼠标时鼠标位置
				mouse_temp.setLocation(e.getLocationOnScreen());
				//记录选择区初始位置
				select_temp.setBounds(select_calc);
			}
		});
		spanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//计算选择区位置
				select_calc.setLocation(
						select_temp.getX()+e.getXOnScreen()-mouse_temp.getX(), 
						select_temp.getY()+e.getYOnScreen()-mouse_temp.getY());
				
				if(select_calc.getX()<0){
					select_calc.setX(0);
				}
				if(select_calc.getX()>BPanel.this.getWidth()-select_temp.getWidth()+1){
					select_calc.setX(BPanel.this.getWidth()-select_temp.getWidth()+1);
				}
				if(select_calc.getY()<0){
					select_calc.setY(0);
				}
				if(select_calc.getY()>BPanel.this.getHeight()-select_temp.getHeight()+1){
					select_calc.setY(BPanel.this.getHeight()-select_temp.getHeight()+1);
				}
				spanel.setLocation(select_calc.getMarginLocation(spanel.getOffset()));
			}
		});
	}
	
	//获取选择位置
	
	public int getSelectedX() {
		return select_calc.getX();
	}



	public int getSelectedY() {
		return select_calc.getY();
	}



	public int getSelectedW() {
		return select_calc.getWidth();
	}



	public int getSelectedH() {
		return select_calc.getHeight();
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
		g.fillRect(0, 0, select_calc.getX(), select_calc.getY());
		g.fillRect(select_calc.getX(), 0, select_calc.getWidth(), select_calc.getY());
		g.fillRect(select_calc.getRight(), 0, getWidth()-select_calc.getRight(), select_calc.getY());
		
		g.fillRect(0, select_calc.getY(), select_calc.getX(), select_calc.getHeight());
		g.fillRect(select_calc.getRight(), select_calc.getY(), getWidth()-select_calc.getRight(), select_calc.getHeight());
		
		g.fillRect(0, select_calc.getBottom(), select_calc.getX(), getHeight()-select_calc.getBottom());
		g.fillRect(select_calc.getX(), select_calc.getBottom(), select_calc.getWidth(), getHeight()-select_calc.getBottom());
		g.fillRect(select_calc.getRight(), select_calc.getBottom(), getWidth()-select_calc.getRight(), getHeight()-select_calc.getBottom());
	}
	
	//选择框鼠标单击事件
	public void mouseClickedSelePanel(MouseEvent e){}
	
	//底板鼠标单击事件
	public void mouseClickedBackPanel(MouseEvent e) {}
}
