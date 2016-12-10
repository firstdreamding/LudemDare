package items;

import graphics.Texture;

public class Weapon extends Item{
	int damage;
	int cost;
	int id;
	int playerHoldingState;
	int cooldown;

	public Weapon(int id, String name, String description){
		super(id, name, description);
		damage = 10;
		playerHoldingState = 0;
	}
}
