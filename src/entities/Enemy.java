package entities;

import graphics.Texture;
import main.Main;

public class Enemy extends Entity {
	final int vel = 1;
	Player player;

	public Enemy(int x, int y, int w, int h, int dir, int zombieNum, Player p) {
		super(x, y, w, h, dir, new Texture("/res/sprites/zombie" + zombieNum + ".png", w, h));
		health = 10;
		player = p;
	}

	@Override
	public void handleDeath() {
		System.out.println("dead");
	}

	public void pathFind() {
		if (x > player.x) {
			x -= vel;
		} else if (x < player.x) {
			x += vel;
		}
		if (y > player.y) {
			y -= vel;
		} else if (y < player.y) {
			y += vel;
		}

	}
}
