package entities;

import graphics.Texture;
import main.SoundPlayer;

public class Projectile extends Entity {
	public int damage;
	public int dur;
	public long start;
	public Hitbox hit;
	SoundPlayer soundplayer;
	public Projectile(int x, int y, int w, int h, int xvel, int yvel, int damage, int dur, String s, String path) {
		super(x, y, w, h, 0, new Texture("/res/sprites/" + s + ".png", w, h));
		marginX = 0;
		marginY = 0;
		radius = 0;
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.dur = dur;
		this.xvel = xvel;
		this.yvel = yvel;
		hit = new Hitbox(damage, 0, 0, w, h, 0, 0, dur, this, true);
		soundplayer = new SoundPlayer(path);
		soundplayer.setVolume(0.25);
		soundplayer.play();
		start = System.currentTimeMillis();
	}

	public void update() {
		x = x + xvel;
		y = y + yvel;
	}

	public boolean expired() {
		return System.currentTimeMillis() - start > dur || y < 150 || x > 860|| x < 50;

	}

}
