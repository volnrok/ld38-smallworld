package com.ldjam.ld38.sgilhuly;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SmallWorldGame extends BasicGame {
	
	private static final String IMG_ROOT = "res/img/";
	
	private float x = 1;
	private Image planet;
	private Image letter;
	private Image guy;
	private Input input;
	
	private Camera camera;
	private Circle c1 = new Circle(-60, -80, 10), c2 = new Circle(110, 20, 10);
	private ArrayList<CameraFocusable> circles;

	public SmallWorldGame(String title) {
		super(title);
		circles = new ArrayList<CameraFocusable>();
		circles.add(c1);
		circles.add(c2);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		input = new Input(gc.getHeight());
		camera = new Camera(0.1f, 3.0f, gc.getWidth(), gc.getHeight());
		planet = new Image(IMG_ROOT + "dumbPlanet.png");
		letter = new Image(IMG_ROOT + "letter.png");
		guy = new Image(IMG_ROOT + "guy.png");
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		input.poll(gc.getWidth(), gc.getHeight());

		if(input.isKeyDown(Input.KEY_W)) c1.y -= 1;
		if(input.isKeyDown(Input.KEY_S)) c1.y += 1;
		if(input.isKeyDown(Input.KEY_A)) c1.x -= 1;
		if(input.isKeyDown(Input.KEY_D)) c1.x += 1;

		if(input.isKeyDown(Input.KEY_UP)) c2.y -= 1;
		if(input.isKeyDown(Input.KEY_DOWN)) c2.y += 1;
		if(input.isKeyDown(Input.KEY_LEFT)) c2.x -= 1;
		if(input.isKeyDown(Input.KEY_RIGHT)) c2.x += 1;

		if(input.isKeyDown(Input.KEY_O)) x -= .01f;
		if(input.isKeyDown(Input.KEY_P)) x += .01f;
		x = Math.max(x, 0.5f);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Set up the camera transformations
		if(c1.collides(c2)) g.drawString("Collides!", 0, 0);
		camera.recalc(circles);
		g.scale(camera.scale, camera.scale);
		g.translate(-camera.x, -camera.y);
		g.translate(gc.getWidth() / 2 / camera.scale, gc.getHeight() / 2 / camera.scale);
		
		Helper.draw(planet, 0, 0, 0);
		
		g.setColor(Color.white);
		c1.draw(g);
		Helper.draw(guy, c1.x, c1.y, Helper.rad_deg((float) Math.atan2(c1.x, -c1.y)));
		
		g.setColor(Color.blue);
		c2.draw(g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SmallWorldGame("Small World, Huh?"));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SmallWorldGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
