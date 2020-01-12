package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	private Font fnt = new Font("Arial", Font.BOLD, 36);
	
	public static int HEALTH = 200;
	public static int SCORE = 0;
	
	public void tick() {
		Game.clamp(HEALTH, 0, 200);
		if(HEALTH <= 0) HEALTH = 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(210, 10, 200, 40);
		g.setFont(fnt);
		g.drawString("Score: "+SCORE, 10, 35);
		
		g.setColor(Color.green);
		g.fillRect(210, 10, HEALTH, 40);
		
	}
}
