//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Collidable;
import gameArk.geomtry.Point;
import gameArk.geomtry.Rectangle;
import gameArk.geomtry.Block;

import java.awt.Color;

/**
 * Details of collision.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param collisionPoint of this collision.
     * @param collisionObject in this collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        if (collisionPoint == null || collisionObject == null) {
            this.collisionPoint = new Point(1, 1);
            this.collisionObject = new Block(new Rectangle(new Point(1, 1),
                    1, 1), Color.cyan);
        }
        this.collisionPoint = new Point(collisionPoint.getX(),
                collisionPoint.getY());
        this.collisionObject = collisionObject;
    }

    /**
     * Getter.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(),
                this.collisionPoint.getY());
    }

    /**
     * Getter.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
