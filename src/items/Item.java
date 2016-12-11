package items;

import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import graphics.Texture;

public class Item {
	int id;
	String name, description;
	public Texture texture;

	public static Map<Integer, Item> items = new HashMap<Integer, Item>();
	public static Item Ak47 = new Ak47(0);
	public static Item Shotgun = new Shotgun(1);
	public static Item Pistol = new Pistol(2);

	public Item(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		items.put(id, this);
	}

	public void use(Entity entity) {
		System.out.println("This item isn't usable");
	}
	
	public static final Item getByID(int id) {
		if (!items.containsKey(id))
			return null;
		
		return items.get(id);
	}
}
