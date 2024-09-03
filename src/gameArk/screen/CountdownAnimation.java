//208134288 Yishai Nissim
package gameArk.screen;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
/**
 * A CountdownAnimation.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class CountdownAnimation implements Animation {
    private static final int TEXT_WIDTH = 350,
            TEXT_HEIGHT = 320, TEXT_SIZE = 200;;
    private double numOfSeconds;
    private int countFrom;
    private Long sleepTime;
    private int courntCount;
    private SpriteCollection sprites = new SpriteCollection();

    /**
     * Constroctor for count down.
     * @param numOfSeconds to stop.
     * @param countFrom to count.
     * @param gameScreen to drew on.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        if (gameScreen != null) {
            this.sprites = gameScreen;
        }
        if (numOfSeconds <= 0) {
            numOfSeconds = 3000;
        }
        this.numOfSeconds = numOfSeconds;
        if (countFrom <= 0) {
            countFrom = 3;
        }
        this.countFrom = countFrom;
        this.sleepTime = (long) (this.numOfSeconds * 1000) / this.countFrom;
        this.courntCount = this.countFrom;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.orange);
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, String.valueOf(this.courntCount),
                TEXT_SIZE);
        this.courntCount--;
    }
    @Override
    public boolean shouldStop() {
        if (courntCount != countFrom) {
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(this.sleepTime);
        }
        return courntCount <= 0;
    }
}
