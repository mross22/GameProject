package super_mario;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import collisions.FireballKoopaCollisionManager;
import collisions.KoopaPowerUpCollisionManager;
import collisions.MarioKoopaCollision;
import collisions.MarioObstacleCollision;
import collisions.MarioPowerUpCollisionManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;

public class MarioGame extends Game{
	 
	public static int points = 0;
	
	 private MarioSprite myMario;
	 
	 //
	 private List<PowerUpSprite> powerUpSpriteList;
	 private boolean underWater = false;
	 
	 //Groups
	 private SpriteGroup marioGroup;
	 private SpriteGroup koopaGroup;
	 private SpriteGroup obstacleGroup;
	 private SpriteGroup powerUpGroup;
	 private SpriteGroup fireballGroup;
	 
	 //Scrolling and timing
	 private Timer screenScrollTimer;
	 private double leftBoundary;
	 
	 //Gameplay parameters
	 private int level;
	 private double scrollSpeed;
	 private double moveSize;
	 private double koopaSpeed;
	 private double koopaGenerateTime;
	 private double powerUpGenerateTime;
	 private double fishSpeed;
	 
	 //Collisions
	 private CollisionManager marioKoopaCollisionManager;
	 private CollisionManager marioObstacleCollisionManager;
	 private CollisionManager marioPowerUpCollisionManager;
	 private CollisionManager koopaPowerUpCollisionManager;
	 private CollisionManager fireballKoopaCollisionManager;


	    @Override
	    public void initResources ()
	    {
	    	underWater = false;
	    	//Initialize variables
	    	screenScrollTimer = new Timer(50);
	    	scrollSpeed = 6;
	    	moveSize = 3;
	    	leftBoundary = 0;
	    	level = 0;
	    	koopaSpeed = 1;
	    	fishSpeed = 0.6;
	    	
	    	koopaGenerateTime = 7;
	    	powerUpGenerateTime = 30;    	
	    		    		    	
	    	//Create groups
	    	marioGroup = new SpriteGroup("Mario Group");
	    	koopaGroup = new SpriteGroup("Koopa Group");
	    	obstacleGroup = new SpriteGroup("Obstacle Group");
	    	powerUpGroup = new SpriteGroup("PowerUp Group");
	    	fireballGroup = new SpriteGroup("Fireball Group");
	    	
	    	//Initialize myMario
	    	myMario = new MarioSprite(getImage("PenguinMario.png"));
	        myMario.setLocation(getWidth() / 2 - myMario.getWidth() / 2, getHeight() - myMario.getHeight() - 50);

	    	//Add sprites to groups
	    	marioGroup.add(myMario);
	    	
	    	//Set up collisions
	    	marioKoopaCollisionManager = new MarioKoopaCollision();
	    	marioKoopaCollisionManager.setCollisionGroup(marioGroup, koopaGroup);
	    	
	    	marioObstacleCollisionManager = new MarioObstacleCollision();
	    	marioObstacleCollisionManager.setCollisionGroup(marioGroup, obstacleGroup);
	    	
	    	marioPowerUpCollisionManager = new MarioPowerUpCollisionManager();
	    	marioPowerUpCollisionManager.setCollisionGroup(marioGroup, powerUpGroup);
	    	
	    	koopaPowerUpCollisionManager = new KoopaPowerUpCollisionManager();
	    	koopaPowerUpCollisionManager.setCollisionGroup(koopaGroup, powerUpGroup);
	    	
	    	fireballKoopaCollisionManager = new FireballKoopaCollisionManager();
	    	fireballKoopaCollisionManager.setCollisionGroup(fireballGroup, koopaGroup);
     
	        System.out.println("Width: " + getWidth());
	        System.out.println("Height: " + getHeight());
	    }

