package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Enemy;
import entities.Hurtbox;
import entities.Player;
import graphics.Screen;
import graphics.Texture;
import main.Level;
import utils.RandomGen;

public class WaveHandler {

	public boolean inWave = true;
	private HashMap<Integer, Point> spawns;
	Texture zombie1;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	ArrayList<Enemy> remove = new ArrayList<Enemy>();
	public int wave;
	Player player;
	AttackController ac;
	private int totalSpawn;
	private Level level;

	private int f(int x) {
		return 2 * x;
	}

	public WaveHandler(Player p, AttackController hbc, Level l) {
		wave = 0;
		player = p;
		this.ac = hbc;
		level = l;
		spawns = new HashMap<>();
		spawns.put(0, new Point(480, 100));
		spawns.put(1, new Point(480, 640));
		spawns.put(2, new Point(0, 370));
		spawns.put(3, new Point(960, 370));
		startWave();
	}

	public void died() {
		totalSpawn--;
	}

	public void startWave() {

		RandomGen ran = new RandomGen();
		wave++;
		level.waveText = "Wave: " + wave;
		totalSpawn = f(wave);
		for (int i = 0; i < totalSpawn; i++) {
			int r = ran.randomInt(0, 3);
			add((int) spawns.get(r).getX(), (int) spawns.get(r).getY(), 60, 60, 1, 1);
		}
		inWave = true;
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
		// System.out.println(totalSpawn);
		if (totalSpawn < 1) {
			level.waveText = "Wave Done";
			inWave = false;
		}
		int remLen = remove.size();
		for (int i = 0; i < remLen; i++) {
			enemyList.remove(remove.get(0));
			remove.remove(0);

		}
		for (Enemy e : enemyList) {
			e.update();
		}

	}
}
