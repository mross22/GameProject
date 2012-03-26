package super_mario;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class MarioSprite extends Sprite{

	private static final long serialVersionUID = 1L;
	private boolean isInvincible = false;
	public int maxHP = 100;
	public int HP;
	public int koopaDamage = 20;
	public int hitWhileInvincible = 0;
	public int numFireballs = 5;
	public int points = 0;
	public int fireballSpeed = 2;
	public boolean isMini = false;
	public boolean isMega = false;
	
	
	public MarioSprite(BufferedImage b){
		super(b);
		HP = maxHP;
	}
	
	public void setInvincible(boolean invincible){
		isInvincible = invincible;
	}
	
	public boolean invincible(){
		return isInvincible;
	}
	
	

}
