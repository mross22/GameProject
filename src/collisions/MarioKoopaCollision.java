package collisions;

import super_mario.MarioGame;
import super_mario.MarioSprite;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class MarioKoopaCollision extends BasicCollisionGroup{

	public MarioKoopaCollision(){
		
	}

	@Override
	public void collided(Sprite spriteA, Sprite spriteB) {
		// TODO Auto-generated method stub		
		MarioSprite mario = (MarioSprite) spriteA;
		mario.isMini = false;
		if(!mario.invincible()){
			mario.HP -= mario.koopaDamage;
			MarioGame.points -= 30;
		}
		else{
			mario.hitWhileInvincible++;
			if(mario.hitWhileInvincible == 3){
				mario.hitWhileInvincible = 0;
				mario.setInvincible(false);
			}
		}
		//spriteA.setActive(false);
		spriteB.setActive(false);
		System.out.println("collided");
		
	}
}
