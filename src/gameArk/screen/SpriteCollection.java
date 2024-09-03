//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.DrawSurface;
import gameArk.geomtry.Sprite;

import java.util.ArrayList;

/**
 * A GameArk.Sprite collection fot the game time.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites =
            new ArrayList<>();

    /**
     * Adding spite.
     * @param s spite to add.
     */
    public void addSprite(Sprite s) {
        if (s == null) {
            return;
        }
        sprites.add(s);
    }
    /**
     * Remove sprite from the game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * On all the time passed functions in object.
     */
    public void notifyAllTimePassed() {
        java.util.List<Sprite> sprites =
                new ArrayList<>(this.sprites);
        for (Sprite sprite: sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draw on the spites.
     * @param d surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        java.util.List<Sprite> sprites =
                new ArrayList<>(this.sprites);
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }
}
