//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameArk.screen.DirectHit;
import gameArk.screen.GameLevel;
import gameArk.screen.LevelInformation;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for screen initialize.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-01
 */
public class ScreenInitialize {
    private static final int BORDER = 17, CENTER_X = 400, CENTER_Y = 550,
            DOWN = 1, UP = 0, LEFT = 3, RIGHT = 2, RADIUS = 4;
    private GUI gui;
    private int width = 800;
    private int height = 600;
    private GameLevel game;
    private HitListener hl;
    private Counter numberOfBlockes;
    private Counter ballNumber;
    private Counter score;
    private HitListener scLis;
    private LevelInformation level;
    private KeyboardSensor ks;
    /**
     * Constructor for screen.
     *
     * @param game          to be on
     * @param numberOfBlocs counter
     * @param ballNumber    counter
     * @param score         counter
     * @param gui The gui
     * @param level of this game
     * @param ks to use
     */
    public ScreenInitialize(GameLevel game,
                            Counter numberOfBlocs, Counter ballNumber,
                            Counter score, LevelInformation level, GUI gui,
                            KeyboardSensor ks) {
        if (numberOfBlocs == null) {
            numberOfBlocs = new Counter();
        }
        this.numberOfBlockes = numberOfBlocs;
        if (ballNumber == null) {
            ballNumber = new Counter();
        }
        this.ballNumber = ballNumber;
        if (score == null) {
            score = new Counter();
        }
        this.score = score;
        scLis = new ScoreTrackingListener(score);
        if (level == null) {
            level = new DirectHit();
        }
        this.level = level;
       // if (gui == null) {
         //   gui = new GUI("Defult", 800, 600);
       // }
        this.gui = gui;
        //if (game == null) {
           // game = new GameLevel(new GUI("problam", 5, 5));
       // }
        this.game = game;
        if (ks == null) {
            ks = gui.getKeyboardSensor();
        }
        this.ks = ks;
        this.hl = new BlockRemover(this.game, this.numberOfBlockes);
    }

    /**
     * Initialize the screen.
     */
    public void initialize() {
        game.addSprite(this.level.getBackground());
        setBorders();
        setBlockes();
        setBalls();
        initializePaddle();
    }
    private void initializePaddle() {
        Paddle p = new Paddle(ks, this.level.paddleWidth(),
                this.level.paddleSpeed());
        p.addToGame(this.game);
    }
    private void setBlockes() {
        List<Block> blocks = this.level.blocks();
        for (Block b: blocks) {
            b.addToGame(this.game);
            b.addHitListener(this.hl);
            b.addHitListener(this.scLis);
        }
        numberOfBlockes.increase(this.level.numberOfBlocksToRemove());
    }
    private void setBalls() {
        int ballNum = this.level.numberOfBalls();
        Ball[] balls = new Ball[ballNum];
        ArrayList<Velocity> vel =
                new ArrayList<>(this.level.initialBallVelocities());
        for (int i = 0; i < ballNum; i++) {
            balls[i] = new Ball(CENTER_X, CENTER_Y, RADIUS, Color.white);
            balls[i].addToGame(this.game);
            balls[i].setVelocity(vel.get(i));
        }
        ballNumber.increase(ballNum);
    }
    private void setBorders() {
        Block[] borders = new Block[4];
        borders[UP] = new Block(BORDER, BORDER, this.width - 2 * BORDER,
                BORDER);
        borders[DOWN] = new Block(BORDER, this.height - BORDER,
                this.width - 2 * BORDER, BORDER);
        borders[DOWN].addHitListener(new BallRemover(this.game,
                this.ballNumber));
        borders[RIGHT] = new Block(this.width - BORDER, BORDER, BORDER,
                this.height);
        borders[LEFT] = new Block(0, BORDER, BORDER, this.height - BORDER);
        for (Block b: borders) {
            b.addToGame(this.game);
            b.setColor(Color.gray, Color.black);
        }
        //for dont show this block
        game.removeSprite(borders[DOWN]);
        this.game.addSprite(new ScoreIndicator(score, this.level.levelName(),
                new Block(0, 0, this.width, BORDER)));
    }
}
