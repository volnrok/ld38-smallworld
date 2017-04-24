package com.ldjam.ld38.sgilhuly;

import org.newdawn.slick.Graphics;

public class Circle implements CameraFocusable {
	
	public float x, y, r;
	
	public Circle(float _x, float _y, float _r) {
		x = _x;
		y = _y;
		r = _r;
	}
	
	public Circle() {
		this(0, 0, 0);
	}

	public Circle getBounds() {
		return this;
	}
	
	public void draw(Graphics g) {
		g.drawOval(x - r, y - r, r * 2, r * 2);
	}
	
	private float sqr(float x) {
		return x * x;
	}
	
	public boolean collides(Circle c) {
		return (sqr(x - c.x) + sqr(y - c.y)) < (sqr(r + c.r));
	}
}
