package com.ldjam.ld38.sgilhuly;

import java.util.List;

public class Camera {
	
	private float border, maxScale, width, height, margin, zoneWidth, zoneHeight;

	public float x, y, scale;
	
	public Camera(float _border, float _maxScale, float _width, float _height) {
		
		setParams(_border, _maxScale, _width, _height);
	}
	
	public void setParams(float _border, float _maxScale, float _width, float _height) {
		
		border = _border;
		maxScale = _maxScale;
		width = _width;
		height = _height;
		margin = border * Math.min(width, height);
		zoneWidth = width - margin * 2;
		zoneHeight = height - margin * 2;
	}
	
	public void recalc(List<CameraFocusable> objs) {
		float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		boolean first = true;
		
		// Calculate the bounds of all of the objects
		for(CameraFocusable obj : objs) {
			Circle c = obj.getBounds();
			if(first) {
				x1 = c.x - c.r;
				x2 = c.x + c.r;
				y1 = c.y - c.r;
				y2 = c.y + c.r;
				first = false;
			} else {
				x1 = Math.min(x1, c.x - c.r);
				x2 = Math.max(x2, c.x + c.r);
				y1 = Math.min(y1, c.y - c.r);
				y2 = Math.max(y2, c.y + c.r);
			}
		}
		
		// Calculate the centre of the screen and the scale
		//float cx = (x1 + x2) / 2, cy = (y1 + y2) / 2;
		x = (x1 + x2) / 2;//-(cx - width / 2);
		y = (y1 + y2) / 2;//-(cy - height / 2);
		
		float rectWidth = x2 - x1, rectHeight = y2 - y1;
		scale = Math.min(Math.min(zoneWidth / rectWidth, zoneHeight / rectHeight), maxScale);
	}
}
