package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 450, HEIGHT = 800;

	private Thread thread;
	private boolean running = false;
	Handler handler;
	Random r;
	Spawner spawner;
	HUD hud;
	Menu menu;
	
	public enum STATE{
		Menu,
		Game,
		Help,
		End
	};

	public STATE gameState = STATE.Menu ;
	public Game() {
		handler = new Handler();
		r = new Random();
		spawner = new Spawner(handler);
		hud = new HUD();
		menu = new Menu(this, handler);
		
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
				
		new Window(WIDTH, HEIGHT, "test", this);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();		
	}
	
	public void tick() {
		
		if(gameState == STATE.Game) {
			handler.tick();
			spawner.tick();
			hud.tick();
		}
		
		if(hud.HEALTH == 0) {
			gameState = STATE.End;
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Enemy) handler.removeObject(tempObject);
				
			}		
		}
		
		menu.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Game) {
		handler.render(g);
		hud.render(g);
		}
		
		if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		if(var <= min) return var = min;
		else return var;
	}

	public static void main(String args[]) {
		new Game();
	}


	

}
