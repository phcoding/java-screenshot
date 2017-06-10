package com.ph.screenshot.V3;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.ph.util.GameUtil;

@SuppressWarnings("serial")
public class ShortUI extends JFrame {

	private BPanel contentPane;
	private SPanel panel;
	
	int x,y,w,h,X,Y,W,H,x1,y1;
	

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
	public ShortUI() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, GameUtil.getScreenWidth(), GameUtil.getScreenHeight());
		
		contentPane = new BPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new SPanel();
		panel.setBounds(0, 0, 0, 0);
		contentPane.add(panel);
		//选择框鼠标事件
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3){
					System.exit(0);
				}
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getClickCount()==2){
						JFileChooser jfc=new JFileChooser();
						if(jfc.showSaveDialog(ShortUI.this)==JFileChooser.APPROVE_OPTION){
							try {
								ImageIO.write(GameUtil.getBufferedImage(contentPane.getBgImage().getSubimage(X, Y, W, H)), "GIF", jfc.getSelectedFile());
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(ShortUI.this, "截图保存失败!");
							}
						}
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getXOnScreen();
				y=e.getYOnScreen();
				x1=panel.getX();
				y1=panel.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				panel.setLocation(x1+e.getXOnScreen()-x, y1+e.getYOnScreen()-y);
				X=x1+e.getXOnScreen()-x+panel.side/2;
				Y=y1+e.getYOnScreen()-y+panel.side/2;
			}
		});
		
		//底板鼠标事件
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x=e.getXOnScreen();
				y=e.getYOnScreen();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3){
					System.exit(0);
				}
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getClickCount()==2){
						GameUtil.setClipboardImage(GameUtil.getBufferedImage(contentPane.getBgImage().getSubimage(X, Y, W, H)));
						System.exit(0);
					}
				}
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				w=e.getX()-x;
				h=e.getY()-y;
				
				W=w>0?w:-w;
				H=h>0?h:-h;
				X=w>0?x:x-W;
				Y=h>0?y:y-H;
				
				panel.setBounds(X-(panel.side%2==0?panel.side:panel.side+1)/2, Y-(panel.side%2==0?panel.side:panel.side+1)/2, W+(panel.side%2==0?panel.side:panel.side+1), H+(panel.side%2==0?panel.side:panel.side+1));
			}
		});
	}

}
