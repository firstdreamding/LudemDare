package main;

import java.awt.Color;
import java.awt.Font;

import controller.HitboxController;
import controller.ProjectileController;
import entities.Hitbox;
import entities.Hurtbox;
import entities.Player;
import entities.Projectile;
import graphics.Screen;
import graphics.Texture;

public class Level {
	public Player player;
	Texture bg;
	Texture coin;
	HitboxController hbc;
	public ProjectileController pc;
	Font defFont;
	int wave = 0;
	public boolean inWave = false;

	public Level() {
		bg = new Texture("/res/sprites/bg.png", 960, 540);
		coin = new Texture("/res/sprites/coin.png", 40, 40);
		player = new Player(0, 150, 200, 60, 60, 1, new Texture("/res/sprites/player.png", 60, 60));
		hbc = new HitboxController();
		pc = new ProjectileController(hbc);

		Projectile p = new Projectile(10, 70, 20, 20, 10, 0, 10, 2000, "coin");
		pc.add(p, 0);

		defFont = new Font("Garamond", 1, 50);
		hbc.add(new Hurtbox(player), 0);
		hbc.add(new Hitbox(10, 0, 0, 60, 60, 0, 0, 1000, player, false), 0);
	}

	public void render(Screen screen) {
		screen.drawTexture(0, 100, bg);
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		pc.render(screen);
		hbc.render(screen);
		gui(screen);
	}

	public void update(Screen screen) {
		player.update();
		pc.update();
		hbc.update();
		render(screen);
	}
	
	public void gui(Screen screen) {
		screen.drawTexture(10, 20, coin);
		screen.drawString(String.valueOf(player.gold), 60, 55, defFont, Color.RED);
		screen.drawTexture(100, 100, player.weapon.texture);
	}

	public Player getPlayer() {
		return player;
	}

}
