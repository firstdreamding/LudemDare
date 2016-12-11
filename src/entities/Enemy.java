package entities;

import controller.WaveHandler;
import graphics.Texture;

public class Enemy extends Entity {
	final int vel = 1;
	Player player;
	WaveHandler wh;
	Hurtbox hurtbox;
	int gold = 10;
	boolean alive = true;

	public Enemy(int x, int y, int w, int h, int dir, int zombieNum, Player p, WaveHandler wh) {
		super(x, y, w, h, dir, new Texture("/res/sprites/zombie" + zombieNum + ".png", w, h));
		health = 50;
		player = p;
		this.wh = wh;
	}

	@Override
	public void handleDeath() {
		if (alive) {
			alive = false;
			System.out.println("enemydead");
			wh.remove(this);
			player.addGold(gold);
			wh.died();
		}
	}

	public void setHurtbox(Hurtbox hurtbox) {
		this.hurtbox = hurtbox;
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

	public Hurtbox getHurtbox() {
		return hurtbox;
	}
}
