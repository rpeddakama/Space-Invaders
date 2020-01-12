package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Main.Game.STATE;

public class Menu extends MouseAdapter{
	
	Game game;
	Handler handler;
	Random r = new Random();
	
	public Menu (Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	private int mx, my;
	private Font fnt = new Font("Arial", Font.BOLD, 48);
	private Font fnt2 = new Font("Arial", Font.BOLD, 12);
	private Font fnt3 = new Font("Arial", Font.BOLD, 36);



	
	public void tick() {	
	
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			//Play button
			g.setColor(Color.WHITE);
			g.setFont(fnt);
			g.drawRect(65, 60, 300, 100);
			g.drawString("Play", 158, 125);
			
			//Help button
			g.drawRect(65, 200, 300, 100);
			g.drawString("Help", 158, 270);
		
			
			//Quit Button
			g.drawRect(65, 340, 300, 100);
			g.drawString("Quit", 158, 410);
		}
		
		if(game.gameState == STATE.Help) {
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Help", 158, 125);
			g.setFont(fnt2);
			g.drawString("R u stupid, just play the game", 60, 200);
			
			//Back button
			g.setFont(fnt);
			g.drawRect(65, 640, 300, 100);
			g.drawString("Back", 158, 710);
		}
		
		if(game.gameState == STATE.End) {
			g.setColor(Color.white);
			g.setFont(fnt3);
			g.drawString("GAME OVER", 110, 125);
			
			//Score
			g.drawString("Your Score was: " + HUD.SCORE, 70, 325);

			//Play Again
			g.drawRect(65, 640, 300, 100);
			g.drawString("Try Again", 135, 700);
		}
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx >= x && mx <= x + width) {
			if(my >= y && my <= y + height) return true;
			else return false;
		}
		else return false;
	}
	
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//Play
			if (mouseOver(mx, my, 65, 60, 300, 100)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2, 730, ID.Player, handler));
	 		}
			//Quit
			if (mouseOver(mx, my, 65, 340, 300, 100)) {
				System.exit(1);
			}
			//Help
			if (mouseOver(mx, my, 65, 200, 300, 100)) {
				game.gameState = STATE.Help;
			}
			}
			
			if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 65, 640, 300, 100)) {
				game.gameState = STATE.Menu;
			}
			}
			
			
			if(game.gameState == STATE.End) {
				if(mouseOver(mx, my, 65, 640, 300, 100)) {
					game.gameState = STATE.Game;
					HUD.HEALTH = 200;
					HUD.SCORE = 0;			
				
				}
			}
	}
}
