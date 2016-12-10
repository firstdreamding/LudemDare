package main;

import controller.HitboxController;
import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class Level {
	Player player;
	Texture bg;
	Texture coin;
	HitboxController hbc;
	int wave = 0;
	boolean inWave = false;

	public Level() {
		bg = new Texture("/res/bg.png", 960, 540);
		coin = new Texture("/res/coin.png", 40, 40);
		player = new Player(0, 100, 0, 100, 100, 1, new Texture("/res/1default.png", 100, 100));
		hbc = new HitboxController();
	}

	public void render(Screen screen) {
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		screen.drawTexture(10, 20, coin);
		screen.drawString(String.valueOf(player.gold), 60, 20);
	}

	public void update(Screen screen) {
		render(screen);
		player.update();
	}

	public Player getPlayer() {
		return player;
	}

}