	    @Override
	    public void render (Graphics2D pen)
	    {	        
	    	
	    	if(level == 0){
	    		ImageBackground background = new ImageBackground(getImage("splash.png"));
	    		points = 0;
	    		background.render(pen);
	    	}
	    	else if(level == 1){
		    	ImageBackground background = new ImageBackground(getImage("SuperMarioBrosMap1-1BG.png"));	    	
		    	background.setLocation(leftBoundary, 0);
		    	//Render background and characters
		  
		    	background.render(pen);
		      	myMario.render(pen);
		      	koopaGroup.render(pen);
		      	powerUpGroup.render(pen);
		      	fireballGroup.render(pen);
		    	//marioGroup.render(pen);
		      	fontManager.getFont("FPS Font").drawString(pen, "MARIO HP: " + myMario.HP +" / " + myMario.maxHP+ 
		      					"          FIREBALLS: " + myMario.numFireballs + 
		      					"          POINTS: "+ MarioGame.points, 0, 10);
		      	
	    	}
	    	else if(level == 2){
	    		ImageBackground background = new ImageBackground(getImage("splash2.png"));
	    		background.render(pen);
	    	}
	    	else if(level == 3){
	    		ImageBackground background = new ImageBackground(getImage("SuperMarioBrosMap2-2.png"));	    	
		    	background.setLocation(leftBoundary, 0);
		    	//Render background and characters
		  
		    	background.render(pen);
		      	myMario.render(pen);
		      	koopaGroup.render(pen);
		      	powerUpGroup.render(pen);
		      	fireballGroup.render(pen);
		    	//marioGroup.render(pen);
		      	fontManager.getFont("FPS Font").drawString(pen, "MARIO HP: " + myMario.HP +" / " + myMario.maxHP+ 
      					"          FIREBALLS: " + myMario.numFireballs + 
      					"          POINTS: "+ MarioGame.points, 0, 10);
	    	
	    	}
	    	else if(level == 4){
	    		ImageBackground background = new ImageBackground(getImage("splashGameComplete.png"));
	    		background.render(pen);
	    		fontManager.getFont("FPS Font").drawString(pen, "POINTS: "+ MarioGame.points, 0, 10);

	    	}
	    	else if(level == -1){
	    		ImageBackground background = new ImageBackground(getImage("loseSplash.png"));
	    		background.render(pen);
	    		fontManager.getFont("FPS Font").drawString(pen, "POINTS: "+ MarioGame.points, 0, 10);
	    	}
	       
	    }

