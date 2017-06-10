package com.ph.screenshot.V6;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

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

	public int getWidth() {
		return W;
	}

	public void setWidth(int w) {
		W = w;
	}

	public int getHeight() {
		return H;
	}

	public void setHeight(int h) {
		H = h;
	}
	
	public int getRight(){
		return X+W;
	}
	
	public int getBottom(){
		return Y+H;
	}
	
	public Dimension getSize(int offset){
		return new Dimension(W+offset, H+offset);
	}
	
	public void setLocation(int x,int y){
		X=x;
		Y=y;
	}
	
	public void setLocation(Point p){
		this.X = p.x;
		this.Y = p.y;
	}
	
	public void setLocation(Posi p){
		this.X = p.getX();
		this.Y = p.getY();
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
	
	public void setBounds(Rectangle r){
		X = r.x;
		Y = r.y;
		W = r.width;
		H = r.height;
	}
	
	public void setBounds(Rect r){
		this.X = r.getX();
		this.Y = r.getY();
		this.W = r.getWidth();
		this.H = r.getHeight();
	}
	
	public Rectangle getBounds(){
		return new Rectangle(X, Y, W, H);
	}
	
	public Rectangle getMarginBounds(int margin){
		return new Rectangle(this.X-margin/2, this.Y-margin/2, this.W+margin, this.H+margin);
	}
	
	public Dimension getMarginSize(int margin){
		return getMarginBounds(margin).getSize();
	}
	
	public Point getMarginLocation(int margin){
		return new Point(getMarginBounds(margin).getLocation());
	}
	
	public Point getLocation(){
		return new Point(this.X, this.Y);
	}
	
	@Override
	public String toString() {
		return "(x:"+this.X+", y:"+this.Y+", w:"+this.W+", h:"+this.H+")";
	}
}
