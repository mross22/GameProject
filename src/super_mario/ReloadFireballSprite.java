package super_mario;

import java.awt.image.BufferedImage;

public class ReloadFireballSprite extends PowerUpSprite {
	
	public ReloadFireballSprite(BufferedImage b){
		super(b);
	}

	@Override
	public void applyEffect(MarioSprite mario) {
		// TODO Auto-generated method stub
		mario.numFireballs = 5;
		
	}
}

