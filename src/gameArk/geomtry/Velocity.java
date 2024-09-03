//208134288 Yishai Nissim
package gameArk.geomtry;

/**
 * A Geomtry.Shapes.basic.GameArk.Velocity represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Velocity {
    private static final int CRICLE = 360, ZERO = 0;
    private double dx;
    private double dy;

    /**
     * Build a new Geomtry.Shapes.basic.GameArk.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply point by the velocity.
     *
     * @param p the point before
     * @return the point after
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Getter for dx.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Getter for dy.
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * From angle and speed to regular velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return a velocity from the speed and the angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}
