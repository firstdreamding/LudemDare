package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

import controller.InputHandler;
import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;
import main.KeyMap;
import main.MoveQueue;

public class Player extends Entity {

	public int specialbar = 0;
	public int dir;
	private int playerID;
	public int moveSpeed, special, lastHealth;
	public int traction = 1;
	public String name;
	public int gold;
	public SpriteSheet sheet;
	public MoveQueue moveQueue;

	public ArrayList<Integer> inventory;

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
		gold = 0;
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
		if (x < -30) {
			x = -30;
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
			xvel = -3;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
			xvel = 3;
		}

		if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
			yvel = -3;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_S)) {
			yvel = 3;
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
