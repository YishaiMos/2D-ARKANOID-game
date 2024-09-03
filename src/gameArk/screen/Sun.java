//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.DrawSurface;
import gameArk.geomtry.Block;
import gameArk.geomtry.Sprite;

import java.awt.Color;

/**
 * The Sun background.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Sun implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Block face = new Block(0, 0, 800, 600);
        face.setColor(Color.white);
        face.drawOn(d);
        d.setColor(Color.getHSBColor(50, 50, 40));
        d.fillCircle(140, 120, 55);
        for (int i = 0; i < 650; i += 10) {
            d.drawLine(140, 120, i, 210);
        }
        d.setColor(Color.getHSBColor(4, 40, 7));
        d.fillCircle(140, 120, 45);
        d.setColor(Color.getHSBColor(4, 4, 4));
        d.fillCircle(140, 120, 35);
        d.setColor(Color.getHSBColor(50, 50, 40));
    }

    @Override
    public void timePassed() {
    }
}
