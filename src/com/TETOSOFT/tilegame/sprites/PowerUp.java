package com.TETOSOFT.tilegame.sprites;

import java.lang.reflect.Constructor;
import com.TETOSOFT.graphics.*;
import com.TETOSOFT.tilegame.GameEngine;

/**
    A PowerUp class is a Sprite that the player can pick up.
*/
public abstract class PowerUp extends Sprite {

    public PowerUp(Animation anim) {
        super(anim);
    }

    public Object clone() {
        // use reflection to create the correct subclass
        Constructor constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(
                new Object[] {(Animation)anim.clone()});
        }
        catch (Exception ex) {
            // should never happen
            ex.printStackTrace();
            return null;
        }
    }


    /**
        A Star PowerUp. Gives the player points.
    */
    public static class Star extends PowerUp {
        public Star(Animation anim) {
            super(anim);
        }

		public void acquirePowerUp(GameEngine gameEngine) {
			gameEngine.setCollectedStars(gameEngine.getCollectedStars() + 1);
			if (gameEngine.getCollectedStars() == 100) {
				gameEngine.setNumLives(gameEngine.getNumLives() + 1);
				gameEngine.setCollectedStars(0);
			}
		}
    }


    /**
        A Music PowerUp. Changes the game music.
    */
    public static class Music extends PowerUp {
        public Music(Animation anim) {
            super(anim);
        }

		public void acquirePowerUp(GameEngine gameEngine) {
		}
    }


    /**
        A Goal PowerUp. Advances to the next map.
    */
    public static class Goal extends PowerUp {
        public Goal(Animation anim) {
            super(anim);
        }

		public void acquirePowerUp(GameEngine gameEngine) {
			gameEngine.setMap(gameEngine.getMapLoader().loadNextMap());
		}
    }


	public abstract void acquirePowerUp(GameEngine gameEngine);

}
