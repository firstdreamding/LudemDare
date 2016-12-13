package items;

import entities.Entity;
import graphics.Texture;
import main.SoundPlayer;

public class Weapon extends Item {

	int knock;
	int id;
	public int playerHoldingState;

	public int clip, clipSize;
	public long startReload;
	public SoundPlayer reloadSound;
	public boolean isReloading;
	public boolean isGolden;

	public Weapon(int id, String name, String description) {
		super(id, name, description);
		needBuyOnce = true;
		damage = 10;
		playerHoldingState = 0;
		texture = new Texture("/res/sprites/items/" + name.toLowerCase() + ".png", 80, 80);
		shopIcon = new Texture("/res/sprites/items/" + name.toLowerCase() + ".png", 160, 160);
	}

	public void use(Entity entity, int xvel, int yvel) {

	}
}
