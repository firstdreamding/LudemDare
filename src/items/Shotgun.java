package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;

public class Shotgun extends Weapon {
	Projectile p;

	public Shotgun(int id) {
		super(id, "Shotgun", "A good sturdy gun to solve all your problems!");
		damage = 7;
		cooldown = 45;
		playerHoldingState = 2;
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!(entity instanceof Player))
			return;
		for (int i = -2; i < 2; i++) {
			if (yvel == 0) {
				p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
						10 * xvel, 10 * yvel + i, 10, 400, "coin", "Ak.wav");
				Main.getInstance().level.ac.add(p, 0);
				p.playOff = true;
			} else {
				p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
						10 * xvel + i, 10 * yvel, 10, 400, "coin", "Ak.wav");
				Main.getInstance().level.ac.add(p, 0);
				p.playOff = true;
			}
		}
		p.playOff = false;
	}

}
