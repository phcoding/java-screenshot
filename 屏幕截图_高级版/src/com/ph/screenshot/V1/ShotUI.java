package com.ph.screenshot.V1;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ShotUI extends JFrame {

	private SPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShotUI frame = new ShotUI();
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
	int x=0,y=0,X=0,Y=0,flag=0,w,h;
	public ShotUI() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setBackground(new Color(0,0,0,40));
		contentPane = new SPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(flag==0){
					setBounds(getX(), getY(), w+e.getXOnScreen()-X, getHeight());
				}
				else if(flag==1){
					setBounds(getX(), getY(), getWidth(), h+e.getYOnScreen()-Y);
				}
				else if(flag==2){
					setBounds(getX(), getY(), w+e.getXOnScreen()-X, h+e.getYOnScreen()-Y);
				}
				else{
					setLocation(e.getXOnScreen()-x,e.getYOnScreen()-y);
				}
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getX()>=getWidth()-4*contentPane.stroke&&!(e.getY()>=getHeight()-4*contentPane.stroke)){
					flag=0;
				}
				if(!(e.getX()>=getWidth()-4*contentPane.stroke)&&(e.getY()>=getHeight()-4*contentPane.stroke)){
					flag=1;
				}
				if(e.getX()>=getWidth()-4*contentPane.stroke&&(e.getY()>=getHeight()-4*contentPane.stroke)){
					flag=2;
				}
				if(!(e.getX()>=getWidth()-4*contentPane.stroke)&&!(e.getY()>=getHeight()-4*contentPane.stroke)){
					flag=3;
				}
				
				x=e.getX();
				y=e.getY();
				w=getWidth();
				h=getHeight();
				
				X=e.getXOnScreen();
				Y=e.getYOnScreen();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getClickCount()==2){
						ShotUI.this.setVisible(false);
						try {
							ImageIO.write(new Robot().createScreenCapture(new Rectangle(getX()+contentPane.stroke+1, getY()+contentPane.stroke+1, getWidth()-2*contentPane.stroke-2, getHeight()-2*contentPane.stroke-2)), "JPEG", new File("E:/s.jpg"));
						} catch (IOException | AWTException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ShotUI.this.setVisible(true);
					}
				}
				if(e.getButton()==MouseEvent.BUTTON3){
					ShotUI.this.dispose();
				}
			}
		});
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public ShotUI(int x,int y,int w,int h) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, w, h);
		setBackground(new Color(0,0,0,1));
		setVisible(true);
		contentPane = new SPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
