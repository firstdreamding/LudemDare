package main;

import java.awt.Color;
import java.awt.Font;

import controller.HitboxController;
import entities.Hitbox;
import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class Level {
	Player player;
	Texture bg;
	Texture coin;
	HitboxController hbc;
	Font defFont;
	int wave = 0;
	boolean inWave = false;
	
	public Level() {
		bg = new Texture("/res/bg.png", 960, 540);
		coin = new Texture("/res/coin.png", 40, 40);
		player = new Player(0, 50, 0, 50, 50, 1, new Texture("/res/1default.png",50, 50));
		hbc = new HitboxController();
		defFont = new Font("Garamond", 1, 50);
		hbc.add(new Hitbox(10, 0, 0, 50, 50, 0, 0, 1000, player, false), 0);
	}

	public void render(Screen screen) {
		screen.drawTexture(0, 100, bg);
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		screen.drawTexture(10, 20, coin);
		screen.drawString(String.valueOf(player.gold), 60, 55,defFont,Color.RED);
		hbc.render(screen);
	}
	
	public void update(Screen screen) {
		player.update();
		hbc.update();
		render(screen);
		
	}

	public Player getPlayer() {
		return player;
	}

}
