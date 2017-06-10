package com.ph.screenshot.UI_5;


public class SizeChangedEvent {
	private int w,h;
	
	public void setWidth(int w){
		this.w=w;
	}
	
	public void setHeight(int h){
		this.h=h;
	}
	
	public int getWidth(){
		return w;
	}
	
	public int getHeight(){
		return h;
	}
	
	public void setSize(int w,int h){
		setWidth(w);
		setHeight(h);
	}
}
