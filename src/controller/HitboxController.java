package controller;

import java.util.ArrayList;

import entities.Hitbox;
import entities.Hurtbox;

public class HitboxController {
	ArrayList<Hitbox> team1Hit;
	ArrayList<Hurtbox> team1Hurt;
	ArrayList<Hitbox> team2Hit;
	ArrayList<Hurtbox> team2Hurt;
	ArrayList<Hitbox> item;

	ArrayList<Hitbox> removeThese;

	public void update() {
		doRemove();
		for (Hurtbox t1hurt : team1Hurt) {
			for (Hitbox t2hit : team2Hit) {
				if (t1hurt.intersects(t2hit)) {
					t1Hit(t2hit, t1hurt);
				}
			}
		}
		for (Hurtbox t2hurt : team2Hurt) {
			for (Hitbox t1hit : team1Hit) {
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
			item.remove(removeThese.get(0));
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
}
