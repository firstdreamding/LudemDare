package entities;

import controller.WaveHandler;
import graphics.SpriteSheet;
import graphics.Texture;
import main.Main;

public class Enemy extends Entity {
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
		gvel = 1;
		player = p;
		zombieSprites = new SpriteSheet(new Texture("/res/sprites/zombie1.png", 240, 240), w, h);
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

	public void update() {
		pathFind();
		y += yvel;
		x += xvel;
		animation();
	}

	public void pathFind() {
		if (xvel != 1) {
			if (xvel < 1 && xvel != 0)
				xvel++;
			else {
				xvel--;
			}
		}
		if (yvel != 1) {
			if (yvel < 1 && yvel != 0)
				yvel++;
			else {
				yvel--;
			}
		}
		if (y == player.y) {
			yvel = 0;
			return;
		}
		if (x == player.x) {
			xvel = 0;
			return;
		}
		if (y > player.y) {
			dir = 1;
			yvel = -1 * gvel;
		} else if (y < player.y) {
			dir = 0;
			yvel = gvel;
		}
		if (x > player.x) {
			dir = 3;
			xvel = -1 * gvel;
		} else if (x < player.x) {
			dir = 2;
			xvel = gvel;
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
