package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

import controller.InputHandler;
import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
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
	long tickLU;

	private Map<String, Integer> keys;

	public Player(int pid, int x, int y, int w, int h, int dir, Texture sprite) {
		super(x, y, w, h, dir, sprite);
		inventory = new ArrayList<Integer>();
		health = 100;
		playerID = pid;
		this.dir = dir;
		special = 0;
		playern = pid;
		keys = KeyMap.getKeyMapping(playerID);
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

	private void handleInput() {
		if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
			dir = -1;
			xvel = -3;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
			dir = 1;
			xvel = 3;
		}

		if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
			dir = -2;
			yvel = -3;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_S)) {
			dir = 0;
			yvel = 3;
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			if (!Main.getInstance().level.inWave) {
				Main.getInstance().inMenu = true;
				Main.getInstance().state = Main.State.MENU;
			}
		}

		if (Main.getInstance().tick - weapon.cooldown >= tickLU) {
			if (InputHandler.isKeyPressed(KeyEvent.VK_RIGHT))
				weapon.use(this, 1, 0);
			else if (InputHandler.isKeyPressed(KeyEvent.VK_UP))
				weapon.use(this, 0, -1);
			else if (InputHandler.isKeyPressed(KeyEvent.VK_LEFT))
				weapon.use(this, -1, 0);
			else if (InputHandler.isKeyPressed(KeyEvent.VK_DOWN))
				weapon.use(this, 0, 1);
			tickLU = Main.getInstance().tick;
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
