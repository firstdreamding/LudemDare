package controller;

import java.util.ArrayList;

import entities.Hitbox;
import entities.Hurtbox;
import entities.Player;
import entities.Projectile;
import graphics.Screen;

public class AttackController {
	ArrayList<Hitbox> team1Hit;
	Hurtbox team1Hurt;
	ArrayList<Hurtbox> team2Hurt;
	ArrayList<Hitbox> item;
	ArrayList<Hitbox> removeH;
	ArrayList<Hurtbox> removeHurt;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Projectile> removeP;
	private long id = 0;
	Player player;

	public AttackController(Player p) {
		player = p;
		// Hitboxes
		team1Hit = new ArrayList<Hitbox>();
		team2Hurt = new ArrayList<Hurtbox>();
		removeHurt = new ArrayList<Hurtbox>();
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

		team1Hurt.update();
		for (Hurtbox t2hit : team2Hurt) {
			t2hit.update();
			if (team1Hurt.intersects(t2hit)) {
				t1Hit(t2hit);
			}
		}
		for (Hurtbox h1 : team2Hurt) {
			for (Hurtbox h2 : team2Hurt) {
				if ((h1.x == h2.x && h1.y == h2.y)) {
					if (!h1.ids(h2.id)) {
						h1.getEntity().x += 15;
						h2.getEntity().x -= 15;
						h1.getEntity().y += 15;
						h2.getEntity().y -= 15;
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
	}

	public void t1Hit(Hurtbox hit) {
		player.updateHealth(-1 * hit.damage);
	}

	public void t2Hit(Hitbox hit, Hurtbox hurt) {
		hurt.getEntity().subHealth(hit.dmg);
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
			removeH.remove(0);
		}
		int removeHur = removeHurt.size();
		for (int i = 0; i < removeHur; i++) {
			team2Hurt.remove(removeHurt.get(0));
			removeHurt.remove(0);
		}
	}

	public void add(Projectile p, int i) {
		add(p.hit.reset(), i);
		projectiles.add(p);
	}

	public void remove(Projectile p) {
		removeP.add(p);
	}

	public void remove(Hurtbox h) {
		removeHurt.add(h);
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
		default:
			item.add(h);
		}
	};

	public void add(Hurtbox h, int i) {
		switch (i) {
		case 0:
			team1Hurt = h;
			break;
		case 1:
			h.id = id;
			id++;
			team2Hurt.add(h);
			break;
		}
	}

	public void render(Screen screen) {
		for (Hitbox h : team1Hit) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0xFF0000);
		}
		screen.drawRect(team1Hurt.x, team1Hurt.y, team1Hurt.width, team1Hurt.height, 0x0000FF);

		for (Hurtbox h : team2Hurt) {
			screen.drawRect(h.x, h.y, h.width, h.height, 0x0000FF);
		}
		for (Projectile p : projectiles) {
			screen.drawTexture(p.x, p.y, p.sprite);
		}
	}
}
