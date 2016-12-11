package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;
import main.SoundPlayer;

public class Pistol extends Weapon {
	public Pistol(int id) {
		super(id, "Pistol", "A modern technology of goodness");
		damage = 3;
		cooldown = 30;
		clipSize = 8;
		reload = 60;
		playerHoldingState = 1;
		clip = clipSize;
		reloadSound = new SoundPlayer("reload.wav");
		reloadSound.setVolume(0.5);
		isReloading = false;
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!isReloading) {
			if (!(entity instanceof Player))
				return;
			Projectile p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
					20 * xvel, 20 * yvel, 10, 2000, "bullet", "Ak.wav");
			Main.getInstance().level.ac.add(p, 0);
			clip--;
			if (clip == 0) {
				startReload = Main.getInstance().tick;
				isReloading = true;
				clip = clipSize;
			}
		} else if(Main.getInstance().tick-startReload > reload){
			reloadSound.play();
			isReloading = false;
		}
	}
}
