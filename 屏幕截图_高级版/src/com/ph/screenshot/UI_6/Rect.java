package com.ph.screenshot.UI_6;

public class Rect {
	
	private int X,Y,W,H;

	public Rect(int x, int y, int w, int h) {
		X = x;
		Y = y;
		W = w;
		H = h;
	}
	
	public Rect() {
		this(0,0,0,0);
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}
	
	public int getR(){
		return X+W;
	}
	
	public int getB(){
		return Y+H;
	}
	
	public void setLocation(int x,int y){
		X=x;
		Y=y;
	}
	
	public void setSize(int w,int h){
		W=w;
		H=h;
	}
	
	public void setBounds(int x,int y,int w,int h){
		X = x;
		Y = y;
		W = w;
		H = h;
	}
	
}
