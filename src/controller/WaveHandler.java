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
	public int wave;
	Player player;
	HitboxController hbc;

	public WaveHandler(Player p, HitboxController hbc) {
		player = p;
		this.hbc = hbc;
		Enemy e = new Enemy(10, 10, 50, 50, 1, 1, player);
		add(e);

	}

	public void render(Screen screen) {
		for (Enemy e : enemyList) {
			screen.drawTexture(e.x, e.y, e.sprite);
		}
	}

	public void add(Enemy e) {
		enemyList.add(e);
		hbc.add(new Hurtbox(e, e.w, e.h, 0, 0), 1);
	}

	public void update() {
		if (!inWave)
			return;
		for (Enemy e : enemyList) {
			e.pathFind();
		}

	}
}
