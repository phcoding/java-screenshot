package com.ph.screenshot.V2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.ph.util.GameUtil;

@SuppressWarnings("serial")
public class ShortUI extends JFrame {

	private BPanel contentPane;
	private SPanel panel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShortUI frame = new ShortUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int x,y,w,h;
	JFileChooser jfc=new JFileChooser();
	public ShortUI() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, GameUtil.getScreenWidth(), GameUtil.getScreenHeight());
		setBackground(new Color(0,0,0,0));
		
		contentPane = new BPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new SPanel();
		panel.setOpaque(false);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		panel.setBounds(76, 83, 273, 181);
		contentPane.add(panel);
		
		
		//底板鼠标事件
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int X,Y;
				w=e.getX()>x?e.getX()-x:x-e.getX();
				h=e.getY()>y?e.getY()-y:y-e.getY();
				X=e.getX()>x?x:x-w;
				Y=e.getY()>y?y:y-h;
				
				panel.setBounds(X-panel.side/2, Y-panel.side/2, w+panel.side,h+panel.side);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getX();
				y=e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//复制截图到剪切板并退出
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getClickCount()==2){
						setVisible(false);
						GameUtil.setClipboardImage(GameUtil.getScreenRectCapture(panel.getX()+panel.side/2, panel.getY()+panel.side/2, panel.getWidth()-panel.side, panel.getHeight()-panel.side));
						setVisible(true);
					}
				}
				else if(e.getButton()==MouseEvent.BUTTON3){
					dispose();
					System.exit(0);
				}
			}
		});
		
		//选择框鼠标事件
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				panel.setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getX();
				y=e.getY();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getClickCount()==2){
						//截图保存到文件
						setVisible(false);
						BufferedImage buf=GameUtil.getScreenRectCapture(panel.getX()+panel.side/2, panel.getY()+panel.side/2, panel.getWidth()-panel.side, panel.getHeight()-panel.side);
						setVisible(true);
						if(jfc.showSaveDialog(ShortUI.this)==JFileChooser.APPROVE_OPTION){
							try {
								ImageIO.write(buf,"JPEG", jfc.getSelectedFile());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
					}
				}
				else if(e.getButton()==MouseEvent.BUTTON3){
					dispose();
					System.exit(0);
				}
			}
		});
	}
}
