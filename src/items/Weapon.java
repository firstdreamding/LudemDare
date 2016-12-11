package items;

import entities.Entity;
import graphics.Texture;
import main.SoundPlayer;

public class Weapon extends Item{
	int damage;
	int cost;
	int id;
	int playerHoldingState;
	public int reload, clipSize, clip;
	public long startReload;
	public SoundPlayer reloadSound;
	public boolean isReloading;
	public int cooldown;

	public Weapon(int id, String name, String description){
		super(id, name, description);
		damage = 10;
		playerHoldingState = 0;
		reload = 0;
		clipSize = 0;
		clip = clipSize;
		isReloading = false;
		texture = new Texture("/res/sprites/items/ak47.png", 80, 80);
	}
	public void use(Entity entity, int xvel, int yvel){
		
	}
}
