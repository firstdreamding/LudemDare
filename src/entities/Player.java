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

	public Player(int pid, int x, int y, int w, int h, int dir, Texture sprite) {
		super(x, y, w, h, dir, sprite);
		playerID = 0;
		inventory = new ArrayList<Integer>();
		health = 100;
		playerID = pid;
		this.dir = dir;
		special = 0;
		xdir = 0;
		ydir = 0;
		playern = pid;
		moveQueue = new MoveQueue();
		weapon = (Weapon) Item.Ak47;
		gold = 0;
		tickLU = 0;
		// TODO Auto-generated constructor stub
	}

	public void init() {

	}

	@Override
	public void handleDeath() {
		System.out.println("dead");
	}

	public void update() {

		if (frozen) {
			if (System.currentTimeMillis() > freezeUntil) {
				frozen = false;
			}
		}
		if (x < 0) {
			x = 0;
		}
		if (x > 900 - w) {
			x = 900 - w;
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
		if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
			dir = -1;
			xvel = -3;
			walk();
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
			dir = 1;
			xvel = 3;
			walk();
		}

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
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			if (!Main.getInstance().level.wh.inWave) {
				Main.getInstance().inMenu = true;
				Main.getInstance().state = Main.State.MENU;
			}
		}

		if (Main.getInstance().tick - weapon.cooldown >= tickLU) {
			if (InputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
				weapon.use(this, 1, 0);
				tickLU = Main.getInstance().tick;
			} else if (InputHandler.isKeyPressed(KeyEvent.VK_UP)) {
				ydir = 1;
				weapon.use(this, 0, -1);
				tickLU = Main.getInstance().tick;
			} else if (InputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
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
