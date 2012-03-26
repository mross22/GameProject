package collisions;

import super_mario.MarioSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class MarioObstacleCollision extends BasicCollisionGroup{

	public MarioObstacleCollision(){
		
	}

	@Override
	public void collided(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub
		MarioSprite s1 = (MarioSprite) spriteA;
	}
}