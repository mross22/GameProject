import java.awt.Dimension;

import super_mario.MarioGame;
//import bounce.BounceGame;
import com.golden.gamedev.GameLoader;


public class Main
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new MarioGame(), new Dimension(800, 350), false);
        loader.start();
    }
}
