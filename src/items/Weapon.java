package items;

import entities.Entity;
import graphics.Texture;

public class Weapon extends Item{
	int damage;
	int cost;
	int id;
	int playerHoldingState;
	public int cooldown;

	public Weapon(int id, String name, String description){
		super(id, name, description);
		damage = 10;
		playerHoldingState = 0;
	}
	
	public void use(Entity entity, int xvel, int yvel){
		
	}
}
