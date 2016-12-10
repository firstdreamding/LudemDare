package entities;
import graphics.Texture;

public class Ak47 extends Weapon{
	public Ak47(int id){
		super(id,"Ak47", "A modern technology of goodness");
		damage = 5;
		cooldown = 30;
		playerHoldingState = 2;
	}
	
	public void use(Entity entity){
		 if (!(entity instanceof Player)) return;
		 
	}
}
