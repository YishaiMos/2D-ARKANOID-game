//208134288 Yishai Nissim
package gameArk.geomtry;

/**
 * Interface to a collidable.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public interface Collidable {
    /**
     * Check the place of collidable.
     * @return the loction of this object
     */
    Rectangle getCollisionRectangle();

    /**
     * Check what happened in collision.
     * @param collisionPoint the point to check
     * @param currentVelocity of the ball
     * @param hitter the ball that hit
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
