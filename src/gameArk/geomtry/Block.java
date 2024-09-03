//208134288 Yishai Nissim
package gameArk.geomtry;


import biuoop.DrawSurface;
import gameArk.screen.GameLevel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Represent of blocks.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private java.awt.Color borderColor;
    private  List<HitListener> hitListeners;
    /**
     * Constructor for the block.
     * @param r the rectangle of the block.
     * @param color of the block.
     */
    public Block(Rectangle r, java.awt.Color color) {
        if (r == null) {
            r = new Rectangle(new Point(1, 1), 1, 1);
        }
        this.block = new Rectangle(r.getUpperLeft(), r.getWidth(),
                r.getHeight());
        this.color = color;
        this.borderColor = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor from points and width only.
     * @param x of upperLeft in the GameArk.Ball.Block
     * @param y of upperLeft in the GameArk.Ball.Block
     * @param width of the GameArk.Ball.Block
     * @param height of the GameArk.Ball.Block
     */
    public Block(double x, double y, double width, double height) {
        this (new Rectangle(new Point(x, y), width, height), Color.cyan);
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(block.getUpperLeft(), block.getWidth(),
                block.getHeight());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        this.notifyHit(hitter);
        return checkBorder(collisionPoint, currentVelocity);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        if (hl == null) {
            return;
        }
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * Remove the block from the game.
     * @param game to remove from.
     */
    public void removeFromGame(GameLevel game) {
        if (game == null) {
            return;
        }
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    private Velocity checkBorder(Point collisionPoint,
                                 Velocity currentVelocity) {
        Line[] borders = this.block.cornerLine();
        if (borders[Rectangle.LEFT].isBetweenMe(collisionPoint)
                || borders[Rectangle.RIGHT].isBetweenMe(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(),
                -currentVelocity.getDy());
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
        surface.setColor(this.borderColor);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
    }
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add GameArk.Ball.Block to the game.
     * @param game to add on.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Setter for color.
     * @param color to set
     */
    public void setColor(Color color) {
        this.color = color;
        this.borderColor = color;
    }

    /**
     * Setter for color and border color.
     * @param color to set
     * @param borderColor to set
     */
    public void setColor(Color color, Color borderColor) {
        this.color = color;
        this.borderColor = borderColor;
    }
}