	    @Override
	    public void update (long elapsedTime)
	    {
	        if(level == 0){	    	
		    	updateSplashPage(elapsedTime);
	        }
	        else if (level == 1){
	        	// update sprites based on their current state
	        	
		        myMario.update(elapsedTime);
		        koopaGroup.update(elapsedTime);
		        powerUpGroup.update(elapsedTime);
		        fireballGroup.update(elapsedTime);
		        
		        if(myMario.invincible()){
		        	myMario.setImage(getImage("PenguinMarioInvincible.png"));
		        }
		        else if(myMario.isMini) {
		        	myMario.setImage(getImage("PenguinMarioMini.png"));
		        }
		        else if(myMario.isMega){
		        	myMario.setImage(getImage("PenguinMarioMega.png"));
		        }
		        else myMario.setImage(getImage("PenguinMario.png"));
		        
		        if(myMario.HP <= 0){
		        	myMario.setActive(false);
		        	level = -1;
		        }
		        
		        //Check for all collisions
		        marioKoopaCollisionManager.checkCollision();
		        marioObstacleCollisionManager.checkCollision();
		        marioPowerUpCollisionManager.checkCollision();
		        koopaPowerUpCollisionManager.checkCollision();
		        fireballKoopaCollisionManager.checkCollision();
		        
		        spriteCheckForMove(myMario);
		        spriteCheckForFire(myMario);
		        
		        //Update left boundary
		        if(screenScrollTimer.action(elapsedTime)){
		        	leftBoundary += scrollSpeed;
		        	if(leftBoundary % (scrollSpeed * koopaGenerateTime) == 0){
			        	generateKoopa(elapsedTime);
			        }
		        	if(leftBoundary % (scrollSpeed * powerUpGenerateTime) == 0){
			        	generatePowerUp(elapsedTime);
			        }
		        }
		        
		        if(leftBoundary >= 4410){
		        	initResources();
		        	level = 2;
		        }
		        System.out.println(leftBoundary);		        		        
	        }
	        else if (level == 2){
	        	updateSplashPage2(elapsedTime);
	        	leftBoundary = 0;
	        }
	        else if (level == 3){ // really level 2
	        	
	        	
		        myMario.update(elapsedTime);
		        koopaGroup.update(elapsedTime);
		        powerUpGroup.update(elapsedTime);
		        fireballGroup.update(elapsedTime);
		        
		        //underwater level
		        myMario.setVerticalSpeed(.05);
		        underWater = true;
		        
		        if(myMario.invincible()) myMario.setImage(getImage("PenguinMarioInvincible.png"));
		        else myMario.setImage(getImage("PenguinMario.png"));
		        
		        if(underWater && myMario.getY() > getHeight() ){
		        	level = -1;
		        }
		        
		        if(myMario.HP <= 0){
		        	myMario.setActive(false);
		        	level = -1;
		        }		        
		        
		        if(myMario.invincible()){
		        	myMario.setImage(getImage("PenguinMarioInvincible.png"));
		        }
		        else if(myMario.isMini) {
		        	myMario.setImage(getImage("PenguinMarioMini.png"));
		        }
		        else if(myMario.isMega){
		        	myMario.setImage(getImage("PenguinMarioMega.png"));
		        }
		        else myMario.setImage(getImage("PenguinMario.png"));		        
		        
		        //Check for all collisions
		        marioKoopaCollisionManager.checkCollision();
		        marioObstacleCollisionManager.checkCollision();
		        marioPowerUpCollisionManager.checkCollision();
		        koopaPowerUpCollisionManager.checkCollision();
		        fireballKoopaCollisionManager.checkCollision();
		        
		        spriteCheckForMove(myMario);
		        spriteCheckForFire(myMario);
		        
		        //Update left boundary
		        if(screenScrollTimer.action(elapsedTime)){
		        	leftBoundary += scrollSpeed;
		        	if(leftBoundary % (scrollSpeed * koopaGenerateTime) == 0){
			        	if(underWater){
			        		generateFish(elapsedTime);
			        	}
			        	else{
			        		generateKoopa(elapsedTime);
			        		
			        	}
			        }
		        	if(leftBoundary % (scrollSpeed * powerUpGenerateTime) == 0){
			        	generatePowerUp(elapsedTime);
			        }
		        }
		        
		        if(leftBoundary >= 4300){
		        	level = 4;
		        }
		        System.out.println(leftBoundary);	
	        }
	        else if(level == 4){
	        	updateLoseSplashPage(elapsedTime); // if you win or lose it will call this same function just different picture
	        }
	        else if (level == -1){
	        	updateLoseSplashPage(elapsedTime);
	        }
	    }
	    
	    public void updateSplashPage(long elapsedTime){
	    	if(keyDown(KeyEvent.VK_ENTER)){
	    		//initResources();
	    		level = 1;
	    	}
	    }
	    
	    public void updateSplashPage2(long elapsedTime){
	    	if(keyDown(KeyEvent.VK_ENTER)){
	    		level = 3;
	    	}
	    }
	    
	    public void updateLoseSplashPage(long elapsedTime){
	    	if(keyDown(KeyEvent.VK_ENTER)){
	    		initResources();
	    	}
	    }
	    
	    
	    public void generateKoopa(long elapsedTime){
	    		KoopaSprite k = new KoopaSprite(getImage("Mad_Koopa.png"));
	    		
	    		double speedFactor = Math.random();
	    		double ylocation = Math.random() * (getHeight() - k.getHeight() - 50);
	    		if(speedFactor < .4) speedFactor = .4; //set a lower limit on speed reduction
	    		
	    		k.setLocation(getWidth()-k.getWidth(),ylocation);
	    		k.addHorizontalSpeed(elapsedTime, 1, -speedFactor * koopaSpeed);
	    		koopaGroup.add(k);
	    }
	    
