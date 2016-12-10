package entities;

import java.awt.event.KeyEvent;
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
	public int gold;
	private int playerID;
	public int moveSpeed, special, lastHealth, tolerance;
	public int traction = 4;
	public String name;
	public SpriteSheet sheet;
	public MoveQueue moveQueue;

	private Map<String, Integer> keys;

	public Player(int pid, int x, int y, int w, int h, int dir, Texture sprite) {
		super(x, y, w, h, dir, sprite);
		gold = 0;
		health = 100;
		playerID = pid;
		this.dir = dir;
		special = 0;
		playern = pid;
		keys = KeyMap.getKeyMapping(playerID);
		moveQueue = new MoveQueue();
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

		handleInput();
	}

	private boolean keyPress(String string) {
		// return
		// InputHandler.isKeyPressed(KeyMap.p1Set.valueOf(string.toUpperCase()).key);
		return InputHandler.isKeyTyped(keys.get(string));
	}

	public void render(Screen screen) {
		screen.drawTexture(x, y, sprite, dir == -1);
	}

	public void add(int playerID, String move) {
		moveQueue.add(playerID, move);
	}

	private void handleInput() {
		if (InputHandler.isKeyTyped(KeyEvent.VK_A)) {
			System.out.println("right");
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
