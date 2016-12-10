package entities;

import graphics.Texture;

public class Weapon extends Item{
	int damage;
	int cost;
	int id;
	int playerHoldingState;
	int cooldown;

	public Weapon(int id, String name, String description, Texture texture){
		super(id, name, description, texture);
		damage = 10;
		playerHoldingState = 0;
	}
}
