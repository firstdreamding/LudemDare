package items;

import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import graphics.Texture;

public class Item {
	int id;
	public int cost, damage, reload, cooldown;
	public String name;
	public String description;
	public Texture texture, shopIcon;
	public boolean needBuyOnce, haveBought;
	public int maxId = 5;

	public static Map<Integer, Item> items = new HashMap<Integer, Item>();
	public static Item Ak47 = new Ak47(2, false);
	public static Item Shotgun = new Shotgun(1, false);
	public static Item Pistol = new Pistol(0, false);
	public static Item GoldenPistol = new Pistol(3,true);
	public static Item GoldenShotgun = new Shotgun(4, true);
	public static Item GoldenAk47 = new Ak47(5, true);

	public Item(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		haveBought = false;
		items.put(id, this);
	}

	public void use(Entity entity) {
		System.out.println("This item isn't usable");
	}
	
	public void bought(){
		haveBought = true;
	}
	
	public static final Item getByID(int id) {
		if (!items.containsKey(id))
			return null;
		
		return items.get(id);
	}
}
