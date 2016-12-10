package controller;

import java.util.ArrayList;

import entities.Enemy;
import entities.Hurtbox;
import entities.Player;
import graphics.Screen;
import graphics.Texture;

public class WaveHandler {

	public boolean inWave = true;

	Texture zombie1;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Enemy> remove = new ArrayList<Enemy>();
	public int wave;
	Player player;
	AttackController ac;

	public WaveHandler(Player p, AttackController hbc) {
		player = p;
		this.ac = hbc;
		add(10, 10, 50, 50, 1, 1);

	}

	public void render(Screen screen) {
		for (Enemy e : enemyList) {
			screen.drawTexture(e.x, e.y, e.sprite);
		}
	}

	public void add(int x, int y, int w, int h, int dir, int zombieNum) {
		Enemy e = new Enemy(x, y, w, h, dir, zombieNum, player, this);
		Hurtbox hurt = new Hurtbox(e, e.w, e.h, 0, 0);
		enemyList.add(e);
		ac.add(hurt, 1);
		e.setHurtbox(hurt);
	}

	public void remove(Enemy e) {
		remove.add(e);
		ac.remove(e.getHurtbox());

	}

	public void update() {
		if (!inWave)
			return;
		int remLen = remove.size();
		for (int i = 0; i < remLen; i++) {
			enemyList.remove(remove.get(0));
			remove.remove(0);

		}
		for (Enemy e : enemyList) {
			e.pathFind();
		}

	}
}
