//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameArk.geomtry.Counter;
import gameArk.geomtry.Collidable;
import gameArk.geomtry.ScreenInitialize;
import gameArk.geomtry.Sprite;


/**
 * A GameArk.Game zone.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Counter remainingBlocks = new Counter();
    private Counter remainBalls = new Counter();
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * Construtor for game level.
     * @param level to build
     * @param keyboard to use
     * @param runner to run with
     * @param gui to show on
     * @param score to update
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard,
                     AnimationRunner runner, GUI gui, Counter score) {
        if (level == null) {
           level = new DirectHit();
        }
        this.level = level;
        if (gui == null) {
            gui = new GUI("Default", 200, 200);
        }
        this.gui = gui;
        if (keyboard == null) {
            keyboard = this.gui.getKeyboardSensor();
        }
        this.keyboard = keyboard;
        if (runner == null) {
            runner = new AnimationRunner(this.gui, 60);
        }
        this.runner = runner;
        if (score == null) {
            score = new Counter();
        }
        this.score = score;
    }

    /**
     * Constructor for problems.
     * @param g to use.
     */
    public GameLevel(GUI g) {
        this(new DirectHit(), g.getKeyboardSensor(),
                new AnimationRunner(g, 60), g, new Counter());
    }

    /**
     * Add collidable to the game.
     * @param c collidable to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the game.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Remove sprite from the game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Remove collidable from the game.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Getter for game environment.
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Initialize a new game: create the Blocks and GameArk.Ball (and GameArk.Paddle) and add
     * them to the game.
     */
    public void initialize() {
        ScreenInitialize screen = new ScreenInitialize(this,
                remainingBlocks, remainBalls, score, level, gui, this.keyboard);
        screen.initialize();
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Count on screen from num.
     * @param numOfSeconds to count
     * @param countFrom number to start on.
     */
    public void countDown(double numOfSeconds, int countFrom) {
        this.runner.run(new CountdownAnimation(numOfSeconds, countFrom, this.sprites));
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBlocks.getValue() <= 0) {
            score.increase(100);
            this.running = false;
        }
        if (remainBalls.getValue() <= 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Getter for num of balls in this game.
     * @return the num.
     */
    public int getNumBall() {
        return remainBalls.getValue();
    }
    /**
     * Getter for num of blockes in this game.
     * @return the num.
     */
    public int getNumBlock() {
        return remainingBlocks.getValue();
    }
}
