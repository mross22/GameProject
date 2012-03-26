package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class KoopaPowerUpCollisionManager extends BasicCollisionGroup{

	@Override
	public void collided(Sprite koopa, Sprite powerUp) {
		// TODO Auto-generated method stub
		koopa.setActive(false);
	}

}
