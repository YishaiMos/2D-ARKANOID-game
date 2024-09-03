//208134288 Yishai Nissim
package gameArk.screen;

import biuoop.DrawSurface;
import gameArk.geomtry.Sprite;

import java.awt.Color;
/**
 * The Building background.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Building implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(20, 150, 20));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.fillRectangle(50, 450, 100, 200);
        d.setColor(new Color(80, 80, 80));
        d.fillRectangle(80, 400, 40, 50);
        d.setColor(new Color(100, 100, 100));
        d.fillRectangle(95, 170, 10, 230);
        d.setColor(new Color(200, 150, 0));
        d.fillCircle(100, 170, 10);
        d.setColor(new Color(240, 100, 0));
        d.fillCircle(100, 170, 7);
        d.setColor(Color.white);
        d.fillCircle(100, 170, 3);
        d.setColor(Color.white);
        for (int i = 460; i < 600; i += 30) {
            for (int j = 62; j < 142; j += 17) {
                d.fillRectangle(j, i, 10, 25);
            }
        }
    }

    @Override
    public void timePassed() {

    }
}
