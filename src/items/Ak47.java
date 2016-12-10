package items;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;

public class Ak47 extends Weapon{
	public Ak47(int id){
		super(id,"Ak47", "A modern technology of goodness");
		damage = 5;
		cooldown = 30;
		playerHoldingState = 2;
	}
	
	public void use(Entity entity){
		 if (!(entity instanceof Player)) return;
		 Projectile p = new Projectile(10, 10, 20, 20, 10, 0, 10, 2000, "coin");
		 Main.getInstance().level.pc.add(p, 0);
	}
}
