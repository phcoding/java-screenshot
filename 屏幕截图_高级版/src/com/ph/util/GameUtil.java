package com.ph.util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import javax.imageio.ImageIO;


public class GameUtil {
	
	public static BufferedImage LoadBuffImage(String filename){
		try{
			return ImageIO.read(new File(filename));
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image LoadImage(String filename, int width, int height, int hints){
		BufferedImage imgbuf = LoadBuffImage(filename);
		if(imgbuf != null){
			return imgbuf.getScaledInstance(width, height, hints);
		}else{
			return null;
		}
	}
	
	public static Image LoadImage(String filename, int width, int height){
		return LoadImage(filename, width, height, Image.SCALE_SMOOTH);
	}
	
	public static Image LoadImage(String filename, int hints){
		BufferedImage imgbuf = LoadBuffImage(filename);
		return imgbuf.getScaledInstance(imgbuf.getWidth(), imgbuf.getHeight(), hints);
	}
	
	public static Image LoadImage(String filename){
		return LoadImage(filename, Image.SCALE_SMOOTH);
	}
	
	public static String getIP(){
		String ip="";
		try {
			ip= InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	public static int getScreenWidth() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}
	
	public static int getScreenHeight() {
		return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	}
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();

	}
	
	public static BufferedImage getScreenRectCapture(int x,int y,int w,int h){
		BufferedImage buf=null;
		try {
			Robot r=new Robot();
			buf= r.createScreenCapture(new Rectangle(x,y,w,h));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}
	
	public static BufferedImage getScreenRectCapture(Rectangle r){
		return GameUtil.getScreenRectCapture((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	}
	
	public static BufferedImage getScreenRectCapture(int x, int y, Dimension size){
		return GameUtil.getScreenRectCapture(x, y, (int)size.getWidth(), (int)size.getHeight());
	}
	
	public static BufferedImage getFullScreenCapture(){
		return getScreenRectCapture(0, 0, getScreenSize());
	}
	
	public static Image byteToImage(byte[] b){
		Image image=null;
		try {
			ByteArrayInputStream bi=new ByteArrayInputStream(b, 0, b.length);
			image=ImageIO.read(bi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;		
	}
	
	public static byte[]imageTobyte(BufferedImage img,String type){
		byte[] b=null;
		try {
			ByteArrayOutputStream bo=new ByteArrayOutputStream();
			ImageIO.write(img, type, bo);
			b=bo.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static void setClipboardImage(final Image img){
		Transferable trans=new Transferable() {
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[]{DataFlavor.imageFlavor};
			}
			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if(isDataFlavorSupported(flavor))
				return img;
				throw new UnsupportedFlavorException(flavor);
			}
		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	}
	
	public static BufferedImage getBufferedImage(Image img, int width, int height, int hints){
		BufferedImage imgbuf = null;
		if(img != null){
			imgbuf = new BufferedImage(width, height, hints);
			imgbuf.getGraphics().drawImage(img.getScaledInstance(width, height, hints), 0, 0, null);
		}
		return imgbuf;
	}
	
	public static BufferedImage getBufferedImage(Image img,int width, int height ){
		return getBufferedImage(img, width, height, Image.SCALE_SMOOTH);
	}
	
	public static BufferedImage getBufferedImage(Image img, int hints){
		return getBufferedImage(img, img.getWidth(null), img.getHeight(null), hints);
	}
	
	public static BufferedImage getBufferedImage(Image img){
		return getBufferedImage(img, Image.SCALE_SMOOTH);
	}
}
