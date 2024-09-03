//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.DrawSurface;
import gameArk.geomtry.Block;
import gameArk.geomtry.Sprite;

import java.awt.Color;
/**
 * The Spider background.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Spider implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block face = new Block(0, 0, 800, 600);
        face.setColor(Color.black);
        face.drawOn(d);
        int radius = 45;
        d.setColor(Color.blue);
        for (int i = 0; i < 3; i++) {
            d.drawCircle(405, 165, radius);
            radius += 30;
        }
        d.drawLine(405, 190, 405, 300);
        d.drawLine(405, 0, 405, 140);
        d.drawLine(250, 165, 380, 165);
        d.drawLine(435, 165, 550, 165);
    }

    @Override
    public void timePassed() {

    }
}
