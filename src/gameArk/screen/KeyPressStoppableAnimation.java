//208134288 Yishai Nissim
package gameArk.screen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Key Press stop able animation.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor to use.
     * @param key to wait for.
     * @param animation to drew.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        if (sensor == null) {
            GUI gui = new GUI("Problem", 600, 800);
            sensor = gui.getKeyboardSensor();
        }
        if (key == null) {
            key = KeyboardSensor.SPACE_KEY;
        }
        if (animation == null) {
            animation = new PauseScreen(sensor);
        }
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}