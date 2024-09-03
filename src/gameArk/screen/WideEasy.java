//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Block;
import gameArk.geomtry.Sprite;
import gameArk.geomtry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The Wide easy level.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class WideEasy implements LevelInformation {
    private static final int NUM_OF_BALLS = 10, WIDTH = 800, HEIGHT = 600,
            NUM_OF_BLOCKS = 15, CORNER_HEIGHT = 210, BORDER_WIDTH = 17,
            BLOCK_HEIGHT = 20, BLOCK_WIDTH =
            (WIDTH - 2 * BORDER_WIDTH) / NUM_OF_BLOCKS;
    private static final double INITEL_SPEED = 10, ANGLE = -45, ANGLE_PLUS =
            (90 + ANGLE) / (NUM_OF_BALLS / 2);
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> vel = new ArrayList<>();
        double angle = ANGLE;
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            if (i == NUM_OF_BALLS / 2) {
                angle += ANGLE_PLUS;
            }
            vel.add(Velocity.fromAngleAndSpeed(angle, INITEL_SPEED));
            angle += ANGLE_PLUS;
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Block face = new Block(0, 0, WIDTH, HEIGHT);
        face.setColor(Color.white);
        return new Sun();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        double width = BORDER_WIDTH;
        Color[] colors = makeColors();
        int color = 0;
        for (int i = 0; i < NUM_OF_BLOCKS / 2; i++) {
            Block b = new Block(width, CORNER_HEIGHT, BLOCK_WIDTH,
                    BLOCK_HEIGHT);
            width += BLOCK_WIDTH;
            b.setColor(colors[color], Color.black);
            blocks.add(b);
            if (i % 2 == 1) {
                color++;
            }
        }
        Block b = new Block(width, CORNER_HEIGHT, BLOCK_WIDTH + 1,
                BLOCK_HEIGHT);
        width += BLOCK_WIDTH + 1;
        b.setColor(colors[color], Color.black);
        blocks.add(b);
        for (int i = NUM_OF_BLOCKS / 2 + 1; i < NUM_OF_BLOCKS; i++) {
            b = new Block(width, CORNER_HEIGHT, BLOCK_WIDTH,
                    BLOCK_HEIGHT);
            width = BLOCK_WIDTH + width;
            b.setColor(colors[color], Color.black);
            blocks.add(b);
            if (i % 2 == 0) {
                color++;
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
    private java.awt.Color[] makeColors() {
        java.awt.Color[] buty = new Color[7];
        buty[0] = Color.red;
        buty[1] = Color.orange;
        buty[2] = Color.yellow;
        buty[3] = Color.green;
        buty[4] = Color.blue;
        buty[5] = Color.pink;
        buty[6] = Color.cyan;
        return buty;
    }
}
