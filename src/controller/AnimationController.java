package controller;

import entities.Player;
import graphics.SpriteSheet;
import main.Main;

public class AnimationController {
	public enum State {
		NONE, MOVE, WALK, DEAD;
	};
	
	public static State playerState = State.DEAD;
	int animationTick, counter;
	long lastTick;
	public AnimationController(){
		animationTick = 0;
		counter = 0;
		lastTick = 0;
	}
	
	public void update(SpriteSheet playerSprites, Player player){
		if (playerState == State.WALK){
			player.setT(playerSprites.getTexture(counter%4, player.weapon.playerHoldingState * 4 + player.ydir));
		}
		if(Main.getInstance().tick - lastTick > 5){
			counter++;
			lastTick =  Main.getInstance().tick;
		}
		if (playerState == State.NONE){
			player.setT(playerSprites.getTexture(1, player.weapon.playerHoldingState * 4 + player.ydir));
		}
	}
	public static void setNone(Player player) {
		playerState = State.NONE;		
	}
}
