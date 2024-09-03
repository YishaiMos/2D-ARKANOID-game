//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * An ENd screen.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class EndScreen implements Animation {
    private Animation decotor;
    private String loseWin;
    private int score;

    /**
     * Constructor.
     * @param k sensor to use.
     * @param win if the user win.
     * @param score of the user.
     */
    public EndScreen(KeyboardSensor k, Boolean win, int score) {
        this.decotor = new KeyPressStoppableAnimation(k,
                KeyboardSensor.SPACE_KEY, this);
        if (win) {
            loseWin = "You Win!";
        } else {
            loseWin = "Game Over.";
        }
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(180, d.getHeight() / 2,
                loseWin + " Your score is " + score, 32);
        decotor.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        return decotor.shouldStop();
    }
}
