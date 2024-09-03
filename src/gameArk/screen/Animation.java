//208134288 Yishai Nissim
package gameArk.screen;
import biuoop.DrawSurface;
/**
 * Interface for animation.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public interface Animation {
    /**
     * Drew one frame.
     * @param d to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * If should stop.
     * @return the answer.
     */
    boolean shouldStop();
}
