//208134288 Yishai Nissim
package gameArk.screen;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
/**
 * A Geomtry.Shapes.basic.GameArk.Velocity represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Animation decortor;

    /**
     * Constoctor.
     * @param k to press.
     */
    public PauseScreen(KeyboardSensor k) {
        decortor = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY,
                 this);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(170, d.getHeight() / 2,
                "paused -- press space to continue", 32);
        decortor.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        return decortor.shouldStop();
    }
}
