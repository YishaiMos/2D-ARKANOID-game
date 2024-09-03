//208134288 Yishai Nissim
package gameArk.screen;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * The Animation runner.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param gui to use.
     * @param framesPerSecond to draw.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        if (gui == null) {
            gui = new GUI("Null gui", 800, 600);
        }
        if (framesPerSecond <= 0) {
            framesPerSecond = 60;
        }
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = new Sleeper();
    }

    /**
     * Run the animation.
     * @param animation to draw.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
