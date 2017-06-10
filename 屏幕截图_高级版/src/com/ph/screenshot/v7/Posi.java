package com.ph.screenshot.v7;

import java.awt.Point;

public class Posi {
	private int x;
	private int y;
	public Posi() {
		this.x = 0;
		this.y = 0;
	}
	
	public Posi(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Posi(Point p){
		this.x = p.x;
		this.y = p.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(Point p){
		this.x = p.x;
		this.y = p.y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public Point getLocation(){
		return new Point(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "(x:"+this.x+", y:"+this.y+")";
	}

}
