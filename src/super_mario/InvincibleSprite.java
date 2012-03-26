package super_mario;

import java.awt.image.BufferedImage;

public class InvincibleSprite extends PowerUpSprite {
	
	public InvincibleSprite(BufferedImage b){
		super(b);
	}

	@Override
	public void applyEffect(MarioSprite mario) {
		// TODO Auto-generated method stub
		mario.isMini = false;
		mario.setInvincible(true);
		mario.isMega = false;
	}
}
