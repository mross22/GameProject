package super_mario;

import java.awt.image.BufferedImage;

public class MegaMushroomSprite extends PowerUpSprite{
	public MegaMushroomSprite(BufferedImage b) {
		super(b);
	}

	@Override
	public void applyEffect(MarioSprite mario) {
		mario.isMini = false;
		mario.setInvincible(false); //cant be both!
		mario.isMega = true;
	}
}
