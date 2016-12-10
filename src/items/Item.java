package items;

import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import graphics.Texture;



public class Item {
	int id;
	String name, description;
	Texture texture;
	
	public static Item Ak47 = new Ak47(0);
	
	public Item(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public void use(Entity entity) {
		System.out.println("This item isn't usable");
	}
}
