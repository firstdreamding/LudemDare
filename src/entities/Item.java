package entities;

import java.util.HashMap;
import java.util.Map;

import graphics.Texture;



public class Item {
	public Item(int id, String name, String description, Texture texture){
		this.id = id;
		this.name = name;
		this.description = description;
		this.texture = texture;
		
		items.put(id, this);
	}
	int id;
	String name, description;
	Texture texture;
	
	private static Map<Integer, Item> items = new HashMap<Integer, Item>();
	
	public static final Item getByID(int id) {
		if (!items.containsKey(id))
			return null;
		
		return items.get(id);
	}
	
	public void use(Entity entity) {
		System.out.println("This item isn't usable");
	}
}
