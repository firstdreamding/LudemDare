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
		playerHoldingState = 1;
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!(entity instanceof Player))
			return;
		for (int i = -2; i < 2; i++) {
			if (yvel == 0) {
				p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
						20 * xvel, 20 * yvel + i, 10, 400, "bullet", "Ak.wav");
				Main.getInstance().level.ac.add(p, 0);
				p.playOff = true;
			} else {
				p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
						20 * xvel + i, 20 * yvel, 10, 400, "bullet", "Ak.wav");
				Main.getInstance().level.ac.add(p, 0);
				p.playOff = true;
			}
		}
		p.playOff = false;
	}

}
