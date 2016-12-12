package menu;

import java.awt.Color;
import java.awt.Font;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import items.Item;
import main.Main;

public class GoldMenu extends Menu {

	Font defFont, arrowFont, title, description;
	int itemSelected;

	public GoldMenu() {
		title = new Font("Garamond", 1, 50);
		defFont = new Font("Garamond", 1, 80);
		description = new Font("Garamond", 1, 25);
		itemSelected = 2;
	}

	public void update() {
		if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			Main.getInstance().inMenu = false;
			Main.getInstance().state = Main.State.GAME;
		}

		if (InputHandler.isKeyTyped(KeyEvent.VK_A) && itemSelected > 0) {
			itemSelected--;
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_D) && itemSelected < 2)
			itemSelected++;
		if (InputHandler.isKeyTyped(KeyEvent.VK_ENTER)) {
			if (Item.getByID(itemSelected).needBuyOnce && Item.getByID(itemSelected).haveBought){
				Main.getInstance().level.player.changeWeapon(itemSelected);
			}else if(Main.getInstance().level.player.gold >= Item.getByID(itemSelected).cost) {
				Main.getInstance().level.player.changeWeapon(itemSelected);
				Main.getInstance().level.player.addGold(Item.getByID(itemSelected).cost * -1);
				Item.getByID(itemSelected).bought();
			} else
				System.out.println("Costs too much");
			System.out.println(Main.getInstance().level.player.gold);
			System.out.println(Item.getByID(itemSelected).cost);
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_0)) {
			Main.getInstance().level.player.addGold(10);
		}
	}

	public void render(Screen screen) {
		screen.drawRect(Main.getInstance().WINDOWX / 2 - 100, Main.getInstance().WINDOWY / 2 - 100, 200, 200, 0x000000);
		screen.drawString("Gold Shop", 115, 170, defFont, Color.YELLOW);
		if (itemSelected > 0)
			screen.drawString("<", Main.getInstance().WINDOWX / 2 - 200, Main.getInstance().WINDOWY / 2 + 40, arrowFont,
					Color.BLACK);
		if (itemSelected < 2)
			screen.drawString(">", Main.getInstance().WINDOWX / 2 + 150, Main.getInstance().WINDOWY / 2 + 40, arrowFont,
					Color.BLACK);

		Main.getInstance().level.gui(screen);

		screen.drawTexture(Main.getInstance().WINDOWX / 2 - 100, Main.getInstance().WINDOWY / 2 - 100,
				Item.getByID(itemSelected).texture);
		screen.drawString(Item.getByID(itemSelected).name, Main.getInstance().WINDOWX / 2 + 200,
				Main.getInstance().WINDOWY / 2 - 100, title, Color.BLACK);
		screen.drawString(Item.getByID(itemSelected).description, 300, Main.getInstance().WINDOWY / 2 + 200,
				description, Color.BLACK);

	}

	public void reset() {

	}

}
