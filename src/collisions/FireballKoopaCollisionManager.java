package collisions;

import super_mario.FullHealthSprite;
import super_mario.MarioGame;
import super_mario.MarioSprite;
import super_mario.PowerUpSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class FireballKoopaCollisionManager extends BasicCollisionGroup{

	public FireballKoopaCollisionManager(){
		
	}

	@Override
	public void collided(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub
		spriteB.setActive(false);
		spriteA.setActive(false);
		MarioGame.points += 30;
	}
}