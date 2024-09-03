//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Velocity;
import gameArk.geomtry.Sprite;
import gameArk.geomtry.Block;
import java.util.List;

/**
 * Interface for level information.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public interface LevelInformation {
    /**
     * Number of balls in this level.
     * @return to num of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return the velocity of all the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed in this level.
     * @return the speed.
     */
    int paddleSpeed();

    /**
     * The paddle width of this level.
     * @return the width.
     */
    int paddleWidth();

    /**
     * The level name.
     * @return the name.
     */
    String levelName();

    /**
     * The background of this level.
     * @return sprite of the background.
     */
    Sprite getBackground();

    /**
     * The blocks of this level.
     * @return the blocks.d
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     * @return the number.
     */
    int numberOfBlocksToRemove();
}
