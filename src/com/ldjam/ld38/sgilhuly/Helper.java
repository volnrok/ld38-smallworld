package com.ldjam.ld38.sgilhuly;

import org.newdawn.slick.Image;

public abstract class Helper {
	
	public static final float PI = (float) Math.PI;

	public static void draw(Image im, float x, float y, float angle) {
		im.setRotation(angle);
		im.drawCentered(x, y);
	}
	
	public static float rad_deg(float rad) {
		return rad * 360f / PI / 2;
	}
}