package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{

	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velY = -5;
	}

	public void tick() {
		x += velX;
		y += velY;
	
		if(y < 0) handler.removeObject(this);
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 10, 30);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 30);
	}
	


}
