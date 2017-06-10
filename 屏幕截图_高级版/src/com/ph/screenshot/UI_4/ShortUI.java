package com.ph.screenshot.UI_4;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import com.ph.util.GameUtil;

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
			public void MouseClicked_selectPanel(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						JFileChooser jfc=new JFileChooser();
						if(jfc.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
							try {
								ImageIO.write(GameUtil.getBufferedImage(contentPane.getBgimg().getSubimage(contentPane.getSelectedX(),contentPane.getSelectedY(),contentPane.getSelectedW(),contentPane.getSelectedH())), "PNG", jfc.getSelectedFile());
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
			public void MouseClicked_this(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						GameUtil.setClipboardImage(GameUtil.getBufferedImage(contentPane.getBgimg().getSubimage(contentPane.getSelectedX(),contentPane.getSelectedY(),contentPane.getSelectedW(),contentPane.getSelectedH())));
						System.exit(0);
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
