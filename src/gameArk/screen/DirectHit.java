//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Block;
import gameArk.geomtry.Sprite;
import gameArk.geomtry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * DirectHit level.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class DirectHit implements LevelInformation {
    private static final int WIDTH = 800, HEIGHT = 600, CENTER_WIDTH = 390,
            CENTER_HEIGHT = 150, SIZE = 30;
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> vel = new ArrayList<>();
        vel.add(new Velocity(0, -8));
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Spider();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new Block(CENTER_WIDTH, CENTER_HEIGHT, SIZE, SIZE));
        blocks.get(0).setColor(Color.red);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
