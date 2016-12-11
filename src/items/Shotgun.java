package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;
import main.SoundPlayer;

public class Shotgun extends Weapon {
	Projectile p;
	int spread;

	public Shotgun(int id) {
		super(id, "Shotgun", "A good sturdy gun to solve all your problems!");
		damage = 7;
		knock = 6;
		cooldown = 3;
		playerHoldingState = 1;
		spread = 3;
		clip = 1;
		reload = 60;
		clipSize = 1;
		reloadSound = new SoundPlayer("reload.wav");
		reloadSound.setVolume(0.3);

	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!isReloading) {
			if (!(entity instanceof Player))
				return;
			for (int i = -2; i < 2; i++) {
				if (yvel == 0) {
					p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
							20 * xvel, 20 * yvel + i * spread, damage, 400, knock, "bullet", "Ak.wav");
					Main.getInstance().level.ac.add(p, 0);
					p.playOff = true;
				} else {
					p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
							20 * xvel + i * spread, 20 * yvel, damage, 400, knock, "bullet", "Ak.wav");
					Main.getInstance().level.ac.add(p, 0);
					p.playOff = true;
				}
			}
			p.playOff = false;
			clip--;
			if (clip == 0) {
				startReload = Main.getInstance().tick;
				isReloading = true;
				clip = clipSize;
			}
		} else if (Main.getInstance().tick - startReload > reload) {
			reloadSound.play();
			isReloading = false;
		}
	}

}
