//208134288 Yishai Nissim
package gameArk.geomtry;
/**
 * A Hit Listener Interface.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06 -15
 */
public interface HitListener {
    /**
     * What happend in the event.
     * @param beingHit block.
     * @param hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
