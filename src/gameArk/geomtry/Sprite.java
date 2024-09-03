//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.DrawSurface;

/**
 * GameArk.Sprite for the game time that draw and handle the objects.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public interface Sprite {
    /**
     * Draw the object on the surface.
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * What to do in a new turn.
     */
    void timePassed();
}
