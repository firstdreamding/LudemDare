package main;

import java.awt.Color;
import java.awt.Font;

import controller.HitboxController;
import controller.ProjectileController;
import entities.Hitbox;
import entities.Player;
import entities.Projectile;
import graphics.Screen;
import graphics.Texture;

public class Level {
	Player player;
	Texture bg;
	Texture coin;
	HitboxController hbc;
	ProjectileController pc;
	Font defFont;
	int wave = 0;
	boolean inWave = false;

	public Level() {
		bg = new Texture("/res/sprites/bg.png", 960, 540);
		coin = new Texture("/res/sprites/coin.png", 40, 40);
		player = new Player(0, 50, 0, 50, 50, 1, new Texture("/res/sprites/1default.png", 50, 50));
		hbc = new HitboxController();
		pc = new ProjectileController();
		Projectile p = new Projectile(10, 10, 20, 20, 20, 0, 10, 2000, "coin");
		defFont = new Font("Garamond", 1, 50);
		hbc.add(new Hitbox(10, 0, 0, 50, 50, 0, 0, 1000, player, false), 0);
	}

	public void render(Screen screen) {
		screen.drawTexture(0, 100, bg);
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		screen.drawTexture(10, 20, coin);
		screen.drawString(String.valueOf(player.gold), 60, 55, defFont, Color.RED);
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
