package items;

import entities.Entity;
import graphics.Texture;
import main.SoundPlayer;

public class Weapon extends Item {
	int damage;
	int cost;
	int id;
	public int playerHoldingState;
	public int cooldown;
	public int reload, clip, clipSize;
	public long startReload;
	public SoundPlayer reloadSound;
	public boolean isReloading;

	public Weapon(int id, String name, String description) {
		super(id, name, description);
		damage = 10;
		playerHoldingState = 0;
		texture = new Texture("/res/sprites/items/" + name.toLowerCase() + ".png", 80, 80);
	}

	public void use(Entity entity, int xvel, int yvel) {

	}
}
