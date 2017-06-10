package com.ph.screenshot.V5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * 选择框绘制层，实现选择框绘制及相关操作。
 * @author ph
 *
 */

@SuppressWarnings("serial")
public class SPanel extends JPanel {
	
	private SizeChangedEvent se=new SizeChangedEvent();
	
	//控制边界线宽与滑块宽	
	private int STROKE=0;	//边界线宽度
	private int SIDE=7;	//控制块边长
	private int OFFSET=SIDE%2==0?SIDE:SIDE+1;	//控制块偏移量
	
	//临时变量
	private int x_select_temp,y_select_temp,
				 w_select_temp,h_select_temp,
				 x_select_calc,y_select_calc,
				 w_select_calc,h_select_calc,
				 x_mouse,y_mouse;
	
	//边界线及控制块颜色
	private Color COLOR_BORDER=Color.RED;
	private Color COLOR_BLOKE =Color.BLUE;
	
	
	//8个调节滑块
	private JPanel block_0;
	private JPanel block_1;
	private JPanel block_2;
	private JPanel block_3;
	private JPanel block_4;
	private JPanel block_5;
	private JPanel block_6;
	private JPanel block_7;
	
	public SPanel() {
		setOpaque(false);
		setLayout(null);
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		
		block_0 = new JPanel();
		block_0.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
		block_0.setLayout(null);
		block_0.setBackground(COLOR_BLOKE);
		block_0.setBounds(0, 0, SIDE, SIDE);
		add(block_0);
		block_0.setLayout(null);
		
		block_1 = new JPanel();
		block_1.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
		block_1.setLayout(null);
		block_1.setBackground(COLOR_BLOKE);
		block_1.setBounds(0, 0, SIDE, SIDE);
		add(block_1);
		
		block_2 = new JPanel();
		block_2.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
		block_2.setLayout(null);
		block_2.setBackground(COLOR_BLOKE);
		block_2.setBounds(0, 0, SIDE, SIDE);
		add(block_2);
		
		block_3 = new JPanel();
		block_3.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		block_3.setLayout(null);
		block_3.setBackground(COLOR_BLOKE);
		block_3.setBounds(0, 0, SIDE, SIDE);
		add(block_3);
		
		block_4 = new JPanel();
		block_4.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		block_4.setLayout(null);
		block_4.setBackground(COLOR_BLOKE);
		block_4.setBounds(0, 0, SIDE, SIDE);
		add(block_4);
		
		block_5 = new JPanel();
		block_5.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
		block_5.setLayout(null);
		block_5.setBackground(COLOR_BLOKE);
		block_5.setBounds(0, 0, SIDE, SIDE);
		add(block_5);
		
		block_6 = new JPanel();
		block_6.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
		block_6.setLayout(null);
		block_6.setBackground(COLOR_BLOKE);
		block_6.setBounds(0, 0, SIDE, SIDE);
		add(block_6);
		
		block_7 = new JPanel();
		block_7.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
		block_7.setLayout(null);
		block_7.setBackground(COLOR_BLOKE);
		block_7.setBounds(0, 0, SIDE, SIDE);
		add(block_7);
		
		//选择框鼠标事件
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MouseClicked(e);
			}
		});
		
		//滑块鼠标事件
		//公共事件
		MouseAdapter ma=new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//记录鼠标在屏幕中的位置
				x_mouse=e.getXOnScreen();
				y_mouse=e.getYOnScreen();
				//记录选择框位置及尺寸
				x_select_temp=getX();
				y_select_temp=getY();
				w_select_temp=getWidth();
				h_select_temp=getHeight();
			}
		};
		block_0.addMouseListener(ma);
		block_1.addMouseListener(ma);
		block_2.addMouseListener(ma);
		block_3.addMouseListener(ma);
		block_4.addMouseListener(ma);
		block_5.addMouseListener(ma);
		block_6.addMouseListener(ma);
		block_7.addMouseListener(ma);
		//滑块独立事件
		//NW
		block_0.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp+x_mouse-e.getXOnScreen();
				h_select_calc=h_select_temp+y_mouse-e.getYOnScreen();
				x_select_calc=x_select_temp-w_select_calc+w_select_temp;
				y_select_calc=y_select_temp-h_select_calc+h_select_temp;
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp+w_select_temp-OFFSET;
				}
				if(h_select_calc<OFFSET){
					h_select_calc=2*OFFSET-h_select_calc;
					y_select_calc=y_select_temp+h_select_temp-OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//N
		block_1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc = w_select_temp;
				h_select_calc = h_select_temp+y_mouse-e.getYOnScreen();
				x_select_calc = x_select_temp;
				y_select_calc = y_select_temp-h_select_calc+h_select_temp;
				
				if(h_select_calc < OFFSET){
					h_select_calc = 2*OFFSET-h_select_calc;
					y_select_calc = y_select_temp+h_select_temp-OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//NE
		block_2.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				x_select_calc=x_select_temp;
				y_select_calc=y_select_temp+e.getYOnScreen()-y_mouse;
				w_select_calc=w_select_temp+e.getXOnScreen()-x_mouse;
				h_select_calc=h_select_temp+y_mouse-e.getYOnScreen();
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp-w_select_calc+OFFSET;
				}
				if(h_select_calc<OFFSET){
					h_select_calc=2*OFFSET-h_select_calc;
					y_select_calc=y_select_temp+h_select_temp-OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//W
		block_3.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp+x_mouse-e.getXOnScreen();
				h_select_calc=h_select_temp;
				x_select_calc=x_select_temp-w_select_calc+w_select_temp;
				y_select_calc=y_select_temp;
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp+w_select_temp-OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//E
		block_4.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp+e.getXOnScreen()-x_mouse;
				h_select_calc=h_select_temp;
				x_select_calc=x_select_temp;
				y_select_calc=y_select_temp;
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp-w_select_calc+OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//SW
		block_5.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp+x_mouse-e.getXOnScreen();
				h_select_calc=h_select_temp+e.getYOnScreen()-y_mouse;
				x_select_calc=x_select_temp-w_select_calc+w_select_temp;
				y_select_calc=y_select_temp;
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp+w_select_temp-OFFSET;
				}
				if(h_select_calc<OFFSET){
					h_select_calc=2*OFFSET-h_select_calc;
					y_select_calc=y_select_temp-h_select_calc+OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//S
		block_6.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp;
				h_select_calc=h_select_temp+e.getYOnScreen()-y_mouse;
				x_select_calc=x_select_temp;
				y_select_calc=y_select_temp;
				
				if(h_select_calc<OFFSET){
					h_select_calc=2*OFFSET-h_select_calc;
					y_select_calc=y_select_temp-h_select_calc+OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
		//SE
		block_7.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				w_select_calc=w_select_temp+e.getXOnScreen()-x_mouse;
				h_select_calc=h_select_temp+e.getYOnScreen()-y_mouse;
				x_select_calc=x_select_temp;
				y_select_calc=y_select_temp;
				
				if(w_select_calc<OFFSET){
					w_select_calc=2*OFFSET-w_select_calc;
					x_select_calc=x_select_temp-w_select_calc+OFFSET;
				}
				if(h_select_calc<OFFSET){
					h_select_calc=2*OFFSET-h_select_calc;
					y_select_calc=y_select_temp-h_select_calc+OFFSET;
				}
				setBounds(x_select_calc, y_select_calc, w_select_calc, h_select_calc);
				se.setSize(w_select_calc-OFFSET, h_select_calc-OFFSET);
				sizeChanged(se);
			}
		});
	}
	
	//画选择框边界线
	protected void paintBorder(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(STROKE));
		
		g2d.setColor(COLOR_BORDER);
		g2d.drawRect(OFFSET/2, OFFSET/2, getWidth()-OFFSET, getHeight()-OFFSET);
	}

	//设置选择框滑块位置
	protected void paintComponent(Graphics g) {
		block_0.setLocation(0,0);
		block_1.setLocation(getWidth()/2-OFFSET/2,0);
		block_2.setLocation(getWidth()-OFFSET+1, 0);
		block_3.setLocation(0,getHeight()/2-OFFSET/2);
		block_4.setLocation(getWidth()-OFFSET+1, getHeight()/2-OFFSET/2);
		block_5.setLocation(0, getHeight()-OFFSET+1);
		block_6.setLocation(getWidth()/2-OFFSET/2,  getHeight()-OFFSET+1);
		block_7.setLocation(getWidth()-OFFSET+1,  getHeight()-OFFSET+1);
	}
	
	//设置鼠标事件
	public void MouseClicked(MouseEvent e){System.out.println("选择框被单击");}

	//设置两个颜色
	public Color getCOLOR_BORDER() {
		return COLOR_BORDER;
	}

	public void setCOLOR_BORDER(Color cOLOR_BORDER) {
		COLOR_BORDER = cOLOR_BORDER;
	}

	public Color getCOLOR_BLOKE() {
		return COLOR_BLOKE;
	}

	public void setCOLOR_BLOKE(Color cOLOR_BLOKE) {
		COLOR_BLOKE = cOLOR_BLOKE;
	}

	//获取两宽
	public int getStroke() {
		return STROKE;
	}

	public int getSide() {
		return SIDE;
	}

	//获取偏移量
	public int getOffset() {
		return OFFSET;
	}
	
	public void sizeChanged(SizeChangedEvent se){}
	
}
