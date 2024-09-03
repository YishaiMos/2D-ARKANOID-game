//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.DrawSurface;
import gameArk.screen.CollisionInfo;
import gameArk.screen.GameLevel;
import gameArk.screen.GameEnvironment;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A GameArk.Ball represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-14
 */
public class Ball implements Sprite {
    private static final int ZERO = 0, MIN = 1, BEFORE = 4;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v = new Velocity(ZERO, ZERO);
    private GameEnvironment game = new GameEnvironment();
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new GameArk.Ball from a center point.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.color = color;
        //if there is no center or there is illegal radius put default
        if (center == null || r <= ZERO) {
            this.center = new Point(MIN, MIN);
            this.r = MIN;
        } else {
            this.center = new Point(center.getX(), center.getY());
            this.r = r;
        }
    }

    /**
     * Instantiates a new GameArk.Ball from center coordinates.
     *
     * @param x     the x of center
     * @param y     the y of center
     * @param r     the radius of center
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }
    /**
     * Instantiates a new GameArk.Ball from center coordinates.
     *
     * @param x
     * @param y
     * @param r
     * @param color the color
     */
    /**
     * Instantiates a new GameArk.Ball from center coordinates and velocity.
     *
     * @param x     the x of center
     * @param y     the y of center
     * @param r     the radius of center
     * @param color the color
     * @param v     the velocity
     */
    public Ball(double x, double y, int r, java.awt.Color color, Velocity v) {
        this(x, y, r, color);
        if (v != null) {
            this.v = v;
        }
    }

    /**
     * Gets x of the center.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y of the center.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size (radius).
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                this.r);
    }

    /**
     * Set a velocity to the ball.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        if (v == null) {
            return;
        }
        setVelocity(v.getDx(), v.getDy());
    }

    /**
     * Set velocity by dx and dy.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Get the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Move the ball according to his velocity and borders.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center,
                this.getVelocity().applyToPoint(this.center));
        CollisionInfo col = this.game.getClosestCollision(trajectory);
        if (col == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            this.center = setAfterCollision(col);
            this.setVelocity(col.collisionObject().hit(this,
                    col.collisionPoint(),
                    this.getVelocity()));
        }
    }

    private Point setAfterCollision(CollisionInfo col) {
        double x = col.collisionPoint().getX();
        double y = col.collisionPoint().getY();
        if (col.collisionPoint().getX() > this.center.getX()) {
            x -= BEFORE;
        } else {
            x += BEFORE;
        }
        if (col.collisionPoint().getY() > this.center.getY()) {
            y -= BEFORE;
        } else {
            y += BEFORE;
        }
        return new Point(x, y);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add GameArk.Ball to the game.
     *
     * @param game to add on.
     */
    public void addToGame(GameLevel game) {
        if (game == null) {
            return;
        }
        game.addSprite(this);
        this.game = game.getGameEnvironment();
    }

    /**
     * Setter for game environment.
     *
     * @param gameEnvironment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        if (gameEnvironment == null) {
            return;
        }
        this.game = gameEnvironment;
    }

    /**
     * Remove the ball from game.
     * @param game to remove from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}