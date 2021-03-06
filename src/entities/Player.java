package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

import controller.AnimationController;
import controller.InputHandler;
import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import items.Ak47;
import items.Item;
import items.Weapon;
import main.KeyMap;
import main.Main;
import main.MoveQueue;
import menu.DeathMenu;

public class Player extends Entity {

	public int specialbar = 0;
	public int dir;
	private int playerID;
	public int moveSpeed, special, lastHealth;
	public int traction = 1;
	public Weapon weapon;
	public String name;
	public int gold;
	public SpriteSheet sheet;
	public MoveQueue moveQueue;
	public ArrayList<Integer> inventory;
	public Item current;
	int xdir;
	public int ydir;
	boolean weaponLock;
	public long tickLU;
	public ArrayList<Texture> healthBar;

	private Texture heart, hheart, nheart;
	public boolean invuln;
	long invulnStop;
	public SpriteSheet playerSprites;
	public SpriteSheet playerInvlunSprites;

	public Player(int pid, int x, int y, int w, int h, int dir) {
		super(x, y, w, h, dir);
		playerSprites = new SpriteSheet(new Texture("/res/sprites/guysheet.png", 240, 1680), w, h);
		playerInvlunSprites = new SpriteSheet(new Texture("/res/sprites/guyinvlunsheet.png", 240, 1680), w, h);
		sheet = playerSprites;
		setT(1, 1);
		heart = new Texture("/res/sprites/heart.png", 40, 40);
		hheart = new Texture("/res/sprites/halfheart.png", 40, 40);
		nheart = new Texture("/res/sprites/emptyheart.png", 40, 40);
		playerID = 0;
		invuln = true;
		inventory = new ArrayList<Integer>();
		health = 100;
		playerID = pid;
		this.dir = dir;
		special = 0;
		xdir = 0;
		ydir = 0;
		invulnStop = 0;
		playern = pid;
		moveQueue = new MoveQueue();
		weapon = (Weapon) Item.Smg;
		gold = 500;
		tickLU = 0;
		healthBar = new ArrayList<Texture>();
		for (int i = 0; i < 5; i++) {
			healthBar.add(heart);
		}
		// TODO Auto-generated constructor stub
	}

	public void init() {

	}

	public void setInvuln(boolean flag) {
		if (flag) {
			invuln = true;
			invulnStop = System.currentTimeMillis() + 1500;
			sheet = playerInvlunSprites;
		} else {
			invuln = false;
			sheet = playerSprites;
		}
	}

	public void changeWeapon(int id) {
		weapon = (Weapon) Item.getByID(id);
	}

	public void updateHealth(int h) {
		healthBar.clear();
		health += h;
		int half = (health % 20) / 10;
		int full = (int) Math.floor((health / 20));
		int empty = 5 - (half + full);
		for (int i = 0; i < full; i++) {
			healthBar.add(heart);
		}
		for (int i = 0; i < half; i++) {
			healthBar.add(hheart);
		}
		for (int i = 0; i < empty; i++) {
			healthBar.add(nheart);
		}

	}

	@Override
	public void handleDeath() {
		System.out.println("dead");
	}

	public void update() {

		/*
		 * if (frozen) { if (System.currentTimeMillis() > freezeUntil) { frozen
		 * = false; } }
		 */
		if (System.currentTimeMillis() > invulnStop) {
			setInvuln(false);
		}
		if (x < 55) {
			x = 55;
		}
		if (y < 155) {
			y = 155;
		}
		if (y > 640 - h) {
			y = 640 - h;
		}
		if (x > 895 - w) {
			x = 895 - w;
		}
		x = x + xvel;
		y = y + yvel;
		if (Math.abs(xvel) - traction > 0) {
			if (xvel > 0) {
				xvel -= traction;
			} else {
				xvel += traction;
			}
		} else {
			xvel = 0;
			if (!(AnimationController.playerState == AnimationController.State.DEAD))
				AnimationController.setNone(this);
		}

		if (Math.abs(yvel) - traction > 0) {
			if (yvel > 0) {
				yvel -= traction;
			} else {
				yvel += traction;
			}
		} else {
			yvel = 0;
		}

		if (weapon.isReloading) {
			if (Main.getInstance().tick - weapon.startReload > weapon.reload) {
				weapon.reloadSound.play();
				weapon.isReloading = false;
			}
		}
		
		if (health <= 0){
			Main.getInstance().level.soundplayer.stop();
			Main.getInstance().menu = new DeathMenu();
			Main.getInstance().state = Main.State.MENU;
		}
		handleInput();
	}

	public boolean inInv(int id) {
		return inventory.contains(id);
	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite, dir == -1);
	}

	public void add(int playerID, String move) {
		moveQueue.add(playerID, move);
	}

	public void addGold(int gold) {
		this.gold += gold;
	}

	private void handleInput() {
		if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
			setDir(0, 1);
			dir = -2;
			yvel = -3;
			walk();
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_S)) {
			setDir(0, 0);
			dir = 0;
			yvel = 3;
			walk();
		}
		if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
			setDir(0, 3);
			dir = -1;
			xvel = -3;
			walk();
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
			setDir(0, 2);
			dir = 1;
			xvel = 3;
			walk();
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			if (!Main.getInstance().level.wh.inWave) {
				Main.getInstance().inMenu = true;
				Main.getInstance().state = Main.State.MENU;
			}
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
			Main.getInstance().level.wh.startWave();
		}

		if (Main.getInstance().tick - weapon.cooldown >= tickLU) {
			if (InputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
				ydir = 2;
				weapon.use(this, 1, 0);
				tickLU = Main.getInstance().tick;
			} else if (InputHandler.isKeyPressed(KeyEvent.VK_UP)) {
				ydir = 1;
				weapon.use(this, 0, -1);
				tickLU = Main.getInstance().tick;
			} else if (InputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
				ydir = 3;
				tickLU = Main.getInstance().tick;
				weapon.use(this, -1, 0);
			} else if (InputHandler.isKeyPressed(KeyEvent.VK_DOWN)) {
				ydir = 0;
				weapon.use(this, 0, 1);
				tickLU = Main.getInstance().tick;
			}
		}
	}

	private void walk() {
		if (!(AnimationController.playerState == AnimationController.State.WALK))
			AnimationController.playerState = AnimationController.State.WALK;

	}

	public void setDir(int xdir, int ydir) {
		if (Main.getInstance().tick - weapon.cooldown >= tickLU) {
			this.xdir = xdir;
			this.ydir = ydir;
		}
	}

	public void setT(int x, int y) {
		sprite = sheet.getTexture(x, y);

	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

}
