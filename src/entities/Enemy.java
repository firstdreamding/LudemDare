package entities;

import controller.WaveHandler;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Enemy extends Entity {
	final int vel = 1;
	int dir;
	Player player;
	WaveHandler wh;
	Hurtbox hurtbox;
	SpriteSheet zombieSprites;
	int counter;
	long lastTick;
	int gold = 10;
	boolean alive = true;

	public Enemy(int x, int y, int w, int h, int dir, int zombieNum, Player p, WaveHandler wh) {
		super(x, y, w, h, dir, new Texture("/res/sprites/zombie" + zombieNum + ".png", w, h));
		health = 50;
		player = p;
		zombieSprites = new SpriteSheet(new Texture("/res/sprites/zombie1.png", 240, 240), w, h);
		this.wh = wh;
	}

	@Override
	public void handleDeath() {
		if (alive) {
			alive = false;
			wh.remove(this);
			player.addGold(gold);
			wh.died();
		}
	}

	public void setHurtbox(Hurtbox hurtbox) {
		this.hurtbox = hurtbox;
	}

	public void update() {
		pathFind();
		if (x < 55) {
			x = 55;
		}
		if (y < 155) {
			y = 155;
		}
		if (y > 640 - h) {
			y = 640 - h;
		}
		if (x > 895 - w) {
			x = 895 - w;
		}
		animation();
	}

	public void pathFind() {
		if (y > player.y) {
			dir = 1;
			y -= vel;
		} else if (y < player.y) {
			dir = 0;
			y += vel;
		}
		if (x > player.x) {
			dir = 3;
			x -= vel;
		} else if (x < player.x) {
			dir = 2;
			x += vel;
		}
	}

	public void animation() {
		this.setT(zombieSprites.getTexture(counter % 4, dir));
		if (Main.getInstance().tick - lastTick > 5) {
			counter++;
			lastTick = Main.getInstance().tick;
		}
	}

	public Hurtbox getHurtbox() {
		return hurtbox;
	}
}
