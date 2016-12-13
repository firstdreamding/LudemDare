package menu;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import graphics.Texture;
import main.Main;

public class StartMenu extends Menu{
	Texture bg;
	int selected;
	public StartMenu(){
		bg = new Texture("/res/sprites/start.png", 960, 640);
		selected = 0;
	}
	public void update(){
		if (InputHandler.isKeyTyped(KeyEvent.VK_S)){
			bg = new Texture("/res/sprites/start3.png", 960, 640);
			selected = 1;
		}
		if (InputHandler.isKeyTyped(KeyEvent.VK_W)){
			bg = new Texture("/res/sprites/start2.png", 960, 640);
			selected = 0;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_ENTER))
			selected();
	}
	
	private void selected() {
		if (selected == 0)
			Main.getInstance().state = Main.State.GAME;
		else
			Main.getInstance().start = new CreditMenu();
		
	}
	public void render(Screen screen){
		screen.drawTexture(0,0,bg);
	}
}
