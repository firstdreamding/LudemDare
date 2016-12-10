package controller;

import java.util.ArrayList;

import entities.Hitbox;
import entities.Hurtbox;
import graphics.Screen;

public class HitboxController {
	ArrayList<Hitbox> team1Hit;
	ArrayList<Hurtbox> team1Hurt;
	ArrayList<Hitbox> team2Hit;
	ArrayList<Hurtbox> team2Hurt;
	ArrayList<Hitbox> item;

	ArrayList<Hitbox> removeThese;

	public HitboxController() {
		team1Hit = new ArrayList<Hitbox>();
		team2Hit = new ArrayList<Hitbox>();
		team1Hurt = new ArrayList<Hurtbox>();
		team2Hurt = new ArrayList<Hurtbox>();
		removeThese = new ArrayList<Hitbox>();

	}

	public void update() {
		doRemove();
		for (Hurtbox t1hurt : team1Hurt) {
			t1hurt.update();
			for (Hitbox t2hit : team2Hit) {
				t2hit.update();
				if (t2hit.expired()) {
					removeThese.add(t2hit);
				}
				if (t1hurt.intersects(t2hit)) {
					t1Hit(t2hit, t1hurt);
				}
			}
		}
		for (Hitbox t1hit : team1Hit) {
			t1hit.update();
			if (t1hit.expired()) {
				removeThese.add(t1hit);
			}
			for (Hurtbox t2hurt : team2Hurt) {
				if (t2hurt.intersects(t1hit)) {
					t2Hit(t1hit, t2hurt);
				}
			}
		}
	}

	public void t1Hit(Hitbox hit, Hurtbox hurt) {
		hurt.getEntity().subHealth(hit.dmg);
	}

	public void t2Hit(Hitbox hit, Hurtbox hurt) {

	}

	// Internal function ignore this
	private void doRemove() {
		int removeSize = removeThese.size();
		for (int i = 0; i < removeSize; i++) {
			team1Hit.remove(removeThese.get(0));
			team2Hit.remove(removeThese.get(0));
			removeThese.remove(0);
		}
	}

	public void remove(Hitbox h) {
		removeThese.add(h);
	}

	public void add(Hitbox h, int i) {
		switch (i) {
		case 0:
			team1Hit.add(h);
			break;
		case 1:
			team2Hit.add(h);
			break;
		default:
			item.add(h);
		}
	};

	public void add(Hurtbox h, int i) {
		switch (i) {
		case 0:
			team1Hurt.add(h);
			break;
		case 1:
			team2Hurt.add(h);
			break;
		}
	}

	public void render(Screen screen) {
		for (Hitbox h : team1Hit) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0xFF0000);
		}
		for (Hurtbox h : team1Hurt) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0x0000FF);
		}
	}
}
