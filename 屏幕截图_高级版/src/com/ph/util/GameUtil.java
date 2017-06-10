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

	public static Image LoadImage(String path,int...args){
    	BufferedImage bf=null;
    	Image ima=null;
    	if(path==null)
    		return ima;
		try {
			bf = ImageIO.read(new File(path));
			switch(args.length){
			case 1:ima=bf.getScaledInstance(bf.getWidth(), bf.getHeight(), args[0]);break;
			case 2:ima=bf.getScaledInstance(args[0], args[1], Image.SCALE_SMOOTH);break;
			case 3:ima=bf.getScaledInstance(args[0], args[1], args[2]);break;
			default:ima=bf.getScaledInstance(bf.getWidth(), bf.getHeight(), Image.SCALE_SMOOTH);break;
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return ima;    	
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
	
	public static BufferedImage getFullScreenCapture(){
		BufferedImage buf=null;
		try {
			Robot r=new Robot();
			buf= r.createScreenCapture(new Rectangle(getScreenSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
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
	
	public static BufferedImage getBufferedImage(Image img,int...args){
		BufferedImage buf=null;
		int imgType=Image.SCALE_SMOOTH,w=0,h=0;
		if(img!=null){
			switch(args.length){
			case 0:
				imgType=Image.SCALE_SMOOTH;
				w=img.getWidth(null);
				h=img.getHeight(null);
				break;
			case 1:
				imgType=args[0];
				w=img.getWidth(null);
				h=img.getHeight(null);
				break;
			case 2:
				imgType=Image.SCALE_SMOOTH;
				w=args[0];
				h=args[1];
				break;
			case 3:
				w=args[0];
				h=args[1];
				imgType=args[2];
				break;
			default:
				imgType=Image.SCALE_SMOOTH;
				w=img.getWidth(null);
				h=img.getHeight(null);
			}
			buf=new BufferedImage(w, h, imgType);
			buf.getGraphics().drawImage(img.getScaledInstance(w, h, imgType), 0, 0,null);
		}
		return buf;
	}
}
