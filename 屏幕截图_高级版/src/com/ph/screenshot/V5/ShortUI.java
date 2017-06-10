package com.ph.screenshot.V5;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import com.ph.util.GameUtil;

/**
 * 本版本在上一版本基础上：
 * 1、添加背景蒙板层
 * 2、添加边界限制
 * 3、取消边界线
 * @author ph
 *
 */
@SuppressWarnings("serial")
public class ShortUI extends JFrame {

	private BPanel contentPane;

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
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, GameUtil.getScreenWidth(), GameUtil.getScreenHeight());
		contentPane = new BPanel() {
			@Override
			public void mouseClickedSelePanel(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						JFileChooser jfc=new JFileChooser();
						//添加文件过滤器
						jfc.setFileFilter(new FileFilter(){
							public String getDescription(){
								return "(*.bmp,*.png,*.jpg)";
							}

							@Override
							public boolean accept(File f) {
								String lowerfilename = f.getName().toLowerCase();
								return lowerfilename.endsWith(".bmp")||
										lowerfilename.endsWith(".png")||
										lowerfilename.endsWith(".jpg")||
										f.isDirectory();
							}
						});
						//保存图片到文件
						if(jfc.showSaveDialog(this)==
								JFileChooser.APPROVE_OPTION){
							try {
								String filename = jfc.getSelectedFile().getAbsolutePath();
								if(!(filename.toLowerCase().endsWith(".png")||
										filename.toLowerCase().endsWith(".bmp")||
										filename.toLowerCase().endsWith(".jpg"))){
									filename += ".bmp";
								}
								String formate = filename.substring(filename.lastIndexOf('.')+1).toUpperCase();
								ImageIO.write(GameUtil.getBufferedImage(contentPane.getBgimg().getSubimage(contentPane.getSelectedX(),contentPane.getSelectedY(),contentPane.getSelectedW(),contentPane.getSelectedH())), 
										formate, new File(filename));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					System.exit(0);
				}
			}

			@Override
			public void mouseClickedBackPanel(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						if(contentPane.getSelectedW()==0||contentPane.getSelectedH()==0){
							JOptionPane.showMessageDialog(ShortUI.this, "选择区不可为0", "提醒", JOptionPane.WARNING_MESSAGE);
						}else{
							GameUtil.setClipboardImage(GameUtil.getBufferedImage(contentPane.getBgimg().getSubimage(contentPane.getSelectedX(),contentPane.getSelectedY(),contentPane.getSelectedW(),contentPane.getSelectedH())));
							System.exit(0);
						}
					}
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					System.exit(0);
				}
			}
		};
		contentPane.setBgimg(GameUtil.getFullScreenCapture());
		contentPane.setCursor(Cursor
				.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
