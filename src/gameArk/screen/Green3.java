//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Block;
import gameArk.geomtry.Sprite;
import gameArk.geomtry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green 3 level.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */

public class Green3 implements LevelInformation {
    private static final int WIDTH = 800, HEIGHT = 600, BORDER = 17,
            BLOCK_WIDTH = 50, BLOCK_HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> vel = new ArrayList<>();
        vel.add(Velocity.fromAngleAndSpeed(30, 8));
        vel.add(Velocity.fromAngleAndSpeed(-30, 8));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Block face = new Block(0, 0, WIDTH, HEIGHT);
        face.setColor(Color.green);
        return new Building();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        setRows(blocks);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
    private void setRows(ArrayList<Block> blocks) {
        int j = 150, color = 0;
        java.awt.Color[] buty = makeColors();
        for (int i = WIDTH - (BORDER + 10 * BLOCK_WIDTH);
             i < WIDTH - (BORDER + 5 * BLOCK_WIDTH);
             j += BLOCK_HEIGHT, i += BLOCK_WIDTH, color++) {
            setOneRow(i, j, buty[color], blocks);
        }
    }
    private void setOneRow(int width, int height, java.awt.Color color,
                           ArrayList<Block> blocks) {
        while (width <= WIDTH - (BORDER + BLOCK_WIDTH)) {
            Block block = new Block(width, height, BLOCK_WIDTH, BLOCK_HEIGHT);
            block.setColor(color, Color.black);
            width += BLOCK_WIDTH;
            blocks.add(block);
        }
    }
    private java.awt.Color[] makeColors() {
        java.awt.Color[] buty = new Color[5];
        buty[0] = Color.gray;
        buty[1] = Color.red;
        buty[2] = Color.yellow;
        buty[3] = Color.blue;
        buty[4] = Color.white;
        return buty;
    }
}
