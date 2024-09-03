//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Score Indicator represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class ScoreIndicator implements Sprite {
    private static final int WIDTH = 800, HEIGHT = 20, SCORE_PLACE = 375,
            TEXT_HEIGHT = 15, TEXT_SIZE = 18, TITLE_PLACE = 550;;
    private Counter score;
    private Block b;
    private String name;

    /**
     * Constructor.
     * @param score to put on screen.
     * @param b the place to.
     * @param name of the level
     */
    public ScoreIndicator(Counter score, String name, Block b) {
        if (score == null) {
            score = new Counter();
        }
        if (b == null) {
            b = new Block(0, 0, WIDTH, HEIGHT);
        }
        this.b = b;
        b.setColor(Color.cyan, Color.black);
        this.score = score;
        if (name == null) {
            name = "Problem";
        }
        this.name = name;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.b.drawOn(d);
        d.drawText(SCORE_PLACE, TEXT_HEIGHT, "Score: " + score.getValue(),
                TEXT_SIZE);
        d.drawText(TITLE_PLACE, TEXT_HEIGHT, "Level Name: " + this.name,
            TEXT_SIZE);
    }

    @Override
    public void timePassed() {
        return;
    }
}
