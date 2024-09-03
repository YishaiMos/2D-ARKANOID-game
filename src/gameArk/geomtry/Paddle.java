//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameArk.screen.GameLevel;

import java.awt.Color;

/**
 * A paddle represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH = 800, HEIGHT = 600, MOVEMENT = 10,
            LENGTH_OF_X = 100, LENGTH_OF_Y = 15, LENGTH = LENGTH_OF_X / 5,
            BORDER_SIDES = 20, BORDER_DOWN = 21, GOOD_START =
            (WIDTH - LENGTH_OF_X) / 2;
    private static final int SEG1 = 0, SEG2 = 1, SEG3 = 2, SEG4 = 3, SEG5 = 4,
            SPEED = 10, CORNER = 6;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private java.awt.Color color = Color.orange;
    private int movement;
    private int widthOfFunPaddle = LENGTH;
    private int lengthOfX = LENGTH_OF_X;
    private int goodStart;

    /**
     * Constructor for the paddle.
     * @param ks to use to move.
     * @param lengthOfX of the paddle.
     * @param movement of the peddle in one move.
     */
    public Paddle(KeyboardSensor ks, int lengthOfX, int movement) {
        if (ks == null) {
            ks = new GUI("problem", 5, 5).getKeyboardSensor();
        }
        this.keyboard = ks;
        if (lengthOfX > 0) {
            this.lengthOfX = lengthOfX;
        }
        if (movement >= 0) {
            this.movement = movement;
        } else {
            this.movement = movement * (-1);
        }
        this.widthOfFunPaddle = lengthOfX / 5;
        this.goodStart = (WIDTH - lengthOfX) / 2;
        this.paddle =
                new Rectangle(new Point(goodStart,
                        HEIGHT - (LENGTH_OF_Y + BORDER_DOWN)),
                        lengthOfX, LENGTH_OF_Y);
    }
    /*
     * Move the paddle to left.
     */
    private void moveLeft() {
        if (this.paddle.getUpperLeft().getX() - movement < BORDER_SIDES) {
            this.paddle = new Rectangle(new Point(BORDER_SIDES,
                    this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                    this.paddle.getHeight());
            return;
        }
        this.paddle =
                new Rectangle(new Point(this.paddle.getUpperLeft().getX() - movement,
                this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                this.paddle.getHeight());
    }

    /*
     * Move the paddle to right.
     */
    private void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() + movement
                > WIDTH - BORDER_SIDES) {
            this.paddle =
                    new Rectangle(new Point(WIDTH - (this.paddle.getWidth() + BORDER_SIDES),
                    this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                    this.paddle.getHeight());
            return;
        }
        this.paddle =
                new Rectangle(new Point(this.paddle.getUpperLeft().getX() + movement,
                        this.paddle.getUpperLeft().getY()), this.paddle.getWidth(),
                        this.paddle.getHeight());
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(),
                (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(),
                (int) this.paddle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddle.getUpperLeft(),
                this.paddle.getWidth(), this.paddle.getHeight());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        int seg = findSegment(collisionPoint);
        switch (seg) {
            case SEG1:
                return Velocity.fromAngleAndSpeed(300, SPEED);
            case SEG2:
                return Velocity.fromAngleAndSpeed(330, SPEED);
            case SEG3:
                return new Velocity(currentVelocity.getDx(),
                        -currentVelocity.getDy());
            case SEG4:
                return Velocity.fromAngleAndSpeed(30, SPEED);
            case SEG5:
                return Velocity.fromAngleAndSpeed(60, SPEED);
            default:
                return new Velocity(-currentVelocity.getDx(),
                        currentVelocity.getDy());
        }
    }

    /*
     * Find the place that the ball will hit
     */
    private int findSegment(Point collisionPoint) {
        Line[] p = pration();
        if (p[SEG1].isBetweenMe(collisionPoint)) {
            return SEG1;
        }
        if (p[SEG2].isBetweenMe(collisionPoint)) {
            return SEG2;
        }
        if (p[SEG3].isBetweenMe(collisionPoint)) {
            return SEG3;
        }
        if (p[SEG4].isBetweenMe(collisionPoint)) {
            return SEG4;
        }
        if (p[SEG5].isBetweenMe(collisionPoint)) {
            return SEG5;
        }
        return CORNER;
    }

    /**
     * Add the paddle to the game.
     * @param g game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /*
     * Make pration of the paddle to 5 lines.
     */
    private Line[] pration() {
        Line[] lines = new Line[5];
        Line face = new Line(this.paddle.getUpperLeft(),
                new Point(this.paddle.getUpperLeft().getX()
                        + this.paddle.getWidth(),
                        this.paddle.getUpperLeft().getY()));
        lines[0] = new Line(face.start(), nextPoint(face.start()));
        lines[1] = new Line(lines[0].end(), nextPoint(lines[0].end()));
        lines[2] = new Line(lines[1].end(), nextPoint(lines[1].end()));
        lines[3] = new Line(lines[2].end(), nextPoint(lines[2].end()));
        lines[4] = new Line(lines[3].end(), nextPoint(lines[3].end()));
        return lines;
    }

    /*
     * Find the next point with the length that chosen
     */
    private Point nextPoint(Point p) {
        return new Point(p.getX() + widthOfFunPaddle, p.getY());
    }
}
