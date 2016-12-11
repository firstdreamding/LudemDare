package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;

public class Ak47 extends Weapon {
	public Ak47(int id) {
		super(id, "Ak47", "A modern technology of goodness");
		damage = 5;
		cooldown = 15;
		clipSize = 20;
		reload = 180;
		clip = clipSize;
		isReloading = false;
		playerHoldingState = 2;
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!isReloading || Main.getInstance().tick - startReload > reload) {
			if (!(entity instanceof Player))
				return;
			Projectile p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
					10 * xvel, 10 * yvel, 10, 2000, "coin", "Ak.wav");
			Main.getInstance().level.ac.add(p, 0);
			clip--;
			if (clip == 0) {
				startReload = Main.getInstance().tick;
				isReloading = true;
				clip = clipSize;
			}
		}
	}
}
