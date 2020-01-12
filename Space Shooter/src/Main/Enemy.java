package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{

	private Handler handler;

	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velY = 2;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y>Game.HEIGHT) {
			handler.removeObject(this);
			HUD.HEALTH-=5;
		}
		collision();
	
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 30, 30);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,30,30);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					handler.removeObject(tempObject);
					HUD.SCORE+=5;
				}
			}
		}
	}


}