	    public void generateFish(long elapsedTime){
		    	KoopaSprite k = new KoopaSprite(getImage("fish.png"));

	    		double speedFactor = Math.random();
	    		double ylocation = Math.random() * (getHeight() - k.getHeight() - 50);
	    		if(speedFactor < .3) speedFactor = .3; //set a lower limit on speed reduction
	    		
	    		k.setLocation(getWidth()-k.getWidth(),ylocation);
	    		k.addHorizontalSpeed(elapsedTime, 1, -speedFactor * fishSpeed);
	    		koopaGroup.add(k);
	    }
	    
	    public void generatePowerUp(long elapsedTime){
	    	//Add all types of power ups to list and weight them
	    	powerUpSpriteList = new ArrayList<PowerUpSprite>();
	    	powerUpSpriteList.add(new FullHealthSprite(getImage("heart.png")));
	    	powerUpSpriteList.add(new InvincibleSprite(getImage("star.png")));
	    	powerUpSpriteList.add(new InvincibleSprite(getImage("star.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new ReloadFireballSprite(getImage("fireball_reload.png")));
	    	powerUpSpriteList.add(new MiniMushroomSprite(getImage("minimushroom.png")));
	    	powerUpSpriteList.add(new MiniMushroomSprite(getImage("minimushroom.png")));
	    	powerUpSpriteList.add(new MegaMushroomSprite(getImage("megamushroom.png")));
	    	powerUpSpriteList.add(new MegaMushroomSprite(getImage("megamushroom.png")));
	    	powerUpSpriteList.add(new MegaMushroomSprite(getImage("megamushroom.png")));
	    	
	    	
	    	
	    	int typePowerUp = (int) Math.floor(Math.random() * (powerUpSpriteList.size()));
	    	System.out.println("Generate power up: " + typePowerUp);
	    	
	    	PowerUpSprite powerUp = powerUpSpriteList.get(typePowerUp);
    		double speedFactor = Math.random();
    		double ylocation = Math.random() * (getHeight() - powerUp.getHeight() - 50);
    		if(speedFactor < .3) speedFactor = .3; //set a lower limit on speed reduction
    		
    		powerUp.setLocation(700,ylocation);
    		powerUp.addHorizontalSpeed(elapsedTime, 1, -speedFactor * koopaSpeed);
    		powerUpGroup.add(powerUp);
	    	
	    }
	    
	    public void spriteCheckForFire(MarioSprite mario){
	    	if(keyPressed(KeyEvent.VK_SPACE) && mario.numFireballs > 0){
	    		Sprite fireball = new Sprite(getImage("fireball.png"));
	    		fireball.setLocation(mario.getX() + mario.getWidth(), mario.getY() + mario.getHeight()/2);
	    		fireball.setHorizontalSpeed(mario.fireballSpeed);
	    		fireballGroup.add(fireball);
	    		mario.numFireballs--;
	    	}
	    }
	    
	    public void spriteCheckForMove(Sprite sprite){	    	
	    	
	    	if(keyDown(KeyEvent.VK_UP) && (sprite.getY() >= 0) ){
	    		sprite.moveY(-moveSize);
	    		System.out.println("move up");
	        }
	        if(keyDown(KeyEvent.VK_RIGHT) && (sprite.getX() + sprite.getWidth() <= getWidth())){
	        	sprite.moveX(moveSize);        	
	        }
	        if(keyDown(KeyEvent.VK_DOWN) && (sprite.getY() + sprite.getHeight() <= getHeight() - 50)){
	        	sprite.moveY(moveSize);        	
	        }
	        if(keyDown(KeyEvent.VK_LEFT) && (sprite.getX() >= 0)){
	        	sprite.moveX(-moveSize);        	
	        }
	    }
	    

	    

	}
