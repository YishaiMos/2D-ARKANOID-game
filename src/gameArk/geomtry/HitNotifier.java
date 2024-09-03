//208134288 Yishai Nissim
package gameArk.geomtry;
/**
 * A Hit Notifier Interface.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl to remove.
     */
    void removeHitListener(HitListener hl);
}
