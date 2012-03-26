package super_mario;

import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class PowerUpSprite extends Sprite{
	private static final long serialVersionUID = 1L;
	
	public abstract void applyEffect(MarioSprite mario);
	
	public PowerUpSprite(BufferedImage b){

		super(b);
	}
}
