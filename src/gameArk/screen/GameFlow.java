//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameArk.geomtry.Counter;

import java.util.List;
/**
 * Game flow level.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */

public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score = new Counter();
    private AnimationRunner ar;

    /**
     * Constoctor.
     * @param gui to drew the game.
     * @param ks the keyboard sensor.
     * @param ar runner to use.
     */

    public GameFlow(GUI gui, KeyboardSensor ks, AnimationRunner ar) {
        if (gui == null) {
            gui = new GUI("Default", 200, 200);
        }
        this.gui = gui;
        if (ar == null) {
            ar = new AnimationRunner(this.gui, 60);
        }
        this.ar = ar;
        if (ks == null) {
            ks = gui.getKeyboardSensor();
        }
        this.keyboardSensor = ks;
    }

    /**
     * Run the levels that user sent.
     * @param levels to run,
     */

    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.ar, this.gui, this.score);
            level.initialize();
            level.countDown(2, 3);
            level.run();
            if (level.getNumBall() <= 0) {
                win = false;
                break;
            }
        }
        ar.run(new EndScreen(keyboardSensor, win, this.score.getValue()));
        gui.close();
    }
}
