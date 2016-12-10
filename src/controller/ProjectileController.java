package controller;

import java.util.ArrayList;

import entities.Projectile;
import graphics.Screen;

public class ProjectileController {
	public ArrayList<Projectile> active;
	public ArrayList<Projectile> remove;
	private HitboxController hbc;

	public ProjectileController(HitboxController hbc) {
		active = new ArrayList<Projectile>();
		remove = new ArrayList<Projectile>();
		this.hbc = hbc;
	}

	int removeSize;

	public void render(Screen screen) {
		for (Projectile p : active) {
			screen.drawTexture(p.x, p.y, p.sprite);
		}
	}

	public void update() {
		removeSize = remove.size();
		for (int i = 0; i < removeSize; i++) {
			active.remove(remove.get(0));
			hbc.remove(remove.get(0).hit);
			remove.remove(0);

		}
		for (Projectile p : active) {
			p.update();
			if (p.expired()) {
				remove.add(p);
			}
		}
	}

	public void add(Projectile p, int i) {
		hbc.add(p.hit.reset(), i);
		active.add(p);
	}

	public void remove(Projectile p) {
		remove.add(p);
	}
}
