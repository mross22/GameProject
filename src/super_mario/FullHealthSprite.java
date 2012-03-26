package super_mario;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;

public class FullHealthSprite extends PowerUpSprite {

	public FullHealthSprite(BufferedImage b){
		super(b);
	}

	@Override
	public void applyEffect(MarioSprite mario) {
		// TODO Auto-generated method stub
		mario.HP = mario.maxHP;
		
	}
}
