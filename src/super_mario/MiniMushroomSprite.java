package super_mario;

import java.awt.image.BufferedImage;

public class MiniMushroomSprite extends PowerUpSprite{

	public MiniMushroomSprite(BufferedImage b) {
		super(b);
	}

	@Override
	public void applyEffect(MarioSprite mario) {
		mario.isMini = true;
		mario.setInvincible(false); //cant be both!
		mario.isMega = false;
	}

}
