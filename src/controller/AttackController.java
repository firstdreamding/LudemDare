package controller;

import java.util.ArrayList;

import entities.Hitbox;
import entities.Hurtbox;
import entities.Projectile;
import graphics.Screen;

public class AttackController {
	ArrayList<Hitbox> team1Hit;
	ArrayList<Hurtbox> team1Hurt;
	ArrayList<Hitbox> team2Hit;
	ArrayList<Hurtbox> team2Hurt;
	ArrayList<Hitbox> item;
	ArrayList<Hitbox> removeH;
	public ArrayList<Projectile> projectiles;
	public ArrayList<Projectile> removeP;

	public AttackController() {
		// Hitboxes
		team1Hit = new ArrayList<Hitbox>();
		team2Hit = new ArrayList<Hitbox>();
		team1Hurt = new ArrayList<Hurtbox>();
		team2Hurt = new ArrayList<Hurtbox>();
		removeH = new ArrayList<Hitbox>();
		// Projectiles
		projectiles = new ArrayList<Projectile>();
		removeP = new ArrayList<Projectile>();

	}

	public void update() {
		// Projectile
		for (Projectile p : projectiles) {
			p.update();
			if (p.expired()) {
				removeP.add(p);
			}
		}
		// Hitboxes
		doRemove();
		for (Hurtbox t1hurt : team1Hurt) {
			t1hurt.update();
			for (Hitbox t2hit : team2Hit) {
				t2hit.update();
				if (t2hit.expired()) {
					removeH.add(t2hit);
				}
				if (t1hurt.intersects(t2hit)) {
					t1Hit(t2hit, t1hurt);
				}
			}
		}
		for (Hurtbox t2hurt : team2Hurt) {
			t2hurt.update();
			for (Hitbox t1hit : team1Hit) {
				t1hit.update();
				if (t1hit.expired()) {
					removeH.add(t1hit);
				}
				if (t2hurt.intersects(t1hit)) {
					t2Hit(t1hit, t2hurt);
				}
			}
		}
	}

	public void t1Hit(Hitbox hit, Hurtbox hurt) {
		hurt.getEntity().subHealth(hit.dmg);
		remove(hit);
	}

	public void t2Hit(Hitbox hit, Hurtbox hurt) {
		System.out.println("hit enmy");
		remove(hit);
	}

	// Internal function ignore this
	private void doRemove() {
		int removePro = removeP.size();
		for (int i = 0; i < removePro; i++) {
			projectiles.remove(removeP.get(0));
			remove(removeP.get(0).hit);
			removeP.remove(0);

		}
		int removeHit = removeH.size();
		for (int i = 0; i < removeHit; i++) {
			team1Hit.remove(removeH.get(0));
			team2Hit.remove(removeH.get(0));
			removeH.remove(0);
		}
	}

	public void add(Projectile p, int i) {
		add(p.hit.reset(), i);
		projectiles.add(p);
	}

	public void remove(Projectile p) {
		removeP.add(p);
	}

	public void remove(Hitbox h) {
		removeH.add(h);
		if (h.projectile) {
			for (Projectile p : projectiles) {
				if (p.hit.equals(h)) {
					remove(p);
				}
			}
		}
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
		for (Hitbox h : team2Hit) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0xFF0000);
		}
		for (Hurtbox h : team2Hurt) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0x0000FF);
		}
		for (Projectile p : projectiles) {
			screen.drawTexture(p.x, p.y, p.sprite);
		}
	}
}