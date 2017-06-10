package com.ph.screenshot.V6;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

/**
 * 选择框绘制层，实现选择框绘制及相关操作。
 * @author ph
 *
 */

public class SPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//控制边界线宽与滑块宽	
	private int STROKE=2;	//边界线宽度
	private int SIDE=8;	//控制块边长
	private int OFFSET=SIDE%2==0?SIDE:SIDE+1;	//控制块偏移量
	
	//边界线及控制块颜色
	private Color COLOR_BORDER=Color.BLUE;
	private Color COLOR_BLOKE =Color.BLUE;
	
	//临时变量
	private Posi mouse_temp;		//鼠标位置临时变量
	private Rect select_temp;		//选择框参数临时变量
	private Rect select_calc;		//选择框理论计算变量
	
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
		
		mouse_temp = new Posi();
		select_temp = new Rect();
		select_calc = new Rect();
		
		setOpaque(false);
		setLayout(null);
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		
		block_0 = new JPanel();
		block_0.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
		block_0.setLayout(null);
		block_0.setBackground(COLOR_BLOKE);
		block_0.setBounds(0, 0, SIDE, SIDE);
		add(block_0);
		
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
				SPanel.this.mouseClicked(e);
			}
		});
		
		//滑块鼠标事件
		//公共事件
		MouseAdapter ma=new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//记录鼠标在屏幕中的位置
				mouse_temp.setLocation(e.getLocationOnScreen());
				//记录选择框位置及尺寸
				select_temp.setBounds(getBounds());
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
				select_calc.setBounds(
						select_temp.getX()-mouse_temp.getX()+e.getXOnScreen(), 
						select_temp.getY()-mouse_temp.getY()+e.getYOnScreen(), 
						select_temp.getWidth()+mouse_temp.getX()-e.getXOnScreen(), 
						select_temp.getHeight()+mouse_temp.getY()-e.getYOnScreen());
				
				if(select_calc.getWidth()<OFFSET){
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()+select_temp.getWidth()-OFFSET);
				}
				if(select_calc.getHeight()<OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()+select_temp.getHeight()-OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//N
		block_1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {			
				select_calc.setBounds(
						select_temp.getX(), 
						select_temp.getY()-mouse_temp.getY()+e.getYOnScreen(), 
						select_temp.getWidth(), 
						select_temp.getHeight()+mouse_temp.getY()-e.getYOnScreen());
				
				if(select_calc.getHeight() < OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()+select_temp.getHeight()-OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//NE
		block_2.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				select_calc.setBounds(
						select_temp.getX(), 
						select_temp.getY()+e.getYOnScreen()-mouse_temp.getY(), 
						select_temp.getWidth()+e.getXOnScreen()-mouse_temp.getX(), 
						select_temp.getHeight()+mouse_temp.getY()-e.getYOnScreen());
				
				if(select_calc.getWidth()<OFFSET){					
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()-select_calc.getWidth()+OFFSET);
				}
				if(select_calc.getHeight()<OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()+select_temp.getHeight()-OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//W
		block_3.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				select_calc.setBounds(
						select_temp.getX()-mouse_temp.getX()+e.getXOnScreen(),
						select_temp.getY(), 
						select_temp.getWidth()+mouse_temp.getX()-e.getXOnScreen(), 
						select_temp.getHeight());
				
				if(select_calc.getWidth()<OFFSET){
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()+select_temp.getWidth()-OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//E
		block_4.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				select_calc.setBounds(
						select_temp.getX(), 
						select_temp.getY(), 
						select_temp.getWidth()+e.getXOnScreen()-mouse_temp.getX(), 
						select_temp.getHeight());
				
				if(select_calc.getWidth()<OFFSET){
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()-select_calc.getWidth()+OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//SW
		block_5.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {			
				select_calc.setBounds(select_temp.getX()-mouse_temp.getX()+e.getXOnScreen(), 
						select_temp.getY(), 
						select_temp.getWidth()+mouse_temp.getX()-e.getXOnScreen(), 
						select_temp.getHeight()+e.getYOnScreen()-mouse_temp.getY());
				
				if(select_calc.getWidth()<OFFSET){
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()+select_temp.getWidth()-OFFSET);
				}
				if(select_calc.getHeight()<OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()-select_calc.getHeight()+OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//S
		block_6.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				select_calc.setBounds(select_temp.getX(), 
						select_temp.getY(), 
						select_temp.getWidth(), 
						select_temp.getHeight()+e.getYOnScreen()-mouse_temp.getY());
				
				if(select_calc.getHeight()<OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()-select_calc.getHeight()+OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
		//SE
		block_7.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				select_calc.setBounds(
						select_temp.getX(), 
						select_temp.getY(), 
						select_temp.getWidth()+e.getXOnScreen()-mouse_temp.getX(), 
						select_temp.getHeight()+e.getYOnScreen()-mouse_temp.getY());
				
				if(select_calc.getWidth()<OFFSET){
					select_calc.setWidth(2*OFFSET-select_calc.getWidth());
					select_calc.setX(select_temp.getX()-select_calc.getWidth()+OFFSET);
				}
				if(select_calc.getHeight()<OFFSET){
					select_calc.setHeight(2*OFFSET-select_calc.getHeight());
					select_calc.setY(select_temp.getY()-select_calc.getHeight()+OFFSET);
				}
				setBounds(select_calc.getBounds());
				sizeChanged(select_calc.getSize(-OFFSET));
			}
		});
	}
	
	public SPanel(int stroke, int side, Color border, Color block){
		this();
		this.STROKE = stroke;
		this.SIDE = side;
		this.COLOR_BORDER = border;
		this.COLOR_BLOKE = block;
	}
	
//	画选择框边界线
	protected void paintBorder(Graphics g) {
//		Graphics2D g2d=(Graphics2D)g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setStroke(new BasicStroke(STROKE));
//		
//		g2d.setColor(COLOR_BORDER);
//		g2d.drawRect(OFFSET/2, OFFSET/2, getWidth()-OFFSET, getHeight()-OFFSET);

		((Graphics2D) g).setStroke(new BasicStroke(STROKE));
		g.setColor(COLOR_BORDER);
		g.drawRect(OFFSET/2, OFFSET/2, getWidth()-OFFSET, getHeight()-OFFSET);
	}

	//设置选择框滑块位置
	protected void paintComponent(Graphics g) {
		block_0.setLocation(0,0);
//		block_0.setBounds(0, 0, OFFSET, OFFSET);
		block_1.setLocation((getWidth()-OFFSET)/2,0);
//		block_1.setBounds(OFFSET,0, getWidth()-2*OFFSET, OFFSET);
		block_2.setLocation(getWidth()-OFFSET, 0);
		block_3.setLocation(0,(getHeight()-OFFSET)/2);
//		block_3.setBounds(0,OFFSET, OFFSET, getHeight()-2*OFFSET);
		block_4.setLocation(getWidth()-OFFSET, (getHeight()-OFFSET)/2);
//		block_4.setBounds(getWidth()-OFFSET, OFFSET, OFFSET, getHeight()-2*OFFSET);
		block_5.setLocation(0, getHeight()-OFFSET);
		block_6.setLocation((getWidth()-OFFSET)/2,  getHeight()-OFFSET);
//		block_6.setBounds(OFFSET, getHeight()-OFFSET, getWidth()-2*OFFSET, OFFSET);
		block_7.setLocation(getWidth()-OFFSET,  getHeight()-OFFSET);
	}

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
	
	//设置尺寸改变事件
	public void sizeChanged(Dimension size){System.out.println("事件:选择框尺寸被改变");}
	
	//设置鼠标事件
	public void mouseClicked(MouseEvent e){System.out.println("事件:选择框被单击");}
}
