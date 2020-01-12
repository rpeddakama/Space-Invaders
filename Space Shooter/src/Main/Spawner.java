package Main;

import java.util.Random;

public class Spawner {
	private Handler handler;
	private int timer = 0;
	private Random r = new Random();
	
	public Spawner(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		timer++;
		
		if(timer%100 == 0) {
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), 10, ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), 10, ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), 10, ID.Enemy, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH-50), 10, ID.Enemy, handler));
		}
	}
}
