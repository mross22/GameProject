package collisions;

import super_mario.FullHealthSprite;
import super_mario.MarioGame;
import super_mario.MarioSprite;
import super_mario.PowerUpSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class MarioPowerUpCollisionManager extends BasicCollisionGroup{

	public MarioPowerUpCollisionManager(){
		
	}

	@Override
	public void collided(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub
		MarioSprite mario = (MarioSprite) spriteA;
		PowerUpSprite powerUp = (PowerUpSprite) spriteB;
		powerUp.applyEffect(mario);		
		spriteB.setActive(false);
		MarioGame.points += 10;
	}
}