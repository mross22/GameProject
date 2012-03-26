package super_mario;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class KoopaSprite extends Sprite{

	private static final long serialVersionUID = 1L;
	private boolean isJumping = false;
	public int maxHP = 100;
	public int HP;
	
	public KoopaSprite(BufferedImage b){
		super(b);
		HP = maxHP;
	}
}

