package entities;

import graphics.Texture;

public class Enemy extends Entity {

	public Enemy(int x, int y, int w, int h, int dir, Texture s) {
		super(x, y, w, h, dir, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleDeath() {
		System.out.println("dead");
	}
}
