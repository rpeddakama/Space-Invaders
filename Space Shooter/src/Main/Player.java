package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		
		x= Game.clamp(x, 0, Game.WIDTH-50);
		y= Game.clamp(y, 0, Game.HEIGHT-70);
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 30, 30);
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,30,30);
	}

}
